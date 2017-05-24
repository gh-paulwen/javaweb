package cn.edu.zhku.xinke.jisuanji.whf.dao;

import java.util.List;

import cn.edu.zhku.xinke.jisuanji.whf.model.UserType;
import cn.edu.zhku.xinke.jisuanji.whf.util.JdbcAction;
import cn.edu.zhku.xinke.jisuanji.whf.util.JdbcUtil;

public class UserTypeDao {
	
	private static UserTypeDao instance = new UserTypeDao();
	
	private UserTypeDao(){}
	
	public static UserTypeDao getInstance (){
		return instance;
	}
	
	private JdbcUtil jdbcUtil = JdbcUtil.getInstance();
	
	public List<UserType> getAll(){
		String sql = "select * from userType";
		JdbcAction action = new JdbcAction(sql);
		return jdbcUtil.queryList(action, UserType.class);
	}

}
