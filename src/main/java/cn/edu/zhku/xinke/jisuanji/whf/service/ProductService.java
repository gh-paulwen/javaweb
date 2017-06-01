package cn.edu.zhku.xinke.jisuanji.whf.service;

import java.util.List;
import java.util.Map;

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
			int res = 0;
			int id = 0;
			synchronized(this){
				id = Integer.parseInt(String.valueOf(productDao.getAvailableId()));
				product.setId(id);
				res = productDao.save(product);
			}
			if (res > 0) {
				ma.setAttribute("message", "添加商品成功");
				ma.setAttribute("productid", id);
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
		
		Product productGet = productDao.getById(product.getId());
		productGet.setDescription(product.getDescription());
		productGet.setName(product.getName());
		productGet.setPrice(product.getPrice());
		productGet.setSecCategory(product.getSecCategory());

		if (canupdate && productGet != null) {
			int res = productDao.update(productGet);
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

	public ModelAttribute all() {
		ModelAttribute ma = new ModelAttribute("forward:product_list.jsp");
		List<Product> list = productDao.all();
		ma.setAttribute("listProduct", list);
		return ma;
	}

	public ModelAttribute categoryAll(int category) {
		ModelAttribute ma = new ModelAttribute("forward:product_list.jsp");
		List<Product> list = productDao.categoryAll(category);
		ma.setAttribute("listProduct", list);
		return ma;
	}
	
	public ModelAttribute secCategoryAll(int secCategory) {
		ModelAttribute ma = new ModelAttribute("forward:product_list.jsp");
		List<Product> list = productDao.secCategoryAll(secCategory);
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
		Map<String,Object> product = productDao.getForEdit(id);
		ma.setAttribute("product", product);
		return ma;
	}
	
	public ModelAttribute getByStore(int store){
		ModelAttribute ma = new ModelAttribute("forward:product_list.jsp");
		List<Product> list = productDao.getByStore(store);
		ma.setAttribute("listProduct", list);
		return ma;
	}
	
	public ModelAttribute connectPic(int product,String pic,HttpSession session){
		ModelAttribute ma = new ModelAttribute("forward:message.jsp");

		User user = (User) session.getAttribute(User.CURRENT_USER);
		if (user == null) {
			ma.setAttribute("message", "未登录");
			return ma;
		}
		
		int result =  Integer.parseInt(String.valueOf(productDao.checkProductUser(product,user.getId())));
		int affected = 0;
		if(result > 0){
			synchronized (this){
				Product productGet = productDao.getById(product);
				productGet.setPic(pic);
				affected = productDao.update(productGet);
			}
			
		}else {
			ma.setAttribute("message", "无操作权限");
		}
		
		if(affected > 0){
			ma.setAttribute("message", "上传图片成功");
		}else {
			ma.setAttribute("message", "上传图片失败");
		}
		
		return ma;
	}
	
	public ModelAttribute getVerbose (int id){
		ModelAttribute ma = new ModelAttribute("forward:product.jsp");
		
		Map<String,Object> product = productDao.getVerbose(id);
		ma.setAttribute("product", product);
		return ma;
	}
	
//	private Random rand = new Random();
	public ModelAttribute random(){
		ModelAttribute ma = new ModelAttribute();
//		int max = Integer.valueOf(String.valueOf(productDao.getAvailableId()));
		List<Product> list = productDao.random(3);
		ma.setAttribute("listProduct", list);
		return ma;
	}

}
