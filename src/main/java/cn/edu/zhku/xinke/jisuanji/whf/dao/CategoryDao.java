package cn.edu.zhku.xinke.jisuanji.whf.dao;

import java.util.List;

import cn.edu.zhku.xinke.jisuanji.whf.model.Category;
import cn.edu.zhku.xinke.jisuanji.whf.util.JdbcAction;
import cn.edu.zhku.xinke.jisuanji.whf.util.JdbcUtil;
import cn.edu.zhku.xinke.jisuanji.whf.util.TxConstructor;

public class CategoryDao {

	/**
	 * 单例实现
	 * */
	private static CategoryDao instance = new CategoryDao();

	private CategoryDao() {
	}

	public static CategoryDao getInstance() {
		return instance;
	}

	private JdbcUtil jdbcUtil = JdbcUtil.getInstance();

	public int delete(int id) {
		return delete(id, null);
	}

	public int delete(int id, TxConstructor tc) {
		String sql = "delete from category where id=?";
		JdbcAction action = new JdbcAction(sql, id);
		if (tc != null) {
			tc.addAction(action);
			return 0;
		}
		return jdbcUtil.execute(action);
	}

	public int save(Category category) {
		return save(category,null);

	}

	public int save(Category category, TxConstructor tc) {
		String sql = "insert into category (name) values (?)";
		Object[] params = new Object[]{
				category.getName()
		};
		JdbcAction action = new JdbcAction(sql,params);
		if(tc != null){
			tc.addAction(action);
			return 0;
		}
		return jdbcUtil.execute(action);
	}
	
	public List<Category> getAll(){
		String sql = "select * from category";
		JdbcAction action = new JdbcAction(sql);
		return jdbcUtil.queryList(action, Category.class);
	}

}
