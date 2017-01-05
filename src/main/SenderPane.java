package main;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class SenderPane extends JPanel {
	public SenderPane(){
		setLayout(new GridLayout(1,2));
		JTextField bSave = new JTextField(10);
		JButton bPattern = new JButton("NEW_PATTERN");
		add(bSave);
		add(bPattern);
	
	}
	
}
