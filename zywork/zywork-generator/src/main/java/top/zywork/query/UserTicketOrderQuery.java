package top.zywork.query;

import java.util.Date;

/**
 * UserTicketOrderQuery查询对象类<br/>
 *
 * 创建于2018-07-18<br/>
 *
 * @author http://zywork.top 王振宇
 * @version 1.0
 */
public class UserTicketOrderQuery extends BaseQuery {

    private static final long serialVersionUID = -9223372034942175545L;

    //t_user表的字段对应的属性
	/**
	 * 编号
	 */
	private Long userId;
	/**
	 * 头像
	 */
	private String userHeadicon;
	/**
	 * 昵称
	 */
	private String userNickname;
	//t_ticket_order表的字段对应的属性
	/**
	 * 剧目编号
	 */
	private Long ticketOrderTicketItemId;
	/**
	 * 用户编号
	 */
	private Long ticketOrderUserId;
	/**
	 * 下单时间
	 */
	private Date ticketOrderOrderTime;
	/**
	 * 下单时间(开始)
	 */
	private Date ticketOrderOrderTimeStart;
	/**
	 * 下单时间(结束)
	 */
	private Date ticketOrderOrderTimeEnd;
	/**
	 * 单价
	 */
	private Double ticketOrderUnitPrice;
	/**
	 * 总座位数
	 */
	private Integer ticketOrderTotalSeat;
	/**
	 * 支付总额
	 */
	private Double ticketOrderTotalPrice;
	//t_ticket_item表的字段对应的属性
	/**
	 * 编号
	 */
	private Long ticketItemId;
	/**
	 * 名称
	 */
	private String ticketItemTitle;
	/**
	 * 封面图片
	 */
	private String ticketItemHeadImg;
	/**
	 * 放映时间
	 */
	private Date ticketItemPlayTime;
	/**
	 * 放映时间(开始)
	 */
	private Date ticketItemPlayTimeStart;
	/**
	 * 放映时间(结束)
	 */
	private Date ticketItemPlayTimeEnd;
	/**
	 * 放映地点
	 */
	private String ticketItemAddress;
	
    public UserTicketOrderQuery() {}

    public UserTicketOrderQuery(Long userId, String userHeadicon, String userNickname, Long ticketOrderTicketItemId, Long ticketOrderUserId, Date ticketOrderOrderTime, Date ticketOrderOrderTimeStart, Date ticketOrderOrderTimeEnd, Double ticketOrderUnitPrice, Integer ticketOrderTotalSeat, Double ticketOrderTotalPrice, Long ticketItemId, String ticketItemTitle, String ticketItemHeadImg, Date ticketItemPlayTime, Date ticketItemPlayTimeStart, Date ticketItemPlayTimeEnd, String ticketItemAddress) {
        this.userId = userId;
		this.userHeadicon = userHeadicon;
		this.userNickname = userNickname;
		this.ticketOrderTicketItemId = ticketOrderTicketItemId;
		this.ticketOrderUserId = ticketOrderUserId;
		this.ticketOrderOrderTime = ticketOrderOrderTime;
		this.ticketOrderOrderTimeStart = ticketOrderOrderTimeStart;
		this.ticketOrderOrderTimeEnd = ticketOrderOrderTimeEnd;
		this.ticketOrderUnitPrice = ticketOrderUnitPrice;
		this.ticketOrderTotalSeat = ticketOrderTotalSeat;
		this.ticketOrderTotalPrice = ticketOrderTotalPrice;
		this.ticketItemId = ticketItemId;
		this.ticketItemTitle = ticketItemTitle;
		this.ticketItemHeadImg = ticketItemHeadImg;
		this.ticketItemPlayTime = ticketItemPlayTime;
		this.ticketItemPlayTimeStart = ticketItemPlayTimeStart;
		this.ticketItemPlayTimeEnd = ticketItemPlayTimeEnd;
		this.ticketItemAddress = ticketItemAddress;
		
    }

    public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserHeadicon() {
		return userHeadicon;
	}

	public void setUserHeadicon(String userHeadicon) {
		this.userHeadicon = userHeadicon;
	}

