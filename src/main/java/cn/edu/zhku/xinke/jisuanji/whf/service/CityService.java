package cn.edu.zhku.xinke.jisuanji.whf.service;

import java.util.List;

import cn.edu.zhku.xinke.jisuanji.whf.dao.CityDao;
import cn.edu.zhku.xinke.jisuanji.whf.dto.ModelAttribute;
import cn.edu.zhku.xinke.jisuanji.whf.model.City;

public class CityService {
	
	/**
	 * 单例实现
	 * */
	private static CityService instance = new CityService();
	private CityService(){}
	public static CityService getInstance(){
		return instance;
	}
	
	private CityDao cityDao = CityDao.getInstance();
	
	public ModelAttribute getProvince(){
		ModelAttribute ma = new ModelAttribute();
		List<City> list = cityDao.getProvince();
		ma.setAttribute("listProvince", list);
		return ma;
	}
	
	public ModelAttribute getCity(int province){
		ModelAttribute ma = new ModelAttribute();
		List<City> list = cityDao.getCity(province);
		ma.setAttribute("listCity", list);
		return ma;
	}
	
}
