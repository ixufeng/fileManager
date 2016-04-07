package sql;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lucene.Lucene;

import file.MyFile;

/**
 *  * @author xufeng
 *	  	用户查询文件类
 */
public class SearchHelper{

	
	/**
	 * 通过文件名来模糊搜索
	 * @param fileName
	 * @return
	 */
	public ArrayList<MyFile> searchFileByName(String fileLogicName){
		
		ArrayList<MyFile> list = new ArrayList<MyFile>();
		SqlHelper sqlHelper = SqlHelper.getSqlHelper();
		list = sqlHelper.getFByLogicName(fileLogicName);
		
		return list;
		
	}
	
	/**
	 * 
	 * @param fileCtime(String)
	 * @return ArrayList<MyFile>
	 * 
	 */

	public ArrayList<MyFile> searchFileByDate(String fileCtime){
		ArrayList<MyFile> list = new ArrayList<MyFile>();
		Date date=null;
		try {
			 date = new SimpleDateFormat("yyyy-MM-dd").parse(fileCtime);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//获得时间范围
		if(date!=null){
			String date1 = new SimpleDateFormat("yyyy-MM-dd").format(date.getTime()-(long)30*24*60*60*1000);
			String date2 = new SimpleDateFormat("yyyy-MM-dd").format(date.getTime()+(long)30*24*60*60*1000);
			list = SqlHelper.getSqlHelper().getAllFByTime(date1, date2);	
		}
		
		return list;
		
	}
	/**
	 * 
	 * @param fileCtime
	 * @return Date
	 */
	public java.sql.Date standardTime(String fileCtime){
		
		String dateStr = fileCtime;
		Date d = parseDate(dateStr);		
		//获得查询日期的返回默认查询一年的范围
		java.sql.Date  sqlDate = new java.sql.Date(d.getTime());
		
		return sqlDate;
	}
	
	/**
	 *           
	 * @param str
	 * @return
	 * @throws ParseException
	 */
	 public Date parseDate(String str)  {
		 
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		try {
			date =  format.parse(str);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return date;
	}
	 
	 
	 public ArrayList<MyFile> SearchContent(String fileContent,int number){
		 
		 ArrayList<MyFile> list = new ArrayList<MyFile>();
		 Lucene  lucene = new Lucene();
		 List<String>  fileNameList = lucene.searchContent(fileContent, number);
		 SqlHelper sqlHelper = SqlHelper.getSqlHelper();
		 for(String item : fileNameList){
			 
			 list.add(sqlHelper.getAFileByUniqueName(item));
			 
		 }
		 
		 
		 return list;
	 }
	 	
}