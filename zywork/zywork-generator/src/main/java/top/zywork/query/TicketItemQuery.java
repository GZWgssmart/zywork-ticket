package top.zywork.query;

import java.util.Date;

/**
 * TicketItemQuery查询对象类<br/>
 *
 * 创建于2018-07-18<br/>
 *
 * @author http://zywork.top 王振宇
 * @version 1.0
 */
public class TicketItemQuery extends BaseQuery {

    private static final long serialVersionUID = -9223372036319350161L;

    /**
	 * 编号
	 */
	private Long id;
	/**
	 * 名称
	 */
	private String title;
	/**
	 * 封面图片
	 */
	private String headImg;
	/**
	 * 放映时间
	 */
	private Date playTime;
	/**
	 * 放映时间(开始)
	 */
	private Date playTimeStart;
	/**
	 * 放映时间(结束)
	 */
	private Date playTimeEnd;
	/**
	 * 原价
	 */
	private Double price;
	/**
	 * 优惠价
	 */
	private Double unitPrice;
	/**
	 * 放映地点
	 */
	private String address;
	/**
	 * 描述
	 */
	private String description;
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
	
    public TicketItemQuery() {}

    public TicketItemQuery(Long id, String title, String headImg, Date playTime, Date playTimeStart, Date playTimeEnd, Double price, Double unitPrice, String address, String description, Date createTime, Date createTimeStart, Date createTimeEnd, Date updateTime, Date updateTimeStart, Date updateTimeEnd) {
        this.id = id;
		this.title = title;
		this.headImg = headImg;
		this.playTime = playTime;
		this.playTimeStart = playTimeStart;
		this.playTimeEnd = playTimeEnd;
		this.price = price;
		this.unitPrice = unitPrice;
		this.address = address;
		this.description = description;
		this.createTime = createTime;
		this.createTimeStart = createTimeStart;
		this.createTimeEnd = createTimeEnd;
		this.updateTime = updateTime;
		this.updateTimeStart = updateTimeStart;
		this.updateTimeEnd = updateTimeEnd;
		
    }

    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getHeadImg() {
		return headImg;
	}

	public void setHeadImg(String headImg) {
		this.headImg = headImg;
	}

	public Date getPlayTime() {
		return playTime;
	}

	public void setPlayTime(Date playTime) {
		this.playTime = playTime;
	}

	public Date getPlayTimeStart() {
		return playTimeStart;
	}

	public void setPlayTimeStart(Date playTimeStart) {
		this.playTimeStart = playTimeStart;
	}

	public Date getPlayTimeEnd() {
		return playTimeEnd;
	}

	public void setPlayTimeEnd(Date playTimeEnd) {
		this.playTimeEnd = playTimeEnd;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	
    @Override
    public String toString() {
        return "TicketItemDO{" +
                "}";
    }

}
