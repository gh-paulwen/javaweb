package cn.edu.zhku.xinke.jisuanji.whf.model;


public class Cart {
	
	private int user;
	
	private int product;
	
	private int count;

	public int getUser() {
		return user;
	}

	public void setUser(int user) {
		this.user = user;
	}

	public int getProduct() {
		return product;
	}

	public void setProduct(int product) {
		this.product = product;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	@Override
	public String toString() {
		return "Cart [user=" + user + ", product=" + product + ", count="
				+ count + "]";
	}
	
	
}
