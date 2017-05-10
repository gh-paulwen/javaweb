package cn.edu.zhku.xinke.jisuanji.whf.servlet;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class EncodingRequestWrapper extends HttpServletRequestWrapper{
	
	private HttpServletRequest request ;

	public EncodingRequestWrapper(HttpServletRequest request) {
		super(request);
		this.request = request;
	}
	
	@Override
	public String getParameter(String name) {
		String result = request.getParameter(name);
		try {
			System.out.println(result);
			result = new String(result.getBytes("ISO-8859-1"),"UTF-8");
			System.out.println(result);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} 
		return result;
	}

}
