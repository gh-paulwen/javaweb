package cn.edu.zhku.xinke.jisuanji.whf.util;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.junit.Test;

import cn.edu.zhku.xinke.jisuanji.whf.model.Order;
import cn.edu.zhku.xinke.jisuanji.whf.model.OrderDetail;

import com.google.gson.Gson;

public class JsonTest {
	
	Gson gson = new Gson();
	
	@Test
	public void testJson(){
		// {order:{user:1,store:1,address:1,createDate:...},orderDetails:[{product:1,count:1},{product:2,count:1}]}
		// Map<String,Object>  order : order , orderDetails : OrderDetail[]
		
		Map<String,Object> map = new HashMap<>();
		Order order = new Order();
		order.setUser(1);
		order.setStatus(Order.Status.UNPAID.name());
		order.setStore(1);
		order.setAddress(1);
		order.setCreateDate(new Date());
		map.put("order", order);
		
		OrderDetail od1 = new OrderDetail();
		od1.setProduct(1);
		od1.setCount(1);
		
		OrderDetail od2 = new OrderDetail();
		od2.setProduct(2);
		od2.setCount(2);
		OrderDetail[] ods = new OrderDetail[]{
				od1,od2
		}; 
		
		map.put("orderDetails", ods);
		
		String json = gson.toJson(map);
		
		@SuppressWarnings("unchecked")
		Map<String,Object> jsonMap = gson.fromJson(json, Map.class);
		
//		@SuppressWarnings("unchecked")
//		Map<String,Object> orderMap = (Map<String, Object>) jsonMap.get("order");
		
//		System.out.println(orderMap.get("user"));
		
		List list = (List) jsonMap.get("orderDetails");
		System.out.println(list);
		
		
		
	}
	
	@Test
	public void testRand(){
		Random rand = new Random();
		for(int i= 0;i < 100 ;i ++){
			int ra = rand.nextInt(10);
			System.out.println(ra);
		}
	}

}
