package file;

import java.io.IOException;

public class Ffmpeg {
	public static boolean getPic(String videoPath,String imgPath){
		//视频文件   

        String videoRealPath = videoPath;   
        String batPath="H:/javaTemp/ffmpeg/bin/ffmpeg.bat";
        //截图的路径（输出路径）   
        String imageRealPath =imgPath;   

        try {   

            //调用批处理文件   
        	
            Runtime.getRuntime().exec("cmd /c start "+batPath+" " + videoRealPath + " " + imageRealPath);   

        } catch (IOException e) {   


            e.printStackTrace();   
            return false;
        }
        return true;

	}
}
