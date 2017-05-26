package cn.edu.zhku.xinke.jisuanji.whf.model;

import java.util.Date;

public class Store {
	private int id;

	private String name;

	private int owner;

	private Date registerDate;

	private int region;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getOwner() {
		return owner;
	}

	public void setOwner(int owner) {
		this.owner = owner;
	}

	public Date getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}

	public int getRegion() {
		return region;
	}

	public void setRegion(int region) {
		this.region = region;
	}

	@Override
	public String toString() {
		return "Store [id=" + id + ", name=" + name + ", owner=" + owner
				+ ", registerDate=" + registerDate + ", region=" + region + "]";
	}

}
