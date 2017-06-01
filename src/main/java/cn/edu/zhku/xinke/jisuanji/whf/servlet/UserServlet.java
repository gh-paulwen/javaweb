package cn.edu.zhku.xinke.jisuanji.whf.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.zhku.xinke.jisuanji.whf.dto.ModelAttribute;
import cn.edu.zhku.xinke.jisuanji.whf.model.User;
import cn.edu.zhku.xinke.jisuanji.whf.service.UserService;

/**
 * 
 * */
public class UserServlet extends BaseServlet{

	private static final long serialVersionUID = 7412784119817382692L;
	
	private UserService userService = UserService.getInstance();
	
	/**
	 * 登录
	 * 
	 * */
	protected String login(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//POST请求
		String method = req.getMethod();
		if(!"POST".equalsIgnoreCase(method)){
			req.setAttribute("message", "Method : " + method + " is not supported");
			req.getRequestDispatcher("/input.jsp").forward(req,resp);
		}
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		
		ModelAttribute ma = userService.login(username,password,req.getSession());
		ma.pollute(req);
		return ma.getDestination();
	}
	
	/**
	 * 注册
	 * */
	protected String register(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String method = req.getMethod();
		if(!"POST".equalsIgnoreCase(method)){
			req.setAttribute("message", "Method : " + method + " is not supported");
			req.getRequestDispatcher("/input.jsp").forward(req,resp);
		}
		
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		String email = req.getParameter("email");
		
		User user = new User();
		user.setName(username);
		user.setPassword(password);
		user.setEmail(email);
		ModelAttribute ma = userService.register(user);
		ma.pollute(req);
		return ma.getDestination();
	}
	
	/**
	 * 获取个人信息
	 * 
	 * */
	protected String getInfo(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String method = req.getMethod();
		if(!"GET".equalsIgnoreCase(method)){
			req.setAttribute("message", "Method : " + method + " is not supported");
			req.getRequestDispatcher("/input.jsp").forward(req,resp);
		}
		ModelAttribute ma = userService.checkInfo(req.getSession());
		ma.pollute(req);
		return ma.getDestination();
	}
	
}
