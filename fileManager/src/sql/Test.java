package sql;

import java.util.ArrayList;

import file.MyFile;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		ArrayList<MyFile> list = SqlHelper.getSqlHelper().getOverdueFile();
		
		System.out.println(list.get(1).getFileMtime());
		
	}

}
