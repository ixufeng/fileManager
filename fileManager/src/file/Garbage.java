package file;

import lucene.Lucene;
import sql.SqlHelper;

public class Garbage {
	public String moveToGab(MyFile file){
		String msg="";
		boolean isBack = SqlHelper.getSqlHelper().backUpFile(file);
		boolean  isDel = false;
		if(isBack){
			isDel = SqlHelper.getSqlHelper().delFile(file.getFileRandomName());
			System.out.println(isDel);
		}else{
			//备份失败
			msg = "文件备份失败！";	
			return msg;
		}
		if(isBack&&isDel)
		{
			msg="备份成功";
		}
		return msg;
	}
	//彻底删除
	public String clearGabage(MyFile myFile){
		String msg="彻底删除";
		//删除回收站数据库数据
		SqlHelper.getSqlHelper().delFromGab(myFile.getFileRandomName());
		//删除文件索引
		Lucene lu=new Lucene();
		lu.delete(myFile.getFileRandomName());
		
		//删除文件
		DelFile.delete(PathConfig.UPLOAD_PATH+myFile.getFileRandomName());
		String picPath=myFile.getFilePic();
				
		//视频或图片删除缩略图
		String type=myFile.getFileType();
		if(type.equals("avi") || type.equals("mp4")||type.equals("jpg") || type.equals("png")
				|| type.equals("jpeg"))
		{
			picPath=picPath.substring(picPath.lastIndexOf("/")+1);
			DelFile.delete(PathConfig.BRE_PATH+picPath);
		}
		
		return msg;
	}
}
