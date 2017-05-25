package cn.edu.zhku.xinke.jisuanji.whf.model;

import java.util.Date;

public class Order {
	
	public static enum Status {
		UNPAID,
		PAID,
		CANCELED,
		SENT,
		FINISHED
	}
	
	private int id;

	private int user;

	private int store;

	private int address;

	private Date createDate;

	private String status;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUser() {
		return user;
	}

	public void setUser(int user) {
		this.user = user;
	}

	public int getStore() {
		return store;
	}

	public void setStore(int store) {
		this.store = store;
	}

	public int getAddress() {
		return address;
	}

	public void setAddress(int address) {
		this.address = address;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", user=" + user + ", store=" + store
				+ ", address=" + address + ", createDate=" + createDate
				+ ", status=" + status + "]";
	}

}
