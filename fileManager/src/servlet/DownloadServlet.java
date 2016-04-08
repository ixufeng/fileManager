package servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import file.PathConfig;

@SuppressWarnings("serial")
public class DownloadServlet extends HttpServlet {

	public DownloadServlet() {
		super();
	}

	
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		if(request.getParameter("file")!=null){
			try{
				System.out.println(request.getParameter("file"));
				String name= request.getParameter("file");
				 //设置响应头，控制浏览器下载该文件
		        response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(name, "UTF-8"));
				//response.setHeader("Content-Type","media");
				String  filePath = PathConfig.UPLOAD_PATH +'/'+ name;
				System.out.println(filePath);
				File file=new File(filePath);
				FileInputStream in=new FileInputStream(file);
				OutputStream out=response.getOutputStream();
				int len;
				byte buff[]=new byte[1024];
				//循环将输入流中的内容读取到缓冲区当中
				while((len=in.read(buff))>0){
					out.write(buff, 0, len);
				}
				in.close();
				out.close();
			}catch(IOException e){
				e.printStackTrace();
				System.out.println("文件下载出错");
			}
		}
		
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			doGet(request,response);
	}



}
