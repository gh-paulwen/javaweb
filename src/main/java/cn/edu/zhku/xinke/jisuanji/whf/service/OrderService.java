package cn.edu.zhku.xinke.jisuanji.whf.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import cn.edu.zhku.xinke.jisuanji.whf.dao.OrderDao;
import cn.edu.zhku.xinke.jisuanji.whf.dao.OrderDetailDao;
import cn.edu.zhku.xinke.jisuanji.whf.dao.StoreDao;
import cn.edu.zhku.xinke.jisuanji.whf.dto.ModelAttribute;
import cn.edu.zhku.xinke.jisuanji.whf.model.Order;
import cn.edu.zhku.xinke.jisuanji.whf.model.OrderDetail;
import cn.edu.zhku.xinke.jisuanji.whf.model.Store;
import cn.edu.zhku.xinke.jisuanji.whf.model.User;
import cn.edu.zhku.xinke.jisuanji.whf.util.TxConstructor;

public class OrderService {
	
	private static OrderService instance = new OrderService();
	
	private OrderService(){}
	
	public static OrderService getInstance(){
		return instance;
	}
	
	private OrderDao orderDao = OrderDao.getInstance();
	
	private OrderDetailDao orderDetailDao = OrderDetailDao.getInstance();
	
	private StoreDao storeDao = StoreDao.getInstance();
	
	private Gson gson = new Gson();
	
	public ModelAttribute cancel(int orderid,HttpSession session){
		ModelAttribute ma = new ModelAttribute("forward:message.jsp");
		User curUser = (User) session.getAttribute(User.CURRENT_USER);
		
		if(curUser == null){
			ma.setAttribute("message", "未登录");
			return ma;
		}
		
		List<Order> list = orderDao.getByCustomer(curUser.getId());
		Order orderGet = null;
		boolean cancancel = false;
		for(Order order :list){
			if(order.getId() == orderid){
				cancancel = true;
				orderGet = order;
				break;
			}
		}
		
		if(cancancel){
			orderGet.setStatus(Order.Status.CANCELED.name());
			int res = orderDao.update(orderGet);
			if(res > 0){
				ma.setAttribute("message", "取消成功");
			}else {
				ma.setAttribute("message", "取消失败");
			}
		}else {
			ma.setAttribute("message", "无权限操作");
		}
		
		return ma;
	}
	
	public ModelAttribute send(int orderid,int storeid,HttpSession session){
		ModelAttribute ma = new ModelAttribute("forward:message.jsp");
		User curUser = (User) session.getAttribute(User.CURRENT_USER);
		
		if(curUser == null){
			ma.setAttribute("message", "未登录");
			return ma;
		}
		
		List<Store> listStore = storeDao.getByUser(curUser.getId()); 
		Store storeGet = null;
		boolean cansend = false;
		for(Store store:listStore){
			if(store.getId() == storeid){
				storeGet = store;
				break;
			}
		}
		
		if(storeGet == null){
			ma.setAttribute("message", "无操作权限");
			return ma;
		}
		
		List<Order> listOrder = orderDao.getByStore(storeid);
		Order orderGet = null;
		for(Order order :listOrder){
			if(order.getId() == orderid){
				cansend = true;
				orderGet = order;
				break;
			}
		}
		
		if(cansend){
			orderGet.setStatus(Order.Status.SENT.name());
			int res = orderDao.update(orderGet);
			if(res > 0){
				ma.setAttribute("message", "发货成功");
			}else {
				ma.setAttribute("message", "发货失败");
			}
		}else {
			ma.setAttribute("message", "无权限操作");
		}
		
		return ma;
	}
	
	public ModelAttribute receive(int orderid,HttpSession session){
		ModelAttribute ma = new ModelAttribute("forward:message.jsp");
		User curUser = (User) session.getAttribute(User.CURRENT_USER);
		
		if(curUser == null){
			ma.setAttribute("message", "未登录");
			return ma;
		}
		
		List<Order> list = orderDao.getByCustomer(curUser.getId());
		Order orderGet = null;
		boolean canreceive = false;
		for(Order order :list){
			if(order.getId() == orderid && Order.Status.SENT.name().equalsIgnoreCase(order.getStatus())){
				canreceive = true;
				orderGet = order;
				break;
			}
		}
		
		if(canreceive){
			orderGet.setStatus(Order.Status.FINISHED.name());
			int res = orderDao.update(orderGet);
			if(res > 0){
				ma.setAttribute("message", "收货成功");
			}else {
				ma.setAttribute("message", "收货失败");
			}
		}else {
			ma.setAttribute("message", "无权限操作");
		}
		
		return ma;
	}
	
	
	public ModelAttribute save(String json,HttpSession session){
		// {order:{user:1,store:1,address:1,createDate:...},orderDetails:[{product:1,count:1},{product:2,count:1}]}
		// Map<String,Object>  order : order , orderDetails : OrderDetail[]
		
		ModelAttribute ma = new ModelAttribute("forward:message.jsp");
		User curUser = (User) session.getAttribute(User.CURRENT_USER);
		
		if(curUser == null){
			ma.setAttribute("message", "未登录");
			return ma;
		}
		
		//解析json
		@SuppressWarnings("unchecked")
		Map<String,Object> map = gson.fromJson(json, Map.class);
		@SuppressWarnings("unchecked")
		Map<String,Object> orderMap = (Map<String, Object>) map.get("order");
		Order order = new Order();
		order.setUser(Integer.parseInt(orderMap.get("user").toString()));
		order.setAddress(Integer.parseInt(orderMap.get("address").toString()));
		order.setStore(Integer.parseInt(orderMap.get("store").toString()));
		
		List<?> listOD = (List<?>) map.get("orderDetails");
		List<OrderDetail> listOrderDetail = new ArrayList<>();
		for(Object obj:listOD){
			@SuppressWarnings("unchecked")
			Map<String,Object> mapOD = (Map<String, Object>) obj;
			OrderDetail od = new OrderDetail();
			od.setProduct(Integer.parseInt(mapOD.get("product").toString()));
			od.setCount(Integer.parseInt(mapOD.get("count").toString()));
			listOrderDetail.add(od);
		}
		
		//开始事务处理
		TxConstructor tc = new TxConstructor();
		//传tc
		orderDao.save(order,tc);
		for(OrderDetail od:listOrderDetail){
			orderDetailDao.save(od,tc);
		}
		//记得提交事务,获得受影响行数
		int res = tc.commit();
		if(res > 0){
			ma.setAttribute("message", "下单成功");
		}else {
			ma.setAttribute("message", "下单失败");
		}
		
		return ma;
		
	}
	
}
