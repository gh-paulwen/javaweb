package cn.edu.zhku.xinke.jisuanji.whf.util;

import java.util.Arrays;

/**
 * 封装sql和params
 * @author Paul
 * 
 * */
public final class JdbcAction {
	
	private final String sql;
	
	private final Object[] params;
	
	public JdbcAction(String sql,Object... params ){
		this.sql = sql;
		this.params = params;
	}
	
	public String getSql(){
		return sql;
	}
	
	public Object[] getParams(){
		return Arrays.copyOf(params,params.length);
	}
	
}
