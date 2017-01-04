package main;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
public class FaceView {
		FaceView(){
			Lynn lynn = new Lynn();
			JFrame layout = new JFrame();
			JTextArea area=new JTextArea("Type here");  
		      area.setBounds(100, 190, 300, 300);  
		      
		        
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
