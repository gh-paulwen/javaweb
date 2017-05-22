package cn.edu.zhku.xinke.jisuanji.whf.dao;

import org.junit.Test;

import cn.edu.zhku.xinke.jisuanji.whf.model.User;

public class UserDaoTest {
	
	private UserDao userDao = UserDao.getInstance();
	
	@Test
	public void testSave(){
		User user = new User();
		user.setName("Henry");
		userDao.save(user);
	}
	
	@Test
	public void testGet(){
		int id = 9;
		User user = userDao.get(id);
		System.out.println(user);
	}

}
