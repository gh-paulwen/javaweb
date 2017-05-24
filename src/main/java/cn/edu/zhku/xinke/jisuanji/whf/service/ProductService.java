package cn.edu.zhku.xinke.jisuanji.whf.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import cn.edu.zhku.xinke.jisuanji.whf.dao.ProductDao;
import cn.edu.zhku.xinke.jisuanji.whf.dao.StoreDao;
import cn.edu.zhku.xinke.jisuanji.whf.dto.ModelAttribute;
import cn.edu.zhku.xinke.jisuanji.whf.model.Product;
import cn.edu.zhku.xinke.jisuanji.whf.model.Store;
import cn.edu.zhku.xinke.jisuanji.whf.model.User;

public class ProductService {

	private ProductService() {
	}

	private static ProductService instance = new ProductService();

	public static ProductService getInstance() {
		return instance;
	}

	private ProductDao productDao = ProductDao.getInstance();
	private StoreDao storeDao = StoreDao.getInstance();

	private int count = 20;

	public ModelAttribute save(Product product, HttpSession session) {
		ModelAttribute ma = new ModelAttribute("forward:message.jsp");

		User user = (User) session.getAttribute(User.CURRENT_USER);
		if (user == null) {
			ma.setAttribute("message", "未登录");
			return ma;
		}

		if (product.getName() == null || product.getName().isEmpty()
				|| product.getPrice() <= 0 || product.getSecCategory() <= 0
				|| product.getStore() <= 0) {
			ma.setAttribute("message", "商品信息不完整");
			return ma;
		}

		List<Store> list = storeDao.getByUser(user.getId());
		boolean ownstore = false;
		for (Store store : list) {
			if (store.getId() == product.getStore()) {
				ownstore = true;
				break;
			}
		}

		if (ownstore) {
			int res = productDao.save(product);
			if (res > 0) {
				ma.setAttribute("message", "添加商品成功");
			} else {
				ma.setAttribute("message", "添加商品失败");
			}
		} else {
			ma.setAttribute("message", "未授权操作");
		}
		return ma;
	}

	public ModelAttribute delete(int id, int storeid, HttpSession session) {
		ModelAttribute ma = new ModelAttribute("forward:message.jsp");

		User user = (User) session.getAttribute(User.CURRENT_USER);
		if (user == null) {
			ma.setAttribute("message", "未登录");
			return ma;
		}

		List<Store> list = storeDao.getByUser(user.getId());
		boolean candelete = false;
		for (Store store : list) {
			if (store.getId() == storeid) {
				candelete = true;
			}
		}

		if (candelete) {
			int res = productDao.delete(id, storeid);
			if (res > 0) {
				ma.setAttribute("message", "删除商品成功");
			} else {
				ma.setAttribute("message", "删除商品失败");
			}
		} else {
			ma.setAttribute("message", "未授权操作");
		}
		return ma;
	}

	public ModelAttribute update(Product product, HttpSession session) {
		ModelAttribute ma = new ModelAttribute("forward:message.jsp");

		User user = (User) session.getAttribute(User.CURRENT_USER);
		if (user == null) {
			ma.setAttribute("message", "未登录");
			return ma;
		}

		List<Store> list = storeDao.getByUser(user.getId());
		boolean canupdate = false;
		for (Store store : list) {
			if (store.getId() == product.getStore()) {
				canupdate = true;
			}
		}

		if (product.getName() == null || product.getName().isEmpty()
				|| product.getPrice() <= 0 || product.getSecCategory() <= 0
				|| product.getStore() <= 0) {
			ma.setAttribute("message", "商品信息不全");
			return ma;
		}

		if (canupdate) {
			int res = productDao.update(product);
			if (res > 0) {
				ma.setAttribute("message", "更新商品成功");
			} else {
				ma.setAttribute("message", "更新商品失败");
			}
		} else {
			ma.setAttribute("message", "未授权操作");
		}
		return ma;
	}

	public ModelAttribute page(int page) {
		return page(page, count);
	}

	public ModelAttribute page(int page, int count) {
		ModelAttribute ma = new ModelAttribute("forward:product_list.jsp");
		List<Product> list = productDao.page((page - 1) * count, count);
		ma.setAttribute("listProduct", list);
		return ma;
	}

	public ModelAttribute categoryPage(int category, int page) {
		return categoryPage(category, page, count);
	}

	public ModelAttribute categoryPage(int category, int page, int count) {
		ModelAttribute ma = new ModelAttribute("forward:product_list.jsp");
		List<Product> list = productDao.categoryPage(category, (page - 1)* count, count);
		ma.setAttribute("listProduct", list);
		return ma;
	}

	public ModelAttribute secCategoryPage(int secCategory, int page) {
		return secCategoryPage(secCategory, page, count);
	}

	public ModelAttribute secCategoryPage(int secCategory, int page, int count) {
		ModelAttribute ma = new ModelAttribute("forward:product_list.jsp");
		List<Product> list = productDao.secCategoryPage(secCategory, (page - 1) * count, count);
		ma.setAttribute("listProduct", list);
		return ma;
	}
	
	public ModelAttribute search(String keyword){
		ModelAttribute ma = new ModelAttribute("forward:product_list.jsp");
		List<Product> list = productDao.search(keyword);
		ma.setAttribute("listProduct", list);
		return ma;
	}
	
	public ModelAttribute getById(int id){
		ModelAttribute ma = new ModelAttribute("forward:product.jsp");
		Product product = productDao.getById(id);
		ma.setAttribute("prodcut", product);
		return ma;
	}
	
	public ModelAttribute getByStore(int store){
		ModelAttribute ma = new ModelAttribute("forward:product_list.jsp");
		List<Product> list = productDao.getByStore(store);
		ma.setAttribute("listProduct", list);
		return ma;
	}

}