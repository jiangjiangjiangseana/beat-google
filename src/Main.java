import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import javax.swing.border.EmptyBorder;

import java.awt.event.ActionListener;

import java.awt.event.ActionEvent;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class Main extends JFrame {

 private JPanel contentPane;
 private JPanel contentPane2;
 private JTextField textField;
 private int times = 0;
 boolean hasAnotherKeyword = true;
 FetchSearchingResult fsr = new FetchSearchingResult();
 ArrayList<WebNode> resultWebs = new ArrayList<WebNode>();

 /**
  * Launch the application.
  */
 public static void main(String[] args) throws IOException{
  EventQueue.invokeLater(new Runnable() {
   public void run() {
    try {
     Main frame = new Main();
     frame.setVisible(true);
    } catch (Exception e) {
     e.printStackTrace();
    }
   }
  });

 }

 /**
  * Create the frame.
  */
 
  public Main() throws IOException{
   
   setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   setBounds(200, 200, 900, 600);
   contentPane = new JPanel();
   contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
   contentPane.setLayout(new BorderLayout(0, 0));
   setContentPane(contentPane);
   
     
      contentPane.setLayout(null); 
      JButton b1=new JButton("搜尋");
      b1.addActionListener(new ActionListener() {
       public void actionPerformed(ActionEvent e) {
        String keyword=textField.getText();
           fsr.add(new Keyword(keyword)); 
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     JFrame jframe = new JFrame("搜尋結果");
     jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         JTextArea content = new JTextArea(600,100) ;
         Font x = new Font("Serif",0,20);
         content.setFont(x);
         JScrollPane g = new JScrollPane(content);
         jframe.setBounds(500,500,1200,800);
         jframe.setVisible(true);
         jframe.setLayout (null);        
         g.setBounds(10,10,1150,700);
         jframe.add(g);
        
        
      
        HashMap<String, String> resultMap=new HashMap<String,String>();
     try {
      resultMap = fsr.query();
     } catch (IOException e1) {
      
      e1.printStackTrace();
     }
        
        //for(int i=0;i<resultMap.size();i++) {
        for(Entry<String,String>result:resultMap.entrySet()){
            WebNode resultWebNode=new WebNode(result.getValue(),fsr.getList()); 
         resultWebNode.setTitle(result.getKey());
            resultWebs.add(resultWebNode);
        
        }
        
        for(WebNode resultWeb:resultWebs) {
         try {
      resultWeb.setChildren();
     } catch (IOException e1) {
      // TODO Auto-generated catch block
      e1.printStackTrace();
     }
         resultWeb.calculateScore();
        }
        
        Rank rank=new Rank();
        ArrayList<WebNode> sortedResult=rank.sort(resultWebs);
        for(int i=0;i<resultWebs.size();i++) {
         content.append("Title:"+resultWebs.get(i).getTitle()+"\r\n");
         content.append("Cite:"+resultWebs.get(i).getCite()+"\r\n");
         content.append("Score:"+resultWebs.get(i).getScore()+"\r\n");
         content.append("\r\n");
            }
          }
        
  
       }
  );      
      b1.setBounds(750,450,100,40);  
      contentPane.add(b1, BorderLayout.NORTH);
      
      contentPane.setLayout(null);
      textField = new JTextField();
      contentPane.add(textField, BorderLayout.CENTER);
      textField.setColumns(10);     
      textField.setBounds(150,230,450,40);
      
      contentPane.setLayout(null); 
      JButton b2=new JButton("新增關鍵字");
      b2.addActionListener(new ActionListener() {

 public void actionPerformed(ActionEvent e) {
        if(times < 4) {
        times += 1;
        contentPane.setLayout(null);
           textField = new JTextField();
           contentPane.add(textField, BorderLayout.CENTER);
           textField.setColumns(10);
           int arg = 230 + 40*times;
           textField.setBounds(150,arg,450,40); 
           }    
       }        
      });
      b2.setBounds(600,230,125,40);  
      contentPane.add(b2, BorderLayout.NORTH);
      
      contentPane.setLayout(null);
      JLabel l1 = new JLabel("");
      l1.setIcon(new ImageIcon("C:\\\\Users\\\\Lin\\\\Desktop\\\\123.jpg"));
      contentPane.add(l1, BorderLayout.EAST);
      l1.setBounds(750,250,100,100);
      
     
     
      
     
      
      
     
  }

}