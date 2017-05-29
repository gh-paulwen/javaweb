package cn.edu.zhku.xinke.jisuanji.whf.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.edu.zhku.xinke.jisuanji.whf.dto.ModelAttribute;
import cn.edu.zhku.xinke.jisuanji.whf.model.Product;
import cn.edu.zhku.xinke.jisuanji.whf.service.ProductService;

public class ProductServlet extends BaseServlet{
	
	private static final long serialVersionUID = -8125459946669052250L;
	
	private ProductService productService = ProductService.getInstance();
	
	protected String save(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		String method = req.getMethod();
		if(!"POST".equalsIgnoreCase(method)){
			req.setAttribute("message", "method : " + method + " not supported");
			req.getRequestDispatcher("/input.jsp").forward(req, resp);
			return null;
		}
		HttpSession session = req.getSession();
		String name = req.getParameter("name");
		double price = Double.parseDouble(req.getParameter("price"));
		String description = req.getParameter("description");
		int store = Integer.parseInt(req.getParameter("store"));
		int secCategory = Integer.parseInt(req.getParameter("secCategory"));
		
		Product product = new Product();
		product.setName(name);
		product.setPrice(price);
		product.setDescription(description);
		product.setStore(store);
		product.setSecCategory(secCategory);
		
		ModelAttribute ma = productService.save(product, session);
		ma.pollute(req);
		return ma.getDestination();
	}
	
	protected String delete(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		String method = req.getMethod();
		if(!"POST".equalsIgnoreCase(method)){
			req.setAttribute("message", "method : " + method + " not supported");
			req.getRequestDispatcher("/input.jsp").forward(req, resp);
			return null;
		}
		int id = Integer.parseInt(req.getParameter("id"));
		int storeid = Integer.parseInt(req.getParameter("storeid"));
		ModelAttribute ma = productService.delete(id, storeid, req.getSession());
		ma.pollute(req);
		return ma.getDestination();
	}
	
	protected String update(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		String method = req.getMethod();
		if(!"POST".equalsIgnoreCase(method)){
			req.setAttribute("message", "method : " + method + " not supported");
			req.getRequestDispatcher("/input.jsp").forward(req, resp);
			return null;
		}
		
		HttpSession session = req.getSession();
		String name = req.getParameter("name");
		double price = Double.parseDouble(req.getParameter("price"));
		String description = req.getParameter("description");
		int store = Integer.parseInt(req.getParameter("store"));
		int secCategory = Integer.parseInt(req.getParameter("secCategory"));
		String pic = req.getParameter("pic");
		
		Product product = new Product();
		product.setName(name);
		product.setPrice(price);
		product.setDescription(description);
		product.setStore(store);
		product.setSecCategory(secCategory);
		product.setPic(pic);
		
		ModelAttribute ma = productService.update(product, session);
		ma.pollute(req);
		return ma.getDestination();
	}
	
	protected String all(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		String method = req.getMethod();
		if(!"GET".equalsIgnoreCase(method)){
			req.setAttribute("message", "method : " + method + " not supported");
			req.getRequestDispatcher("/input.jsp").forward(req, resp);
			return null;
		}
		ModelAttribute ma = productService.all();
		ma.pollute(req);
		return ma.getDestination();
	}
	
	protected String categoryAll(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		String method = req.getMethod();
		if(!"GET".equalsIgnoreCase(method)){
			req.setAttribute("message", "method : " + method + " not supported");
			req.getRequestDispatcher("/input.jsp").forward(req, resp);
			return null;
		}
		
		int category = Integer.parseInt(req.getParameter("category"));
		ModelAttribute ma = productService.categoryAll(category);
		ma.pollute(req);
		return ma.getDestination();
	}
	
	protected String secCategoryAll(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		String method = req.getMethod();
		if(!"GET".equalsIgnoreCase(method)){
			req.setAttribute("message", "method : " + method + " not supported");
			req.getRequestDispatcher("/input.jsp").forward(req, resp);
			return null;
		}
		
		int secCategory = Integer.parseInt(req.getParameter("secCategory"));
		ModelAttribute ma = productService.secCategoryAll(secCategory);
		ma.pollute(req);
		return ma.getDestination();
	}
	
	protected String search(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		String method = req.getMethod();
		if(!"GET".equalsIgnoreCase(method)){
			req.setAttribute("message", "method : " + method + " not supported");
			req.getRequestDispatcher("/input.jsp").forward(req, resp);
			return null;
		}
		String keyword = req.getParameter("keyword");
		ModelAttribute ma = productService.search(keyword);
		ma.pollute(req);
		return ma.getDestination();
	}
	
	protected String getById(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		String method = req.getMethod();
		if(!"GET".equalsIgnoreCase(method)){
			req.setAttribute("message", "method : " + method + " not supported");
			req.getRequestDispatcher("/input.jsp").forward(req, resp);
			return null;
		}
		int id = Integer.parseInt(req.getParameter("id"));
		ModelAttribute ma = productService.getById(id);
		ma.pollute(req);
		return ma.getDestination();
	}
	
	protected String getByStore(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		String method = req.getMethod();
		if(!"GET".equalsIgnoreCase(method)){
			req.setAttribute("message", "method : " + method + " not supported");
			req.getRequestDispatcher("/input.jsp").forward(req, resp);
			return null;
		}
		int store = Integer.parseInt(req.getParameter("store"));
		ModelAttribute ma = productService.getByStore(store);
		ma.pollute(req);
		return ma.getDestination();
	}
}
