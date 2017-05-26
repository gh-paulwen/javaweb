package cn.edu.zhku.xinke.jisuanji.whf.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.zhku.xinke.jisuanji.whf.dto.ModelAttribute;
import cn.edu.zhku.xinke.jisuanji.whf.service.CollectService;

public class CollectServlet extends BaseServlet{

	private static final long serialVersionUID = -6829456413633561234L;
	
	private CollectService collectService = CollectService.getInstance();
	
	protected String collect(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String method = req.getMethod();
		if(!"GET".equalsIgnoreCase(method)){
			req.setAttribute("message", "method : " + method + " not supported");
			req.getRequestDispatcher("/input.jsp").forward(req, resp);
			return null;
		}
		
		int product = Integer.parseInt(req.getParameter("product"));
		ModelAttribute ma = collectService.collect(product, req.getSession());
		ma.pollute(req);
		return ma.getDestination();
	}
	
	protected String delete(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String method = req.getMethod();
		if(!"GET".equalsIgnoreCase(method)){
			req.setAttribute("message", "method : " + method + " not supported");
			req.getRequestDispatcher("/input.jsp").forward(req, resp);
			return null;
		}
		
		int product = Integer.parseInt(req.getParameter("product"));
		ModelAttribute ma = collectService.delete(product, req.getSession());
		ma.pollute(req);
		return ma.getDestination();
	}
	
	protected String getByUser(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String method = req.getMethod();
		if(!"GET".equalsIgnoreCase(method)){
			req.setAttribute("message", "method : " + method + " not supported");
			req.getRequestDispatcher("/input.jsp").forward(req, resp);
			return null;
		}
		
		ModelAttribute ma = collectService.getByUser(req.getSession());
		ma.pollute(req);
		return ma.getDestination();
	}
}
