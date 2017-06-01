package cn.edu.zhku.xinke.jisuanji.whf.servlet.json;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

public class JsonServlet extends HttpServlet {

	private static final long serialVersionUID = 6759222934237147964L;
	
	private static final Gson gson = new Gson();

	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws javax.servlet.ServletException, java.io.IOException {
		/**
		 * 处理参数乱码问题
		 * */
		// req = new EncodingRequestWrapper(req);
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=UTF-8");

		/**
		 * 得到method,利用反射调用对应方法
		 * */
		String methodName = req.getParameter("method");
		Class<?> clazz = this.getClass();
		try {
			Method method = clazz.getDeclaredMethod(methodName,HttpServletRequest.class, HttpServletResponse.class);
			method.setAccessible(true);
			Object obj = method.invoke(this, req, resp);
			if (obj == null)
				return;
			String jsonString = gson.toJson(obj);
			resp.getWriter().write(jsonString);
		} catch (Exception e) {
			e.printStackTrace();
		}

	};

}