	public String getUserNickname() {
		return userNickname;
	}

	public void setUserNickname(String userNickname) {
		this.userNickname = userNickname;
	}

	public Long getTicketOrderTicketItemId() {
		return ticketOrderTicketItemId;
	}

	public void setTicketOrderTicketItemId(Long ticketOrderTicketItemId) {
		this.ticketOrderTicketItemId = ticketOrderTicketItemId;
	}

	public Long getTicketOrderUserId() {
		return ticketOrderUserId;
	}

	public void setTicketOrderUserId(Long ticketOrderUserId) {
		this.ticketOrderUserId = ticketOrderUserId;
	}

	public Date getTicketOrderOrderTime() {
		return ticketOrderOrderTime;
	}

	public void setTicketOrderOrderTime(Date ticketOrderOrderTime) {
		this.ticketOrderOrderTime = ticketOrderOrderTime;
	}

	public Date getTicketOrderOrderTimeStart() {
		return ticketOrderOrderTimeStart;
	}

	public void setTicketOrderOrderTimeStart(Date ticketOrderOrderTimeStart) {
		this.ticketOrderOrderTimeStart = ticketOrderOrderTimeStart;
	}

	public Date getTicketOrderOrderTimeEnd() {
		return ticketOrderOrderTimeEnd;
	}

	public void setTicketOrderOrderTimeEnd(Date ticketOrderOrderTimeEnd) {
		this.ticketOrderOrderTimeEnd = ticketOrderOrderTimeEnd;
	}

	public Double getTicketOrderUnitPrice() {
		return ticketOrderUnitPrice;
	}

	public void setTicketOrderUnitPrice(Double ticketOrderUnitPrice) {
		this.ticketOrderUnitPrice = ticketOrderUnitPrice;
	}

	public Integer getTicketOrderTotalSeat() {
		return ticketOrderTotalSeat;
	}

	public void setTicketOrderTotalSeat(Integer ticketOrderTotalSeat) {
		this.ticketOrderTotalSeat = ticketOrderTotalSeat;
	}

	public Double getTicketOrderTotalPrice() {
		return ticketOrderTotalPrice;
	}

	public void setTicketOrderTotalPrice(Double ticketOrderTotalPrice) {
		this.ticketOrderTotalPrice = ticketOrderTotalPrice;
	}

	public Long getTicketItemId() {
		return ticketItemId;
	}

	public void setTicketItemId(Long ticketItemId) {
		this.ticketItemId = ticketItemId;
	}

	public String getTicketItemTitle() {
		return ticketItemTitle;
	}

	public void setTicketItemTitle(String ticketItemTitle) {
		this.ticketItemTitle = ticketItemTitle;
	}

	public String getTicketItemHeadImg() {
		return ticketItemHeadImg;
	}

	public void setTicketItemHeadImg(String ticketItemHeadImg) {
		this.ticketItemHeadImg = ticketItemHeadImg;
	}

	public Date getTicketItemPlayTime() {
		return ticketItemPlayTime;
	}

	public void setTicketItemPlayTime(Date ticketItemPlayTime) {
		this.ticketItemPlayTime = ticketItemPlayTime;
	}

	public Date getTicketItemPlayTimeStart() {
		return ticketItemPlayTimeStart;
	}

	public void setTicketItemPlayTimeStart(Date ticketItemPlayTimeStart) {
		this.ticketItemPlayTimeStart = ticketItemPlayTimeStart;
	}

	public Date getTicketItemPlayTimeEnd() {
		return ticketItemPlayTimeEnd;
	}

	public void setTicketItemPlayTimeEnd(Date ticketItemPlayTimeEnd) {
		this.ticketItemPlayTimeEnd = ticketItemPlayTimeEnd;
	}

	public String getTicketItemAddress() {
		return ticketItemAddress;
	}

	public void setTicketItemAddress(String ticketItemAddress) {
		this.ticketItemAddress = ticketItemAddress;
	}

	
    @Override
    public String toString() {
        return "UserTicketOrderDO{" +
                "}";
    }

}
