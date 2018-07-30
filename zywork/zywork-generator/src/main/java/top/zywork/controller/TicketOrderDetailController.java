package top.zywork.controller;

import org.dozer.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import top.zywork.common.BindingResultUtils;
import top.zywork.common.DozerMapperUtils;
import top.zywork.common.StringUtils;
import top.zywork.dto.PagerDTO;
import top.zywork.dto.TicketOrderDetailDTO;
import top.zywork.exception.ServiceException;
import top.zywork.query.PageQuery;
import top.zywork.query.StatusQueries;
import top.zywork.query.StatusQuery;
import top.zywork.query.TicketOrderDetailQuery;
import top.zywork.service.TicketOrderDetailService;
import top.zywork.vo.ControllerStatusVO;
import top.zywork.vo.PagerVO;
import top.zywork.vo.TicketOrderDetailVO;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * TicketOrderDetailController控制器类<br/>
 *
 * 创建于2018-07-25<br/>
 *
 * @author http://zywork.top 王振宇
 * @version 1.0
 */
@Controller
@RequestMapping("/tickeorder-detail")
public class TicketOrderDetailController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(TicketOrderDetailController.class);

    private TicketOrderDetailService ticketOrderDetailService;

    @GetMapping("page")
    public String page() {
        return "TicketOrderDetail/TicketOrderDetail";
    }

    @GetMapping("add-modal")
    public String addModal() {
        return "TicketOrderDetail/TicketOrderDetailAddModal";
    }

    @GetMapping("edit-modal")
    public String editModal() {
        return "TicketOrderDetail/TicketOrderDetailEditModal";
    }

    @GetMapping("detail-modal")
    public String detailModal() {
        return "TicketOrderDetail/TicketOrderDetailDetailModal";
    }

    @GetMapping("search-modal")
    public String searchModal() {
        return "TicketOrderDetail/TicketOrderDetailSearchModal";
    }

    @PostMapping("save")
    @ResponseBody
    public ControllerStatusVO save(@Validated TicketOrderDetailVO ticketOrderDetailVO, BindingResult bindingResult) {
        ControllerStatusVO statusVO = new ControllerStatusVO();
        if (bindingResult.hasErrors()) {
            statusVO.dataErrorStatus(500, BindingResultUtils.errorString(bindingResult));
        } else {
            try {
                ticketOrderDetailService.save(getBeanMapper().map(ticketOrderDetailVO, TicketOrderDetailDTO.class));
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
    public ControllerStatusVO remove(TicketOrderDetailVO ticketOrderDetailVO) {
        ControllerStatusVO statusVO = new ControllerStatusVO();
        try {
            ticketOrderDetailService.remove(getBeanMapper().map(ticketOrderDetailVO, TicketOrderDetailDTO.class));
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
            ticketOrderDetailService.removeById(id);
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
            ticketOrderDetailService.removeByIds(StringUtils.strToLongArray(ids, ","));
            statusVO.okStatus(200, "批量删除成功");
        } catch (ServiceException e) {
            logger.error("批量删除失败：{}", e.getMessage());
            statusVO.errorStatus(500, "批量删除失败");
        }
        return statusVO;
    }

    @PostMapping("update")
    @ResponseBody
    public ControllerStatusVO update(@Validated TicketOrderDetailVO ticketOrderDetailVO, BindingResult bindingResult) {
        ControllerStatusVO statusVO = new ControllerStatusVO();
        if (bindingResult.hasErrors()) {
            statusVO.dataErrorStatus(500, BindingResultUtils.errorString(bindingResult));
        } else {
            try {
                ticketOrderDetailService.update(getBeanMapper().map(ticketOrderDetailVO, TicketOrderDetailDTO.class));
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
            ticketOrderDetailService.updateActiveStatus(statusQuery);
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
            ticketOrderDetailService.updateActiveStatuses(statusQueries);
            statusVO.okStatus(200, statusQueries.getStatus() == 0 ? "批量激活成功" : "批量冻结成功");
        } catch (ServiceException e) {
            logger.error("激活或冻结失败：{}", e.getMessage());
            statusVO.errorStatus(500, statusQueries.getStatus() == 0 ? "批量激活失败" : "批量冻结失败");
        }
        return statusVO;
    }

    @RequestMapping("one/{id}")
    @ResponseBody
    public TicketOrderDetailVO getById(@PathVariable("id") Long id) {
        TicketOrderDetailVO ticketOrderDetailVO = new TicketOrderDetailVO();
        try {
            Object obj = ticketOrderDetailService.getById(id);
            if (obj != null) {
                ticketOrderDetailVO = getBeanMapper().map(obj, TicketOrderDetailVO.class);
            }
        } catch (ServiceException e) {
            logger.error("返回单个对象JSON数据失败：{}", e.getMessage());
        }
        return ticketOrderDetailVO;
    }

    @GetMapping("all")
    @ResponseBody
    public List<TicketOrderDetailVO> listAll() {
        List<TicketOrderDetailVO> ticketOrderDetailVOList = new ArrayList<>();
        try {
            List<Object> objectList = ticketOrderDetailService.listAll();
            ticketOrderDetailVOList =  DozerMapperUtils.map(getBeanMapper(), objectList, TicketOrderDetailVO.class);
        } catch (ServiceException e) {
            logger.error("返回所有对象JSON数据失败：{}", e.getMessage());
        }
        return ticketOrderDetailVOList;
    }

    @RequestMapping("pager")
    @ResponseBody
    public PagerVO listPage(int offset, int limit, String sort, String order) {
        PagerVO pagerVO = new PagerVO();
        PageQuery pageQuery = new PageQuery(offset / limit + 1, limit, sort, order);
        try {
            PagerDTO pagerDTO = ticketOrderDetailService.listPage(pageQuery);
            Mapper mapper = getBeanMapper();
            pagerVO = mapper.map(pagerDTO, PagerVO.class);
            pagerVO.setRows(DozerMapperUtils.mapList(mapper, pagerDTO.getRows(), TicketOrderDetailVO.class));
        } catch (ServiceException e) {
            logger.error("返回分页对象JSON数据失败：{}", e.getMessage());
        }
        return pagerVO;
    }

    @RequestMapping("pager-cond")
    @ResponseBody
    public PagerVO listPageByCondition(int offset, int limit, String sort, String order, TicketOrderDetailQuery ticketOrderDetailQuery) {
        PagerVO pagerVO = new PagerVO();
        PageQuery pageQuery = new PageQuery(offset / limit + 1, limit, sort, order);
        try {
            PagerDTO pagerDTO = ticketOrderDetailService.listPageByCondition(pageQuery, ticketOrderDetailQuery);
            Mapper mapper = getBeanMapper();
            pagerVO = mapper.map(pagerDTO, PagerVO.class);
            pagerVO.setRows(DozerMapperUtils.mapList(mapper, pagerDTO.getRows(), TicketOrderDetailVO.class));
        } catch (ServiceException e) {
            logger.error("返回指定条件的分页对象JSON数据失败：{}", e.getMessage());
        }
        return pagerVO;
    }

    @RequestMapping("selected-seats/{ticketItemId}/{playTimeStr}")
    @ResponseBody
    public List<TicketOrderDetailVO> listSelectedSeats(@PathVariable("ticketItemId") String ticketItemId, @PathVariable("playTimeStr") String playTimeStr) {
        List<TicketOrderDetailVO> ticketOrderDetailVOList = new ArrayList<>();
        try {
            List<Object> objectList = ticketOrderDetailService.listSelectedSeats(ticketItemId, playTimeStr);
            ticketOrderDetailVOList =  DozerMapperUtils.map(getBeanMapper(), objectList, TicketOrderDetailVO.class);
            List<Object> objectListAdmin = ticketOrderDetailService.listSelectedSeatsAdmin(ticketItemId, playTimeStr);
            ticketOrderDetailVOList.addAll(DozerMapperUtils.map(getBeanMapper(), objectListAdmin, TicketOrderDetailVO.class));
        } catch (ServiceException e) {
            logger.error("返回所有对象JSON数据失败：{}", e.getMessage());
        }
        return ticketOrderDetailVOList;
    }

    @RequestMapping("selected-seats-admin/{ticketItemId}/{playTimeStr}")
    @ResponseBody
    public List<TicketOrderDetailVO> listSelectedSeatsAdmin(@PathVariable("ticketItemId") String ticketItemId, @PathVariable("playTimeStr") String playTimeStr) {
        List<TicketOrderDetailVO> ticketOrderDetailVOList = new ArrayList<>();
        try {
            List<Object> objectList = ticketOrderDetailService.listSelectedSeatsAdmin(ticketItemId, playTimeStr);
            ticketOrderDetailVOList =  DozerMapperUtils.map(getBeanMapper(), objectList, TicketOrderDetailVO.class);
        } catch (ServiceException e) {
            logger.error("返回所有对象JSON数据失败：{}", e.getMessage());
        }
        return ticketOrderDetailVOList;
    }

    @Resource
    public void setTicketOrderDetailService(TicketOrderDetailService ticketOrderDetailService) {
        this.ticketOrderDetailService = ticketOrderDetailService;
    }
}
