package common;


import java.util.ArrayList;

/**
 * 
 * @author xufeng
 *根据url来显示当前文档的目录
 */
public class CatalogHelper {
	
	public static ArrayList<Catalog> getCatalog(String url){
		//将传进来的url拆分成数组
		String[] catalog = url.split("/");
		
		 ArrayList<Catalog> list = new ArrayList<Catalog>();
		
	
		 for(int i = 0;i < catalog.length;i++){
			 	
			 	if(i==1||i==0){
			 		
			 		Catalog myCatalog =new Catalog("所有文件","./home");
			 		list.add(myCatalog);	
			 		
			 	}else{
			 	
			 		Catalog myCatalog =  new Catalog();
			 		myCatalog.setName(catalog[i]);
			 		myCatalog.setLink(list.get(i-1).getLink()+"/"+catalog[i]);
			 		list.add(myCatalog);
			 	}
			
			 }
		
		 list.remove(0);
		 return list;
		
	}

}

	