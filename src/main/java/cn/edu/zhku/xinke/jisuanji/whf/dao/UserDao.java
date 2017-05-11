package cn.edu.zhku.xinke.jisuanji.whf.dao;

import cn.edu.zhku.xinke.jisuanji.whf.model.User;
import cn.edu.zhku.xinke.jisuanji.whf.util.JdbcAction;
import cn.edu.zhku.xinke.jisuanji.whf.util.JdbcUtil;
import cn.edu.zhku.xinke.jisuanji.whf.util.TxConstructor;

/**
 * @author Paul
 * 
 * temp
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
	public void save(User user){
//		String sql = "insert into user (name,age) values (?,?)";
//		jdbcUtil.execute(sql,user.getName(),user.getAge());
		save(user,null);
	}
	
	/**
	 * 事务版的保存用户
	 * */
	public void save(User user,TxConstructor tc){
		String sql = "insert into user (name,age) values (?,?)";
		Object[] params = new Object[]{
				user.getName(),
				user.getAge()
		};
		JdbcAction action = new JdbcAction(sql,params);
		if(tc != null){
			//添加到事务列表
			tc.addAction(action);
			return ;
		}
		jdbcUtil.execute(action);
	}
	
	public User get(int id){
		User user = null;
		String sql = "select * from user where id = ?";
		JdbcAction action = new JdbcAction(sql,id);
		user = jdbcUtil.query(action, User.class);
		return user;
	}

}
