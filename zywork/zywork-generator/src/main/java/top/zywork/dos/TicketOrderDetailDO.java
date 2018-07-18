package top.zywork.dos;

import java.util.Date;

/**
 * TicketOrderDetailDO数据对象实体类<br/>
 *
 * 创建于2018-07-18<br/>
 *
 * @author http://zywork.top 王振宇
 * @version 1.0
 */
public class TicketOrderDetailDO extends BaseDO {

    private static final long serialVersionUID = -9223372036195081143L;

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
	 * 更新时间
	 */
	private Date updateTime;
	
    public TicketOrderDetailDO () {}

    public TicketOrderDetailDO (Long id, Long ticketOrderId, String seat, Date createTime, Date updateTime) {
        this.id = id;
		this.ticketOrderId = ticketOrderId;
		this.seat = seat;
		this.createTime = createTime;
		this.updateTime = updateTime;
		
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

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	
    @Override
    public String toString() {
        return "TicketOrderDetailDO{" +
                "id = " + id + 
				", ticketOrderId = " + ticketOrderId + 
				", seat = " + seat + 
				", createTime = " + createTime + 
				", updateTime = " + updateTime + 
				"}";
    }

}
