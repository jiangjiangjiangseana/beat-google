import java.util.ArrayList;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.io.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

class FetchSearchingResult {
	
	private ArrayList<Keyword> list;
	
	public String url;
	public String content;
	public HashMap<String,String> retVal;
	
	public FetchSearchingResult() {
		this.list=new ArrayList<Keyword>();
	}
	
	public void add(Keyword keyword) {
		this.list.add(keyword);
		System.out.println("Done");
	}
	
	
	private String getUrl() {
		String keywords=list.get(0).getName();
		if(list.size()>1) {
		for(int i=1;i<list.size();i++) {
		  keywords+="+"+list.get(i).getName();
		}}
		this.url="https://www.google.com.tw/search?q="+keywords+"+lifehack&oe=utf8&num=15";
		return url;
	}
	
	
	private String fetchContent() throws IOException {
		this.getUrl();
		String retVal="";
		URL urlStr=new URL(this.url);
		URLConnection connection=urlStr.openConnection();
		connection.setRequestProperty("User-Agent","Mozilla/5.0(Macintosh;U;Intel Mac OS X 10.4;en-US;rv:1.9.2.2)Gecko/20100316Firefox/3.6.2");
		connection.connect();
		
		InputStream inputStream=connection.getInputStream();
		InputStreamReader inReader=new InputStreamReader(inputStream,"UTF8");
		BufferedReader bf=new BufferedReader(inReader);
		String line=null;
		while((line=bf.readLine())!=null) {
			retVal+=line;
		}
		return retVal;
		
	}
	
	public HashMap<String,String>query() throws IOException{
		if(this.content==null) {
			this.content=fetchContent();
		}
		HashMap<String,String> retVal=new HashMap<String,String>();
		Document document=Jsoup.parse(this.content);
		Elements lis=document.select("div.g");
		
		for(Element li : lis)
		  {
		   try 
		   {
		    Element h3 = li.select("h3.r").get(0);
		    String title = h3.text();
		 
		    Element cite = li.getElementsByTag("a").first();
		    String citeUrl = "https://www.google.com.tw"+ cite.attr("href");
		    
		    retVal.put(title, citeUrl);
		    
		   } catch (IndexOutOfBoundsException e) {
		    
		   }
		   
		  }
		this.retVal=retVal;
		return this.retVal;
	}
	
	public HashMap<String,String> getQueryResult(){
		return this.retVal;
	}

	
	public ArrayList<Keyword> getList(){
		return this.list;
	}
}
