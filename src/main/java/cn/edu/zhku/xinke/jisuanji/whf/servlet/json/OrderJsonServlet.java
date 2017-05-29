package cn.edu.zhku.xinke.jisuanji.whf.servlet.json;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.zhku.xinke.jisuanji.whf.dto.ModelAttribute;
import cn.edu.zhku.xinke.jisuanji.whf.service.OrderService;

public class OrderJsonServlet extends JsonServlet {

	private static final long serialVersionUID = 3086746953092549398L;

	private OrderService orderService = OrderService.getInstance();

	protected Object cancel(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String method = req.getMethod();
		if (!"POST".equalsIgnoreCase(method)) {
			req.setAttribute("message", "method : " + method + " not supported");
			req.getRequestDispatcher("/input.jsp").forward(req, resp);
			return null;
		}

		int orderid = Integer.parseInt(req.getParameter("order"));
		ModelAttribute ma = orderService.cancel(orderid, req.getSession());
		return ma.get();
	}

	protected Object send(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String method = req.getMethod();
		if (!"POST".equalsIgnoreCase(method)) {
			req.setAttribute("message", "method : " + method + " not supported");
			req.getRequestDispatcher("/input.jsp").forward(req, resp);
			return null;
		}

		int orderid = Integer.parseInt(req.getParameter("order"));
		int storeid = Integer.parseInt(req.getParameter("storeid"));

		ModelAttribute ma = orderService.send(orderid, storeid,req.getSession());
		return ma.get();
	}

	protected Object receive(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String method = req.getMethod();
		if (!"POST".equalsIgnoreCase(method)) {
			req.setAttribute("message", "method : " + method + " not supported");
			req.getRequestDispatcher("/input.jsp").forward(req, resp);
			return null;
		}

		int orderid = Integer.parseInt(req.getParameter("order"));
		ModelAttribute ma = orderService.receive(orderid, req.getSession());
		return ma.get();
	}

	protected Object save(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String method = req.getMethod();
		if (!"POST".equalsIgnoreCase(method)) {
			req.setAttribute("message", "method : " + method + " not supported");
			req.getRequestDispatcher("/input.jsp").forward(req, resp);
			return null;
		}
		String json = req.getParameter("json");
		ModelAttribute ma = orderService.save(json, req.getSession());
		return ma.get();
	}
	
	protected Object getByUser(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String method = req.getMethod();
		if (!"GET".equalsIgnoreCase(method)) {
			req.setAttribute("message", "method : " + method + " not supported");
			req.getRequestDispatcher("/input.jsp").forward(req, resp);
			return null;
		}
		ModelAttribute ma = orderService.getVerboseByCustomer(req.getSession());
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
		int storeid = Integer.parseInt(req.getParameter("store"));
		ModelAttribute ma = orderService.getVerboseByStore(storeid, req.getSession());
		return ma.get();
	}

}
