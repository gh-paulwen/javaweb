package cn.edu.zhku.xinke.jisuanji.whf.service;

import cn.edu.zhku.xinke.jisuanji.whf.dao.UserDao;
import cn.edu.zhku.xinke.jisuanji.whf.model.User;
import cn.edu.zhku.xinke.jisuanji.whf.util.TxConstructor;

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
	
	public void txsave(){
		User user = new User();
		user.setName("Mary");
		user.setAge(18);
		TxConstructor tc = new TxConstructor();
		userDao.save(user, tc);
//		user = new User();
//		user.setName("Louis");
//		user.setAge(19);
		user.setName(null);
		userDao.save(user, tc);
		tc.commit();
	}

}
