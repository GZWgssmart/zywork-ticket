package top.zywork.controller;

import org.dozer.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import top.zywork.common.*;
import top.zywork.dto.PagerDTO;
import top.zywork.dto.TicketItemDTO;
import top.zywork.exception.ServiceException;
import top.zywork.query.PageQuery;
import top.zywork.query.StatusQueries;
import top.zywork.query.StatusQuery;
import top.zywork.query.TicketItemQuery;
import top.zywork.service.TicketItemService;
import top.zywork.vo.ControllerStatusVO;
import top.zywork.vo.PagerVO;
import top.zywork.vo.TicketItemVO;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * TicketItemController控制器类<br/>
 *
 * 创建于2018-07-18<br/>
 *
 * @author http://zywork.top 王振宇
 * @version 1.0
 */
@Controller
@RequestMapping("/tickeitem")
public class TicketItemController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(TicketItemController.class);

    private TicketItemService ticketItemService;

    @GetMapping("page")
    public String page() {
        return "TicketItem/TicketItem";
    }

    @GetMapping("add-modal")
    public String addModal() {
        return "TicketItem/TicketItemAddModal";
    }

    @GetMapping("edit-modal")
    public String editModal() {
        return "TicketItem/TicketItemEditModal";
    }

    @GetMapping("detail-modal")
    public String detailModal() {
        return "TicketItem/TicketItemDetailModal";
    }

    @GetMapping("search-modal")
    public String searchModal() {
        return "TicketItem/TicketItemSearchModal";
    }

    @PostMapping("save")
    @ResponseBody
    public ControllerStatusVO save(@Validated TicketItemVO ticketItemVO, MultipartFile headImgFile, BindingResult bindingResult, HttpServletRequest request) {
        ControllerStatusVO statusVO = new ControllerStatusVO();
        if (bindingResult.hasErrors()) {
            statusVO.dataErrorStatus(500, BindingResultUtils.errorString(bindingResult));
        } else {
            try {
                if (headImgFile != null && !org.springframework.util.StringUtils.isEmpty(headImgFile.getOriginalFilename())) {
                    String uploadDir = "static/ticket";
                    String imgName = DateUtils.currentTimeMillis() + "" + RandomUtils.randomNum(10000, 99999) + FileUtils.getExtension(headImgFile.getOriginalFilename());
                    headImgFile.transferTo(new File(FileUtils.uploadPath(request, uploadDir) + "/" + imgName));
                    ticketItemVO.setHeadImg(uploadDir + "/" + imgName);
                }
                ticketItemService.save(getBeanMapper().map(ticketItemVO, TicketItemDTO.class));
                statusVO.okStatus(200, "添加成功");
            } catch (ServiceException | IOException e) {
                logger.error("添加失败：{}", e.getMessage());
                statusVO.errorStatus(500, "添加失败");
            }
        }
        return statusVO;
    }

    @PostMapping("remove")
    @ResponseBody
    public ControllerStatusVO remove(TicketItemVO ticketItemVO) {
        ControllerStatusVO statusVO = new ControllerStatusVO();
        try {
            ticketItemService.remove(getBeanMapper().map(ticketItemVO, TicketItemDTO.class));
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
            ticketItemService.removeById(id);
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
            ticketItemService.removeByIds(StringUtils.strToLongArray(ids, ","));
            statusVO.okStatus(200, "批量删除成功");
        } catch (ServiceException e) {
            logger.error("批量删除失败：{}", e.getMessage());
            statusVO.errorStatus(500, "批量删除失败");
        }
        return statusVO;
    }

    @PostMapping("update")
    @ResponseBody
    public ControllerStatusVO update(@Validated TicketItemVO ticketItemVO, MultipartFile headImgFile, BindingResult bindingResult, HttpServletRequest request) {
        ControllerStatusVO statusVO = new ControllerStatusVO();
        if (bindingResult.hasErrors()) {
            statusVO.dataErrorStatus(500, BindingResultUtils.errorString(bindingResult));
        } else {
            try {
                if (headImgFile != null && !org.springframework.util.StringUtils.isEmpty(headImgFile.getOriginalFilename())) {
                    String uploadDir = "static/ticket";
                    String imgName = DateUtils.currentTimeMillis() + "" + RandomUtils.randomNum(10000, 99999) + FileUtils.getExtension(headImgFile.getOriginalFilename());
                    headImgFile.transferTo(new File(FileUtils.uploadPath(request, uploadDir) + "/" + imgName));
                    ticketItemVO.setHeadImg(uploadDir + "/" + imgName);
                }
                ticketItemService.update(getBeanMapper().map(ticketItemVO, TicketItemDTO.class));
                statusVO.okStatus(200, "更新成功");
            } catch (ServiceException | IOException e) {
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
            ticketItemService.updateActiveStatus(statusQuery);
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
            ticketItemService.updateActiveStatuses(statusQueries);
            statusVO.okStatus(200, statusQueries.getStatus() == 0 ? "批量激活成功" : "批量冻结成功");
        } catch (ServiceException e) {
            logger.error("激活或冻结失败：{}", e.getMessage());
            statusVO.errorStatus(500, statusQueries.getStatus() == 0 ? "批量激活失败" : "批量冻结失败");
        }
        return statusVO;
    }

    @RequestMapping("one/{id}")
    @ResponseBody
    public TicketItemVO getById(@PathVariable("id") Long id) {
        TicketItemVO ticketItemVO = new TicketItemVO();
        try {
            Object obj = ticketItemService.getById(id);
            if (obj != null) {
                ticketItemVO = getBeanMapper().map(obj, TicketItemVO.class);
            }
        } catch (ServiceException e) {
            logger.error("返回单个对象JSON数据失败：{}", e.getMessage());
        }
        return ticketItemVO;
    }

    @GetMapping("all")
    @ResponseBody
    public List<TicketItemVO> listAll() {
        List<TicketItemVO> ticketItemVOList = new ArrayList<>();
        try {
            List<Object> objectList = ticketItemService.listAll();
            ticketItemVOList =  DozerMapperUtils.map(getBeanMapper(), objectList, TicketItemVO.class);
        } catch (ServiceException e) {
            logger.error("返回所有对象JSON数据失败：{}", e.getMessage());
        }
        return ticketItemVOList;
    }

    @RequestMapping("pager")
    @ResponseBody
    public PagerVO listPage(int offset, int limit, String sort, String order) {
        PagerVO pagerVO = new PagerVO();
        PageQuery pageQuery = new PageQuery(offset / limit + 1, limit, sort, order);
        try {
            PagerDTO pagerDTO = ticketItemService.listPage(pageQuery);
            Mapper mapper = getBeanMapper();
            pagerVO = mapper.map(pagerDTO, PagerVO.class);
            pagerVO.setRows(DozerMapperUtils.mapList(mapper, pagerDTO.getRows(), TicketItemVO.class));
        } catch (ServiceException e) {
            logger.error("返回分页对象JSON数据失败：{}", e.getMessage());
        }
        return pagerVO;
    }

    @RequestMapping("pager-cond")
    @ResponseBody
    public PagerVO listPageByCondition(int offset, int limit, String sort, String order, TicketItemQuery ticketItemQuery) {
        PagerVO pagerVO = new PagerVO();
        PageQuery pageQuery = new PageQuery(offset / limit + 1, limit, sort, order);
        try {
            PagerDTO pagerDTO = ticketItemService.listPageByCondition(pageQuery, ticketItemQuery);
            Mapper mapper = getBeanMapper();
            pagerVO = mapper.map(pagerDTO, PagerVO.class);
            pagerVO.setRows(DozerMapperUtils.mapList(mapper, pagerDTO.getRows(), TicketItemVO.class));
        } catch (ServiceException e) {
            logger.error("返回指定条件的分页对象JSON数据失败：{}", e.getMessage());
        }
        return pagerVO;
    }

    @Resource
    public void setTicketItemService(TicketItemService ticketItemService) {
        this.ticketItemService = ticketItemService;
    }
}
