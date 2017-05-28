package cn.edu.zhku.xinke.jisuanji.whf.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import cn.edu.zhku.xinke.jisuanji.whf.model.Product;
import cn.edu.zhku.xinke.jisuanji.whf.util.JdbcAction;
import cn.edu.zhku.xinke.jisuanji.whf.util.JdbcUtil;
import cn.edu.zhku.xinke.jisuanji.whf.util.TxConstructor;

public class ProductDao {
	
	private static ProductDao instance = new ProductDao();
	
	private ProductDao(){}
	
	public static ProductDao getInstance(){
		return instance;
	}
	
	private JdbcUtil jdbcUtil = JdbcUtil.getInstance();
	
	public int save(Product product){
		return save(product,null);
	}
	
	public int save(Product product,TxConstructor tc){
		String sql = "insert into product (name,price,description,store,secCategory,createDate) values (?,?,?,?,?,?)";
		Object[] params = new Object[]{
				product.getName(),
				product.getPrice(),
				product.getDescription(),
				product.getStore(),
				product.getSecCategory(),
				new Date()
		};
		JdbcAction action = new JdbcAction(sql,params);
		
		if(tc != null){
			tc.addAction(action);
			return 0;
		}
		
		return jdbcUtil.execute(action);
	}
	
	public int delete(int id,int store){
		return delete(id,store,null);
	}
	
	public int delete(int id,int store,TxConstructor tc){
		String sql = "delete from product where id = ? and store = ?";
		JdbcAction action = new JdbcAction(sql,id,store);
		
		if(tc != null){
			tc.addAction(action);
			return 0;
		}
		
		return jdbcUtil.execute(action);
	}
	
	public int update(Product product){
		return update(product,null);
	}
	
	public int update(Product product,TxConstructor tc){
		String sql = "update product set name=?,price=?,pic=?,description=?,secCategory=? where id=? and store=?";
		Object[] params = new Object[]{
				product.getName(),
				product.getPrice(),
				product.getPic(),
				product.getDescription(),
				product.getSecCategory(),
				product.getId(),
				product.getStore()
		};
		JdbcAction action = new JdbcAction(sql,params);
		
		if(tc != null){
			tc.addAction(action);
			return 0;
		}
		return jdbcUtil.execute(action);
	}
	
	public List<Product> page(int from , int count){
		String sql = "select * from product order by createDate desc limit ?,?";
		JdbcAction action = new JdbcAction(sql,from,count);
		return jdbcUtil.queryList(action, Product.class);
	} 
	
	public List<Product> categoryPage(int category,int from , int count){
		String sql = "select * from product where secCategory in (select id from seccategory where category = ?) order by createDate desc limit ?,?";
		JdbcAction action = new JdbcAction(sql,category,from,count);
		return jdbcUtil.queryList(action, Product.class);
	}
	
	public List<Product> secCategoryPage(int secCategory,int from,int count){
		String sql = "select * from product where secCategory = ? order by createDate limit ?,?";
		JdbcAction action = new JdbcAction(sql,secCategory,from,count);
		return jdbcUtil.queryList(action, Product.class);
	}
	
	public List<Product> search(String keyword){
		String sql = "select * from product where name like '%" + keyword+"%' or description like '%" + keyword +"%' order by createDate desc";
		JdbcAction action = new JdbcAction(sql);
		return jdbcUtil.queryList(action, Product.class);
	}
	
	public Product getById(int id){
		String sql = "select * from product where id = ?";
		JdbcAction action = new JdbcAction(sql,id);
		return jdbcUtil.query(action, Product.class);
	}
	
	public List<Product> getByStore(int storeid){
		String sql = "select * from product where store = ?";
		JdbcAction action = new JdbcAction(sql,storeid);
		return jdbcUtil.queryList(action, Product.class);
	}
	
	public List<Product> getCollectProduct(int user){
		String sql = "select * from product where id in (select product from collect where user = ?)";
		JdbcAction action = new JdbcAction(sql,user);
		return jdbcUtil.queryList(action, Product.class);
	}
	
	public long getAvailableId(){
		String sql = "select max(id) + 1 as id from product";
		JdbcAction action = new JdbcAction(sql);
		return (long)jdbcUtil.queryPrimitive(action);
	}
	
	public long checkProductUser(int product,int user ){
		String sql = "select count(*) from product,store,user where product.id=? and user.id = ? and product.store = store.id and store.`owner` = user.id";
		JdbcAction action = new JdbcAction(sql,product,user);
		return (long) jdbcUtil.queryPrimitive(action);
	}
	
	public Map<String,Object> getVerbose(int id){
		String sql = "select product.id,trim(product.name) as productname,product.price,product.pic,product.description,product.createDate,trim(store.name) as storename,concat(category.name,' ' ,seccategory.name) as category from product , store , category , seccategory where product.store = store.id and product.secCategory = seccategory.id and seccategory.category = category.id and product.id = ?";
		JdbcAction action = new JdbcAction(sql,id);
		return jdbcUtil.queryMap(action);
	}
}
