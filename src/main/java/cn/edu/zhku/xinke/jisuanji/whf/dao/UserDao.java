package cn.edu.zhku.xinke.jisuanji.whf.dao;

import cn.edu.zhku.xinke.jisuanji.whf.model.User;
import cn.edu.zhku.xinke.jisuanji.whf.util.JdbcAction;
import cn.edu.zhku.xinke.jisuanji.whf.util.JdbcUtil;

public class UserDao {
	
	private static UserDao instance = new UserDao();
	private UserDao(){}
	public static UserDao getInstance(){
		return instance;
	}
	
	private JdbcUtil jdbcUtil = JdbcUtil.getInstance();
	
	public void save(User user){
		String sql = "insert into user (name,age) values (?,?)";
		jdbcUtil.execute(sql,user.getName(),user.getAge());
	}
	
	public User get(int id){
		User user = null;
		String sql = "select * from user where id = ?";
		JdbcAction action = new JdbcAction(sql,id);
		user = jdbcUtil.query(action, User.class);
		return user;
	}

}
