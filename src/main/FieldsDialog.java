package main;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import core.DatabasePing;

public class FieldsDialog extends JDialog{
	public FieldsDialog(JPanel parent, int subCount, int emailCount, String subText, String emaiText){
		
		System.out.println("Get Sub text" +subText);
		 setLayout(new BorderLayout());
		 setTitle("Enter subject fields");
		 setSize(500,500);
		 ArrayList<JTextField> textFields = new ArrayList<JTextField>();
		 final Toolkit toolkit = Toolkit.getDefaultToolkit();
		 final Dimension screenSize = toolkit.getScreenSize();
		 final int x = (screenSize.width - this.getWidth()) / 2;
		 final int y = (screenSize.height - this.getHeight()) / 2;
		 this.setLocation(x, y);
		 this.setVisible(true);
		 //panelControl(true, parent, subCount, emailCount);
		 JPanel parentFrame = new JPanel();
		 parentFrame.setLayout(new BorderLayout());
		 JPanel cpanel = new JPanel();
			cpanel.setLayout(new BoxLayout(cpanel, BoxLayout.PAGE_AXIS));
			JLabel head = new JLabel();
			head.setText("Subject Panel");
			cpanel.add(head);
			System.out.println("HEERA");
			//getContentPane().add(scroll);
			for(int i = 1; i<= subCount; i++){
				JPanel fields = new JPanel();
				fields.setLayout(new BoxLayout(fields, BoxLayout.X_AXIS));
				JLabel fName = new JLabel();
				fName.setText("Field #"+i);
				
				fields.add(fName);
				JTextField ftext = new JTextField();
				ftext.setName("Field " + i);
				textFields.add(ftext);
				
				fields.add(ftext);
				cpanel.add(fields);
			}
			System.out.println("TExt fields created " + textFields.size());
			for(int i=0; i<textFields.size(); i++){
				System.out.println("TExt fields created " + textFields.get(i).getName());	
			}
			cpanel.setVisible(true);
			parentFrame.add(cpanel, BorderLayout.NORTH );
			JScrollPane scroll = new JScrollPane(parentFrame);
			scroll.setPreferredSize(new Dimension(450,400));
			getContentPane().add(scroll, BorderLayout.NORTH);
			
			JPanel extraP = new JPanel();
			extraP.setLayout(new BorderLayout());
			JButton save = new JButton("Save");
			extraP.add(save, BorderLayout.SOUTH);
			getContentPane().add(extraP, BorderLayout.SOUTH);
			System.out.println("inders "+ subText);
			save.addActionListener(new ActionListener() { 
	        	  public void actionPerformed(ActionEvent e) { 
	        		  
	        		  System.out.println("inders SSSEEE "+ subText);
	        		  ArrayList<String> answers = new ArrayList<String>();
	        		  for(int i = 0; i< textFields.size();i++){
	        			  System.out.println("Update   " + textFields.get(i).getText());
	        		  }
	        		  ArrayList<String> _arr = new ArrayList<String>();
	        		  convertToproper(subText, _arr, answers);
	        		 
	        		  //System.out.println("Breathing" + .toString());
	        		  //ArrayList<String> ope = convertToproper(subText, _arr);
	        		  //for(int i=0; i< ope.size(); i++){
	        			//  System.out.println("Item " + ope.get(i));
	        		  //}
	        		  if(emailCount!=0){
	        			  
	        			  
	        		  EmailDialog eDialog = new EmailDialog(getContentPane(), emailCount);
	        		  eDialog.setVisible(true);
	        		  dispose();
	        		  }else{
	        			  dispose();
	        		  }
	        	  }
	        	});
			
			
	}
	public void convertToproper(String _sub, ArrayList<String> _arr, ArrayList<String> answers){
		//System.out.println("This" + _arr.toString());
		int beginIndex = _sub.indexOf("#*#");
		int newIndex = beginIndex+3;
		int pivotIndex = 0;
		
		String newS = null;

		String remS = null;
		if(newIndex > (_sub.length()-1)){
			
			_arr.add(_sub.substring(pivotIndex, beginIndex+3));
			 for(int i=0; i< _arr.size(); i++){
     			 System.out.println("Item " + _arr.get(i));
     		  }
			System.out.println("Heya a " + _arr);
			
		}else{
			newS = _sub.substring(pivotIndex, beginIndex+3);
			remS = _sub.substring(beginIndex + 3);
			
			pivotIndex = beginIndex+3;
			_arr.add(newS);
		}
		
		convertToproper(remS, _arr, answers);
	}
	
}
