package main;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.HashMap;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingWorker;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

import core.ControlPanelMethods;
import core.DatabasePing;

public class TemplateDialog extends JDialog{

	public TemplateDialog(JFrame frameboy,JPanel parent) {
		//super();
		
		 setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		 setTitle("Enter a template message");
		 setSize(900,900);
		 final Toolkit toolkit = Toolkit.getDefaultToolkit();
		 final Dimension screenSize = toolkit.getScreenSize();
		 final int x = (screenSize.width - this.getWidth()) / 2;
		 final int y = (screenSize.height - this.getHeight()) / 2;
		 this.setLocation(x, y);
		 this.setVisible(true);
		 
		 JPanel head = new JPanel();
		 Border border0 = head.getBorder();
	        Border margin0 = new EmptyBorder(10,10,10,10);
	       head.setBorder(new CompoundBorder(border0, margin0));
	        head.setLayout(new BorderLayout());
	        JLabel extension = new JLabel("Preferred template name");
	        head.add(extension, BorderLayout.NORTH);
	        add(head);
	        JTextField extField = new JTextField();
	        add(extField);
	        
		 JPanel header = new JPanel();
		 Border border1 = header.getBorder();
	        Border margin1 = new EmptyBorder(10,10,10,10);
	       header.setBorder(new CompoundBorder(border1, margin1));
	        header.setLayout(new BorderLayout());
	      
	       
	        JLabel topH = new JLabel("Enter the template subject below"); 
	        header.add(topH, BorderLayout.CENTER);
	        add(header);
	       
	        JTextField subField = new JTextField();
	        add(subField);
	        
        /* JLabel yMsg = new JLabel();
         Border border = yMsg.getBorder();
		 Border margin = new EmptyBorder(50,10,50,10);*/
	        JPanel msgHeader = new JPanel();
			 Border border2 = header.getBorder();
		        Border margin2 = new EmptyBorder(10,10,10,10);
		       msgHeader.setBorder(new CompoundBorder(border2, margin2));
		        msgHeader.setLayout(new BorderLayout());
		        JLabel msg = new JLabel("Enter the template message below"); 
		        msgHeader.add(msg, BorderLayout.CENTER);
		        add(msgHeader);
         JEditorPane temp = new JEditorPane();
 		JScrollPane editorScrollPane = new JScrollPane(temp);
 		editorScrollPane.setVerticalScrollBarPolicy(
 		                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
 		editorScrollPane.setPreferredSize(new Dimension(750, 750));
 		editorScrollPane.setMinimumSize(new Dimension(10, 10));
       //  temp.setPreferredSize(new Dimension(750, 750));
        add( editorScrollPane, BorderLayout.CENTER);
        JPanel saveBut = new JPanel();
        Border border = saveBut.getBorder();
        Border margin = new EmptyBorder(10,10,10,10);
       saveBut.setBorder(new CompoundBorder(border, margin));
        saveBut.setLayout(new BorderLayout());
        JButton sButton = new JButton("Save"); 
        saveBut.add(sButton, BorderLayout.CENTER);
        sButton.addActionListener(new ActionListener() { 
        	  public void actionPerformed(ActionEvent e) { 
        		 if(extField.getText().toString().isEmpty() || subField.getText().toString().isEmpty() || temp.getText().toString().isEmpty()){
        			 JOptionPane.showOptionDialog(null, "Something is missing", "Warning",
        					 JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
        					 null, null, null);
        			// JOptionPane.showMessageDialog(getContentPane(), "Hello World");
        		 }else{
        		  DatabasePing.saveTemplateMessage(extField.getText().toString(),subField.getText().toString(), temp.getText().toString());
        		  dispose();
        		 }
//        		  SwingWorker<Boolean, Void> worker = new SwingWorker<Boolean, Void>(){
//
//						@Override
//						protected Boolean doInBackground()
//								throws Exception {
//							// TODO Auto-generated method stub
//		                				
//							return true;
//						}
//
//						@Override
//						protected void done() {
//							// TODO Auto-generated method stub
//							super.done();
//							
//						
//						}
//
//						@Override
//						protected void process(List<Void> arg0) {
//							// TODO Auto-generated method stub
//							super.process(arg0);
//						}
//						
//						
//          			  
//          		  };
//          		  worker.execute();
        		  // DatabasePing.userRegistration(email.getText(), password.getText());
        	  }
        	});
        add(saveBut);
        
         
        
         
         addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent){
               dispose();
            }
         });
		
	}

}
