package top.zywork.controller;

import org.dozer.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import top.zywork.common.BindingResultUtils;
import top.zywork.common.DozerMapperUtils;
import top.zywork.common.StringUtils;
import top.zywork.dao.TicketOrderDetailDAO;
import top.zywork.dto.PagerDTO;
import top.zywork.dto.TicketOrderDetailDTO;
import top.zywork.dto.UserTicketOrderDTO;
import top.zywork.exception.ServiceException;
import top.zywork.query.*;
import top.zywork.service.TicketOrderDetailService;
import top.zywork.service.UserTicketOrderService;
import top.zywork.vo.ControllerStatusVO;
import top.zywork.vo.PagerVO;
import top.zywork.vo.TicketOrderDetailVO;
import top.zywork.vo.UserTicketOrderVO;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * UserTicketOrderController控制器类<br/>
 *
 * 创建于2018-07-24<br/>
 *
 * @author http://zywork.top 王振宇
 * @version 1.0
 */
@Controller
@RequestMapping("/user-order")
public class UserTicketOrderController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(UserTicketOrderController.class);

    private UserTicketOrderService userTicketOrderService;

    private TicketOrderDetailService ticketOrderDetailService;

    @GetMapping("page")
    public String page() {
        return "UserTicketOrder/UserTicketOrder";
    }

    @GetMapping("add-modal")
    public String addModal() {
        return "UserTicketOrder/UserTicketOrderAddModal";
    }

    @GetMapping("edit-modal")
    public String editModal() {
        return "UserTicketOrder/UserTicketOrderEditModal";
    }

    @GetMapping("detail-modal")
    public String detailModal() {
        return "UserTicketOrder/UserTicketOrderDetailModal";
    }

    @GetMapping("search-modal")
    public String searchModal() {
        return "UserTicketOrder/UserTicketOrderSearchModal";
    }

    @PostMapping("save")
    @ResponseBody
    public ControllerStatusVO save(@Validated UserTicketOrderVO userTicketOrderVO, BindingResult bindingResult) {
        ControllerStatusVO statusVO = new ControllerStatusVO();
        if (bindingResult.hasErrors()) {
            statusVO.dataErrorStatus(500, BindingResultUtils.errorString(bindingResult));
        } else {
            try {
                userTicketOrderService.save(getBeanMapper().map(userTicketOrderVO, UserTicketOrderDTO.class));
                statusVO.okStatus(200, "添加成功");
            } catch (ServiceException e) {
                logger.error("添加失败：{}", e.getMessage());
                statusVO.errorStatus(500, "添加失败");
            }
        }
        return statusVO;
    }

    @PostMapping("remove")
    @ResponseBody
    public ControllerStatusVO remove(UserTicketOrderVO userTicketOrderVO) {
        ControllerStatusVO statusVO = new ControllerStatusVO();
        try {
            userTicketOrderService.remove(getBeanMapper().map(userTicketOrderVO, UserTicketOrderDTO.class));
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
            userTicketOrderService.removeById(id);
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
            userTicketOrderService.removeByIds(StringUtils.strToLongArray(ids, ","));
            statusVO.okStatus(200, "批量删除成功");
        } catch (ServiceException e) {
            logger.error("批量删除失败：{}", e.getMessage());
            statusVO.errorStatus(500, "批量删除失败");
        }
        return statusVO;
    }

    @PostMapping("update")
    @ResponseBody
    public ControllerStatusVO update(@Validated UserTicketOrderVO userTicketOrderVO, BindingResult bindingResult) {
        ControllerStatusVO statusVO = new ControllerStatusVO();
        if (bindingResult.hasErrors()) {
            statusVO.dataErrorStatus(500, BindingResultUtils.errorString(bindingResult));
        } else {
            try {
                userTicketOrderService.update(getBeanMapper().map(userTicketOrderVO, UserTicketOrderDTO.class));
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
            userTicketOrderService.updateActiveStatus(statusQuery);
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
            userTicketOrderService.updateActiveStatuses(statusQueries);
            statusVO.okStatus(200, statusQueries.getStatus() == 0 ? "批量激活成功" : "批量冻结成功");
        } catch (ServiceException e) {
            logger.error("激活或冻结失败：{}", e.getMessage());
            statusVO.errorStatus(500, statusQueries.getStatus() == 0 ? "批量激活失败" : "批量冻结失败");
        }
        return statusVO;
    }

    @RequestMapping("one/{id}")
    @ResponseBody
    public UserTicketOrderVO getById(@PathVariable("id") Long id) {
        UserTicketOrderVO userTicketOrderVO = new UserTicketOrderVO();
        try {
            Object obj = userTicketOrderService.getById(id);
            if (obj != null) {
                userTicketOrderVO = getBeanMapper().map(obj, UserTicketOrderVO.class);
            }
        } catch (ServiceException e) {
            logger.error("返回单个对象JSON数据失败：{}", e.getMessage());
        }
        return userTicketOrderVO;
    }

    @GetMapping("all")
    @ResponseBody
    public List<UserTicketOrderVO> listAll() {
        List<UserTicketOrderVO> userTicketOrderVOList = new ArrayList<>();
        try {
            List<Object> objectList = userTicketOrderService.listAll();
            userTicketOrderVOList =  DozerMapperUtils.map(getBeanMapper(), objectList, UserTicketOrderVO.class);
        } catch (ServiceException e) {
            logger.error("返回所有对象JSON数据失败：{}", e.getMessage());
        }
        return userTicketOrderVOList;
    }

    @RequestMapping("pager")
    @ResponseBody
    public PagerVO listPage(int offset, int limit, String sort, String order) {
        PagerVO pagerVO = new PagerVO();
        PageQuery pageQuery = new PageQuery(offset / limit + 1, limit, sort, order);
        try {
            PagerDTO pagerDTO = userTicketOrderService.listPage(pageQuery);
            Mapper mapper = getBeanMapper();
            pagerVO = mapper.map(pagerDTO, PagerVO.class);
            pagerVO.setRows(DozerMapperUtils.mapList(mapper, pagerDTO.getRows(), UserTicketOrderVO.class));
        } catch (ServiceException e) {
            logger.error("返回分页对象JSON数据失败：{}", e.getMessage());
        }
        return pagerVO;
    }

    @RequestMapping("pager-cond")
    @ResponseBody
    public PagerVO listPageByCondition(int offset, int limit, String sort, String order, UserTicketOrderQuery userTicketOrderQuery) {
        PagerVO pagerVO = new PagerVO();
        PageQuery pageQuery = new PageQuery(offset / limit + 1, limit, sort, order);
        try {
            PagerDTO pagerDTO = userTicketOrderService.listPageByCondition(pageQuery, userTicketOrderQuery);
            Mapper mapper = getBeanMapper();
            pagerVO = mapper.map(pagerDTO, PagerVO.class);
            List<Object> userTicketOrderVOList = DozerMapperUtils.mapList(mapper, pagerDTO.getRows(), UserTicketOrderVO.class);
            for (Object obj : userTicketOrderVOList) {
                UserTicketOrderVO userTicketOrderVO = (UserTicketOrderVO) obj;
                TicketOrderDetailQuery ticketOrderDetailQuery = new TicketOrderDetailQuery();
                ticketOrderDetailQuery.setOrderNo(userTicketOrderVO.getTicketOrderOrderNo());
                PagerDTO allSeats = ticketOrderDetailService.listPageByCondition(new PageQuery(1, 100, null, null), ticketOrderDetailQuery);
                StringBuilder allSeatString = new StringBuilder();
                for (Object seatObj : allSeats.getRows()) {
                    TicketOrderDetailDTO ticketOrderDetailDTO = (TicketOrderDetailDTO) seatObj;
                    String seat = ticketOrderDetailDTO.getSeat();
                    if (seat.contains("-")) {
                        allSeatString.append(ticketOrderDetailDTO.getSeat().split("-")[0])
                                .append("区")
                                .append(ticketOrderDetailDTO.getSeat().split("-")[1])
                                .append("排")
                                .append(ticketOrderDetailDTO.getSeat().split("-")[2])
                                .append("座 ");
                    }
                }
                userTicketOrderVO.setAllSeatsString(allSeatString.toString());
            }
            pagerVO.setRows(userTicketOrderVOList);
        } catch (ServiceException e) {
            logger.error("返回指定条件的分页对象JSON数据失败：{}", e.getMessage());
        }
        return pagerVO;
    }

    @Resource
    public void setUserTicketOrderService(UserTicketOrderService userTicketOrderService) {
        this.userTicketOrderService = userTicketOrderService;
    }

    @Autowired
    public void setTicketOrderDetailService(TicketOrderDetailService ticketOrderDetailService) {
        this.ticketOrderDetailService = ticketOrderDetailService;
    }
}
