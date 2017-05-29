package cn.edu.zhku.xinke.jisuanji.whf.dao;

import java.util.List;
import java.util.Map;

import cn.edu.zhku.xinke.jisuanji.whf.model.OrderDetail;
import cn.edu.zhku.xinke.jisuanji.whf.util.JdbcAction;
import cn.edu.zhku.xinke.jisuanji.whf.util.JdbcUtil;
import cn.edu.zhku.xinke.jisuanji.whf.util.TxConstructor;

public class OrderDetailDao {
	
	private OrderDetailDao (){}
	
	private static OrderDetailDao instance = new OrderDetailDao();
	
	public static OrderDetailDao getInstance(){
		return instance;
	}
	
	private JdbcUtil jdbcUtil = JdbcUtil.getInstance();
	
	public int save(OrderDetail od){
		return save(od,null);
	}

	public int save(OrderDetail od, TxConstructor tc) {
		String sql = "insert into orderdetail (`order`,product,count) values (?,?,?)";
		Object[] params = new Object[]{
				od.getOrder(),
				od.getProduct(),
				od.getCount()
		};
		
		JdbcAction action = new JdbcAction(sql,params);
		
		if(tc != null){
			tc.addAction(action);
			return 0;
		}
		return jdbcUtil.execute(action);
	}
	
	public List<OrderDetail> getByOrder(int order){
		String sql = "select * from orderDetail where `order` = ?";
		JdbcAction action = new JdbcAction(sql,order);
		return jdbcUtil.queryList(action, OrderDetail.class);
	}
	
	public List<Map<String,Object>> getVerboseByOrder(int order){
		String sql = "select orderdetail.order,orderdetail.count,product.name as productname,product.price,product.pic,product.description,product.secCategory as categoryid , concat(category.`name`,' ',seccategory.`name`) as category from `orderdetail`,product,seccategory,category where orderdetail.product = product.id and product.secCategory=seccategory.id and seccategory.category = category.id and  orderdetail.`order` = ?";
		
		JdbcAction action = new JdbcAction(sql,order);
		return jdbcUtil.queryMapList(action);
	}
	
}
