package servlet;

import java.io.IOException;
import java.io.Writer;

import java.util.ArrayList;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;


import file.MyFile;
import sql.SearchHelper;

@SuppressWarnings("serial")
public class searchFile extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			doPost(request,response);

	
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			response.setCharacterEncoding("utf-8");
			request.setCharacterEncoding("utf-8");
			Writer writer = response.getWriter();
			String searchWord = request.getParameter("searchWord");
			String type = request.getParameter("searchType");
			System.out.println("输入日期"+searchWord);
			//searchHelper查询类
			SearchHelper searchHelper = new SearchHelper();
			//存储查询结果
			ArrayList<MyFile> list = new ArrayList<MyFile>();
			
			
			if(type.equals("fimeName")){
				//通过文件的逻辑名来查找文件
				list = searchHelper.searchFileByName(searchWord);

				
			}else if(type.equals("fileContent")){
				//对文件内容的检索
				
				list = searchHelper.SearchContent(searchWord,30);
				
			}else{
				
				list = searchHelper.searchFileByDate(searchWord);
			}
			
			//对查询结果做出判断
			if(list.size()>0){
				//转---json
	
				JSONArray jsonArray = JSONArray.fromObject(list);
				
				writer.write(jsonArray.toString());
				
			}else{
				JSONArray jsonArray = JSONArray.fromObject("['没有找到文件']");
			
				writer.write(jsonArray.toString());
			}
			
			
	}

}
