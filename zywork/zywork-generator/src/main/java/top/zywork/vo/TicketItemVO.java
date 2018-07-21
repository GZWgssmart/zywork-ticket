package top.zywork.vo;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * TicketItemVO值对象类<br/>
 *
 * 创建于2018-07-18<br/>
 *
 * @author http://zywork.top 王振宇
 * @version 1.0
 */
public class TicketItemVO extends BaseVO {

    private static final long serialVersionUID = -9223372036419059702L;

    /**
	 * 编号
	 */
	private Long id;
	/**
	 * 名称
	 */
	@NotBlank(message = "此项是必须项")
	private String title;
	/**
	 * 封面图片
	 */
	@Size(min = 0, max = 500, message = "必须小于500个字符")
	private String headImg;
	/**
	 * 放映时间
	 */
	@NotNull(message = "此项是必须项")
	private Date playTime;
	/**
	 * 原价
	 */
	@NotNull(message = "此项是必须项")
	private Double price;
	/**
	 * 优惠价
	 */
	@NotNull(message = "此项是必须项")
	private Double unitPrice;
	/**
	 * 放映地点
	 */
	@NotBlank(message = "此项是必须项")
	private String address;
	/**
	 * 描述
	 */
	@Size(min = 0, max = 500, message = "必须小于500个字符")
	private String description;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 更新时间
	 */
	private Date updateTime;
	
    public TicketItemVO() {}

    public TicketItemVO(Long id, String title, String headImg, Date playTime, Double price, Double unitPrice, String address, String description, Date createTime, Date updateTime) {
        this.id = id;
		this.title = title;
		this.headImg = headImg;
		this.playTime = playTime;
		this.price = price;
		this.unitPrice = unitPrice;
		this.address = address;
		this.description = description;
		this.createTime = createTime;
		this.updateTime = updateTime;
		
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

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	
    @Override
    public String toString() {
        return "TicketItemDO{" +
                "id = " + id + 
				", title = " + title + 
				", headImg = " + headImg + 
				", playTime = " + playTime + 
				", price = " + price + 
				", unitPrice = " + unitPrice + 
				", address = " + address + 
				", description = " + description + 
				", createTime = " + createTime + 
				", updateTime = " + updateTime + 
				"}";
    }

}
