package cn.edu.zhku.xinke.jisuanji.whf.servlet.json;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.zhku.xinke.jisuanji.whf.dto.ModelAttribute;
import cn.edu.zhku.xinke.jisuanji.whf.model.Cart;
import cn.edu.zhku.xinke.jisuanji.whf.service.CartService;

public class CartJsonServlet extends JsonServlet {

	private static final long serialVersionUID = -1762062370642095527L;

	private CartService cartService = CartService.getInstance();

	/**
	 * 添加商品到购物车
	 */
	public Object save(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String method = req.getMethod();
		if (!"POST".equalsIgnoreCase(method)) {
			req.setAttribute("message", "Method : " + method
					+ " is not supported");
			req.getRequestDispatcher("/input.jsp").forward(req, resp);
		}
		int product = Integer.parseInt(req.getParameter("product"));
		int count = Integer.parseInt(req.getParameter("count"));

		Cart cart = new Cart();
		cart.setProduct(product);
		cart.setCount(count);
		ModelAttribute ma = cartService.save(cart, req.getSession());
		return ma.get();
	}

	/**
	 * 在购物车删除商品
	 */
	public Object delete(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String method = req.getMethod();
		if (!"POST".equalsIgnoreCase(method)) {
			req.setAttribute("message", "Method : " + method
					+ " is not supported");
			req.getRequestDispatcher("/input.jsp").forward(req, resp);
		}
		int product = Integer.parseInt(req.getParameter("product"));
		int count = Integer.parseInt(req.getParameter("count"));

		Cart cart = new Cart();
		cart.setProduct(product);
		cart.setCount(count);
		ModelAttribute ma = cartService.delete(cart, req.getSession());
		return ma.get();
	}

	/**
	 * 在购物车更新商品
	 */
	public Object update(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String method = req.getMethod();
		if (!"POST".equalsIgnoreCase(method)) {
			req.setAttribute("message", "Method : " + method
					+ " is not supported");
			req.getRequestDispatcher("/input.jsp").forward(req, resp);
		}
		int product = Integer.parseInt(req.getParameter("product"));
		int count = Integer.parseInt(req.getParameter("count"));

		Cart cart = new Cart();
		cart.setProduct(product);
		cart.setCount(count);
		ModelAttribute ma = cartService.update(cart, req.getSession());
		return ma.get();
	}

	/**
	 * 显示购物车的信息
	 */
	public Object displayCart(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String method = req.getMethod();
		if (!"POST".equalsIgnoreCase(method)) {
			req.setAttribute("message", "Method : " + method
					+ " is not supported");
			req.getRequestDispatcher("/input.jsp").forward(req, resp);
		}
		int userId = Integer.parseInt(req.getParameter("userId"));

		ModelAttribute ma = cartService.getByUser(userId, req.getSession());
		return ma.get();
	}

}
