package cn.edu.zhku.xinke.jisuanji.whf.servlet;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 专门用作继承，目的是减少Servlet的数量，连接时url写成: servletName?method=methodName&param=xxx
 * @author Paul
 * 
 * */
public abstract class BaseServlet extends HttpServlet{

	private static final long serialVersionUID = 9162442764842064382L;
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		/**
		 * 处理参数乱码问题
		 * */
//		req = new EncodingRequestWrapper(req);
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=UTF-8");
		
		/**
		 * 得到method,利用反射调用对应方法
		 * */		
		String methodName = req.getParameter("method");
		Class<? extends BaseServlet> clazz = this.getClass();		
		try {
			Method method = clazz.getDeclaredMethod(methodName, HttpServletRequest.class,HttpServletResponse.class);
			method.setAccessible(true);
			Object obj = method.invoke(this, req,resp);
			if(obj == null)
				return ;
			String command = (String)obj;
			boolean match = command.matches(".*:.*");
			if(match){
				// forward:page redirect:page			
				String[] components = command.split(":");
				String howto = components[0];
				String page = components[1];
				if("forward".equals(howto)){
					req.getRequestDispatcher("/"+ page).forward(req, resp);
				}else if("redirect".equals(howto)){
					resp.sendRedirect(page);
				}
			}else{
				throw new RuntimeException("Wrong Pattern");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 	
	}

}
