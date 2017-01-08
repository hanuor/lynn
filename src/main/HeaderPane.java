package main;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import core.DatabasePing;
import core.GetMac;

public class HeaderPane extends JPanel{
		public HeaderPane(){
			 ArrayList<String> _arrStr;
	            setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
	           
	            JPanel tempSel = new JPanel();
	            tempSel.setLayout(new BoxLayout(tempSel, BoxLayout.Y_AXIS));
	            JButton jb= new JButton("Hi sadasdasd");
	            tempSel.add(jb); 
	            JButton jbt = new JButton("Add a template");
	            jbt.setVisible(true);
	            tempSel.add(jbt);
	            add(tempSel);
	          
	            jbt.addActionListener(new ActionListener() { 
	            	  public void actionPerformed(ActionEvent e) { 
	            		  
	            		 // DatabasePing.userRegistration(email.getText(), password.getText());
	            		  
	            		  //System.exit(0);
	            		  
	         		  TemplateDialog tmpD = new TemplateDialog(tempSel);
	            		  tmpD.setVisible(true);
	           		  
	            		  //
	            	  }
	            	});
		}
}
