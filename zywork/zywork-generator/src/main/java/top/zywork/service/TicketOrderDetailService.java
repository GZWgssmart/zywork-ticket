package top.zywork.service;

import java.util.List;

/**
 * TicketOrderDetailService服务接口<br/>
 *
 * 创建于2018-07-25<br/>
 *
 * @author http://zywork.top 王振宇
 * @version 1.0
 */
public interface TicketOrderDetailService extends BaseService {
    List<Object> listSelectedSeats(String ticketItemId, String playTimeStr);
    List<Object> listSelectedSeatsAdmin(String ticketItemId, String playTimeStr);
    void removeSelectedSeatsAdmin(String ticketItemId, String playTimeStr);
}
