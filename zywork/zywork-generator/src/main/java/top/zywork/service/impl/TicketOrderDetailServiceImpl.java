package top.zywork.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.zywork.dao.TicketOrderDetailDAO;
import top.zywork.dos.TicketOrderDetailDO;
import top.zywork.dto.TicketOrderDetailDTO;
import top.zywork.service.AbstractBaseService;
import top.zywork.service.TicketOrderDetailService;

import javax.annotation.PostConstruct;

/**
 * TicketOrderDetailServiceImpl服务接口实现类<br/>
 *
 * 创建于2018-07-18<br/>
 *
 * @author http://zywork.top 王振宇
 * @version 1.0
 */
@Service(value = "ticketOrderDetailService")
public class TicketOrderDetailServiceImpl extends AbstractBaseService implements TicketOrderDetailService {

    private TicketOrderDetailDAO ticketOrderDetailDAO;

    @Autowired
    public void setTicketOrderDetailDAO(TicketOrderDetailDAO ticketOrderDetailDAO) {
        super.setBaseDAO(ticketOrderDetailDAO);
        this.ticketOrderDetailDAO = ticketOrderDetailDAO;
    }

    @PostConstruct
    public void init() {
        super.init(TicketOrderDetailDO.class, TicketOrderDetailDTO.class);
    }
}
