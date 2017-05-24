package cn.edu.zhku.xinke.jisuanji.whf.servlet.json;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.zhku.xinke.jisuanji.whf.dto.ModelAttribute;
import cn.edu.zhku.xinke.jisuanji.whf.model.Address;
import cn.edu.zhku.xinke.jisuanji.whf.service.AddressService;

public class AddressJsonServlet extends JsonServlet{

	private static final long serialVersionUID = -2792737295092187864L;
	
	private AddressService addressService = AddressService.getInstance();
	
	protected Object save(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String method = req.getMethod();
		if(!"POST".equalsIgnoreCase(method)){
			req.setAttribute("message", "method : " + method + " not supported");
			req.getRequestDispatcher("/input.jsp").forward(req, resp);
			return null;
		}
		
		int region = Integer.valueOf(req.getParameter("region"));
		String verboseAddress = req.getParameter("verboseAddress");
		
		Address address = new Address();
		address.setRegion(region);
		address.setVerboseAddress(verboseAddress);
		
		ModelAttribute ma = addressService.save(address, req.getSession());
		return ma.get();
	}
	
	protected Object delete(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String method = req.getMethod();
		if(!"POST".equalsIgnoreCase(method)){
			req.setAttribute("message", "method : " + method + " not supported");
			req.getRequestDispatcher("/input.jsp").forward(req, resp);
			return null;
		}
		int id = Integer.parseInt(req.getParameter("id"));
		ModelAttribute ma = addressService.delete(id, req.getSession());
		return ma.get();
	}
	
	protected Object update(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String method = req.getMethod();
		if(!"POST".equalsIgnoreCase(method)){
			req.setAttribute("message", "method : " + method + " not supported");
			req.getRequestDispatcher("/input.jsp").forward(req, resp);
			return null;
		}
		
		int id = Integer.parseInt(req.getParameter("id"));
		int user = Integer.parseInt(req.getParameter("user"));
		int region = Integer.parseInt(req.getParameter("region"));
		String verbose = req.getParameter("verboseAddress");
		
		Address address = new Address();
		address.setId(id);
		address.setUser(user);
		address.setRegion(region);
		address.setVerboseAddress(verbose);
		
		ModelAttribute ma = addressService.update(address, req.getSession());
		return ma.get();
	}

	protected Object getByUser(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String method = req.getMethod();
		if(!"POST".equalsIgnoreCase(method)){
			req.setAttribute("message", "method : " + method + " not supported");
			req.getRequestDispatcher("/input.jsp").forward(req, resp);
			return null;
		}
		
		ModelAttribute ma = addressService.getByUser(req.getSession());
		return ma.get();
	}

}
