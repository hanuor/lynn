package main;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import core.DatabasePing;

public class SenderPane extends JPanel {
	public SenderPane(){
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		JTextField bSave = new JTextField();
		JButton bPattern = new JButton("Send!");
		add(bSave);
		add(bPattern);
		 
        bPattern.addActionListener(new ActionListener() { 
        	  public void actionPerformed(ActionEvent e) { 
        		//  DatabasePing.userRegistration(email.getText(), password.getText());
        	  }
        	});
	
	}
	
}
