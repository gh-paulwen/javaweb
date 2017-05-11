package cn.edu.zhku.xinke.jisuanji.whf.service;

import cn.edu.zhku.xinke.jisuanji.whf.dao.UserDao;
import cn.edu.zhku.xinke.jisuanji.whf.model.User;

/**
 * temp
 * @author Paul
 * */
public class UserService {
	
	private static UserService instance = new UserService();
	private UserService(){}
	public static UserService getInstance(){
		return instance;
	}
	
	private UserDao userDao = UserDao.getInstance();
	
	public void save(User user){
		userDao.save(user);
	}
	
	public User get(int id){
		return userDao.get(id);
	}

}
