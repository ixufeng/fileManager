package handleLoad;

import file.Ffmpeg;
import file.PathConfig;
import file.PicBreviary;

public class UpLoadBre {
	// 缩略文件存储路径
	final static String breSavePath = PathConfig.BRE_PATH;
	// 文件存储路径
	final static String savePath = PathConfig.UPLOAD_PATH;

	// 生成缩略图
	public static String getBreviary(String fileRandomName, String type) {
		String imgName = "fail";
		// 处理视频缩略图
		if (type.equals("avi") || type.equals("mp4")) {

			imgName = savePath.substring(0, savePath.lastIndexOf(".")) + "jpg";
			Ffmpeg.getPic(savePath + "\\" + fileRandomName, breSavePath + "\\"
					+ imgName);
		} else if (type.equals("jpg") || type.equals("png")
				|| type.equals("jpeg")) {

			imgName = "Breviary" + fileRandomName;
			PicBreviary.getThumbnail(savePath + "\\" + fileRandomName,
					breSavePath + "\\" + imgName, 60, 80);
		} else if (type.equals("txt") || type.equals("html")
				|| type.equals("js") || type.equals("css")
				|| type.equals("htm")) {

			imgName = "txt.png";

		} else if (type.equals("mp3") || type.equals("cad")
				|| type.equals("wav") || type.equals("ogg")) {

			imgName = "music.png";

		} else if (type.equals("doc") || type.equals("docx")) {
			// word类型
			imgName = "word.png";

		}

		System.out.println(breSavePath + "\\" + imgName);

		return ("images/filePic/" + imgName);
	}

}
