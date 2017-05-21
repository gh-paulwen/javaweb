package cn.edu.zhku.xinke.jisuanji.whf.service;

import org.junit.Test;

public class UserServiceTest {
	
	private UserService userService = UserService.getInstance();
	
	@Test
	public void testTx(){
		userService.txsave();
	}

}
