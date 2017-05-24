package cn.edu.zhku.xinke.jisuanji.whf.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.zhku.xinke.jisuanji.whf.dto.ModelAttribute;
import cn.edu.zhku.xinke.jisuanji.whf.service.UserTypeService;

public class UserTypeJsonServlet extends JsonServlet{

	private static final long serialVersionUID = 8194713178394955997L;
	
	private UserTypeService userTypeService = UserTypeService.getInstance();
	
	protected Object getAll(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		String method = req.getMethod();
		if(!"GET".equalsIgnoreCase(method)){
			req.setAttribute("message", "method : " + method + " not supported");
			req.getRequestDispatcher("/input.jsp").forward(req, resp);
			return null;
		}
		
		ModelAttribute ma = userTypeService.getAll();
		return ma.get();
	}

}
