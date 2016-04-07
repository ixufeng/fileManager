package sql;

import file.MyFile;

public class GetSql {
	public static String selectFile(String fileName){
		String sql="select * from file where filename="+fileName;
		return sql;
	}
	
	/**
	 * @param fileRandomName
	 * @param fileName
	 * @param filePath
	 * @param fileType
	 * @return sql语句
	 */
	public static String uploadSql(String fileRandomName,String fileName,String fileLogicPath,String fileType){
		String sql="insert into file (fileRandomName,fileName,fileLogicPath,fileType,fileCtime) values('"+
	fileRandomName+"','"+fileName+"','"+fileLogicPath+"','"+fileType+"',now());";
		return sql;
	}
	
	public static String uploadSql(MyFile file){
		
		String sql="insert into file (fileRandomName,fileName,fileLogicPath,fileType,fileCtime,filePic) values('"+
				file.getFileRandomName()+"','"+file.getFileName()+"','"+file.getFileLogicPath()+"','"+file.getFileType()+"',now(),'"+file.getFilePic()+"');";
					
		return sql;
	}
	
	/**
	 * 
	 * @param file
	 * @return sql语句
	 * 向备份数据表添加一条记录
	 */
	public static String addBackUp(MyFile file){
		
		String sql="insert into file_backup (fileRandomName,fileName,fileLogicPath,fileType,fileCtime,filePic) values('"+
				file.getFileRandomName()+"','"+file.getFileName()+"','"+file.getFileLogicPath()+"','"+file.getFileType()+"','"+file.getFileCtime()+"','"+file.getFilePic()+"');";
		
		return sql;
	}
	/**
	 * 获取某段时间的所有文件
	 * @param String
	 * @return String
	 */
	
	public static String timeSearchSql(String time1,String time2){
		String sql = "";
		sql = "select * from file  where fileCtime>='"+time1+"'and fileCtime<='"+time2+"'order by fileCtime asc";		
		return sql;
	}
	
	/**
	 * 关键字模糊查询
	 * @param String
	 * @return String--sql
	 */
	public static String fileNameSearchSql(String fileName){
		String sql = "";
		sql = "select * from file where fileName LIKE '%"+fileName+"%'";
		
		return sql;
	}

	public static String getOverdueSql(){
		String sql ="";
		sql = "select * from file_backup where UNIX_TIMESTAMP(NOW())- UNIX_TIMESTAMP(fileMtime)>7*24*60*60";
		return sql;
	}
}





