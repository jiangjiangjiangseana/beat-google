import java.util.ArrayList;

public class Rank {

 public WebNode root;
 public ArrayList<WebNode> nodeList = new ArrayList<WebNode>();

 public Rank() {
  
 }



 public ArrayList<WebNode> sort(ArrayList<WebNode> nodeList) {

  if (nodeList.size() == 0)
   return nodeList;

  for (int i = 0; i < nodeList.size(); i++) {

   for (int j = 0; j < nodeList.size() - 1 - i; j++) {

    if (nodeList.get(j + 1).getScore() > nodeList.get(j).getScore()) {
     WebNode temp = nodeList.get(j + 1);
     nodeList.set(j + 1, nodeList.get(j));
     nodeList.set(j, temp);
    }
   }
  }

  return nodeList;
 }
}
