package cn.edu.zhku.xinke.jisuanji.whf.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.zhku.xinke.jisuanji.whf.dto.ModelAttribute;
import cn.edu.zhku.xinke.jisuanji.whf.model.Address;
import cn.edu.zhku.xinke.jisuanji.whf.service.AddressService;

public class AddressServlet extends BaseServlet{

	private static final long serialVersionUID = 6041562039983978817L;
	
	private AddressService addressService = AddressService.getInstance();
	
	protected String save(HttpServletRequest req, HttpServletResponse resp)
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
		ma.pollute(req);
		return ma.getDestination();
	}
	
	protected String delete(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String method = req.getMethod();
		if(!"POST".equalsIgnoreCase(method)){
			req.setAttribute("message", "method : " + method + " not supported");
			req.getRequestDispatcher("/input.jsp").forward(req, resp);
			return null;
		}
		int id = Integer.parseInt(req.getParameter("id"));
		ModelAttribute ma = addressService.delete(id, req.getSession());
		
		ma.pollute(req);
		return ma.getDestination();
		
	}
	
	protected String update(HttpServletRequest req, HttpServletResponse resp)
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
		ma.pollute(req);
		return ma.getDestination();
	}

	protected String getByUser(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String method = req.getMethod();
		if(!"POST".equalsIgnoreCase(method)){
			req.setAttribute("message", "method : " + method + " not supported");
			req.getRequestDispatcher("/input.jsp").forward(req, resp);
			return null;
		}
		
		ModelAttribute ma = addressService.getByUser(req.getSession());
		ma.pollute(req);
		return ma.getDestination();
	}
}
