package handleLoad;

import java.io.File;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import file.PathConfig;
import file.StoreFile;

import sql.Dbconnect;

public class upload {
	HttpServletRequest request;
	Dbconnect db = null;
	// 存储路径
	final static String savePath = PathConfig.UPLOAD_PATH;
	// 逻辑路径即所在的逻辑目录
	String logicPath;
	// 文件上传最大总量以及单个文件上传总量
	long maxSize = 1024 * 1024 * 10, fileMaxSize = 1024 * 1024;
	public String message = "";

	public upload(HttpServletRequest request, String logicPath, long maxSize,
			long fileMaxSize) {
		this.request = request;
		this.logicPath = logicPath;
		this.maxSize = maxSize;
		this.fileMaxSize = fileMaxSize;
		// this.picSavePath = request.getRealPath("/")+"WebRoot/images/filePic";
	}

	public upload(HttpServletRequest request, String logicPath) {
		this.request = request;
		this.logicPath = logicPath;
	}

	// 对前端传入的数据进行解析并存储
	@SuppressWarnings("unchecked")
	public boolean doUpload() {
		// 创建上传目录
		File file = new File(savePath);

		if (!file.exists() && !file.isDirectory())
			file.mkdir();
		// 创建工厂类实例
		DiskFileItemFactory factory = new DiskFileItemFactory();
		// 创建一个解析器
		ServletFileUpload sfu = new ServletFileUpload(factory);
		// 设置文件大小上传限制
		setSize(sfu, maxSize, fileMaxSize);
		sfu.setHeaderEncoding("utf-8");
		// 使用ServletFileUpload解析器解析上传数据，解析结果返回的是一个List<FileItem>集合，每一个FileItem对应一个Form表单的输入项
		try {
			List<FileItem> items = sfu.parseRequest(request);
			// 将解析的FileItem存储
			upFile(items);
			if(message.equals("ok"))
			return true;
			else
				return false;
		} catch (FileUploadException e) {
			message = "fail";
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	// 检验文件类型是否符合上传规定
	public boolean cannotUpload(String forbid, String fileType) {
		if (fileType.equals(forbid))
			return false;
		return true;
	}

	// 对传过来的FileItem进行处理
	void upFile(List<FileItem> items) {
		StoreFile store = new StoreFile(logicPath);
		for (FileItem item : items)
			{
			message=store.storageFileItem(item);
			}
	}

	// 设置文件上传 最大总量以及单个文件的大小
	void setSize(ServletFileUpload sfu, long maxSize, long fileMaxSize) {
		sfu.setSizeMax(maxSize);
		sfu.setFileSizeMax(fileMaxSize);
	}

}
