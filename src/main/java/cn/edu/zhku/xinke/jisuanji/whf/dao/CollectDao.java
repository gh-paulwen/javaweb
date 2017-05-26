package cn.edu.zhku.xinke.jisuanji.whf.dao;

import java.util.List;

import cn.edu.zhku.xinke.jisuanji.whf.model.Collect;
import cn.edu.zhku.xinke.jisuanji.whf.util.JdbcAction;
import cn.edu.zhku.xinke.jisuanji.whf.util.JdbcUtil;
import cn.edu.zhku.xinke.jisuanji.whf.util.TxConstructor;

public class CollectDao {
	
	private static CollectDao instance = new CollectDao();
	
	private CollectDao(){}
	
	public static CollectDao getInstance(){
		return instance;
	}
	
	private JdbcUtil jdbcUtil = JdbcUtil.getInstance();
	
	public int save(Collect collect){
		return save(collect,null);
	}
	
	public int save(Collect collect,TxConstructor tc){
		String sql = "insert into collect (user,product) values (?,?)";
		Object[] params = new Object[]{
				collect.getUser(),
				collect.getProduct()
		};
		JdbcAction action = new JdbcAction(sql,params);
		
		if(tc != null){
			tc.addAction(action);
			return 0;
		}
		
		return jdbcUtil.execute(action);
	}
	
	public int delete(Collect collect){
		return delete(collect,null);
	}
	
	public int delete(Collect collect,TxConstructor tc){
		String sql = "delete from collect where user = ? and product = ?";
		Object[] params = new Object[]{
				collect.getUser(),
				collect.getProduct()
		};
		
		JdbcAction action = new JdbcAction(sql,params);
		
		if(tc != null){
			tc.addAction(action);
			return 0;
		}
		
		return jdbcUtil.execute(action);
	}
	
	public List<Collect> getByUser(int user){
		String sql = "select * from collect where user = ?";
		JdbcAction action = new JdbcAction(sql,user);
		return jdbcUtil.queryList(action, Collect.class);
	}
	
}
