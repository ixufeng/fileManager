package servlet;

import handleLoad.upload;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UploadServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 获取文件要保存的逻辑位置
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		System.out.println(request.getParameter("path"));

		Writer write = response.getWriter();

		long maxSize = 1024 * 1024 * 10, fileMaxSize = 1024 * 1024;

		upload up = new upload(request, request.getParameter("path"), maxSize,
				fileMaxSize);
		if (up.doUpload()) {
			write.write("上传成功");
		} else {
			write.write(up.message);
		}
	}
}