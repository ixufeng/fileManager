package servlet;

import java.io.IOException;

import java.util.UUID;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sql.SqlHelper;

import file.MyFile;

@SuppressWarnings("serial")
public class NewDir extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request,response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String msg = "";
		String dirName = request.getParameter("dir");
		String path = request.getParameter("path");
		System.out.println(path);
		MyFile file = new MyFile(UUID.randomUUID().toString()+"_"+dirName,dirName,path,"floder","./images/filePic/floder.png");
		
		if(SqlHelper.getSqlHelper().addFile(file)){
			//插入数据库成功 --即新建文件夹成功
			
//			msg = URLEncoder.encode("新建文件夹成功！","utf-8");
			msg = "新建文件夹成功！";
			request.setAttribute("msg", msg);
			request.setAttribute("currentPath", path);
			RequestDispatcher rd = request.getRequestDispatcher("homePage.jsp");
			rd.forward(request, response);
		}
		
		
		
	}

}
