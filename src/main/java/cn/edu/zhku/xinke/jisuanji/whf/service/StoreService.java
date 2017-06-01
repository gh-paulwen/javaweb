package cn.edu.zhku.xinke.jisuanji.whf.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import cn.edu.zhku.xinke.jisuanji.whf.dao.StoreDao;
import cn.edu.zhku.xinke.jisuanji.whf.dao.UserDao;
import cn.edu.zhku.xinke.jisuanji.whf.dto.ModelAttribute;
import cn.edu.zhku.xinke.jisuanji.whf.model.Store;
import cn.edu.zhku.xinke.jisuanji.whf.model.User;
import cn.edu.zhku.xinke.jisuanji.whf.util.TxConstructor;

public class StoreService {

	private static StoreService instance = new StoreService();

	private StoreService() {
	}

	public static StoreService getInstance() {
		return instance;
	}

	private StoreDao storeDao = StoreDao.getInstance();
	
	private UserDao userDao = UserDao.getInstance();

	public ModelAttribute register(Store store, HttpSession session) {
		TxConstructor tx = new TxConstructor();
		ModelAttribute ma = new ModelAttribute("forward:message.jsp");

		User curUser = (User) session.getAttribute(User.CURRENT_USER);
		if (curUser == null) {
			ma.setAttribute("message", "未登录");
			return ma;
		}

		if (store.getName() == null || store.getName().isEmpty()
				|| store.getRegion() <= 0) {
			ma.setAttribute("message", "店铺信息不完整");
			return ma;
		}
		
		store.setOwner(curUser.getId());
		storeDao.save(store,tx);
		ma.setAttribute("message", "注册店铺成功");
			
			//更新用户为商家
		if(curUser.getType() == User.TYPE_USER){
			curUser.setType(User.TYPE_STORE_HOLDER);
			userDao.update(curUser,tx);
		}
		
		int res = tx.commit();
		if(res >0 ){	
			ma.setAttribute("message", "注册店铺成功");
		} else {
			ma.setAttribute("message", "注册店铺失败");
		}

		return ma;
	}

	public ModelAttribute delete(int store, HttpSession session) {
		ModelAttribute ma = new ModelAttribute("forward:message.jsp");

		User curUser = (User) session.getAttribute(User.CURRENT_USER);
		if (curUser == null) {
			ma.setAttribute("message", "未登录");
			return ma;
		}

		int res = storeDao.delete(store, curUser.getId());
		if (res > 0) {
			ma.setAttribute("message", "删除店铺成功");
		} else {
			ma.setAttribute("message", "删除店铺失败");
		}
		return ma;
	}

	public ModelAttribute update(Store store, HttpSession session) {
		ModelAttribute ma = new ModelAttribute("forward:message.jsp");

		User curUser = (User) session.getAttribute(User.CURRENT_USER);
		if (curUser == null) {
			ma.setAttribute("message", "未登录");
			return ma;
		}

		if (store.getOwner() != curUser.getId()) {
			ma.setAttribute("message", "无权限操作");
			return ma;
		}

		if (store.getRegion() <= 0 || store.getName() == null
				|| store.getName().isEmpty()) {
			ma.setAttribute("message", "店铺信息不完整");
			return ma;
		}

		int res = storeDao.update(store);
		if (res > 0) {
			ma.setAttribute("message", "修改店铺信息成功");
		} else {
			ma.setAttribute("message", "修改店铺信息失败");
		}
		return ma;
	}

	public ModelAttribute getById(int id) {
		ModelAttribute ma = new ModelAttribute("forward:store.jsp");
		Store store = storeDao.getById(id);
		ma.setAttribute("store", store);
		return ma;
	}

	public ModelAttribute getByUser(HttpSession session) {
		ModelAttribute ma = new ModelAttribute("forward:store_list.jsp");

		User curUser = (User) session.getAttribute(User.CURRENT_USER);
		if (curUser == null) {
			ma.setAttribute("message", "未登录");
			ma.setDestination("forward:message.jsp");
			return ma;
		}

		List<Store> list = storeDao.getByUser(curUser.getId());
		ma.setAttribute("listStore", list);
		return ma;
	}

	public ModelAttribute getByName(String name) {
		ModelAttribute ma = new ModelAttribute("forward:store_list.jsp");
		List<Store> list = storeDao.getByName(name);
		ma.setAttribute("listStore", list);
		return ma;
	}
	
	
	public ModelAttribute getVerbose(int id){
		ModelAttribute ma = new ModelAttribute("forward:store.jsp");
		Map<String,Object> store = storeDao.getVerboseById(id);
		ma.setAttribute("store", store);
		return ma;
	}
	
	public ModelAttribute getAll(HttpSession session){
		ModelAttribute ma = new ModelAttribute();
		User curUser = (User) session.getAttribute(User.CURRENT_USER);
		if(curUser == null){
			ma.setAttribute("message", "未登录");
			return ma;
		}
		
		if(User.TYPE_ADMIN != curUser.getType()){
			ma.setAttribute("message", "没有管理员权限");
			return ma;
		}
		
		List<Store> list = storeDao.getAll();
		ma.setAttribute("listStore", list);
		
		return ma;
	}
}
