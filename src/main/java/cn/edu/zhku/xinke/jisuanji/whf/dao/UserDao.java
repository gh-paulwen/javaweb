package cn.edu.zhku.xinke.jisuanji.whf.dao;

import java.util.Date;
import java.util.List;

import cn.edu.zhku.xinke.jisuanji.whf.model.User;
import cn.edu.zhku.xinke.jisuanji.whf.util.JdbcAction;
import cn.edu.zhku.xinke.jisuanji.whf.util.JdbcUtil;
import cn.edu.zhku.xinke.jisuanji.whf.util.TxConstructor;

/**
 * @author Paul
 * 
 * user dao
 * */
public class UserDao {
	
	/**
	 * 单例实现
	 * */
	private static UserDao instance = new UserDao();
	private UserDao(){}
	public static UserDao getInstance(){
		return instance;
	}
	
	private JdbcUtil jdbcUtil = JdbcUtil.getInstance();
	
	/**
	 * 保存用户
	 * */
	public int save(User user){
//		String sql = "insert into user (name,age) values (?,?)";
//		jdbcUtil.execute(sql,user.getName(),user.getAge());
		return save(user,null);
	}
	
	/**
	 * 事务版的保存用户
	 * */
	public int save(User user,TxConstructor tc){
		String sql = "insert into user (name,password,type,email,registerDate) values (?,?,?,?,?)";
		Object[] params = new Object[]{
				user.getName(),user.getPassword(),user.getType(),user.getEmail(),new Date()
		};
		JdbcAction action = new JdbcAction(sql,params);
		if(tc != null){
			//添加到事务列表
			tc.addAction(action);
			return 0;
		}
		return jdbcUtil.execute(action);
	}
	
	/**
	 * 用id获取用户
	 * */
	public User get(int id){
		User user = null;
		String sql = "select * from user where id = ?";
		JdbcAction action = new JdbcAction(sql,id);
		user = jdbcUtil.query(action, User.class);
		return user;
	}
	
	/**
	 * 用用户名获取用户
	 * 
	 * */
	public User getByName(String name){
		User user = null;
		String sql = "select * from user where name=?";
		JdbcAction action = new JdbcAction(sql,name);
		user = jdbcUtil.query(action, User.class);
		return user;
	}
	
	/**
	 * 用邮箱获取用户
	 * */
	public User getByEmail(String email){
		User user = null;
		String sql = "select * from user where email=?";
		JdbcAction action = new JdbcAction(sql,email);
		user = jdbcUtil.query(action, User.class);
		return user;
	}
	
	/**
	 * 修改用户
	 * 
	 * */
	public int update(User user){
		String sql = "update user set name=?,password=?,type=?,status=?,email=? where id=?";
		Object[] params = new Object[]{
				user.getName(),user.getPassword(),user.getType(),user.getStatus(),user.getEmail(),user.getId()
		};
		JdbcAction action = new JdbcAction(sql,params);
		return jdbcUtil.execute(action);
	}
	
	/**
	 * 得到所有用户
	 * */
	public List<User> getAll(){
		String sql = "select * from user";
		JdbcAction action = new JdbcAction(sql);
		return jdbcUtil.queryList(action, User.class);
	}

}
