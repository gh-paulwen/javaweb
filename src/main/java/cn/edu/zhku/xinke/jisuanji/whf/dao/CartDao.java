package cn.edu.zhku.xinke.jisuanji.whf.dao;

import cn.edu.zhku.xinke.jisuanji.whf.util.JdbcAction;
import cn.edu.zhku.xinke.jisuanji.whf.util.JdbcUtil;
import cn.edu.zhku.xinke.jisuanji.whf.util.TxConstructor;

import java.util.List;

import cn.edu.zhku.xinke.jisuanji.whf.model.Cart;

/**
 * @author aScholars
 * */
public class CartDao {
	/**
	 * 单例实现
	 **/
	private static CartDao instance = new CartDao();
	private CartDao(){}
	public static CartDao getInstance(){
		return instance;
	}
	
	private JdbcUtil jdbcUtil = JdbcUtil.getInstance();
	
	/**
	 * 保存商品到购物车
	 */
	public int save(Cart cart){
//		String sql="insert into cart values(?,?,?)";
		//return jdbcUtil.execute(sql,cart.getUserId(),cart.getProductId(),cart.getCount());
		return save(cart,null);
	}
	
	/**
	 * 事务版的保存商品购物车
	 */
	public int save(Cart cart,TxConstructor tc){
		String sql="insert into cart values(?,?,?)";
		Object[] params=new Object[]{
				cart.getUserId(),cart.getProductId(),cart.getCount()
		};
		JdbcAction action =new JdbcAction(sql,params);
		if(tc!=null){
			tc.addAction(action);
			return 0;
		}
		return jdbcUtil.execute(action);
	}
	
	/**
	 * 从购物车删除商品
	 */
	public int delete(Cart cart){
//		String sql="delete from cart where userId=? and productId=?";
		//return jdbcUtil.execute(sql, cart.getUserId(),cart.getProductId());
		return delete(cart,null);
	}
	
	/**
	 * 从购物车删除商品的事务版
	 */
	public int delete(Cart cart,TxConstructor tc){
		String sql="delete from cart where userId=? and productId=?";
		Object[] params=new Object[]{
				cart.getUserId(),cart.getProductId()
		};
		JdbcAction action=new JdbcAction(sql,params);
		if(tc!=null){
			tc.addAction(action);
			return 0;
		}
		return jdbcUtil.execute(action);
	}
	
	/**
	 * 更新购物车商品
	 */
	public int update(Cart cart){
//		String sql="update cart set count=? where userId=? and productId=?";
		//return jdbcUtil.execute(sql, cart.getUserId(),cart.getProductId(),cart.getCount());
		return update(cart,null);
	}
	
	/**
	 * 更新购物车商品的事务版
	 */
	public int update(Cart cart,TxConstructor tc){
		String sql="update cart set count=? where userId=? and productId=?";
		Object[] params=new Object[]{
				cart.getUserId(),cart.getProductId(),cart.getCount()
		};
		JdbcAction action=new JdbcAction(sql,params);
		if(tc!=null){
			tc.addAction(action);
			return 0;
		}
		return jdbcUtil.execute(action);
	}
	
	/**
	 * 用用户Id获取购物车
	 */
	
	public List<Cart> getByUser(int id){
		String sql="select * from cart where id=?";
		Object[] params=new Object[]{id};
		JdbcAction action=new JdbcAction(sql,params);
		return jdbcUtil.queryList(action,Cart.class);
	}
}
