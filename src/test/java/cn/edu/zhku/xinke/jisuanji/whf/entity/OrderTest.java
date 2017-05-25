package cn.edu.zhku.xinke.jisuanji.whf.entity;

import org.junit.Test;

import cn.edu.zhku.xinke.jisuanji.whf.model.Order;

public class OrderTest {
	@Test
	public void testEnum(){
		System.out.println(Order.Status.UNPAID.ordinal());
	}
}
