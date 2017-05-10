package cn.edu.zhku.xinke.jisuanji.whf.util;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import cn.edu.zhku.xinke.jisuanji.whf.dao.handler.ObjectHandler;
import cn.edu.zhku.xinke.jisuanji.whf.dao.handler.ObjectListHandler;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class JdbcUtil {

	//singleton
	private JdbcUtil(){}
	private static JdbcUtil instance = new JdbcUtil();
	public static JdbcUtil getInstance(){
		return instance;
	}
	
	private ObjectHandler objectHandler = new ObjectHandler();
	private ObjectListHandler objectListHandler = new ObjectListHandler();
	
	private ComboPooledDataSource dataSource = new ComboPooledDataSource();
	
	QueryRunner queryRunner ;
	
	{
		InputStream input = JdbcUtil.class.getClassLoader().getResourceAsStream("jdbc.properties");
		Properties prop = new Properties();
		
		try {
			prop.load(input);
			input.close();
			dataSource.setDriverClass(prop.getProperty("jdbc.driverClass"));
			dataSource.setJdbcUrl(prop.getProperty("jdbc.url"));
			dataSource.setUser(prop.getProperty("jdbc.user"));
			dataSource.setPassword(prop.getProperty("jdbc.password"));
			dataSource.setMaxIdleTime(Integer.valueOf(prop.getProperty("maxIdleTime")));
			dataSource.setMaxPoolSize(Integer.valueOf(prop.getProperty("maxPoolSize")));
		} catch (PropertyVetoException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		queryRunner = new QueryRunner(dataSource);
	}
	
	public Connection getConnection(){
		Connection conn = null;
		try {
			 conn = dataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return conn;
	}
	
	public int execute(String sql,Object... params){
		int res = 0;
		try {
			res = queryRunner.update(sql, params);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}
	
	public int execute(JdbcAction action){
		if(action == null) throw new NullPointerException("action can not be null");
		int res = execute(action.getSql(),action.getParams());
		return res;
	}
	
	//事务版execute
	public int execute(List<JdbcAction> actions){
		if(actions == null|| actions.size() < 1) throw new NullPointerException();
		Connection conn = null;
		try{
			conn = dataSource.getConnection();
			conn.setAutoCommit(false);
			for(int i=0;i<actions.size();i++){
				JdbcAction action = actions.get(i);
				queryRunner.update(conn, action.getSql(),action.getParams());
			}
			conn.commit();
		}catch(SQLException e){
			try{
				if(conn != null){
					conn.rollback();
					conn.close();
				}
			}catch(SQLException e1){
				e1.printStackTrace();
			}
			
			e.printStackTrace();
		}		
		return 0;
	}
	
	public <T> T query(JdbcAction action,Class<T> c){
		T t = null;
		try {
			t = queryRunner.query(action.getSql(), new BeanHandler<T>(c), action.getParams());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return t;
	}
	
	public <T> List<T> queryList(JdbcAction action,Class<T> c){
		List<T> list = Collections.emptyList();
		try {
			list = queryRunner.query(action.getSql(), new BeanListHandler<T>(c), action.getParams());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public Object queryPrimitive(JdbcAction action){
		Object obj = null;
		try {
			obj = queryRunner.query(action.getSql(), objectHandler , action.getParams());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return obj;
	}
	
	public List<Object> queryPrimitiveList(JdbcAction action){
		List<Object> list = Collections.emptyList();
		try {
			list = queryRunner.query(action.getSql(), objectListHandler, action.getParams());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
}
