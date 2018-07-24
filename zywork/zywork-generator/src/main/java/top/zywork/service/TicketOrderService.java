package top.zywork.service;

/**
 * TicketOrderService服务接口<br/>
 *
 * 创建于2018-07-24<br/>
 *
 * @author http://zywork.top 王振宇
 * @version 1.0
 */
public interface TicketOrderService extends BaseService {
    void updateOrderTimeByOrderNo(String orderNo);
}
