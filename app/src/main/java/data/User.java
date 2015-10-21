package data;

import java.io.Serializable;

/**
 * Created by Nifury on 8/13/2015.
 */
public class User implements Serializable {
	private static final long serialVersionUID = 1L;
	private String name; // 用户姓名
	private String account; // 用户账号
	private String password; // 用户密码
	private String phone; // 联系方式：可以是手机，也可以是邮箱，需要验证码注册
	private String address; // 联系地址
	private int authority; // 权限等级
	private long property; // 资金
	private long cost; // 成本，只有作为市商的系统有。其他用户为0

	public User(String name, String user, String password, String phone,
			String address, int authority, long property, long cost) {
		super();
		this.name = name;
		this.account = user;
		this.password = password;
		this.phone = phone;
		this.address = address;
		this.authority = authority;
		this.property = property;
		this.cost = cost;
	}

	public User(String name, String user, String phone, String address,
			int authority, long property, long cost) {
		super();
		this.name = name;
		this.account = user;
		this.phone = phone;
		this.address = address;
		this.authority = authority;
		this.property = property;
		this.cost = cost;
	}

	public int getAuthority() {
		return this.authority;
	}

	public String getPhone() {
		return phone;
	}

	public String getAddress() {
		return address;
	}

	public String getName() {
		return name;
	}

	public String getAccount() {
		return account;
	}

	public String getPassword() {
		return password;
	}

	public long getProperty() {
		return property;
	}

	public void setProperty(long property) {
		this.property = property;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setAuthority(int authority) {
		this.authority = authority;
	}

	public long getCost() {
		return cost;
	}

	public String toString() {
		return "account:\t" + account + "\tname:\t" + name + "\tphone:\t"
				+ phone + "\taddress:\t" + address;
	}
}
