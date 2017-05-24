package cn.edu.zhku.xinke.jisuanji.whf.servlet.json;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.zhku.xinke.jisuanji.whf.dto.ModelAttribute;
import cn.edu.zhku.xinke.jisuanji.whf.model.Store;
import cn.edu.zhku.xinke.jisuanji.whf.service.StoreService;

public class StoreJsonServlet extends JsonServlet{
	
	private static final long serialVersionUID = -9079852607782382115L;
	
	private StoreService storeService = StoreService.getInstance();

	protected Object save(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String method = req.getMethod();
		if (!"POST".equalsIgnoreCase(method)) {
			req.setAttribute("message", "method : " + method + " not supported");
			req.getRequestDispatcher("/input.jsp").forward(req, resp);
			return null;
		}

		String name = req.getParameter("name");
		int region = Integer.parseInt(req.getParameter("region"));
		Store store = new Store();
		store.setName(name);
		store.setRegion(region);

		ModelAttribute ma = storeService.register(store, req.getSession());
		return ma.get();
	}

	protected Object delete(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String method = req.getMethod();
		if (!"POST".equalsIgnoreCase(method)) {
			req.setAttribute("message", "method : " + method + " not supported");
			req.getRequestDispatcher("/input.jsp").forward(req, resp);
			return null;
		}
		int id = Integer.parseInt(req.getParameter("id"));
		ModelAttribute ma = storeService.delete(id, req.getSession());
		return ma.get();
	}

	protected Object update(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String method = req.getMethod();
		if (!"POST".equalsIgnoreCase(method)) {
			req.setAttribute("message", "method : " + method + " not supported");
			req.getRequestDispatcher("/input.jsp").forward(req, resp);
			return null;
		}
		String name = req.getParameter("name");
		int region = Integer.parseInt(req.getParameter("region"));
		int owner = Integer.parseInt(req.getParameter("owner"));
		int id = Integer.parseInt(req.getParameter("id"));

		Store store = new Store();
		store.setId(id);
		store.setRegion(region);
		store.setOwner(owner);
		store.setName(name);
		ModelAttribute ma = storeService.update(store, req.getSession());
		return ma.get();
	}

	protected Object getById(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String method = req.getMethod();
		if (!"GET".equalsIgnoreCase(method)) {
			req.setAttribute("message", "method : " + method + " not supported");
			req.getRequestDispatcher("/input.jsp").forward(req, resp);
			return null;
		}

		int id = Integer.parseInt(req.getParameter("id"));

		ModelAttribute ma = storeService.getById(id);
		return ma.get();
	}

	protected Object getByUser(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String method = req.getMethod();
		if (!"GET".equalsIgnoreCase(method)) {
			req.setAttribute("message", "method : " + method + " not supported");
			req.getRequestDispatcher("/input.jsp").forward(req, resp);
			return null;
		}
		ModelAttribute ma = storeService.getByUser(req.getSession());
		return ma.get();
	}

	protected Object getByName(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String method = req.getMethod();
		if (!"GET".equalsIgnoreCase(method)) {
			req.setAttribute("message", "method : " + method + " not supported");
			req.getRequestDispatcher("/input.jsp").forward(req, resp);
			return null;
		}
		String name = req.getParameter("name");
		ModelAttribute ma = storeService.getByName(name);
		return ma.get();
	}

}
