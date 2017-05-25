package cn.edu.zhku.xinke.jisuanji.whf.dao;

import java.util.Date;
import java.util.List;

import cn.edu.zhku.xinke.jisuanji.whf.model.Order;
import cn.edu.zhku.xinke.jisuanji.whf.util.JdbcAction;
import cn.edu.zhku.xinke.jisuanji.whf.util.JdbcUtil;
import cn.edu.zhku.xinke.jisuanji.whf.util.TxConstructor;

public class OrderDao {
	
	private static OrderDao instance = new OrderDao();
	
	private OrderDao(){}
	
	public static OrderDao getInstance(){
		return instance;
	}
	
	private JdbcUtil jdbcUtil = JdbcUtil.getInstance();
	
	public int save(Order order){
		return save(order,null);
	}
	
	public int save(Order order,TxConstructor tc){
		String sql = "insert into order (user,store,address,createDate,status) values (?,?,?,?,?)";
		Object[] params = new Object[]{
				order.getUser(),
				order.getStore(),
				order.getAddress(),
				new Date(),
				Order.Status.UNPAID.name()
		};
		JdbcAction action = new JdbcAction(sql,params);
		if(tc != null){
			tc.addAction(action);
			return 0;
		}
		return jdbcUtil.execute(action);
	}
	
	public int update(Order order){
		return update(order,null);
	}
	
	public int update(Order order,TxConstructor tc){
		String sql = "update order set status = ? where id=?";
		Object[] params = new Object[]{
				order.getStatus(),order.getId()
		};
		JdbcAction action = new JdbcAction(sql,params);
		if(tc != null){
			tc.addAction(action);
			return 0;
		}
		return jdbcUtil.execute(action);
	}
	
	public List<Order> getByCustomer(int cust){
		String sql = "select * from order where user = ? order by createDate desc";
		JdbcAction action = new JdbcAction(sql,cust);
		return jdbcUtil.queryList(action, Order.class);
	}
	
	public List<Order> getByStore(int store){
		String sql = "select * from order where store = ? order by createDate desc";
		JdbcAction action = new JdbcAction(sql,store);
		return jdbcUtil.queryList(action, Order.class);
	}

}
