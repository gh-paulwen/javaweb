package cn.edu.zhku.xinke.jisuanji.whf.servlet.json;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.edu.zhku.xinke.jisuanji.whf.dto.ModelAttribute;
import cn.edu.zhku.xinke.jisuanji.whf.model.Product;
import cn.edu.zhku.xinke.jisuanji.whf.service.ProductService;

public class ProductJsonServlet extends JsonServlet {

	private static final long serialVersionUID = 1026798384120522011L;

	private ProductService productService = ProductService.getInstance();

	protected Object save(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		String method = req.getMethod();
		if (!"POST".equalsIgnoreCase(method)) {
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
		return ma.get();
	}

	protected Object delete(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		String method = req.getMethod();
		if (!"POST".equalsIgnoreCase(method)) {
			req.setAttribute("message", "method : " + method + " not supported");
			req.getRequestDispatcher("/input.jsp").forward(req, resp);
			return null;
		}
		int id = Integer.parseInt(req.getParameter("id"));
		int storeid = Integer.parseInt(req.getParameter("storeid"));
		ModelAttribute ma = productService.delete(id, storeid, req.getSession());
		return ma.get();
	}

	protected Object update(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		String method = req.getMethod();
		if (!"POST".equalsIgnoreCase(method)) {
			req.setAttribute("message", "method : " + method + " not supported");
			req.getRequestDispatcher("/input.jsp").forward(req, resp);
			return null;
		}

		HttpSession session = req.getSession();
		int id = Integer.parseInt(req.getParameter("id"));
		String name = req.getParameter("name");
		double price = Double.parseDouble(req.getParameter("price"));
		String description = req.getParameter("description");
		int secCategory = Integer.parseInt(req.getParameter("secCategory"));
		int store = Integer.parseInt(req.getParameter("store"));

		Product product = new Product();
		product.setId(id);
		product.setName(name);
		product.setPrice(price);
		product.setDescription(description);
		product.setSecCategory(secCategory);
		product.setStore(store);

		ModelAttribute ma = productService.update(product, session);
		return ma.get();
	}

	protected Object all(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		String method = req.getMethod();
		if (!"GET".equalsIgnoreCase(method)) {
			req.setAttribute("message", "method : " + method + " not supported");
			req.getRequestDispatcher("/input.jsp").forward(req, resp);
			return null;
		}
		ModelAttribute ma = productService.all();
		return ma.get();
	}

	protected Object categoryAll(HttpServletRequest req,
			HttpServletResponse resp) throws ServletException, IOException {

		String method = req.getMethod();
		if (!"GET".equalsIgnoreCase(method)) {
			req.setAttribute("message", "method : " + method + " not supported");
			req.getRequestDispatcher("/input.jsp").forward(req, resp);
			return null;
		}

		int category = Integer.parseInt(req.getParameter("category"));
		ModelAttribute ma = productService.categoryAll(category);
		return ma.get();
	}

	protected Object secCategoryAll(HttpServletRequest req,
			HttpServletResponse resp) throws ServletException, IOException {

		String method = req.getMethod();
		if (!"GET".equalsIgnoreCase(method)) {
			req.setAttribute("message", "method : " + method + " not supported");
			req.getRequestDispatcher("/input.jsp").forward(req, resp);
			return null;
		}

		int secCategory = Integer.parseInt(req.getParameter("secCategory"));
		ModelAttribute ma = productService.secCategoryAll(secCategory);
		return ma.get();
	}

	protected Object search(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		String method = req.getMethod();
		if (!"GET".equalsIgnoreCase(method)) {
			req.setAttribute("message", "method : " + method + " not supported");
			req.getRequestDispatcher("/input.jsp").forward(req, resp);
			return null;
		}
		String keyword = req.getParameter("keyword");
		ModelAttribute ma = productService.search(keyword);
		return ma.get();
	}

	protected Object getById(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		String method = req.getMethod();
		if (!"GET".equalsIgnoreCase(method)) {
			req.setAttribute("message", "method : " + method + " not supported");
			req.getRequestDispatcher("/input.jsp").forward(req, resp);
			return null;
		}
		int id = Integer.parseInt(req.getParameter("id"));
		ModelAttribute ma = productService.getById(id);
		return ma.get();
	}

	protected Object getByStore(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		String method = req.getMethod();
		if (!"GET".equalsIgnoreCase(method)) {
			req.setAttribute("message", "method : " + method + " not supported");
			req.getRequestDispatcher("/input.jsp").forward(req, resp);
			return null;
		}
		int store = Integer.parseInt(req.getParameter("store"));
		ModelAttribute ma = productService.getByStore(store);
		return ma.get();
	}
	
	protected Object connectPic(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		String method = req.getMethod();
		if (!"GET".equalsIgnoreCase(method)) {
			req.setAttribute("message", "method : " + method + " not supported");
			req.getRequestDispatcher("/input.jsp").forward(req, resp);
			return null;
		}
		int product = Integer.parseInt(req.getParameter("product"));
		String pic = req.getParameter("pic");
		ModelAttribute ma = productService.connectPic(product, pic, req.getSession());
		return ma.get();
	}
	
	protected Object getVerbose(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		String method = req.getMethod();
		if (!"GET".equalsIgnoreCase(method)) {
			req.setAttribute("message", "method : " + method + " not supported");
			req.getRequestDispatcher("/input.jsp").forward(req, resp);
			return null;
		}
		int id = Integer.parseInt(req.getParameter("id"));
		ModelAttribute ma = productService.getVerbose(id);
		return ma.get();
	}
	
	protected Object random(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		String method = req.getMethod();
		if (!"GET".equalsIgnoreCase(method)) {
			req.setAttribute("message", "method : " + method + " not supported");
			req.getRequestDispatcher("/input.jsp").forward(req, resp);
			return null;
		}
		ModelAttribute ma = productService.random();
		return ma.get();
	}

}
