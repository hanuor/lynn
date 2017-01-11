package main;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class FieldsDialog extends JDialog{
	public FieldsDialog(JPanel parent, int subCount, int emailCount){
		 setLayout(new BorderLayout());
		 setTitle("Enter subject fields");
		 setSize(500,500);
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
			
			//getContentPane().add(scroll);
			for(int i = 1; i<= 89; i++){
				JPanel fields = new JPanel();
				fields.setLayout(new BoxLayout(fields, BoxLayout.X_AXIS));
				JLabel fName = new JLabel();
				fName.setText("Field #"+i);
				fields.add(fName);
				JTextField ftext = new JTextField();
				fields.add(ftext);
				cpanel.add(fields);
			}
			cpanel.setVisible(true);
			parentFrame.add(cpanel);
			JScrollPane scroll = new JScrollPane(cpanel);
			scroll.setPreferredSize(new Dimension(395,500));
			//getContentPane().add(scroll, BorderLayout.NORTH);
			JPanel extraP = new JPanel();
			extraP.setLayout(new BorderLayout());
			JButton jjb = new JButton("Save");
			extraP.add(jjb, BorderLayout.SOUTH);
			//getContentPane().add(extraP, BorderLayout.SOUTH);
			
	}
	
}
