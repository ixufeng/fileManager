package file;

import handleLoad.UpLoadBre;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import lucene.Lucene;

import org.apache.commons.fileupload.FileItem;

import sql.SqlHelper;

public class StoreFile {
	SqlHelper sh = null;
	String logicPath = "";

	public StoreFile(String logicPath) {
		this.logicPath = logicPath;
	}

	// 存储
	public String storageFileItem(FileItem item) {
		String fileName = "";
		if (item.isFormField())
			return "This is not a file";
		fileName = item.getName();
		// 对路径进行截断
		fileName = fileName.substring(fileName.lastIndexOf('\\') + 1);
		if (fileName == null || fileName.trim().equals("")) {
			return "FileName is Empty";
		}
		String fileType = fileName.substring(fileName.lastIndexOf(".") + 1);
		// 将上传文件存储到file2中
		String fileRandomName = GetRandomName.getFileRandomName(fileName);
		// 实际存储的位置
		String filePath = PathConfig.UPLOAD_PATH + "\\" + fileRandomName;

		// 存储操作
		File file2 = new File(filePath);
		InputStream in;
		FileOutputStream out = null;
		try {
			out = new FileOutputStream(file2);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		try {
			in = item.getInputStream();
			// 将文件存储到磁盘中
			fileStorage(in, out);
			in.close();
			out.close();
			// 生成缩略图
			String breviaryPath = "";
			breviaryPath = UpLoadBre.getBreviary(fileRandomName, fileType);
			if (breviaryPath.equals("fail")) {
				return "Fail to get Breviary picture";
			}
			// 创建索引
			createIndex(filePath, fileType,fileName);
			// 初始化文件对象
			MyFile myFile = new MyFile(fileRandomName, fileName, logicPath,
					fileType, breviaryPath);

			// 将文件信息存储到数据库中
			updateDateBase(myFile);
			// 清空FileItem对象中封装的主体内容，如果内容是被保存在临时文件中，该方法会把临时文件删除。
			item.delete();
			return "ok";
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "error";
	}

	// 将文件信息存储到数据库中
	public String updateDateBase(MyFile file) {
		sh = SqlHelper.getSqlHelper();
		if (sh.addFile(file) == false)
			return "fail to insert";
		sh.closeState();
		SqlHelper.closeConn();
		return "ok";
	}

	// 文件存储到磁盘
	public void fileStorage(InputStream in, FileOutputStream out) {
		byte buffer[] = new byte[1024];
		int len;
		try {
			while ((len = in.read(buffer)) != -1) {
				out.write(buffer, 0, len);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 生成索引
	public static void createIndex(String filePath, String fileType,String title) {

		if (fileType.equals("txt")) {

			Lucene lu = new Lucene();
			lu.createIndex(filePath,title);

		} else {
			Lucene lu = new Lucene();
			lu.createIndex(filePath,title);
		}

	}

}
