package cn.edu.zhku.xinke.jisuanji.whf.util;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;

import cn.edu.zhku.xinke.jisuanji.whf.dao.handler.ObjectHandler;
import cn.edu.zhku.xinke.jisuanji.whf.dao.handler.ObjectListHandler;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * Jdbc工具，使用了c3p0，配置文件是类路径中的jdbc.properties
 * @author Paul 
 * */
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
	
	/**
	 * 执行增删改操作
	 * 
	 * @author Paul 
	 * @param sql 
	 * @param params 参数
	 * @return 受影响行数
	 * */
	public int execute(String sql,Object... params){
		int res = 0;
		try {
			res = queryRunner.update(sql, params);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}
	
	/**
	 * 执行增删改操作
	 * @author Paul
	 * @param action 包含sql和params的封装，为了事务处理更方便一些
	 * @return 受影响行数
	 * 
	 * */
	public int execute(JdbcAction action){
		if(action == null) throw new NullPointerException("action can not be null");
		int res = execute(action.getSql(),action.getParams());
		return res;
	}
	
	
	/**
	 * 事务版的增删改操作
	 * @author Paul
	 * @param actions action列表
	 * @return 受影响行数
	 * */
	public int execute(List<JdbcAction> actions){
		if(actions == null|| actions.size() < 1) throw new NullPointerException();
		int res = 0;
		Connection conn = null;
		try{
			conn = dataSource.getConnection();
			conn.setAutoCommit(false);
			for(int i=0;i<actions.size();i++){
				JdbcAction action = actions.get(i);
				int temp = queryRunner.update(conn, action.getSql(),action.getParams());
				res += temp;
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
		return res;
	}
	
	/**
	 * 查询并加入到Bean中
	 * @author Paul
	 * @param action 
	 * @param c 实体类的class	  
	 * @return t 对应实体
	 * 
	 * */
	public <T> T query(JdbcAction action,Class<T> c){
		T t = null;
		try {
			t = queryRunner.query(action.getSql(), new BeanHandler<T>(c), action.getParams());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return t;
	}
	
	/**
	 * 查询列表
	 * @author Paul
	 * @param action 
	 * @param c 实体类的class	  
	 * @return list 对应结果集populate到bean后添加到列表
	 * 
	 * */
	public <T> List<T> queryList(JdbcAction action,Class<T> c){
		List<T> list = Collections.emptyList();
		try {
			list = queryRunner.query(action.getSql(), new BeanListHandler<T>(c), action.getParams());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * 查询单个值
	 * @author Paul
	 * @param action
	 * @return obj 结果，需要强转
	 * */
	public Object queryPrimitive(JdbcAction action){
		Object obj = null;
		try {
			obj = queryRunner.query(action.getSql(), objectHandler , action.getParams());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return obj;
	}
	
	/**
	 * 查询单值列表
	 * @author Paul
	 * @param action
	 * @return list list of primitive
	 * */
	public List<Object> queryPrimitiveList(JdbcAction action){
		List<Object> list = Collections.emptyList();
		try {
			list = queryRunner.query(action.getSql(), objectListHandler, action.getParams());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public List<Map<String,Object>> queryMap(JdbcAction action){
		List<Map<String,Object>> list = Collections.emptyList();
		try {
			list = queryRunner.query(action.getSql(), new MapListHandler(),action.getParams());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
}
