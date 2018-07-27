package top.zywork.controller;

import org.dozer.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import top.zywork.common.*;
import top.zywork.dto.PagerDTO;
import top.zywork.dto.TicketOrderDTO;
import top.zywork.dto.TicketOrderDetailDTO;
import top.zywork.exception.ServiceException;
import top.zywork.query.PageQuery;
import top.zywork.query.StatusQueries;
import top.zywork.query.StatusQuery;
import top.zywork.query.TicketOrderQuery;
import top.zywork.service.TicketOrderDetailService;
import top.zywork.service.TicketOrderService;
import top.zywork.vo.ControllerStatusVO;
import top.zywork.vo.PagerVO;
import top.zywork.vo.TicketOrderVO;
import top.zywork.wechat.PayData;
import top.zywork.wechat.WechatAPI;
import top.zywork.wechat.WechatUtil;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * TicketOrderController控制器类<br/>
 *
 * 创建于2018-07-24<br/>
 *
 * @author http://zywork.top 王振宇
 * @version 1.0
 */
@Controller
@RequestMapping("/tickeorder")
public class TicketOrderController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(TicketOrderController.class);

    private TicketOrderService ticketOrderService;

    private TicketOrderDetailService ticketOrderDetailService;

    @GetMapping("page")
    public String page() {
        return "TicketOrder/TicketOrder";
    }

    @GetMapping("add-modal")
    public String addModal() {
        return "TicketOrder/TicketOrderAddModal";
    }

    @GetMapping("edit-modal")
    public String editModal() {
        return "TicketOrder/TicketOrderEditModal";
    }

    @GetMapping("detail-modal")
    public String detailModal() {
        return "TicketOrder/TicketOrderDetailModal";
    }

    @GetMapping("search-modal")
    public String searchModal() {
        return "TicketOrder/TicketOrderSearchModal";
    }

    @PostMapping("save")
    @ResponseBody
    public PayData save(@Validated TicketOrderVO ticketOrderVO, BindingResult bindingResult, String allSeatsString, HttpServletRequest request) {
        PayData returnPayData = new PayData();
        if (bindingResult.hasErrors()) {
            return null;
        } else {
            try {
                List<Object> ticketOrderDetailDTOList = ticketOrderDetailService.listSelectedSeats(ticketOrderVO.getTicketItemId() + "", ticketOrderVO.getPlayTimeStr());
                String[] strArray = ticketOrderVO.getSelectedSeats().split(";");
                for (String seat : strArray) {
                    for (Object obj : ticketOrderDetailDTOList) {
                        TicketOrderDetailDTO ticketOrderDetailDTO = (TicketOrderDetailDTO) obj;
                        if (ticketOrderDetailDTO.getSeat().equals(seat)) {
                            returnPayData.setAppId("none");
                            return returnPayData;
                        }
                    }
                }
                ticketOrderVO.setOrderNo(System.currentTimeMillis() + "" + RandomUtils.randomNum(100000, 999999));
                ticketOrderService.save(getBeanMapper().map(ticketOrderVO, TicketOrderDTO.class));
                WechatUtil wechatUtil = new WechatUtil();
                Map<String, String> prepayResult = wechatUtil.prepayResult(ticketOrderVO.getOpenid(),
                        ticketOrderVO.getOrderNo(),
                        IPUtils.getIP(request), "北艺赣州剧场选座付款", allSeatsString,
                        (int) (ticketOrderVO.getTotalPrice() * 100));
                Map<String, String> payData = wechatUtil.payData(prepayResult);
                returnPayData.setAppId(WechatAPI.APP_ID);
                returnPayData.setTimeStamp(payData.get("timeStamp"));
                returnPayData.setNonceStr(payData.get("nonceStr"));
                returnPayData.setPackages(payData.get("package"));
                returnPayData.setPaySign(payData.get("paySign"));
                return returnPayData;
            } catch (ServiceException e) {
                logger.error("添加失败：{}", e.getMessage());
            }
        }
        return null;
    }

    @RequestMapping("result")
    public void payResult(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("***********************notify_url*************************");
        WechatUtil wechatUtil = new WechatUtil();
        Map<String, String> resultMap = wechatUtil.payResult(request);
        String resultCode = resultMap.get("result_code");
        if (resultCode != null && resultCode.equals("SUCCESS")) {
            String totalFee = resultMap.get("total_fee");
            String openId = resultMap.get("openid");
            String tranId = resultMap.get("transaction_id");
            String outTradeNo = resultMap.get("out_trade_no");
            ticketOrderService.updateOrderTimeByOrderNo(outTradeNo);
            wechatUtil.responsePayNotify(response);
        }
    }

    @PostMapping("remove")
    @ResponseBody
    public ControllerStatusVO remove(TicketOrderVO ticketOrderVO) {
        ControllerStatusVO statusVO = new ControllerStatusVO();
        try {
            ticketOrderService.remove(getBeanMapper().map(ticketOrderVO, TicketOrderDTO.class));
            statusVO.okStatus(200, "删除成功");
        } catch (ServiceException e) {
            logger.error("删除失败：{}", e.getMessage());
            statusVO.errorStatus(500, "删除失败");
        }
        return statusVO;
    }

    @GetMapping("remove/{id}")
    @ResponseBody
    public ControllerStatusVO removeById(@PathVariable("id") Long id) {
        ControllerStatusVO statusVO = new ControllerStatusVO();
        try {
            ticketOrderService.removeById(id);
            statusVO.okStatus(200, "删除成功");
        } catch (ServiceException e) {
            logger.error("删除失败：{}", e.getMessage());
            statusVO.errorStatus(500, "删除失败");
        }
        return statusVO;
    }

    @PostMapping("batch-remove")
    @ResponseBody
    public ControllerStatusVO removeByIds(String ids) {
        ControllerStatusVO statusVO = new ControllerStatusVO();
        try {
            ticketOrderService.removeByIds(StringUtils.strToLongArray(ids, ","));
            statusVO.okStatus(200, "批量删除成功");
        } catch (ServiceException e) {
            logger.error("批量删除失败：{}", e.getMessage());
            statusVO.errorStatus(500, "批量删除失败");
        }
        return statusVO;
    }

    @PostMapping("update")
    @ResponseBody
    public ControllerStatusVO update(@Validated TicketOrderVO ticketOrderVO, BindingResult bindingResult) {
        ControllerStatusVO statusVO = new ControllerStatusVO();
        if (bindingResult.hasErrors()) {
            statusVO.dataErrorStatus(500, BindingResultUtils.errorString(bindingResult));
        } else {
            try {
                ticketOrderService.update(getBeanMapper().map(ticketOrderVO, TicketOrderDTO.class));
                statusVO.okStatus(200, "更新成功");
            } catch (ServiceException e) {
                logger.error("更新失败：{}", e.getMessage());
                statusVO.errorStatus(500, "更新失败");
            }
        }
        return statusVO;
    }

    @PostMapping("active")
    @ResponseBody
    public ControllerStatusVO updateActiveStatus(StatusQuery statusQuery) {
        ControllerStatusVO statusVO = new ControllerStatusVO();
        try {
            ticketOrderService.updateActiveStatus(statusQuery);
            statusVO.okStatus(200, statusQuery.getStatus() == 0 ? "激活成功" : "冻结成功");
        } catch (ServiceException e) {
            logger.error("激活或冻结失败：{}", e.getMessage());
            statusVO.errorStatus(500, statusQuery.getStatus() == 0 ? "激活失败" : "冻结失败");
        }
        return statusVO;
    }

    @PostMapping("batch-active")
    @ResponseBody
    public ControllerStatusVO updateActiveStatuses(String ids, StatusQueries statusQueries) {
        ControllerStatusVO statusVO = new ControllerStatusVO();
        statusQueries.setIds(StringUtils.strToLongArray(ids, ","));
        try {
            ticketOrderService.updateActiveStatuses(statusQueries);
            statusVO.okStatus(200, statusQueries.getStatus() == 0 ? "批量激活成功" : "批量冻结成功");
        } catch (ServiceException e) {
            logger.error("激活或冻结失败：{}", e.getMessage());
            statusVO.errorStatus(500, statusQueries.getStatus() == 0 ? "批量激活失败" : "批量冻结失败");
        }
        return statusVO;
    }

    @RequestMapping("one/{id}")
    @ResponseBody
    public TicketOrderVO getById(@PathVariable("id") Long id) {
        TicketOrderVO ticketOrderVO = new TicketOrderVO();
        try {
            Object obj = ticketOrderService.getById(id);
            if (obj != null) {
                ticketOrderVO = getBeanMapper().map(obj, TicketOrderVO.class);
            }
        } catch (ServiceException e) {
            logger.error("返回单个对象JSON数据失败：{}", e.getMessage());
        }
        return ticketOrderVO;
    }

    @GetMapping("all")
    @ResponseBody
    public List<TicketOrderVO> listAll() {
        List<TicketOrderVO> ticketOrderVOList = new ArrayList<>();
        try {
            List<Object> objectList = ticketOrderService.listAll();
            ticketOrderVOList =  DozerMapperUtils.map(getBeanMapper(), objectList, TicketOrderVO.class);
        } catch (ServiceException e) {
            logger.error("返回所有对象JSON数据失败：{}", e.getMessage());
        }
        return ticketOrderVOList;
    }

    @RequestMapping("pager")
    @ResponseBody
    public PagerVO listPage(int offset, int limit, String sort, String order) {
        PagerVO pagerVO = new PagerVO();
        PageQuery pageQuery = new PageQuery(offset / limit + 1, limit, sort, order);
        try {
            PagerDTO pagerDTO = ticketOrderService.listPage(pageQuery);
            Mapper mapper = getBeanMapper();
            pagerVO = mapper.map(pagerDTO, PagerVO.class);
            pagerVO.setRows(DozerMapperUtils.mapList(mapper, pagerDTO.getRows(), TicketOrderVO.class));
        } catch (ServiceException e) {
            logger.error("返回分页对象JSON数据失败：{}", e.getMessage());
        }
        return pagerVO;
    }

    @RequestMapping("pager-cond")
    @ResponseBody
    public PagerVO listPageByCondition(int offset, int limit, String sort, String order, TicketOrderQuery ticketOrderQuery) {
        PagerVO pagerVO = new PagerVO();
        PageQuery pageQuery = new PageQuery(offset / limit + 1, limit, sort, order);
        try {
            PagerDTO pagerDTO = ticketOrderService.listPageByCondition(pageQuery, ticketOrderQuery);
            Mapper mapper = getBeanMapper();
            pagerVO = mapper.map(pagerDTO, PagerVO.class);
            pagerVO.setRows(DozerMapperUtils.mapList(mapper, pagerDTO.getRows(), TicketOrderVO.class));
        } catch (ServiceException e) {
            logger.error("返回指定条件的分页对象JSON数据失败：{}", e.getMessage());
        }
        return pagerVO;
    }

    @Resource
    public void setTicketOrderService(TicketOrderService ticketOrderService) {
        this.ticketOrderService = ticketOrderService;
    }

    @Autowired
    public void setTicketOrderDetailService(TicketOrderDetailService ticketOrderDetailService) {
        this.ticketOrderDetailService = ticketOrderDetailService;
    }
}
