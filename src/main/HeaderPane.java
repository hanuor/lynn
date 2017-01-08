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
	            tempSel.setLayout(new BorderLayout());
	            JButton jb= new JButton("Hi");
	            tempSel.add(jb); 
	            JPanel panel = new JPanel();
	            panel.setLayout(new GridBagLayout());
	            _arrStr = DatabasePing.getEmailandPassword(GetMac.getMac());
	            System.out.println("Heya");
	            GridBagConstraints gbc = new GridBagConstraints();
	            gbc.gridx = 0;
	            gbc.gridy = 0;
	            gbc.insets = new Insets(2, 2, 2, 2);

	            panel.add(new JLabel("Your email"), gbc);
	            gbc.gridx++;
	            panel.add(new JLabel("Your password"), gbc);

	            gbc.gridx = 0;
	            gbc.gridy++;
	            gbc.fill = GridBagConstraints.HORIZONTAL;
	            JTextField email = new JTextField(10);
	            if(_arrStr.size()!=0){
	            	email.setText(_arrStr.get(0).toString());
	            }else{
	            	email.setText("");      
	            }
	           
	            panel.add(email, gbc);
	            gbc.gridx++;
	            JTextField password = new JTextField(10);
	            if(_arrStr.size()!=0){
	            	password.setText(_arrStr.get(1).toString());
	            }else{
	            	password.setText("");      
	            }
	            panel.add(password, gbc);
	            gbc.gridx = 0;
	            gbc.gridy++;
	            gbc.gridwidth = 2;
	            JButton jbt = new JButton("Register!");
	            jbt.setVisible(true);
	            panel.add(jbt, gbc);
	            if(_arrStr.size()==0 && _arrStr.size()==0)
	            {
	            	panel.setVisible(true);
	            }else{
	            	panel.setVisible(false);
	            }
	            add(panel);
	            add(tempSel);
	            jbt.addActionListener(new ActionListener() { 
	            	  public void actionPerformed(ActionEvent e) { 
	            		  
	            		  DatabasePing.userRegistration(email.getText(), password.getText());
	            		  
	            		  System.exit(0);
	            		  
	     /*       		  TemplateDialog tmpD = new TemplateDialog(tempSel);
	            		  tmpD.setVisible(true);
	     */       		  
	            		  //
	            	  }
	            	});
		}
}
