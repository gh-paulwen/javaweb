package cn.edu.zhku.xinke.jisuanji.whf.model;

/**
 * @author aScholars
 * */
public class Cart {
	
	private int userId;
	
	private int productId;
	
	private int count;

	@Override
	public String toString() {
		return "Cart [userId=" + userId + ", productId=" + productId + ", count=" + count + "]";
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
	
	
}
