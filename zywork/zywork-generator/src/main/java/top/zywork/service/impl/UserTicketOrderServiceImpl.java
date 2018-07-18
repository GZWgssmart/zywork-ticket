package top.zywork.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.zywork.dao.UserTicketOrderDAO;
import top.zywork.dos.UserTicketOrderDO;
import top.zywork.dto.UserTicketOrderDTO;
import top.zywork.service.AbstractBaseService;
import top.zywork.service.UserTicketOrderService;

import javax.annotation.PostConstruct;

/**
 * UserTicketOrderServiceImpl服务接口实现类<br/>
 *
 * 创建于2018-07-18<br/>
 *
 * @author http://zywork.top 王振宇
 * @version 1.0
 */
@Service(value = "userTicketOrderService")
public class UserTicketOrderServiceImpl extends AbstractBaseService implements UserTicketOrderService {

    private UserTicketOrderDAO userTicketOrderDAO;

    @Autowired
    public void setUserTicketOrderDAO(UserTicketOrderDAO userTicketOrderDAO) {
        super.setBaseDAO(userTicketOrderDAO);
        this.userTicketOrderDAO = userTicketOrderDAO;
    }

    @PostConstruct
    public void init() {
        super.init(UserTicketOrderDO.class, UserTicketOrderDTO.class);
    }
}
