package cn.edu.zhku.xinke.jisuanji.whf.service;

import java.util.regex.Pattern;

import javax.servlet.http.HttpSession;

import cn.edu.zhku.xinke.jisuanji.whf.dao.UserDao;
import cn.edu.zhku.xinke.jisuanji.whf.dto.ModelAttribute;
import cn.edu.zhku.xinke.jisuanji.whf.model.User;

/**
 * @author Paul
 * */
public class UserService {
	
	private static UserService instance = new UserService();
	private UserService(){}
	public static UserService getInstance(){
		return instance;
	}
	
	private UserDao userDao = UserDao.getInstance();
	
	/**
	 * 注册
	 * */
	public ModelAttribute register(User user){
		ModelAttribute ma = new ModelAttribute("forward:message.jsp");
		//6到16位合法用户名
		if(!Pattern.matches("^[a-zA-Z_][a-zA-Z0-9_]{5,15}$", user.getName())){
			//不合法
			ma.setAttribute("message", "用户名不符合6-16位字母下划线开头");
			ma.setDestination("forward:register.jsp");
			return ma;
		}
		if(user.getPassword() == null || user.getPassword().isEmpty()){
			//密码为空
			ma.setAttribute("message", "密码为空");
			ma.setDestination("forward:register.jsp");
			return ma;
		}
		if(!Pattern.matches("^[a-zA-Z_0-9]+@[a-zA-Z_0-9]+\\.[a-zA-Z_0-9]+$", user.getEmail())){
			//邮箱格式有误
			ma.setAttribute("message", "邮箱格式错误");
			ma.setDestination("forward:register.jsp");
			return ma;
		}
		User userGet = null;
		userGet = userDao.getByName(user.getName());
		if(userGet != null){
			//用户名重复
			ma.setAttribute("message", "用户名已被占用");
			ma.setDestination("forward:register.jsp");
			return ma;
		}
		userGet = userDao.getByEmail(user.getEmail());
		if(userGet != null){
			//邮箱重复
			ma.setAttribute("message", "邮箱已被注册");
			ma.setDestination("forward:register.jsp");
			return ma;
		}
		//默认顾客注册
		user.setType(1);
		userDao.save(user);
		ma.setAttribute("message", "注册成功");
		return ma;
	}
	
	public ModelAttribute login(String username,String password,HttpSession session){
		ModelAttribute ma = new ModelAttribute("redirect:index.jsp");
		//非空校验
		if(username == null || username.isEmpty() || password == null || password.isEmpty()){
			ma.setAttribute("message", "登录信息填写不完整");
			ma.setDestination("forward:login.jsp");
			return ma;
		}
		User userGet = null;
		userGet = userDao.getByName(username);
		if(userGet == null){
			userGet = userDao.getByEmail(username);
		}
		
		//用户名和邮箱都返回null
		if(userGet == null){
			ma.setAttribute("message", "用户不存在，请注册");
			ma.setDestination("forward:login.jsp");
			return ma;
		}
		
		String realPassword = userGet.getPassword();
		if(!realPassword.equals(password)){
			ma.setAttribute("message", "密码错误");
			ma.setDestination("forward:login.jsp");
			return ma;
		}
		//登录成功
		session.setAttribute(User.CURRENT_USER, userGet);
		ma.setAttribute("message", "登录成功");
		return ma;
	}
	
	/**
	 * 获取个人信息
	 * */
	public ModelAttribute checkInfo(HttpSession session){
		ModelAttribute ma = new ModelAttribute("forward:info.jsp");
		User curUser = (User) session.getAttribute(User.CURRENT_USER);
		if(curUser == null){
			ma.setDestination("forward:message.jsp");
			ma.setAttribute("message", "未登录");
			return ma;
		}
		ma.setAttribute("info", curUser);
		return ma;
	}
}
