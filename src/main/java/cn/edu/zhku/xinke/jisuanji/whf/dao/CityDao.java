package cn.edu.zhku.xinke.jisuanji.whf.dao;

import java.util.List;

import cn.edu.zhku.xinke.jisuanji.whf.model.City;
import cn.edu.zhku.xinke.jisuanji.whf.util.JdbcAction;
import cn.edu.zhku.xinke.jisuanji.whf.util.JdbcUtil;

public class CityDao {
	
	/**
	 * 单例实现
	 * */
	private static CityDao instance = new CityDao();
	private CityDao(){}
	public static CityDao getInstance(){
		return instance;
	}
	
	private JdbcUtil jdbcUtil = JdbcUtil.getInstance();
	
	public List<City> getProvince(){
		String sql = "select * from city where superCity is NULL";
		JdbcAction action = new JdbcAction(sql);
		return jdbcUtil.queryList(action, City.class);
	}
	
	public List<City> getCity(int province){
		String sql = "select * from city where superCity = ?";
		JdbcAction action = new JdbcAction(sql,province);
		return jdbcUtil.queryList(action, City.class);
	}
	
}
