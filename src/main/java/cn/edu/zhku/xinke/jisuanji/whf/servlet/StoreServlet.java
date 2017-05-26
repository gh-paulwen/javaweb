package cn.edu.zhku.xinke.jisuanji.whf.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.zhku.xinke.jisuanji.whf.dto.ModelAttribute;
import cn.edu.zhku.xinke.jisuanji.whf.model.Store;
import cn.edu.zhku.xinke.jisuanji.whf.service.StoreService;

public class StoreServlet extends BaseServlet {

	private static final long serialVersionUID = 8040325942861370231L;

	private StoreService storeService = StoreService.getInstance();

	protected String save(HttpServletRequest req, HttpServletResponse resp)
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
		ma.pollute(req);
		return ma.getDestination();
	}

	protected String delete(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String method = req.getMethod();
		if (!"POST".equalsIgnoreCase(method)) {
			req.setAttribute("message", "method : " + method + " not supported");
			req.getRequestDispatcher("/input.jsp").forward(req, resp);
			return null;
		}
		int id = Integer.parseInt(req.getParameter("id"));
		ModelAttribute ma = storeService.delete(id, req.getSession());
		ma.pollute(req);
		return ma.getDestination();
	}

	protected String update(HttpServletRequest req, HttpServletResponse resp)
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
		ma.pollute(req);
		return ma.getDestination();
	}

	protected String getById(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String method = req.getMethod();
		if (!"GET".equalsIgnoreCase(method)) {
			req.setAttribute("message", "method : " + method + " not supported");
			req.getRequestDispatcher("/input.jsp").forward(req, resp);
			return null;
		}

		int id = Integer.parseInt(req.getParameter("id"));

		ModelAttribute ma = storeService.getById(id);
		ma.pollute(req);
		return ma.getDestination();
	}

	protected String getByUser(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String method = req.getMethod();
		if (!"GET".equalsIgnoreCase(method)) {
			req.setAttribute("message", "method : " + method + " not supported");
			req.getRequestDispatcher("/input.jsp").forward(req, resp);
			return null;
		}
		ModelAttribute ma = storeService.getByUser(req.getSession());
		ma.pollute(req);
		return ma.getDestination();
	}

	protected String getByName(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String method = req.getMethod();
		if (!"GET".equalsIgnoreCase(method)) {
			req.setAttribute("message", "method : " + method + " not supported");
			req.getRequestDispatcher("/input.jsp").forward(req, resp);
			return null;
		}
		String name = req.getParameter("name");
		ModelAttribute ma = storeService.getByName(name);
		ma.pollute(req);
		return ma.getDestination();
	}

}
