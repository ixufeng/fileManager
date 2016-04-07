package suggest;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import file.PathConfig;

public class WriteToFile {
	String filePath=PathConfig.dictionaryPath;
	FileWriter fw=null;
	FileWriter fw2=null;
	BufferedWriter bw=null;
	BufferedWriter bw2=null;
	public void write(String value){
		File file=new File(filePath);
		File fileToday=new File(PathConfig.todayDictionary);
		try {
			fw = new FileWriter(file,true);
			fw2=new FileWriter(fileToday);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		bw=new BufferedWriter(fw);
		bw2=new BufferedWriter(fw2);
		try {
			bw.newLine();
			bw.write(value);
			bw.flush();
			bw2.write(value);
			bw2.flush();
			bw2.close();
			fw2.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void close(){
		try {
			if(fw!=null)
				fw.close();
			if(bw!=null){
					bw.close();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
