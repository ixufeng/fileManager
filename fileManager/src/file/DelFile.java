package file;

import java.io.File;

public class DelFile {
	public static boolean delete(String filePath){
		boolean result=false;
		File file=new File(filePath);
		if(file.isDirectory())
		{
			File files[]=file.listFiles();
			for(File f :files){
				result=f.delete();
				if(result==false)
					return result;
			}
		}
		result=file.delete();
		return result;
	}
}
