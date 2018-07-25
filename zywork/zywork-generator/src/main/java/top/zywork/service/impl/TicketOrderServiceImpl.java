package top.zywork.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.zywork.common.ExceptionUtils;
import top.zywork.dao.TicketOrderDAO;
import top.zywork.dao.TicketOrderDetailDAO;
import top.zywork.dos.TicketOrderDO;
import top.zywork.dos.TicketOrderDetailDO;
import top.zywork.dto.TicketOrderDTO;
import top.zywork.service.AbstractBaseService;
import top.zywork.service.TicketOrderService;

import javax.annotation.PostConstruct;

/**
 * TicketOrderServiceImpl服务接口实现类<br/>
 *
 * 创建于2018-07-24<br/>
 *
 * @author http://zywork.top 王振宇
 * @version 1.0
 */
@Service(value = "ticketOrderService")
public class TicketOrderServiceImpl extends AbstractBaseService implements TicketOrderService {

    private TicketOrderDAO ticketOrderDAO;
    private TicketOrderDetailDAO ticketOrderDetailDAO;

    @Override
    public void save(Object dataTransferObj) {
        try {
            TicketOrderDO ticketOrderDO = getBeanMapper().map(dataTransferObj, TicketOrderDO.class);
            ticketOrderDAO.save(ticketOrderDO);
            String[] strArray = ticketOrderDO.getSelectedSeats().split(";");
            for (String seat : strArray) {
                TicketOrderDetailDO ticketOrderDetailDO = new TicketOrderDetailDO();
                ticketOrderDetailDO.setOrderNo(ticketOrderDO.getOrderNo());
                ticketOrderDetailDO.setSeat(seat);
                ticketOrderDetailDAO.save(ticketOrderDetailDO);
            }
        } catch (RuntimeException e) {
            throw ExceptionUtils.serviceException(e);
        }
    }

    @Override
    public void updateOrderTimeByOrderNo(String orderNo) {
        ticketOrderDAO.updateOrderTimeByOrderNo(orderNo);
    }

    @Autowired
    public void setTicketOrderDAO(TicketOrderDAO ticketOrderDAO) {
        super.setBaseDAO(ticketOrderDAO);
        this.ticketOrderDAO = ticketOrderDAO;
    }

    @Autowired
    public void setTicketOrderDetailDAO(TicketOrderDetailDAO ticketOrderDetailDAO) {
        this.ticketOrderDetailDAO = ticketOrderDetailDAO;
    }

    @PostConstruct
    public void init() {
        super.init(TicketOrderDO.class, TicketOrderDTO.class);
    }
}
