package cn.edu.zhku.xinke.jisuanji.whf.servlet.json;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.zhku.xinke.jisuanji.whf.dto.ModelAttribute;
import cn.edu.zhku.xinke.jisuanji.whf.model.Category;
import cn.edu.zhku.xinke.jisuanji.whf.model.SecCategory;
import cn.edu.zhku.xinke.jisuanji.whf.service.CategoryService;

public class CategoryJsonServlet extends JsonServlet{

	private static final long serialVersionUID = 1L;
	
	private CategoryService categoryService = CategoryService.getInstance();
	
	protected Object getAllCategory(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String method = req.getMethod();
		if(!"GET".equalsIgnoreCase(method)){
			req.setAttribute("message", "method : " + method + " not supported");
			req.getRequestDispatcher("/input.jsp").forward(req, resp);
			return null;
		}
		ModelAttribute ma = categoryService.getAllCategory();
		return ma.get();
	}
	
	
	protected Object getSecByCategory(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String method = req.getMethod();
		if(!"GET".equalsIgnoreCase(method)){
			req.setAttribute("message", "method : " + method + " not supported");
			req.getRequestDispatcher("/input.jsp").forward(req, resp);
			return null;
		}
		
		int category = Integer.parseInt(req.getParameter("category"));
		
		
		ModelAttribute ma = categoryService.getSecByCategory(category);
		return ma.get();
	}
	
	protected Object saveCategory(HttpServletRequest req, HttpServletResponse resp)
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
		return ma.get();
	}
	
	protected Object saveSecCategory(HttpServletRequest req, HttpServletResponse resp)
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
		return ma.get();
	}
	
	protected Object deleteCategory(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String method = req.getMethod();
		if(!"POST".equalsIgnoreCase(method)){
			req.setAttribute("message", "method : " + method + " not supported");
			req.getRequestDispatcher("/input.jsp").forward(req, resp);
			return null;
		}
		
		int categoryid = Integer.parseInt(req.getParameter("category"));
		
		ModelAttribute ma = categoryService.deleteCategory(categoryid);
		return ma.get();
	}
	
	protected Object deleteSecCategory(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String method = req.getMethod();
		if(!"POST".equalsIgnoreCase(method)){
			req.setAttribute("message", "method : " + method + " not supported");
			req.getRequestDispatcher("/input.jsp").forward(req, resp);
			return null;
		}
		
		int secCategoryid = Integer.parseInt(req.getParameter("secCategory"));
		
		ModelAttribute ma = categoryService.deleteCategory(secCategoryid);
		return ma.get();
	}

}
