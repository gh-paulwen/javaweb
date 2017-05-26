package cn.edu.zhku.xinke.jisuanji.whf.service;

import java.util.List;

import cn.edu.zhku.xinke.jisuanji.whf.dao.UserTypeDao;
import cn.edu.zhku.xinke.jisuanji.whf.dto.ModelAttribute;
import cn.edu.zhku.xinke.jisuanji.whf.model.UserType;

public class UserTypeService {
	
	private static UserTypeService instance = new UserTypeService();
	
	private UserTypeService(){}
	
	public static UserTypeService getInstance() {
		return instance;
	}
	
	
	private UserTypeDao userTypeDao = UserTypeDao.getInstance();
	
	public ModelAttribute getAll(){
		ModelAttribute ma = new ModelAttribute();
		List<UserType> list = userTypeDao.getAll();
		ma.setAttribute("listUserType", list);
		return ma;
	}

}
