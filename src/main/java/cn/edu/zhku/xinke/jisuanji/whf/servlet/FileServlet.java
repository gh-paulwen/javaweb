package cn.edu.zhku.xinke.jisuanji.whf.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class FileServlet extends BaseServlet {

	private static final long serialVersionUID = 6672458729632341354L;
	
	private DiskFileItemFactory factory ;

	private ServletFileUpload upload ;
	
	private String uploadDir = "D:\\upload";
	
	private int maxSize = 1024 * 1024 * 5;
	
	@Override
	public void init() throws ServletException {
		super.init();
		factory = new DiskFileItemFactory();
		factory.setSizeThreshold(4096);
		factory.setRepository(new File("D:\\tmp"));
		upload = new ServletFileUpload(factory);
		upload.setSizeMax(maxSize);
	}
	
	protected String upload(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException{
		
		String method = req.getMethod();
		if(!"POST".equalsIgnoreCase(method)){
			req.setAttribute("message", "method : " + method + " not supported");
			req.getRequestDispatcher("/input.jsp").forward(req, resp);
			return null;
		}
		
		List<?> fileItems = null;
		String fileName = null;
		try {
			fileItems = upload.parseRequest(req);
			Iterator<?> i =fileItems.iterator();
			while(i.hasNext()){
				FileItem fileItem = (FileItem) i.next();
				if(fileItem.isFormField()){
					
				}else {
					String uuid = UUID.randomUUID().toString();
					fileName = uuid + fileItem.getName();
					fileItem.write(new File(uploadDir,fileName));
					break;
				}
			}
		} catch (FileUploadException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(fileName!=null){
			resp.getWriter().write(fileName);
		}
		return null;
	}
	
	protected String retrive(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String method = req.getMethod();
		if(!"GET".equalsIgnoreCase(method)){
			req.setAttribute("message", "method : " + method + " not supported");
			req.getRequestDispatcher("/input.jsp").forward(req, resp);
			return null;
		}
		
		String img = req.getParameter("img");
		File file = new File(uploadDir,img);
		InputStream input = new FileInputStream(file);
		OutputStream output = resp.getOutputStream();
		int len = 0;
		int _1M = 1024 * 1024;
		byte[] buffer = new byte[_1M];
		while((len = input.read(buffer)) > 0){
			output.write(buffer,0,len);
		}
		input.close();
		
		return null;
	}

}
