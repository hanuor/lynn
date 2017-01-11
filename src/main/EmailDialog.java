package main;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class EmailDialog extends JDialog{
	public EmailDialog(Container parent, int emailCount){
		 setLayout(new BorderLayout());
		 setTitle("Enter email fields");
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
			head.setText("Email Panel");
			cpanel.add(head);
			
			//getContentPane().add(scroll);
			for(int i = 1; i<= emailCount; i++){
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
			parentFrame.add(cpanel, BorderLayout.NORTH );
			JScrollPane scroll = new JScrollPane(parentFrame);
			scroll.setPreferredSize(new Dimension(450,400));
			getContentPane().add(scroll, BorderLayout.NORTH);
			
			JPanel extraP = new JPanel();
			extraP.setLayout(new BorderLayout());
			JButton save = new JButton("Save");
			extraP.add(save, BorderLayout.SOUTH);
			getContentPane().add(extraP, BorderLayout.SOUTH);
			save.addActionListener(new ActionListener() { 
	        	  public void actionPerformed(ActionEvent e) { 
	        		  EmailDialog eDialog = new EmailDialog(getContentPane(), emailCount);
	        		  
	        	  }
	        	});
			
			
	}

}
