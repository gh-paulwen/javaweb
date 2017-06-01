package cn.edu.zhku.xinke.jisuanji.whf.servlet.json;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.edu.zhku.xinke.jisuanji.whf.dto.ModelAttribute;
import cn.edu.zhku.xinke.jisuanji.whf.model.User;
import cn.edu.zhku.xinke.jisuanji.whf.service.UserService;

public class UserJsonServlet extends JsonServlet{

	private static final long serialVersionUID = -8518087087770040745L;
	
private UserService userService = UserService.getInstance();
	
	/**
	 * 登录
	 * 
	 * */
	protected Object login(HttpServletRequest req, HttpServletResponse resp)
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
		return ma.get();
	}
	
	/**
	 * 注册
	 * */
	protected Object register(HttpServletRequest req, HttpServletResponse resp)
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
		return ma.get();
	}
	
	protected Object update(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String method = req.getMethod();
		if(!"POST".equalsIgnoreCase(method)){
			req.setAttribute("message", "Method : " + method + " is not supported");
			req.getRequestDispatcher("/input.jsp").forward(req,resp);
		}
		
		String username = req.getParameter("username");
		String email = req.getParameter("email");
		
		User user = new User();
		user.setName(username);
		user.setEmail(email);
		ModelAttribute ma = userService.update(user,req.getSession());
		return ma.get();
	}
	
	/**
	 * 获取个人信息
	 * 
	 * */
	protected Object getInfo(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String method = req.getMethod();
		if(!"GET".equalsIgnoreCase(method)){
			req.setAttribute("message", "Method : " + method + " is not supported");
			req.getRequestDispatcher("/input.jsp").forward(req,resp);
		}
		ModelAttribute ma = userService.checkInfo(req.getSession());
		return ma.get();
	}
	
	protected Object logout(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String method = req.getMethod();
		if(!"GET".equalsIgnoreCase(method)){
			req.setAttribute("message", "Method : " + method + " is not supported");
			req.getRequestDispatcher("/input.jsp").forward(req,resp);
		}
		ModelAttribute ma = new ModelAttribute();
		HttpSession session = req.getSession();
		session.removeAttribute(User.CURRENT_USER);
		ma.setAttribute("message", "退出成功");
		return ma.get();
	}
	
	protected Object getAll(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String method = req.getMethod();
		if(!"GET".equalsIgnoreCase(method)){
			req.setAttribute("message", "Method : " + method + " is not supported");
			req.getRequestDispatcher("/input.jsp").forward(req,resp);
		}
		HttpSession session = req.getSession();
		ModelAttribute ma = userService.getAll(session);
		return ma.get();
	}

}
