package top.zywork.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import top.zywork.query.PageQuery;

import java.util.List;

/**
 * TicketOrderDetailDAO数据访问接口<br/>
 *
 * 创建于2018-07-25<br/>
 *
 * @author http://zywork.top 王振宇
 * @version 1.0
 */
@Repository
public interface TicketOrderDetailDAO extends BaseDAO {

    @Override
    List<Object> listPageByCondition(@Param("pager") PageQuery pageQuery, @Param("query") Object queryObj);

    @Override
    Long countByCondition(@Param("query") Object queryObj);

    List<Object> listSelectedSeats(@Param("ticketItemId") String ticketItemId, @Param("playTimeStr") String playTimeStr);
}
