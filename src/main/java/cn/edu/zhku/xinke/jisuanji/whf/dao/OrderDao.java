package cn.edu.zhku.xinke.jisuanji.whf.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

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
		String sql = "insert into `order`(id,user,store,address,createDate,status) values (?,?,?,?,?,?)";
		Object[] params = new Object[]{
				order.getId(),
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
		String sql = "update `order` set status = ? where id=?";
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
		String sql = "select * from `order` where user = ? order by createDate desc";
		JdbcAction action = new JdbcAction(sql,cust);
		return jdbcUtil.queryList(action, Order.class);
	}
	
	public List<Order> getByStore(int store){
		String sql = "select * from `order` where store = ? order by createDate desc";
		JdbcAction action = new JdbcAction(sql,store);
		return jdbcUtil.queryList(action, Order.class);
	}
	
	public int getAvailableId(){
		String sql = "select max(id) + 1 as id from `order`";
		JdbcAction action = new JdbcAction(sql);
		Object res =  jdbcUtil.queryPrimitive(action);
		long id = res == null ? 1l : (long) res;
		return Integer.parseInt(String.valueOf(id));
	}
	
	public List<Map<String,Object>> getVerboseListByCustomer(int user){
		String sql = "select `order`.id,`order`.user as userid,`order`.store as storeid,`order`.address as addressid,`order`.createDate,`order`.`status`,trim(user.`name`) as username,trim(store.`name`) as storename, address.verboseAddress ,address.receiverName,address.receiverPhone ,concat(c2.`name`,' ',c1.`name`)  as region from `order`,user,store,address,city as c1,city as c2 where `order`.user = user.id and `order`.store = store.id and `order`.address=address.id and address.region=c1.id and c1.superCity = c2.id and `order`.user = ?";
		JdbcAction action = new JdbcAction(sql,user);
		return jdbcUtil.queryMapList(action);
	}
	
	public List<Map<String,Object>> getVerboseListByStore(int store){
		String sql = "select `order`.id,`order`.user as userid,`order`.store as storeid,`order`.address as addressid,`order`.createDate,`order`.`status`,trim(user.`name`) as username,trim(store.`name`) as storename, address.verboseAddress ,address.receiverName,address.receiverPhone,concat(c2.`name`,' ',c1.`name`)  as region from `order`,user,store,address,city as c1,city as c2 where `order`.user = user.id and `order`.store = store.id and `order`.address=address.id and address.region=c1.id and c1.superCity = c2.id and `order`.store = ?";
		JdbcAction action = new JdbcAction(sql,store);
		return jdbcUtil.queryMapList(action);
	}
	
	public Map<String,Object> getVerboseById(int id){
		String sql = "select `order`.id,`order`.user as userid,`order`.store as storeid,`order`.address as addressid,`order`.createDate,`order`.`status`,trim(user.`name`) as username,trim(store.`name`) as storename, address.verboseAddress ,address.receiverName,address.receiverPhone,concat(c2.`name`,' ',c1.`name`)  as region from `order`,user,store,address,city as c1,city as c2 where `order`.user = user.id and `order`.store = store.id and `order`.address=address.id and address.region=c1.id and c1.superCity = c2.id and `order`.id = ?";
		JdbcAction action = new JdbcAction(sql,id);
		return jdbcUtil.queryMap(action);
	}

}
