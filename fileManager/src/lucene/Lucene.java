package lucene;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.analysis.util.CharArraySet;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.FieldType;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.queryparser.classic.QueryParser.Operator;
import org.apache.lucene.search.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

import file.PathConfig;


public class Lucene {
	String Indexpath=PathConfig.indexSavePath;
	Directory directory=null; 
	 CharArraySet cas = new CharArraySet(Version.LUCENE_46, 0, true);
	SmartChineseAnalyzer sca = new SmartChineseAnalyzer(Version.LUCENE_46, cas);
	private static IndexReader indexReader=null;
	public Lucene(){
		try {
			directory= FSDirectory.open(new File(Indexpath));
			indexReader=DirectoryReader.open(directory);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	public Lucene(String IndexPath){
		this.Indexpath=IndexPath;
	}
	//强制清空回收站
	public void forceMeger(){
		IndexWriter iw=null;
		try {
			iw=new IndexWriter(directory,new IndexWriterConfig(Version.LUCENE_46,sca));
			iw.forceMergeDeletes();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			try {
				if(iw!=null)
				iw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public IndexSearcher getSearch(){
		
		try {
			if(indexReader==null)
			{
				indexReader=DirectoryReader.open(directory);
			}else
			{
				IndexReader reader=DirectoryReader.openIfChanged((DirectoryReader)indexReader);
				if(reader!=null)
					{
					indexReader=null;
					indexReader=reader;
					}
				return new IndexSearcher(indexReader);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public void deletAll(){
		IndexWriter indexWriter=null;
		try {
			indexWriter=new IndexWriter(directory,new IndexWriterConfig(Version.LUCENE_46,sca));
			indexWriter.deleteAll();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
		if(indexWriter!=null){
			try {
				indexWriter.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		}
	}
	//给randomName
	public void delete(String fileRadomName){
		IndexWriter indexWriter=null;
		try {
			indexWriter=new IndexWriter(directory,new IndexWriterConfig(Version.LUCENE_46,sca));
			indexWriter.deleteDocuments(new Term("fileRadomName",fileRadomName));
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
		if(indexWriter!=null){
			try {
				indexWriter.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		}
		
	}
	/*
	 * 
	 * 
	 * 加权
	 */
	public void updateWeight(String filePath){
		IndexWriter iwriter=null;
		try {
			
			Directory directory=FSDirectory.open(new File(Indexpath));
			IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_46, sca);
			iwriter = new IndexWriter(directory, config);
			File file=new File(filePath);
			List<String> list=getBoost(file.getName(), 1);
			String boost=list.get(0);			
			float newboost=Float.parseFloat(boost)+0.5f;
			Document document=new Document();
			Field fieldContent=new Field("content",new FileReader(file),TextField.TYPE_NOT_STORED);
			fieldContent.setBoost(newboost);
			document.add(fieldContent);

			FieldType fieldType=new FieldType();
			fieldType.setIndexed(true);
			fieldType.setStored(true);
			fieldType.setTokenized(false);
			
			document.add(new Field("title",list.get(1),fieldType));
			
			Field fieldTitle=new Field("fileRadomName",file.getName(),fieldType);
			
			document.add(new Field("boost",newboost+"",fieldType));
			document.add(fieldTitle);
			iwriter.updateDocument(new Term("fileRadomName",file.getName()),document);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		finally{
			if(iwriter!=null){
				try {
					iwriter.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	/*
	 * 精确查找
	 */
	public  List<String>  getBoost(String fileRandomName,int number){
		List<String> list=new ArrayList<String>();
		try {
			IndexSearcher indexSearcher=getSearch();
			//创建搜索
			Query query=new TermQuery(new Term("fileRadomName",fileRandomName));
			//获取搜索
			TopDocs docs=indexSearcher.search(query, number);
			ScoreDoc[] sDocs=docs.scoreDocs;
				Document document=indexSearcher.doc(sDocs[0].doc);
				list.add(document.get("boost"));
				list.add(document.get("title"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	public List<String> searchByTitle(String title,int number){
		List<String> list=new ArrayList<String>();
		try {
			IndexSearcher indexSearcher=getSearch();
			//创建搜索
			QueryParser parser=new QueryParser(Version.LUCENE_46, "title", sca);
			parser.setDefaultOperator(Operator.AND);
			parser.setAllowLeadingWildcard(true);
			Query query=parser.parse("*"+title+"*");
			//获取搜索
			TopDocs docs=indexSearcher.search(query, number);
			ScoreDoc[] sDocs=docs.scoreDocs;
			System.out.println(indexReader.maxDoc());
			System.out.println(indexReader.numDocs());
			for(ScoreDoc s:sDocs){
				Document document=indexSearcher.doc(s.doc);
				list.add(PathConfig.UPLOAD_PATH+"/"+document.get("fileRadomName"));
				System.out.println("filePath   "+"/"+PathConfig.UPLOAD_PATH+document.get("fileRadomName"));
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	public  List<String> searchContent(String searchValue,int number){
		List<String> list=new ArrayList<String>();
		try {
			IndexSearcher indexSearcher=getSearch();
			//创建搜索
			QueryParser parser=new QueryParser(Version.LUCENE_46, "content", sca);
			parser.setDefaultOperator(Operator.AND);
			Query query=parser.parse(searchValue);
			//获取搜索
			TopDocs docs=indexSearcher.search(query, number);
			ScoreDoc[] sDocs=docs.scoreDocs;
			System.out.println(indexReader.maxDoc());
			System.out.println(indexReader.numDocs());
			for(ScoreDoc s:sDocs){
				Document document=indexSearcher.doc(s.doc);
				list.add(PathConfig.UPLOAD_PATH+"/"+document.get("fileRadomName"));
				System.out.println("filePath   "+PathConfig.UPLOAD_PATH+"/"+document.get("fileRadomName"));
				System.out.println(document.get("boost"));
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	public  void createIndex(String filePath,String title){
		//索引建立的路径
		//词法分析器
				IndexWriter iwriter=null;
				try {
					//创建Director
					//创建索引文件的写入
					IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_46, sca);
					iwriter = new IndexWriter(directory, config);
					//创建Document对象(相当于数据库的表)
					//创建Filed
					File files=new File(filePath);
					if(files.isDirectory())
					{	for(File file:files.listFiles()){
							Document document=new Document();
							Field content=new Field("content",new FileReader(file),TextField.TYPE_NOT_STORED);
							document.add(content);
							//只存储，不分词，不索引
							FieldType fieldType=new FieldType();
							fieldType.setIndexed(true);
							fieldType.setStored(true);
							fieldType.setTokenized(false);
							document.add(new Field("title",title,fieldType));
							document.add(new Field("fileRadomName",file.getName(),fieldType));
							document.add(new Field("boost","1",fieldType));
							iwriter.addDocument(document);
						}
					}
					else {
						Document document=new Document();
						document.add(new Field("content",new FileReader(files),TextField.TYPE_NOT_STORED));
						FieldType fieldType=new FieldType();
						fieldType.setIndexed(true);
						fieldType.setStored(true);
						fieldType.setTokenized(false);
						document.add(new Field("title",title,fieldType));
						document.add(new Field("fileRadomName",files.getName(),fieldType));
						document.add(new Field("boost","1",fieldType));
						iwriter.addDocument(document);
					}
				} catch (Exception e) {

				}
				finally{
					if(iwriter!=null)
						try {
							iwriter.close();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
				}
			}
}
