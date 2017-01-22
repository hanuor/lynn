package main;

import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class EditDialog extends JDialog{
	public EditDialog(JFrame parent){
		setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		JTextField jj = new JTextField();
		add(jj);
		
	}
	
	public void showDialog(){
		
	}
}
