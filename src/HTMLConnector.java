import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;


public class HTMLConnector {
	
	private String url;
	private String content;
	private ArrayList<String> childrenURL;
	
	
	public HTMLConnector(String url) {
		this.url=url;
		childrenURL=new ArrayList<String>();
	}

	private String fetchContent(){
		String retVal = null;
		try {
			retVal = "";
			//System.out.println(url);
			
			URL urlStr = new URL(this.url);
			URLConnection connection = urlStr.openConnection();
			connection.setRequestProperty("User-Agent",
					"Mozilla/5.0(Macintosh;U;Intel Mac OS X 10.4;en-US;rv:1.9.2.2)Gecko/20100316Firefox/3.6.2");
			connection.connect();
			InputStream inputStream = connection.getInputStream();
			InputStreamReader inReader = new InputStreamReader(inputStream, "UTF8");
			BufferedReader bf = new BufferedReader(inReader);
			String line = null;
			while ((line = bf.readLine()) != null) {				
				retVal += line;
			} 
		} catch (Exception e) {
			System.out.print("");
		}
		return retVal;
	}
		public int countKeyword(Keyword keyword) throws IOException{
			  
			  if(content == null) { 
			   content = fetchContent();
			  }
			  
			  content = content.toUpperCase();
			  String keywordName = keyword.getName().toUpperCase();
			  
			  int counts = 0;
			  
			  while(content.indexOf(keywordName)>-1) {
					content=content.substring(content.indexOf(keywordName)+keywordName.length());
			   counts+= + 1 ;  
			  }
			  
			  return counts;
			  
			     }
		
		public int countKeyword(String keyword) throws IOException{
			  
			  if(content == null) { 
			   content = fetchContent();
			  }
			  
			  content = content.toUpperCase();
			  String upperKeyword = keyword.toUpperCase();
			  
			  int counts = 0;
			  
			  while(content.indexOf(upperKeyword)>-1) {
					content=content.substring(content.indexOf(upperKeyword)+upperKeyword.length());
			   counts+= + 1 ;  
			  
			  }
			  return counts;
			  
			     }
		
		
		public ArrayList<String> getChildrenURL() throws IOException {
			if(content == null) { 
				   content = fetchContent();
			 }
			  
			  int indexOfOpen = 0;
			  
			 // System.out.println("-------------------------------------");
			  
			  while ((indexOfOpen = content.indexOf("a href=\"",indexOfOpen))!= -1) {
			    indexOfOpen = indexOfOpen +8; 
			    int indexOfClose = content.indexOf("\"",indexOfOpen);
			    String childURL = content.substring(indexOfOpen, indexOfClose);
			    
			    if(!childURL.startsWith("http")) {
			    	continue;
			    }
			    
			    childrenURL.add(childURL);
			    if(childrenURL.size()>3)
			    	break;
			    indexOfOpen = indexOfClose;
			  }
			  return childrenURL;
			  }
		
	}