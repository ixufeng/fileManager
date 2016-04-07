package sql;
import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;


import file.MyFile;


public class SqlHelper {
	
	private static SqlHelper sqlHelper = new SqlHelper();
	private static Connection connection = null;
	private static Statement stmt = null;
	
	
	private SqlHelper(){

		
	}
	public static SqlHelper getSqlHelper(){
		
		connection = SqlConnect.getConnection();
		try {
			stmt = connection.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return sqlHelper;
	}
	
	
	/**
	 * 
	 * @param fileRandomName
	 * @return 返回唯一的一个Myfile对像
	 */
	public MyFile  getAFileByUniqueName(String fileUniqueName){
		
		MyFile myFile = new MyFile();
		String sql = "select * from file where fileRandomName = '"+fileUniqueName+"'"; 
		ResultSet result = null;
		try {
			 result =  stmt.executeQuery(sql);
			
			while(result.next()){
				
				myFile.setFileRandomName(result.getString(1));
				myFile.setFileName(result.getString(2));
				myFile.setFileLogicPath(result.getString(3));
				myFile.setFileType(result.getString(4));
				myFile.setFileSize(result.getString(5));
				myFile.setFileCtime(result.getDate(6).toString());
				if(result.getDate(7)!=null){
					myFile.setFileMtime(result.getDate(7).toString());
				}
				myFile.setFileLimit(result.getInt(8));	
				myFile.setFilePic(result.getString(9));	
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//关闭结果集
		try {
			result.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return myFile;
		
	}
	
	
	/**
	 * 
	 * @return list
	 * 根据文件的逻辑名来模糊查询
	 */
	public ArrayList<MyFile> getFByLogicName(String fileLogicName){
		

		ArrayList<MyFile> list = new ArrayList<MyFile>();
		String sql = GetSql.fileNameSearchSql(fileLogicName);
		ResultSet result = null;
		try {
			 result =  stmt.executeQuery(sql);
			
			//将结果集转化为ArrayList
			list = RestoMyFile(result);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//关闭结果集
		try {
			result.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}
	
	/**
	 * 通过文件的时间来查找文件
	 * @param time1
	 * @param time2
	 * @return
	 */
	public ArrayList<MyFile>getAllFByTime(String time1,String time2){
		ArrayList<MyFile> list = new ArrayList<MyFile>();
		String sql = GetSql.timeSearchSql(time1, time2);
		System.out.println(sql);
		ResultSet result = null;
		try {
			 result =  stmt.executeQuery(sql);
			
	
			list = RestoMyFile(result);
			 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//关闭结果集
		try {
			result.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		return list;
	}
	
	/**
	 * 查找某一个路径下的所有文件
	 * @param path
	 * @return Myfile集合
	 */
	public ArrayList<MyFile> getAllFileByPath(String path){
		ArrayList<MyFile >  list = new ArrayList<MyFile>();
		String sql = "select * from file where fileLogicpath ='"+path+"'";
		ResultSet result =null;
		try {
			PreparedStatement psd = connection.prepareStatement(sql);
			
			result = psd.executeQuery(sql);
			//将结果集转化为ArrayList
			list = RestoMyFile(result);

			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//关闭结果集
		try {
			if (result != null &&!result.isClosed() )
			result.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
		
	}
	/**
	 * @param userName
	 * @param userPassword
	 * @return boolean
	 */
	public boolean testUser(String userName,String userPassword) {
		
		userName = userName.trim();
		userPassword = userPassword.trim();
		String realPass = "";
		if(userName.equals(null)||userName.equals("")){
			return false;
		}
		String sqlString ="select user_password from user where user_name='"+userName+"'";
		
		try {
			ResultSet resultSet = stmt.executeQuery(sqlString);
			while(resultSet.next()){				
				realPass = resultSet.getString(1);				
			}
			if(userPassword.equals(realPass)){
				return true;
			}else{
				return false;
			}
						
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
		}
		
		return false;
		
	}
	/**
	 * 
	 * @param fileRandomName
	 * @return boolean
	 */
	public boolean delFile(String fileRandomName){
		String sql = "delete from file where fileRandomName = '"+fileRandomName+"'";
		try{
			int i = stmt.executeUpdate(sql);
			if(i>0){
				return true;
			}else{
				return false;
			}
		}catch(Exception e){
			System.out.println("删除失败");
		}
		
		return false;
	}


	public String[] delFiles(){
		String[] files = null;
		
		
		
		
		return files;
	}
	/**
	 * 
	 * @param file
	 * @return
	 *	备份文件项
	 */
	public boolean backUpFile(MyFile file){
		
		String sql = GetSql.addBackUp(file);
		System.out.println(sql);
		try {
			
			PreparedStatement psd = connection.prepareStatement(sql);
			if(psd.executeUpdate()>0){
				return true;
			}else{
				return false;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

	}
	/*
	 * 删除备份即彻底删除
	 */
	public boolean delFromGab(String fileRandomName){
		String sql = "delete from file_backup where fileRandomName = '"+fileRandomName+"'";
		try{
			int i = stmt.executeUpdate(sql);
			if(i>0){
				return true;
			}else{
				return false;
			}
		}catch(Exception e){
			System.out.println("删除失败");
		}
		
		return false;
	}
	/**
	 * 添加文件项
	 * @param file
	 * @return
	 */
	public boolean addFile(MyFile file){
		
		String sql = GetSql.uploadSql(file);
	try {
			
			PreparedStatement psd = connection.prepareStatement(sql);
			if(psd.executeUpdate()>0){
				return true;
			}else{
				return false;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	
		
	}
	/**
	 * 
	 * @return
	 */
	public ArrayList<MyFile> getOverdueFile(){
		ArrayList<MyFile> list = new ArrayList<MyFile>();
		String sql = GetSql.getOverdueSql();
		ResultSet result =null;
		try {
			PreparedStatement psd = connection.prepareStatement(sql);
			
			result = psd.executeQuery(sql);
			//将结果集转化为ArrayList
			list = RestoMyFile(result);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//关闭结果集
		try {
			if (result != null &&!result.isClosed() )
			result.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
		
	}
	
	// 定义函数进行关闭
	public static void closeConn(){
		
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void closeState() {
		try {
			if (stmt != null || !stmt.isClosed()) {
				stmt.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 *  将res类型转换为list<MyFile>集合
	 * @param result
	 * @return ArrayList<MyFile>
	 * @throws SQLException
	 */
	private ArrayList<MyFile> RestoMyFile(ResultSet result) throws SQLException{
		
		ArrayList<MyFile> list = new ArrayList<MyFile>();
		
		while(result.next()){
			
			MyFile myFile = new MyFile();
			myFile.setFileRandomName(result.getString(1));
			myFile.setFileName(result.getString(2));
			myFile.setFileLogicPath(result.getString(3));
			myFile.setFileType(result.getString(4));
			myFile.setFileSize(result.getString(5));
			myFile.setFileCtime(result.getDate(6).toString());
			if(result.getDate(7)!=null){
				myFile.setFileMtime(result.getDate(7).toString());
			}
			myFile.setFileLimit(result.getInt(8));
			myFile.setFilePic(result.getString(9));	
			list.add(myFile);							
		}
	
		return list;
	}
}