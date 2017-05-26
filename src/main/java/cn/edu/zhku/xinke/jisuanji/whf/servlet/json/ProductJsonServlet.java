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
		return ma.get();
	}

	protected Object page(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		String method = req.getMethod();
		if (!"GET".equalsIgnoreCase(method)) {
			req.setAttribute("message", "method : " + method + " not supported");
			req.getRequestDispatcher("/input.jsp").forward(req, resp);
			return null;
		}
		int page = Integer.parseInt(req.getParameter("page"));
		ModelAttribute ma = productService.page(page);
		return ma.get();
	}

	protected Object categoryPage(HttpServletRequest req,
			HttpServletResponse resp) throws ServletException, IOException {

		String method = req.getMethod();
		if (!"GET".equalsIgnoreCase(method)) {
			req.setAttribute("message", "method : " + method + " not supported");
			req.getRequestDispatcher("/input.jsp").forward(req, resp);
			return null;
		}

		int page = Integer.parseInt(req.getParameter("page"));
		int category = Integer.parseInt(req.getParameter("category"));
		ModelAttribute ma = productService.categoryPage(category, page);
		return ma.get();
	}

	protected Object secCategoryPage(HttpServletRequest req,
			HttpServletResponse resp) throws ServletException, IOException {

		String method = req.getMethod();
		if (!"GET".equalsIgnoreCase(method)) {
			req.setAttribute("message", "method : " + method + " not supported");
			req.getRequestDispatcher("/input.jsp").forward(req, resp);
			return null;
		}

		int page = Integer.parseInt(req.getParameter("page"));
		int secCategory = Integer.parseInt(req.getParameter("secCategory"));
		ModelAttribute ma = productService.secCategoryPage(secCategory, page);
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

}
