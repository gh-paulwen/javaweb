package cn.edu.zhku.xinke.jisuanji.whf.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.zhku.xinke.jisuanji.whf.dto.ModelAttribute;
import cn.edu.zhku.xinke.jisuanji.whf.model.Cart;
import cn.edu.zhku.xinke.jisuanji.whf.service.CartService;

public class CartServlet extends BaseServlet{

	private static final long serialVersionUID = 5929568505467103354L;
	
	private CartService  cartService=CartService.getInstance();
	
	/**
	 * 添加商品到购物车
	 */
	public String saveCart(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String method = req.getMethod();
		if(!"POST".equalsIgnoreCase(method)){
			req.setAttribute("message", "Method : " + method + " is not supported");
			req.getRequestDispatcher("/input.jsp").forward(req,resp);
		}
		int product=Integer.parseInt(req.getParameter("product"));
		int count=Integer.parseInt(req.getParameter("count"));
		
		Cart cart=new Cart();
		cart.setProduct(product);
		cart.setCount(count);
		ModelAttribute ma=cartService.save(cart, req.getSession());
		ma.pollute(req);
		return ma.getDestination();
	}
	
	/**
	 * 在购物车删除商品
	 */
	public String deleteCart(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String method = req.getMethod();
		if(!"POST".equalsIgnoreCase(method)){
			req.setAttribute("message", "Method : " + method + " is not supported");
			req.getRequestDispatcher("/input.jsp").forward(req,resp);
		}
		int user=Integer.parseInt(req.getParameter("user"));
		int product=Integer.parseInt(req.getParameter("product"));
		int count=Integer.parseInt(req.getParameter("count"));
		
		Cart cart=new Cart();
		cart.setUser(user);
		cart.setProduct(product);
		cart.setCount(count);
		ModelAttribute ma=cartService.delete(cart, req.getSession());
		ma.pollute(req);
		return ma.getDestination();
	}
	
	/**
	 * 在购物车更新商品
	 */
	public String updateCart(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String method = req.getMethod();
		if(!"POST".equalsIgnoreCase(method)){
			req.setAttribute("message", "Method : " + method + " is not supported");
			req.getRequestDispatcher("/input.jsp").forward(req,resp);
		}
		int product=Integer.parseInt(req.getParameter("product"));
		int count=Integer.parseInt(req.getParameter("countShop"));
		
		Cart cart=new Cart();
		cart.setProduct(product);
		cart.setCount(count);
		ModelAttribute ma=cartService.update(cart, req.getSession());
		ma.pollute(req);
		return ma.getDestination();
	}
	
	/**
	 * 显示购物车的信息
	 */
	public String displayCart(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String method = req.getMethod();
		if(!"POST".equalsIgnoreCase(method)){
			req.setAttribute("message", "Method : " + method + " is not supported");
			req.getRequestDispatcher("/input.jsp").forward(req,resp);
		}
		int userId=Integer.parseInt(req.getParameter("userId"));
		
		ModelAttribute ma=cartService.getByUser(userId, req.getSession());
		ma.pollute(req);
		return ma.getDestination();
	}
}
