package top.zywork.query;

import java.util.Date;

/**
 * TicketOrderDetailQuery查询对象类<br/>
 *
 * 创建于2018-07-25<br/>
 *
 * @author http://zywork.top 王振宇
 * @version 1.0
 */
public class TicketOrderDetailQuery extends BaseQuery {

    private static final long serialVersionUID = -9223372036563905826L;

    /**
	 * 编号
	 */
	private Long id;
	/**
	 * 订单编号
	 */
	private Long ticketOrderId;
	/**
	 * 座位号
	 */
	private String seat;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 创建时间(开始)
	 */
	private Date createTimeStart;
	/**
	 * 创建时间(结束)
	 */
	private Date createTimeEnd;
	/**
	 * 更新时间
	 */
	private Date updateTime;
	/**
	 * 更新时间(开始)
	 */
	private Date updateTimeStart;
	/**
	 * 更新时间(结束)
	 */
	private Date updateTimeEnd;
	/**
	 * 订单编号
	 */
	private String orderNo;
	
    public TicketOrderDetailQuery() {}

    public TicketOrderDetailQuery(Long id, Long ticketOrderId, String seat, Date createTime, Date createTimeStart, Date createTimeEnd, Date updateTime, Date updateTimeStart, Date updateTimeEnd, String orderNo) {
        this.id = id;
		this.ticketOrderId = ticketOrderId;
		this.seat = seat;
		this.createTime = createTime;
		this.createTimeStart = createTimeStart;
		this.createTimeEnd = createTimeEnd;
		this.updateTime = updateTime;
		this.updateTimeStart = updateTimeStart;
		this.updateTimeEnd = updateTimeEnd;
		this.orderNo = orderNo;
		
    }

    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getTicketOrderId() {
		return ticketOrderId;
	}

	public void setTicketOrderId(Long ticketOrderId) {
		this.ticketOrderId = ticketOrderId;
	}

	public String getSeat() {
		return seat;
	}

	public void setSeat(String seat) {
		this.seat = seat;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getCreateTimeStart() {
		return createTimeStart;
	}

	public void setCreateTimeStart(Date createTimeStart) {
		this.createTimeStart = createTimeStart;
	}

	public Date getCreateTimeEnd() {
		return createTimeEnd;
	}

	public void setCreateTimeEnd(Date createTimeEnd) {
		this.createTimeEnd = createTimeEnd;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Date getUpdateTimeStart() {
		return updateTimeStart;
	}

	public void setUpdateTimeStart(Date updateTimeStart) {
		this.updateTimeStart = updateTimeStart;
	}

	public Date getUpdateTimeEnd() {
		return updateTimeEnd;
	}

	public void setUpdateTimeEnd(Date updateTimeEnd) {
		this.updateTimeEnd = updateTimeEnd;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	
    @Override
    public String toString() {
        return "TicketOrderDetailDO{" +
                "}";
    }

}
