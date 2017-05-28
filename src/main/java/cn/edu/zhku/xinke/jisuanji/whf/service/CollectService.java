package cn.edu.zhku.xinke.jisuanji.whf.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import cn.edu.zhku.xinke.jisuanji.whf.dao.CollectDao;
import cn.edu.zhku.xinke.jisuanji.whf.dao.ProductDao;
import cn.edu.zhku.xinke.jisuanji.whf.dto.ModelAttribute;
import cn.edu.zhku.xinke.jisuanji.whf.model.Collect;
import cn.edu.zhku.xinke.jisuanji.whf.model.Product;
import cn.edu.zhku.xinke.jisuanji.whf.model.User;

public class CollectService {

	private static CollectService instance = new CollectService();

	private CollectService() {
	}

	public static CollectService getInstance() {
		return instance;
	}

	private CollectDao collectDao = CollectDao.getInstance();
	
	private ProductDao productDao = ProductDao.getInstance();

	public ModelAttribute collect(int product, HttpSession session) {
		ModelAttribute ma = new ModelAttribute("forward:message.jsp");
		User curUser = (User) session.getAttribute(User.CURRENT_USER);
		if (curUser == null) {
			ma.setAttribute("message", "未登录");
			return ma;
		}
		Collect collect = new Collect();
		collect.setUser(curUser.getId());
		collect.setProduct(product);
		int res = collectDao.save(collect);
		if (res > 0) {
			ma.setAttribute("message", "收藏成功");
		} else {
			ma.setAttribute("message", "收藏失败");
		}

		return ma;
	}

	public ModelAttribute delete(int product, HttpSession session) {
		ModelAttribute ma = new ModelAttribute("forward:message.jsp");
		User curUser = (User) session.getAttribute(User.CURRENT_USER);
		if (curUser == null) {
			ma.setAttribute("message", "未登录");
			return ma;
		}
		Collect collect = new Collect();
		collect.setUser(curUser.getId());
		collect.setProduct(product);
		int res = collectDao.delete(collect);
		if (res > 0) {
			ma.setAttribute("message", "取消收藏成功");
		} else {
			ma.setAttribute("message", "取消收藏失败");
		}

		return ma;
	}

	public ModelAttribute getByUser(HttpSession session) {
		ModelAttribute ma = new ModelAttribute("forward:collect.jsp");
		User curUser = (User) session.getAttribute(User.CURRENT_USER);
		if (curUser == null) {
			ma.setDestination("forward:message.jsp");
			ma.setAttribute("message", "未登录");
			return ma;
		}
		List<Product> list = productDao.getCollectProduct(curUser.getId());
		ma.setAttribute("listCollectProduct", list);
		return ma;
	}
	
	public ModelAttribute getVerboseByUser(HttpSession session){
		ModelAttribute ma = new ModelAttribute("forward:collect.jsp");
		User curUser = (User) session.getAttribute(User.CURRENT_USER);
		if (curUser == null) {
			ma.setDestination("forward:message.jsp");
			ma.setAttribute("message", "未登录");
			return ma;
		}
		List<Map<String,Object>> list = collectDao.getVerboseByUser(curUser.getId());
		ma.setAttribute("listVerbose", list);
		return ma;
	}

}
