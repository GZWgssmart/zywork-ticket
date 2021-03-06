package top.zywork.dto;

import java.util.Date;

/**
 * TicketOrderDetailDTO数据传输对象类<br/>
 *
 * 创建于2018-07-25<br/>
 *
 * @author http://zywork.top 王振宇
 * @version 1.0
 */
public class TicketOrderDetailDTO extends BaseDTO {

    private static final long serialVersionUID = -9223372035278410570L;

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
	/**
	 * 订单编号
	 */
	private String orderNo;

	private String area;
	
    public TicketOrderDetailDTO() {}

    public TicketOrderDetailDTO(Long id, Long ticketOrderId, String seat, Date createTime, Date updateTime, String orderNo) {
        this.id = id;
		this.ticketOrderId = ticketOrderId;
		this.seat = seat;
		this.createTime = createTime;
		this.updateTime = updateTime;
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

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	@Override
    public String toString() {
        return "TicketOrderDetailDO{" +
                "id = " + id + 
				", ticketOrderId = " + ticketOrderId + 
				", seat = " + seat + 
				", createTime = " + createTime + 
				", updateTime = " + updateTime + 
				", orderNo = " + orderNo + 
				"}";
    }

}
