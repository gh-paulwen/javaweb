package cn.edu.zhku.xinke.jisuanji.whf.dao;

import java.util.List;

import org.junit.Test;

import cn.edu.zhku.xinke.jisuanji.whf.model.Store;

public class StoreDaoTest {
	
	private StoreDao storeDao = StoreDao.getInstance();
	
	@Test
	public void testSave(){
		Store store = new Store();
		store.setName("paul store");
		store.setOwner(1);
		store.setRegion(249);
		int res = storeDao.save(store);
		System.out.println(res);
	}
	
	@Test
	public void testDelete(){
		int res = storeDao.delete(1,1);
		System.out.println(res);
	}
	
	@Test
	public void testUpdate(){
		Store store = storeDao.getById(4);
		store.setName("david's store");
		int res = storeDao.update(store);
		System.out.println(res);
	}
	
	@Test
	public void testGetById(){
		
	}
	
	@Test
	public void testGetByUser(){
		List<Store> list = storeDao.getByUser(1);
		for(Store store:list){
			System.out.println(store);
		}
	}
	
	@Test
	public void testGetByName(){
		List<Store> list = storeDao.getByName("store");
		for(Store store:list){
			System.out.println(store);
		}
	}

}
