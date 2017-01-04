package main;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
public class FaceView {
		FaceView(){
			Lynn lynn = new Lynn();
			JFrame layout = new JFrame("Lynn - Your template messenger");
			JPanel headerPanel = new JPanel();
			 
			 JTextField senderEmail = new JTextField("Enter your email-id");
			 senderEmail.setBounds(0, 0, 100, 50);
			 
			JTextArea area=new JTextArea("");  
			area.setBounds(0, 0, 200, 100);
			headerPanel.add(senderEmail);
			headerPanel.add(area);
			
			   headerPanel.setLayout(new GridLayout(3,3));
			   layout.add(headerPanel);
		        JButton b=new JButton("Click Here");  
		        b.setBounds(50,100,95,30); 
		        layout.add(area);  
		        layout.add(b); 
		        layout.setSize(500,500);  
		        layout.setLayout(null);  
		        layout.setVisible(true); 
		        b.addActionListener(new ActionListener(){  
		        public void actionPerformed(ActionEvent e){  
		    	System.out.println(area.getText().toString());
		    	
		    	StringBuilder strBr = new StringBuilder();
		    	strBr.append(area.getText().toString());
		    	lynn.sendMessage(strBr.toString());
		    	
		    	
		            }  
		        }); 
		       
		        
		     
		}
	public static void main(String args[]){
		new FaceView();
	}

}
