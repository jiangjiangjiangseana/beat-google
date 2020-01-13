import java.io.IOException;
import java.util.ArrayList;

public class WebNode {

	
	private String title;
	private String cite;
	private ArrayList<WebNode> children;
	private double score;
	private HTMLConnector connector;
	private ArrayList<Keyword> keywordList;
	
	public WebNode(String cite,ArrayList<Keyword> keywordList) {
		
		this.cite=cite;
		this.children=new ArrayList<WebNode>();
		this.score=0;
		this.keywordList=keywordList;
		this.connector=new HTMLConnector(cite);
	}
	
	
	public void setChildren() throws IOException {
		ArrayList<String> childrenURL=new ArrayList<String>();
		childrenURL=connector.getChildrenURL();
		for(int i=0;i<childrenURL.size();i++) {
			
			WebNode child=new WebNode(childrenURL.get(i),keywordList);
			children.add(child);
		}
	}
	
	public void setTitle(String title) {
		this.title=title;
	}
	
	
	public String getTitle() {
		return title;
	}
	
	public String getCite() {
		return cite;
	}
	
	
	
	public void calculateScore(){
		
		double userKeywordScore=0;
		double childrenScore;
		double otherScore;
		try {
			for (int i = 0; i < keywordList.size(); i++) {
				Keyword keyword = keywordList.get(i);
				userKeywordScore += this.connector.countKeyword(keyword) * keyword.getWeight();
				this.connector.countKeyword(keywordList.get(i));
			}
			childrenScore = 0;
			for (WebNode child : children) {
				for (int i = 0; i < keywordList.size(); i++) {
					Keyword keyword = keywordList.get(i);
					childrenScore += child.connector.countKeyword(keyword) * (keyword.getWeight() - 2);
				}
			}
			otherScore = 0;
			for (int i = 0; i < keywordList.size(); i++) {
				otherScore += this.connector.countKeyword("convenient") * 0.5;
			} 
			for (int i = 0; i < keywordList.size(); i++) {
				otherScore += this.connector.countKeyword("lifehacks") * 1;
			} 
			for (int i = 0; i < keywordList.size(); i++) {
				otherScore += this.connector.countKeyword("³Ð·N") * 0.5;
			}
			for (int i = 0; i < keywordList.size(); i++) {
				otherScore += this.connector.countKeyword("Â¬ªù") * 2;
			} 
			this.score=userKeywordScore+childrenScore+otherScore;
		} catch (Exception e) {
			e.printStackTrace();
		}		
		
	}
	
	
	public double getScore() {
		return score;
	}
	
}
