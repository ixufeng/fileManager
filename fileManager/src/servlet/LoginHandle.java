package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sql.SqlHelper;

@SuppressWarnings("serial")
public class LoginHandle extends HttpServlet {

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy();
		
	}

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		String name = request.getParameter("username");
		String password = request.getParameter("password");
		SqlHelper sqlHelper = SqlHelper.getSqlHelper();
		boolean bool = sqlHelper.testUser(name, password);
		if(bool){
			//登陆成功
			response.sendRedirect("homePage.jsp");
		}else{
			
		}
		
	

	}

	
	public void init() throws ServletException {
		
		
	}

}
