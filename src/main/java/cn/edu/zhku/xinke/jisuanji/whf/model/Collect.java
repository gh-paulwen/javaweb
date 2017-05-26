package cn.edu.zhku.xinke.jisuanji.whf.model;

public class Collect {

	private int user;

	private int product;

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

	@Override
	public String toString() {
		return "Collect [user=" + user + ", product=" + product + "]";
	}

}
