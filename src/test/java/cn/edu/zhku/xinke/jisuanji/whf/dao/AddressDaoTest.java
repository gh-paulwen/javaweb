package cn.edu.zhku.xinke.jisuanji.whf.dao;

import java.util.List;

import org.junit.Test;

import cn.edu.zhku.xinke.jisuanji.whf.model.Address;

public class AddressDaoTest {
	
	private AddressDao addressDao = AddressDao.getInstance();
	
	@Test
	public void testSave(){
		Address addr = new Address();
		addr.setRegion(249);
		addr.setVerboseAddress("东城区学贤路2号");
//		addr.setVerboseAddress(null);
		addr.setUser(1);
		int res = addressDao.save(addr);
		System.out.println(res);
	}
	
	@Test
	public void testDelete(){
		int res = addressDao.delete(2, 1);
		System.out.println(res);
	}
	
	@Test
	public void testUpdate(){
		Address addr = addressDao.get(3);
		addr.setVerboseAddress("东城区学贤路4号");
		int res = addressDao.update(addr);
		System.out.println(res);
	}
	
	@Test
	public void testGetByUser(){
		List<Address> list = addressDao.getByUser(1);
		for(Address addr : list){
			System.out.println(addr);
		}
	}
	
	

}
