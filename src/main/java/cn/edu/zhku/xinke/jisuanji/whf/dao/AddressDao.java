package cn.edu.zhku.xinke.jisuanji.whf.dao;

import java.util.List;
import java.util.Map;

import cn.edu.zhku.xinke.jisuanji.whf.model.Address;
import cn.edu.zhku.xinke.jisuanji.whf.util.JdbcAction;
import cn.edu.zhku.xinke.jisuanji.whf.util.JdbcUtil;
import cn.edu.zhku.xinke.jisuanji.whf.util.TxConstructor;

public class AddressDao {
	
	private static AddressDao instance = new AddressDao();
	
	private AddressDao(){}
	
	public static AddressDao getInstance(){
		return instance;
	}
	
	private JdbcUtil jdbcUtil = JdbcUtil.getInstance();
	
	public int save(Address address){
		
		return save(address , null);
		
	}
	
	public int save(Address address , TxConstructor tc){
		
		String sql = "insert into address (region,verboseAddress,user,receiverName,receiverPhone) values (?,?,?,?,?)";
		Object[] params = new Object[]{
				address.getRegion(),
				address.getVerboseAddress(),
				address.getUser(),
				address.getReceiverName(),
				address.getReceiverPhone()
		};
		JdbcAction action = new JdbcAction(sql,params);
		
		if(tc != null){
			tc.addAction(action);
			return 0;
		}
		
		return jdbcUtil.execute(action);
		
	}
	
	public int delete(int id,int user){
		return delete(id,user,null);
	}
	
	public int delete(int id,int user,TxConstructor tc){
		String sql = "delete from address where id=? and user=?";
		JdbcAction action = new JdbcAction(sql,id,user);
		if(tc != null){
			tc.addAction(action);
			return 0;
		}
		
		return jdbcUtil.execute(action);
	}
	
	public int update(Address address){
		return update(address,null);
	}
	
	public int update(Address address,TxConstructor tc){
//		String sql = "update address set region=?,verboseAddress=?,user=? where id=?";
		String sql = "update address set region=?,verboseAddress=?,receiverName=?,receiverPhone=? where id=? and user=?";
		Object[] params = new Object[]{
				address.getRegion(),
				address.getVerboseAddress(),
				address.getReceiverName(),
				address.getReceiverPhone(),
				address.getId(),
				address.getUser()
		};
		
		JdbcAction action = new JdbcAction(sql,params);
		if(tc != null){
			tc.addAction(action);
			return 0;
		}
		return jdbcUtil.execute(action);	
	}
	
	public List<Address> getByUser(int user){
		String sql = "select * from address where user = ?";
		JdbcAction action = new JdbcAction(sql,user);
		return jdbcUtil.queryList(action, Address.class);
	}
	
	public List<Map<String,Object>> getVerboseByUser(int user){
		String sql = "select address.id,concat(c2.name,' ',c1.name) as region,verboseAddress,receiverName,receiverPhone from address,city as c1,city as c2 where address.region=c1.id and c1.superCity=c2.id and user = ?";
		JdbcAction action = new JdbcAction(sql,user);
		return jdbcUtil.queryMap(action);
	}
	
	public Address get(int id){
		String sql = "select * from address where id = ?";
		JdbcAction action = new JdbcAction(sql,id);
		return jdbcUtil.query(action, Address.class);
	}
	
}
