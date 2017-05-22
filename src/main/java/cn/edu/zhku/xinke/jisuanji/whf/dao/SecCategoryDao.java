package cn.edu.zhku.xinke.jisuanji.whf.dao;

import java.util.List;

import cn.edu.zhku.xinke.jisuanji.whf.model.SecCategory;
import cn.edu.zhku.xinke.jisuanji.whf.util.JdbcAction;
import cn.edu.zhku.xinke.jisuanji.whf.util.JdbcUtil;
import cn.edu.zhku.xinke.jisuanji.whf.util.TxConstructor;

public class SecCategoryDao {
	
	/**
	 * 单例实现
	 * */
	private static SecCategoryDao instance = new SecCategoryDao();
	private SecCategoryDao(){}
	public static SecCategoryDao getInstance(){
		return instance;
	}
	
	private JdbcUtil jdbcUtil = JdbcUtil.getInstance();

	public int delete(int id) {
		return delete(id, null);
	}

	public int delete(int id, TxConstructor tc) {
		String sql = "delete from seccategory where id=?";
		JdbcAction action = new JdbcAction(sql, id);
		if (tc != null) {
			tc.addAction(action);
			return 0;
		}
		return jdbcUtil.execute(action);
	}

	public int save(SecCategory secCategory) {
		return save(secCategory,null);

	}

	public int save(SecCategory secCategory, TxConstructor tc) {
		String sql = "insert into seccategory (name,category) values (?,?)";
		Object[] params = new Object[]{
				secCategory.getName(),
				secCategory.getCategory()
		};
		JdbcAction action = new JdbcAction(sql,params);
		if(tc != null){
			tc.addAction(action);
			return 0;
		}
		return jdbcUtil.execute(action);
	}
	
	public List<SecCategory> getByCategory(int category){
		String sql = "select * from seccategory where category= ?";
		JdbcAction action = new JdbcAction(sql,category);
		return jdbcUtil.queryList(action, SecCategory.class);
	}

}
