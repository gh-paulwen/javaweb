package cn.edu.zhku.xinke.jisuanji.whf.dao;

import org.junit.Test;

import cn.edu.zhku.xinke.jisuanji.whf.model.Cart;

public class CartDaoTest {
	private CartDao cartDao=CartDao.getInstance();
	
	@Test
	public void TestSave(){
		Cart cart=new Cart();
		cart.setUserId(10);
		cart.setProductId(20);
		cartDao.save(cart);
	}
}
