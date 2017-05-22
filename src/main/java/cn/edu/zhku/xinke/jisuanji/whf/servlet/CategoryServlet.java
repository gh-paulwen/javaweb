package cn.edu.zhku.xinke.jisuanji.whf.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.zhku.xinke.jisuanji.whf.dto.ModelAttribute;
import cn.edu.zhku.xinke.jisuanji.whf.model.Category;
import cn.edu.zhku.xinke.jisuanji.whf.model.SecCategory;
import cn.edu.zhku.xinke.jisuanji.whf.service.CategoryService;

public class CategoryServlet extends BaseServlet{

	private static final long serialVersionUID = -5293286645012853132L;
	
	private CategoryService categoryService = CategoryService.getInstance();
	
	protected String saveCategory(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String method = req.getMethod();
		if(!"POST".equalsIgnoreCase(method)){
			req.setAttribute("message", "method : " + method + " not supported");
			req.getRequestDispatcher("/input.jsp").forward(req, resp);
			return null;
		}
		
		String categoryName = req.getParameter("categoryName");
		Category category = new Category();
		category.setName(categoryName);
		
		ModelAttribute ma = categoryService.save(category);
		ma.pollute(req);
		return ma.getDestination();
	}
	
	protected String saveSecCategory(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String method = req.getMethod();
		if(!"POST".equalsIgnoreCase(method)){
			req.setAttribute("message", "method : " + method + " not supported");
			req.getRequestDispatcher("/input.jsp").forward(req, resp);
			return null;
		}
		
		String secCategoryName = req.getParameter("secCategoryName");
		int categoryid = Integer.parseInt(req.getParameter("category"));
		SecCategory secCategory = new SecCategory();
		secCategory.setName(secCategoryName);
		secCategory.setCategory(categoryid);
		
		ModelAttribute ma = categoryService.save(secCategory);
		ma.pollute(req);
		return ma.getDestination();
	}
	
	protected String deleteCategory(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String method = req.getMethod();
		if(!"POST".equalsIgnoreCase(method)){
			req.setAttribute("message", "method : " + method + " not supported");
			req.getRequestDispatcher("/input.jsp").forward(req, resp);
			return null;
		}
		
		int categoryid = Integer.parseInt(req.getParameter("category"));
		
		ModelAttribute ma = categoryService.deleteCategory(categoryid);
		ma.pollute(req);
		return ma.getDestination();
	}
	
	protected String deleteSecCategory(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String method = req.getMethod();
		if(!"POST".equalsIgnoreCase(method)){
			req.setAttribute("message", "method : " + method + " not supported");
			req.getRequestDispatcher("/input.jsp").forward(req, resp);
			return null;
		}
		
		int secCategoryid = Integer.parseInt(req.getParameter("secCategory"));
		
		ModelAttribute ma = categoryService.deleteCategory(secCategoryid);
		ma.pollute(req);
		return ma.getDestination();
	}

}
