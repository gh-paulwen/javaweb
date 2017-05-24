package cn.edu.zhku.xinke.jisuanji.whf.model;

import java.util.Date;

public class Product {
	private int id;

	private String name;

	private double price;

	private String pic;

	private String description;

	private int store;
	
	private int secCategory;
	
	private Date createDate;

	public int getSecCategory() {
		return secCategory;
	}

	public void setSecCategory(int secCategory) {
		this.secCategory = secCategory;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

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

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getStore() {
		return store;
	}

	public void setStore(int store) {
		this.store = store;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", price=" + price
				+ ", pic=" + pic + ", description=" + description + ", store="
				+ store + ", secCategory=" + secCategory + ", createDate="
				+ createDate + "]";
	}

}
