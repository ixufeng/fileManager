package file;




public class MyFile{
	private String fileRandomName;// 文件实际上传后存储的名字
	private String fileName;// 文件的逻辑名字即用户看到的名字
	private String fileLogicPath;// 文件逻辑存储的地址
	private String fileType;
	private String fileSize;
	private String fileCtime;// 文件创建时间
	private String fileMtime;// 文件修改时间
	private int fileLimit;
	private String filePic;
	/**
	 * 构造方法的重载 --提供多种file类的初始化
	 */
	public MyFile(){
		//初始化
	}
	public MyFile(String fileRandomName,String fileName, String fileLogicPath,String fileType,String filePic){
		this.fileRandomName = fileRandomName;
		this.fileName = fileName;
		this.fileLogicPath = fileLogicPath;
		this.fileType = fileType;
		this.filePic = filePic;
	}
	
	
	
	public int getFileLimit() {
		return fileLimit;
	}

	public void setFileLimit(int fileLimit) {
		this.fileLimit = fileLimit;
	}

	
	public void setFileRandomName(String fileRandomName){
		this.fileRandomName = fileRandomName;
	}
	
	public String getFileRandomName(){
		
		return this.fileRandomName;
	}

	public String getFileName() {
		return this.fileName;
	}


	public void setFileName(String fileName) {
		this.fileName = fileName;
	}


	public String getFileLogicPath() {
		return fileLogicPath;
	}


	public void setFileLogicPath(String fileLogicPath) {
		this.fileLogicPath = fileLogicPath;
	}


	public String getFileType() {
		return fileType;
	}


	public void setFileType(String fileType) {
		this.fileType = fileType;
	}


	public String getFileSize() {
		return fileSize;
	}


	public void setFileSize(String fileSize) {
		this.fileSize = fileSize;
	}


	public String getFileCtime() {
		return fileCtime;
	}


	public void setFileCtime(String fileCtime) {
		this.fileCtime = fileCtime;
	}


	public String getFileMtime() {
		return fileMtime;
	}


	public void setFileMtime(String fileMtime) {
		this.fileMtime = fileMtime;
	}
	
	/*
	private Date getTime(){
		
		java.util.Date nowTime =new  java.util.Date();
		
		=
	
	}*/
	public String getFilePic() {
		return filePic;
	}
	
	public void setFilePic(String filePic) {
		this.filePic = filePic;
	}
	
	
	
	
}