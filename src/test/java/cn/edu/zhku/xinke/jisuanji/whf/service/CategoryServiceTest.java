package cn.edu.zhku.xinke.jisuanji.whf.service;

import org.junit.Test;

import cn.edu.zhku.xinke.jisuanji.whf.model.Category;
import cn.edu.zhku.xinke.jisuanji.whf.model.SecCategory;

public class CategoryServiceTest{
	
	private CategoryService categoryService = CategoryService.getInstance();
	
	@Test
	public void testSave1(){
		Category cate = new Category();
		cate.setName("服装");
		categoryService.save(cate);
	}
	
	@Test
	public void testSave2(){
		SecCategory secCate = new SecCategory();
		secCate.setName("男装");
		secCate.setCategory(1);
		
		categoryService.save(secCate);
		secCate.setName("女装");
		categoryService.save(secCate);
	}
	
	@Test
	public void testDelete1(){
		
	}
	
	@Test
	public void testDelete2(){
		
	}
	

}
