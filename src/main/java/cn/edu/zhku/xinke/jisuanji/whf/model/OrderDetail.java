package cn.edu.zhku.xinke.jisuanji.whf.model;

public class OrderDetail {

	private int order;

	private int product;

	private int count;

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
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
		return "OrderDetail [order=" + order + ", product=" + product
				+ ", count=" + count + "]";
	}

}
