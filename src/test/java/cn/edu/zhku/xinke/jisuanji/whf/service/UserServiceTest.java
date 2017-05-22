package cn.edu.zhku.xinke.jisuanji.whf.service;

import org.junit.Test;

import cn.edu.zhku.xinke.jisuanji.whf.dto.ModelAttribute;
import cn.edu.zhku.xinke.jisuanji.whf.model.User;

public class UserServiceTest {
	
	private UserService userService = UserService.getInstance();
	
	@Test
	public void testRegister(){
		User user = new User();
		user.setName("PaulWen");
		user.setPassword("2292");
		user.setEmail("229267643@qq.com");
		ModelAttribute ma = userService.register(user);
		System.out.println(ma.getDestination());
	}
	
	@Test
	public void testLogin(){
//		ModelAttribute ma = userService.login("PaulWen", "2292", null);
//		ModelAttribute ma = userService.login("229267643@qq.com", "2292", null);
		ModelAttribute ma = userService.login("229267643@qq.com", "22292", null);
		System.out.println(ma.getDestination());
	}

}
