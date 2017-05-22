package cn.edu.zhku.xinke.jisuanji.whf.service;

import java.util.List;

import cn.edu.zhku.xinke.jisuanji.whf.dao.CategoryDao;
import cn.edu.zhku.xinke.jisuanji.whf.dao.SecCategoryDao;
import cn.edu.zhku.xinke.jisuanji.whf.dto.ModelAttribute;
import cn.edu.zhku.xinke.jisuanji.whf.model.Category;
import cn.edu.zhku.xinke.jisuanji.whf.model.SecCategory;

public class CategoryService {

	/**
	 * 单例实现
	 * */
	private static CategoryService instance = new CategoryService();

	private CategoryService() {
	}

	public static CategoryService getInstance() {
		return instance;
	}

	private CategoryDao categoryDao = CategoryDao.getInstance();
	private SecCategoryDao secCategoryDao = SecCategoryDao.getInstance();

	public ModelAttribute save(Category category) {
		ModelAttribute ma = new ModelAttribute("forward:message.jsp");

		// 检查约束

		categoryDao.save(category);
		ma.setAttribute("message", "添加成功");
		return ma;
	}

	public ModelAttribute save(SecCategory secCategory) {
		ModelAttribute ma = new ModelAttribute("forward:message.jsp");

		// 检查约束

		secCategoryDao.save(secCategory);
		ma.setAttribute("message", "添加成功");
		return ma;
	}

	public ModelAttribute deleteCategory(int id) {
		ModelAttribute ma = new ModelAttribute("forward:message.jsp");

		// 检查约束

		categoryDao.delete(id);
		ma.setAttribute("message", "刪除成功");
		return ma;
	}

	public ModelAttribute deleteSecCategory(int id) {
		ModelAttribute ma = new ModelAttribute("forward:message.jsp");

		// 检查约束

		secCategoryDao.delete(id);
		ma.setAttribute("message", "刪除成功");
		return ma;
	}

	public ModelAttribute getAllCategory(){
		ModelAttribute ma = new ModelAttribute();
		List<Category> list = categoryDao.getAll();
		ma.setAttribute("listCategory", list);
		return ma;
	}
	
	public ModelAttribute getSecByCategory(int category){
		ModelAttribute ma = new ModelAttribute();
		List<SecCategory> list = secCategoryDao.getByCategory(category);
		ma.setAttribute("listSecCategory", list);
		return ma;
	}

}
