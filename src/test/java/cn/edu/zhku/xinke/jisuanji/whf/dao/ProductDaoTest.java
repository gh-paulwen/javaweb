package cn.edu.zhku.xinke.jisuanji.whf.dao;

import org.junit.Test;

import cn.edu.zhku.xinke.jisuanji.whf.model.Product;

public class ProductDaoTest {
	
	private ProductDao productDao = ProductDao.getInstance();
	
	@Test
	public void testSave(){
		Product product = new Product();
		product.setName("夏季新款短袖t恤");
		product.setDescription("d");
		product.setPrice(110.5);
		product.setStore(2);
		product.setSecCategory(1);
		int res = productDao.save(product);
		System.out.println(res);
	}
	
	@Test
	public void testDelete(){
		int res = productDao.delete(3, 2);
		System.out.println(res);
	}
	
	@Test
	public void testUpdate(){
		Product p = productDao.getById(2);
		p.setPic("/test.png");
		int res = productDao.update(p);
		System.out.println(res);
		
	}
	
}
