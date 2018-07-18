package top.zywork.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.zywork.dao.TicketOrderDAO;
import top.zywork.dos.TicketOrderDO;
import top.zywork.dto.TicketOrderDTO;
import top.zywork.service.AbstractBaseService;
import top.zywork.service.TicketOrderService;

import javax.annotation.PostConstruct;

/**
 * TicketOrderServiceImpl服务接口实现类<br/>
 *
 * 创建于2018-07-18<br/>
 *
 * @author http://zywork.top 王振宇
 * @version 1.0
 */
@Service(value = "ticketOrderService")
public class TicketOrderServiceImpl extends AbstractBaseService implements TicketOrderService {

    private TicketOrderDAO ticketOrderDAO;

    @Autowired
    public void setTicketOrderDAO(TicketOrderDAO ticketOrderDAO) {
        super.setBaseDAO(ticketOrderDAO);
        this.ticketOrderDAO = ticketOrderDAO;
    }

    @PostConstruct
    public void init() {
        super.init(TicketOrderDO.class, TicketOrderDTO.class);
    }
}
