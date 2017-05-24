package cn.edu.zhku.xinke.jisuanji.whf.servlet.json;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.zhku.xinke.jisuanji.whf.dto.ModelAttribute;
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

}
