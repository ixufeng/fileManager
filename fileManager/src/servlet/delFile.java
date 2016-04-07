package servlet;

import java.io.IOException;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lucene.Lucene;


import file.MyFile;

import sql.SqlHelper;
/**
 * 
 * @author xufeng
 * @version 1.0
 * 删除指定文件或文件集合
 * 
 */
@SuppressWarnings("serial")
public class delFile extends HttpServlet {

	


	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		doPost(request , response);
	
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String msg = "";
		//接受文件的物理名称（id）
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String fileName = request.getParameter("fileName");
		String path = request.getParameter("path");
		
		
		//先备份要删除的记录    --
		MyFile file = SqlHelper.getSqlHelper().getAFileByUniqueName(fileName);
		boolean isBack = SqlHelper.getSqlHelper().backUpFile(file);
		//删除数据库中的记录
		boolean  isDel = false;
		if(isBack){
			isDel = SqlHelper.getSqlHelper().delFile(file.getFileRandomName());
			System.out.println(isDel);
		}else{
			//备份失败
			msg = "文件备份失败！";	
			
			request.getRequestDispatcher("homePage.jsp?currentPath="+path+"&msg="+msg).forward(request, response);
		}
		//如果删除记录成功，在删除磁盘数据
		if(isDel){
			//删除磁盘内容 ----周期删除
			//----------带处理-----留给自动处理机制
			msg = "删除成功";
			
			
			/*response.sendRedirect("homePage.jsp?currentPath="+path+"&msg="+msg);*/
			
			
			//删除索引
			Lucene lu=new Lucene();
			//fileName必须为RandomName
			lu.delete(fileName);
			
			request.setAttribute("msg", msg);
			request.setAttribute("currentPath", path);
			RequestDispatcher rd = request.getRequestDispatcher("homePage.jsp");
			rd.forward(request, response);
		}
		
		
		
		
	}

}
