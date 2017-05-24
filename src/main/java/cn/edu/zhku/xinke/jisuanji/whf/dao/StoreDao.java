package cn.edu.zhku.xinke.jisuanji.whf.dao;

import java.util.Date;
import java.util.List;

import cn.edu.zhku.xinke.jisuanji.whf.model.Store;
import cn.edu.zhku.xinke.jisuanji.whf.util.JdbcAction;
import cn.edu.zhku.xinke.jisuanji.whf.util.JdbcUtil;
import cn.edu.zhku.xinke.jisuanji.whf.util.TxConstructor;

public class StoreDao {

	private static StoreDao instance = new StoreDao();

	private StoreDao() {
	}

	public static StoreDao getInstance() {
		return instance;
	}

	private JdbcUtil jdbcUtil = JdbcUtil.getInstance();

	public int delete(int id, int owner) {
		return delete(id, owner, null);
	}

	public int delete(int id, int owner, TxConstructor tc) {
		String sql = "delete from store where id=? and owner = ?";
		JdbcAction action = new JdbcAction(sql, id, owner);

		if (tc != null) {
			tc.addAction(action);
			return 0;
		}

		return jdbcUtil.execute(action);
	}

	public int save(Store store) {
		return save(store, null);
	}

	public int save(Store store, TxConstructor tc) {
		String sql = "insert into store (name,owner,registerDate,region) values (?,?,?,?)";
		Object[] params = new Object[] { store.getName(), store.getOwner(),
				new Date(), store.getRegion() };

		JdbcAction action = new JdbcAction(sql, params);

		if (tc != null) {
			tc.addAction(action);
			return 0;
		}

		return jdbcUtil.execute(action);
	}

	public int update(Store store) {
		return update(store, null);
	}

	public int update(Store store, TxConstructor tc) {
		String sql = "update store set name = ?,region = ? where id=? and owner = ?";
		Object[] params = new Object[] { store.getName(), store.getRegion(),
				store.getId(), store.getOwner() };
		JdbcAction action = new JdbcAction(sql, params);

		if (tc != null) {
			tc.addAction(action);
			return 0;
		}

		return jdbcUtil.execute(action);

	}

	public Store getById(int id) {
		String sql = "select * from store where id = ?";
		JdbcAction action = new JdbcAction(sql, id);

		return jdbcUtil.query(action, Store.class);

	}
	
	public List<Store> getByUser(int user){
		String sql = "select * from store where owner=?";
		
		JdbcAction action = new JdbcAction(sql,user);
		return jdbcUtil.queryList(action, Store.class);
	}
	
	public List<Store> getByName(String name){
		String sql = "select * from store where name like '%" + name +"%'" ;
		JdbcAction action = new JdbcAction(sql);
		return jdbcUtil.queryList(action, Store.class);
	}

}
