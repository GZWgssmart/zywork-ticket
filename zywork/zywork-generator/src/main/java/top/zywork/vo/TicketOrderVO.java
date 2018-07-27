package top.zywork.vo;

import javax.validation.constraints.Size;
import java.util.Date;

/**
 * TicketOrderVO值对象类<br/>
 *
 * 创建于2018-07-24<br/>
 *
 * @author http://zywork.top 王振宇
 * @version 1.0
 */
public class TicketOrderVO extends BaseVO {

    private static final long serialVersionUID = -9223372035747528726L;

    /**
	 * 编号
	 */
	private Long id;
	/**
	 * 剧目编号
	 */
	private Long ticketItemId;
	/**
	 * 用户编号
	 */
	private Long userId;
	/**
	 * 微信openid
	 */
	@Size(min = 0, max = 200, message = "必须小于200个字符")
	private String openid;
	/**
	 * 订单编号
	 */
	@Size(min = 0, max = 100, message = "必须小于100个字符")
	private String orderNo;
	/**
	 * 下单时间
	 */
	private Date orderTime;
	/**
	 * 单价
	 */
	private Double unitPrice;
	/**
	 * 总座位数
	 */
	private Integer totalSeat;
	/**
	 * 支付总额
	 */
	private Double totalPrice;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 更新时间
	 */
	private Date updateTime;

	private String selectedSeats;
	private String playTimeStr;
	
    public TicketOrderVO () {}

    public TicketOrderVO (Long id, Long ticketItemId, Long userId, String openid, String orderNo, Date orderTime, Double unitPrice, Integer totalSeat, Double totalPrice, Date createTime, Date updateTime) {
        this.id = id;
		this.ticketItemId = ticketItemId;
		this.userId = userId;
		this.openid = openid;
		this.orderNo = orderNo;
		this.orderTime = orderTime;
		this.unitPrice = unitPrice;
		this.totalSeat = totalSeat;
		this.totalPrice = totalPrice;
		this.createTime = createTime;
		this.updateTime = updateTime;
		
    }

    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getTicketItemId() {
		return ticketItemId;
	}

	public void setTicketItemId(Long ticketItemId) {
		this.ticketItemId = ticketItemId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public Date getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
	}

	public Double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public Integer getTotalSeat() {
		return totalSeat;
	}

	public void setTotalSeat(Integer totalSeat) {
		this.totalSeat = totalSeat;
	}

	public Double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
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

	public String getSelectedSeats() {
		return selectedSeats;
	}

	public void setSelectedSeats(String selectedSeats) {
		this.selectedSeats = selectedSeats;
	}

	public String getPlayTimeStr() {
		return playTimeStr;
	}

	public void setPlayTimeStr(String playTimeStr) {
		this.playTimeStr = playTimeStr;
	}

	@Override
    public String toString() {
        return "TicketOrderDO{" +
                "id = " + id + 
				", ticketItemId = " + ticketItemId + 
				", userId = " + userId + 
				", openid = " + openid + 
				", orderNo = " + orderNo + 
				", orderTime = " + orderTime + 
				", unitPrice = " + unitPrice + 
				", totalSeat = " + totalSeat + 
				", totalPrice = " + totalPrice + 
				", createTime = " + createTime + 
				", updateTime = " + updateTime + 
				"}";
    }

}
