package top.zywork.vo;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * UserVO值对象类<br/>
 *
 * 创建于2018-07-18<br/>
 *
 * @author http://zywork.top 王振宇
 * @version 1.0
 */
public class UserVO extends BaseVO {

    private static final long serialVersionUID = -9223372035409295709L;

    /**
	 * 编号
	 */
	private Long id;
	/**
	 * 邮箱
	 */
	@Size(min = 0, max = 100, message = "必须小于100个字符")
	private String email;
	/**
	 * 手机号
	 */
	@Size(min = 0, max = 11, message = "必须小于11个字符")
	private String phone;
	/**
	 * 账户名
	 */
	@Size(min = 0, max = 20, message = "必须小于20个字符")
	private String accountName;
	/**
	 * 密码
	 */
	@NotBlank(message = "此项是必须项")
	private String password;
	/**
	 * 加密盐值
	 */
	@Size(min = 0, max = 200, message = "必须小于200个字符")
	private String salt;
	/**
	 * 头像
	 */
	@Size(min = 0, max = 500, message = "必须小于500个字符")
	private String headicon;
	/**
	 * 昵称
	 */
	@Size(min = 0, max = 45, message = "必须小于45个字符")
	private String nickname;
	/**
	 * 性别
	 */
	private Byte gender;
	/**
	 * 微信openid
	 */
	@Size(min = 0, max = 200, message = "必须小于200个字符")
	private String openid;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 更新时间
	 */
	private Date updateTime;
	/**
	 * 是否激活
	 */
	private Byte isActive;
	
    public UserVO() {}

    public UserVO(Long id, String email, String phone, String accountName, String password, String salt, String headicon, String nickname, Byte gender, String openid, Date createTime, Date updateTime, Byte isActive) {
        this.id = id;
		this.email = email;
		this.phone = phone;
		this.accountName = accountName;
		this.password = password;
		this.salt = salt;
		this.headicon = headicon;
		this.nickname = nickname;
		this.gender = gender;
		this.openid = openid;
		this.createTime = createTime;
		this.updateTime = updateTime;
		this.isActive = isActive;
		
    }

    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getHeadicon() {
		return headicon;
	}

	public void setHeadicon(String headicon) {
		this.headicon = headicon;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public Byte getGender() {
		return gender;
	}

	public void setGender(Byte gender) {
		this.gender = gender;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
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

	public Byte getIsActive() {
		return isActive;
	}

	public void setIsActive(Byte isActive) {
		this.isActive = isActive;
	}

	
    @Override
    public String toString() {
        return "UserDO{" +
                "id = " + id + 
				", email = " + email + 
				", phone = " + phone + 
				", accountName = " + accountName + 
				", password = " + password + 
				", salt = " + salt + 
				", headicon = " + headicon + 
				", nickname = " + nickname + 
				", gender = " + gender + 
				", openid = " + openid + 
				", createTime = " + createTime + 
				", updateTime = " + updateTime + 
				", isActive = " + isActive + 
				"}";
    }

}
