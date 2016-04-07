package suggest;
import java.io.File;
import java.io.IOException;

import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.analysis.util.CharArraySet;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.search.spell.PlainTextDictionary;
import org.apache.lucene.search.spell.SpellChecker;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

import file.PathConfig;

public class Suggest {
	String dictionaryPath=PathConfig.dictionaryPath;
	String spellIndexPath=PathConfig.dictionaryIndex;
	CharArraySet cas = new CharArraySet(Version.LUCENE_46, 0, true);
	SmartChineseAnalyzer sca = new SmartChineseAnalyzer(Version.LUCENE_46, cas);
	Directory dir=null;
	 SpellChecker sp=null;
	public Suggest(){
		try {
			// 创建词典
			 dir=FSDirectory.open(new File(spellIndexPath));
			// 实例化拼写检查器
			 sp = new SpellChecker(dir);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void setAccute(float num){
		sp.setAccuracy(num);
	}
	public void addWords(){
		try {
			sp.indexDictionary(new PlainTextDictionary(new File(PathConfig.todayDictionary)),new IndexWriterConfig(Version.LUCENE_46,sca),true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void creatIndex(){
		try {
			sp.clearIndex();
			// 对词典进行索引
			sp.indexDictionary(new PlainTextDictionary(new File(dictionaryPath)),new IndexWriterConfig(Version.LUCENE_46,sca),true);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	//检查是否存在
	public boolean checkExist(String value){
		try {
			return sp.exist(value);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	public String[] suggest(String searchValue,int num) {
		 try {
			// “错误”的搜索
			String search = searchValue;
			// 建议个数
			 int suggestionNumber = num;
			// 获取建议的关键字
			String[] suggestions = sp.suggestSimilar(search, suggestionNumber);
			// 显示结果
			return suggestions;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 return null;
	}
}
