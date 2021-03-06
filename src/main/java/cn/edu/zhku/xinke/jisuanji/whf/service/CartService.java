package cn.edu.zhku.xinke.jisuanji.whf.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import cn.edu.zhku.xinke.jisuanji.whf.dao.CartDao;
import cn.edu.zhku.xinke.jisuanji.whf.dao.StoreDao;
import cn.edu.zhku.xinke.jisuanji.whf.dto.ModelAttribute;
import cn.edu.zhku.xinke.jisuanji.whf.model.Cart;
import cn.edu.zhku.xinke.jisuanji.whf.model.Store;
import cn.edu.zhku.xinke.jisuanji.whf.model.User;

public class CartService {
	private static CartService instance = new CartService();
	
	private CartService(){}
	public static CartService getInstance(){
		return instance;
	}
	private CartDao cartDao=CartDao.getInstance();
	private StoreDao storeDao = StoreDao.getInstance();
	
	/**
	 * 添加商品到购物车
	 */
	public ModelAttribute save(Cart cart,HttpSession session){
		ModelAttribute ma=new ModelAttribute("forword:cart.jsp");
		User curUser = (User) session.getAttribute(User.CURRENT_USER);
		if(curUser == null){
			ma.setDestination("forward:message.jsp");
			ma.setAttribute("message", "未登录");
			return ma;
		}
		cart.setUser(curUser.getId());
		cartDao.save(cart);
		ma.setAttribute("message", "加入购物车成功");
		return ma;
	}
	
	/**
	 * 从购物车删除商品
	 */
	public ModelAttribute delete(Cart cart,HttpSession session){
		ModelAttribute ma=new ModelAttribute("forword:cart.jsp");
		User curUser = (User) session.getAttribute(User.CURRENT_USER);
		if(curUser == null){
			ma.setDestination("forward:message.jsp");
			ma.setAttribute("message", "未登录");
			return ma;
		}
		cart.setUser(curUser.getId());
		cartDao.delete(cart);
		ma.setAttribute("message", "删除成功");
		return ma;
	}
	
	/**
	 * 更新购物车
	 */
	public ModelAttribute update(Cart cart,HttpSession session){
		ModelAttribute ma=new ModelAttribute("forword:cart.jsp");
		User curUser = (User) session.getAttribute(User.CURRENT_USER);
		if(curUser == null){
			ma.setDestination("forward:message.jsp");
			ma.setAttribute("message", "未登录");
			return ma;
		}
		cart.setUser(curUser.getId());
		cartDao.update(cart);
		return ma;
	}
	
	/**
	 * 显示购物车的信息
	 */
	public ModelAttribute getByUser(int id,HttpSession session){
		ModelAttribute ma=new ModelAttribute("forword:cart.jsp");
		User curUser = (User) session.getAttribute(User.CURRENT_USER);
		if(curUser == null){
			ma.setDestination("forward:message.jsp");
			ma.setAttribute("message", "未登录");
			return ma;
		}
		List<Cart> list=cartDao.getByUser(id);
		ma.setAttribute("listCart", list);
		return ma;
	}
	
	public ModelAttribute getVerbose(HttpSession session){
		ModelAttribute ma = new ModelAttribute("forwar:cart.jsp");
		User curUser = (User) session.getAttribute(User.CURRENT_USER);
		if(curUser == null){
			ma.setDestination("forward:message.jsp");
			ma.setAttribute("message", "未登录");
			return ma;
		}
		int userid = curUser.getId();
		List<Map<String,Object>> listStore = cartDao.getStoreByUser(userid);
		List<Map<String,Object>> list = new ArrayList<>();
		for(Map<String,Object> map:listStore){
			int storeid = (int) map.get("id");
			List<Map<String,Object>> listVerbose = cartDao.getVerboseByStoreAndUser(storeid, userid);
			Map<String,Object> one = new HashMap<>();
			one.put("store", map);
			one.put("listVerbose", listVerbose);
			list.add(one);
		}
		ma.setAttribute("list", list);
		return ma;
	}
	
	public ModelAttribute getVerboseByStore(int store,HttpSession session){
		ModelAttribute ma = new ModelAttribute("forwar:cart.jsp");
		User curUser = (User) session.getAttribute(User.CURRENT_USER);
		if(curUser == null){
			ma.setDestination("forward:message.jsp");
			ma.setAttribute("message", "未登录");
			return ma;
		}
		List<Map<String,Object>> list = cartDao.getVerboseByStoreAndUser(store, curUser.getId());
		Store storeGet = storeDao.getById(store);
		ma.setAttribute("listVerbose", list);
		ma.setAttribute("store", storeGet);
		return ma;
	}
}
