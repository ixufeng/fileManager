package file;

import java.util.UUID;

public class GetRandomName {
	// 为文件获得唯一名字
	public static String getFileRandomName(String fileName) {
		return UUID.randomUUID().toString() + "_" + fileName;
	}
}
