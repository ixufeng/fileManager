package common;

public class Catalog{
	
	private String name;
	private String link;
	
	
	public Catalog(String name, String link){
		this.name = name;
		this.link = link;
	}
	
	public Catalog(){
		
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
}