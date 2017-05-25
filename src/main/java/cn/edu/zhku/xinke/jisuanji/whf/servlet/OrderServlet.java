package cn.edu.zhku.xinke.jisuanji.whf.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.zhku.xinke.jisuanji.whf.dto.ModelAttribute;
import cn.edu.zhku.xinke.jisuanji.whf.service.OrderService;

public class OrderServlet extends BaseServlet{

	private static final long serialVersionUID = -6667496448672282391L;
	
	private OrderService orderService = OrderService.getInstance();
	
	protected String cancel(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String method = req.getMethod();
		if(!"POST".equalsIgnoreCase(method)){
			req.setAttribute("message", "method : " + method + " not supported");
			req.getRequestDispatcher("/input.jsp").forward(req, resp);
			return null;
		}
		
		int orderid = Integer.parseInt(req.getParameter("order"));
		ModelAttribute ma = orderService.cancel(orderid, req.getSession());
		ma.pollute(req);
		return ma.getDestination();
	}
	
	protected String send(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String method = req.getMethod();
		if(!"POST".equalsIgnoreCase(method)){
			req.setAttribute("message", "method : " + method + " not supported");
			req.getRequestDispatcher("/input.jsp").forward(req, resp);
			return null;
		}
		
		int orderid = Integer.parseInt(req.getParameter("order"));
		int storeid = Integer.parseInt(req.getParameter("storeid"));
		
		ModelAttribute ma = orderService.send(orderid, storeid, req.getSession());
		ma.pollute(req);
		return ma.getDestination();
	}
	
	protected String receive(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String method = req.getMethod();
		if(!"POST".equalsIgnoreCase(method)){
			req.setAttribute("message", "method : " + method + " not supported");
			req.getRequestDispatcher("/input.jsp").forward(req, resp);
			return null;
		}
		
		int orderid = Integer.parseInt(req.getParameter("order"));
		ModelAttribute ma = orderService.receive(orderid, req.getSession());
		ma.pollute(req);
		return ma.getDestination();
	}
	
	protected String save(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String method = req.getMethod();
		if(!"POST".equalsIgnoreCase(method)){
			req.setAttribute("message", "method : " + method + " not supported");
			req.getRequestDispatcher("/input.jsp").forward(req, resp);
			return null;
		}
		String json = req.getParameter("json");
		ModelAttribute ma = orderService.save(json, req.getSession());
		ma.pollute(req);
		return ma.getDestination();
	}

}
