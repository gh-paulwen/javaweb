package cn.edu.zhku.xinke.jisuanji.whf.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.zhku.xinke.jisuanji.whf.service.CityService;

public class CityJsonServlet extends JsonServlet{

	private static final long serialVersionUID = 793340513811575881L;
	
	private CityService cityService = CityService.getInstance();
	
	protected Object getProvince(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String method = req.getMethod();
		if(!"GET".equalsIgnoreCase(method)){
			req.getRequestDispatcher("/input.jsp").forward(req, resp);
			return null;
		}
		return cityService.getProvince().get();
	}
	
	protected Object getCity(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String method = req.getMethod();
		if(!"GET".equalsIgnoreCase(method)){
			req.getRequestDispatcher("/input.jsp").forward(req, resp);
			return null;
		}
		
		String pStr = req.getParameter("province");
		int province = Integer.parseInt(pStr);
		
		return cityService.getCity(province).get();
	}

}
