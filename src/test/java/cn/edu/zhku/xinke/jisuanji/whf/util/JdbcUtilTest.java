package cn.edu.zhku.xinke.jisuanji.whf.util;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import cn.edu.zhku.xinke.jisuanji.whf.model.User;
import cn.edu.zhku.xinke.jisuanji.whf.util.JdbcAction;
import cn.edu.zhku.xinke.jisuanji.whf.util.JdbcUtil;

public class JdbcUtilTest {

	JdbcUtil jdbcUtil = JdbcUtil.getInstance();

	@Test
	public void connectionTest() {
		Connection conn = jdbcUtil.getConnection();
		System.out.println(conn);
	}
	
	@Test
	public void saveTest(){
		String sql = "insert into user (name,age) values (?,?)";
		Object[] params = new Object[]{
				"Johnson",20
		};
		JdbcAction action = new JdbcAction(sql, params);
		jdbcUtil.execute(action);
	}
	
	@Test
	public void txsaveTest(){
		List<JdbcAction> actions = new ArrayList<>();
		String sql = "insert into user (name,age) values (?,?) ";
		Object[] params = new Object[]{
				"Neal",18
		};
		JdbcAction action = new JdbcAction(sql,params);
		actions.add(action);
		
		sql = "insert into user (name) values (?)";
		params = new Object[]{
				"Becky"
		};
		action = new JdbcAction(sql, params);
		actions.add(action);
		jdbcUtil.execute(actions);
	}
	
	@Test
	public void testQuery(){
//		String sql = "select * from user where id=?";
//		JdbcAction action = new JdbcAction(sql, 3);
//		User user = jdbcUtil.query(action, User.class);
//		System.out.println(user);
		
//		String sql = "select age from user where id=?";
//		JdbcAction action = new JdbcAction(sql, 3);
//		int age = (int) jdbcUtil.queryPrimitive(action);
//		System.out.println(age);
		
		String sql = "select age from user";
		JdbcAction action = new JdbcAction(sql);
		List<Object> list =  jdbcUtil.queryPrimitiveList(action);
		for(Object obj:list){
			int age = (int) obj;
			System.out.println(age);
		}
	}
	
}
