package suggest;

public class UpdateSuggest {
	public void update(String str){
		Suggest s=new Suggest();
		if(s.checkExist(str))
			return;
		WriteToFile w=new WriteToFile();
		w.write(str);
		s.addWords();
		w.close();
	}
}
