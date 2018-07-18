package top.zywork.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.zywork.dao.TicketItemDAO;
import top.zywork.dos.TicketItemDO;
import top.zywork.dto.TicketItemDTO;
import top.zywork.service.AbstractBaseService;
import top.zywork.service.TicketItemService;

import javax.annotation.PostConstruct;

/**
 * TicketItemServiceImpl服务接口实现类<br/>
 *
 * 创建于2018-07-18<br/>
 *
 * @author http://zywork.top 王振宇
 * @version 1.0
 */
@Service(value = "ticketItemService")
public class TicketItemServiceImpl extends AbstractBaseService implements TicketItemService {

    private TicketItemDAO ticketItemDAO;

    @Autowired
    public void setTicketItemDAO(TicketItemDAO ticketItemDAO) {
        super.setBaseDAO(ticketItemDAO);
        this.ticketItemDAO = ticketItemDAO;
    }

    @PostConstruct
    public void init() {
        super.init(TicketItemDO.class, TicketItemDTO.class);
    }
}
