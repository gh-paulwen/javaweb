package cn.edu.zhku.xinke.jisuanji.whf.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.zhku.xinke.jisuanji.whf.model.User;
import cn.edu.zhku.xinke.jisuanji.whf.service.UserService;

/**
 * temp
 * @author Paul
 * 
 * */
public class UserServlet extends BaseServlet{

	private static final long serialVersionUID = 7412784119817382692L;
	
	private UserService userService = UserService.getInstance();
	
	protected String save(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		String username = req.getParameter("username");
		int age = Integer.valueOf(req.getParameter("age"));
		System.out.println(username);
		User user = new User();
		user.setName(username);
		user.setAge(age);
		userService.save(user);
		
		return null;
		
	}
	
	protected String get(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		int id = Integer.valueOf(req.getParameter("id"));
		User user = userService.get(id);
		String message = "hello , " + user.getName();
		req.setAttribute("message", message);
		return "forward:message.jsp";
	}
	
}
