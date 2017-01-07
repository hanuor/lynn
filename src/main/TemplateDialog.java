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
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

public class TemplateDialog extends JDialog{

	public TemplateDialog(JPanel parent) {
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
		 
		 JPanel header = new JPanel();
		 Border border1 = header.getBorder();
	        Border margin1 = new EmptyBorder(10,10,10,10);
	       header.setBorder(new CompoundBorder(border1, margin1));
	        header.setLayout(new BorderLayout());
	        JLabel topH = new JLabel("Enter a template message below"); 
	        header.add(topH, BorderLayout.CENTER);
	        add(header);
        /* JLabel yMsg = new JLabel();
         Border border = yMsg.getBorder();
		 Border margin = new EmptyBorder(50,10,50,10);*/
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
        add(saveBut);
         
         
        
         
         addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent){
               dispose();
            }
         });
		
	}

}
