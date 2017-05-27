package cn.edu.zhku.xinke.jisuanji.whf.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import cn.edu.zhku.xinke.jisuanji.whf.dao.AddressDao;
import cn.edu.zhku.xinke.jisuanji.whf.dto.ModelAttribute;
import cn.edu.zhku.xinke.jisuanji.whf.model.Address;
import cn.edu.zhku.xinke.jisuanji.whf.model.User;

public class AddressService {
	
	private static AddressService instance = new AddressService();
	
	private AddressService(){}
	
	public static AddressService getInstance(){
		return instance;
	}
	
	private AddressDao addressDao = AddressDao.getInstance();
	
	public ModelAttribute save(Address address,HttpSession session){
		ModelAttribute ma = new ModelAttribute("forward:message.jsp");
		User user = (User) session.getAttribute(User.CURRENT_USER);
		if(user == null){
			ma.setAttribute("message", "未登录");
			return ma;
		}
		
		if(address.getRegion() <= 0 || address.getVerboseAddress() == null ||
				address.getVerboseAddress().isEmpty() || 
				address.getReceiverName() == null || address.getReceiverName().isEmpty() || 
				address.getReceiverPhone() == null || address.getReceiverPhone().isEmpty()){
			ma.setAttribute("message", "地址信息不完整");
			return ma;
		}
		
		address.setUser(user.getId());
		
		addressDao.save(address);
		ma.setAttribute("message", "已保存");
		return ma;
	}
	
	public ModelAttribute delete(int id,HttpSession session){
		ModelAttribute ma = new ModelAttribute("forward:message.jsp");
		User user = (User) session.getAttribute(User.CURRENT_USER);
		if(user == null){
			ma.setAttribute("message", "未登录");
			return ma;
		}
		
		int res = addressDao.delete(id,user.getId());
		if(res > 0){
			ma.setAttribute("message", "已删除");
		}else{
			ma.setAttribute("message", "删除失败");
		}
		
		return ma;
	}
	
	public ModelAttribute update(Address address,HttpSession session){
		ModelAttribute ma = new ModelAttribute("forward:message.jsp");
		User user = (User) session.getAttribute(User.CURRENT_USER);
		if(user == null){
			ma.setAttribute("message", "未登录");
			return ma;
		}
		if(user.getId() != address.getUser()){
			ma.setAttribute("message", "无权限操作");
			return ma;
		}
		
		if(address.getRegion() <= 0 || address.getVerboseAddress() == null ||
				address.getVerboseAddress().isEmpty() || 
				address.getReceiverName() == null || address.getReceiverName().isEmpty() || 
				address.getReceiverPhone() == null || address.getReceiverPhone().isEmpty()){
			ma.setAttribute("message", "地址信息不完整");
			return ma;
		}
		
		int res = addressDao.update(address);
		if(res > 0){
			ma.setAttribute("message", "已更改");
		} else {
			ma.setAttribute("message", "更改失败");
		}
		return ma;
	}
	
	public ModelAttribute getByUser(HttpSession session){
		ModelAttribute ma = new ModelAttribute("forward:address.jsp");
		User user = (User) session.getAttribute(User.CURRENT_USER);
		if(user == null){
			ma.setAttribute("message", "未登录");
			return ma;
		}
		List<Address> list = addressDao.getByUser(user.getId());
		ma.setAttribute("listAddress", list);
		return ma;
	}
	
	public ModelAttribute getVerboseByUser(HttpSession session){
		ModelAttribute ma = new ModelAttribute("forward:address.jsp");
		User user = (User) session.getAttribute(User.CURRENT_USER);
		if(user == null){
			ma.setAttribute("message", "未登录");
			return ma;
		}
		List<Map<String,Object>> list = addressDao.getVerboseByUser(user.getId());
		ma.setAttribute("listVerbose", list);
		return ma;
	}
	
}
