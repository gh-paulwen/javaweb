package cn.edu.zhku.xinke.jisuanji.whf.servlet.json;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.zhku.xinke.jisuanji.whf.dto.ModelAttribute;
import cn.edu.zhku.xinke.jisuanji.whf.service.CollectService;

public class CollectJsonServlet extends JsonServlet{

	private static final long serialVersionUID = 2095440840376333017L;

	private CollectService collectService = CollectService.getInstance();
	
	protected Object collect(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String method = req.getMethod();
		if(!"GET".equalsIgnoreCase(method)){
			req.setAttribute("message", "method : " + method + " not supported");
			req.getRequestDispatcher("/input.jsp").forward(req, resp);
			return null;
		}
		
		int product = Integer.parseInt(req.getParameter("product"));
		ModelAttribute ma = collectService.collect(product, req.getSession());
		return ma.get();
	}
	
	protected Object delete(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String method = req.getMethod();
		if(!"GET".equalsIgnoreCase(method)){
			req.setAttribute("message", "method : " + method + " not supported");
			req.getRequestDispatcher("/input.jsp").forward(req, resp);
			return null;
		}
		
		int product = Integer.parseInt(req.getParameter("product"));
		ModelAttribute ma = collectService.delete(product, req.getSession());
		return ma.get();
	}
	
	protected Object getByUser(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String method = req.getMethod();
		if(!"GET".equalsIgnoreCase(method)){
			req.setAttribute("message", "method : " + method + " not supported");
			req.getRequestDispatcher("/input.jsp").forward(req, resp);
			return null;
		}
		
		ModelAttribute ma = collectService.getByUser(req.getSession());
		return ma.get();
	}
}
