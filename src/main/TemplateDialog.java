package main;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Panel;
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
		 setSize(800,800);
		 JPanel header = new JPanel();
	        header.setLayout(new BorderLayout());
	        JLabel topH = new JLabel("Enter a template message below"); 
	        header.add(topH, BorderLayout.CENTER);
	        add(header);
         JLabel yMsg = new JLabel();
         Border border = yMsg.getBorder();
		 Border margin = new EmptyBorder(50,10,50,10);
         JEditorPane temp = new JEditorPane();
 		JScrollPane editorScrollPane = new JScrollPane(temp);
 		editorScrollPane.setVerticalScrollBarPolicy(
 		                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
 		editorScrollPane.setPreferredSize(new Dimension(750, 750));
 		editorScrollPane.setMinimumSize(new Dimension(10, 10));
       //  temp.setPreferredSize(new Dimension(750, 750));
        add( editorScrollPane, BorderLayout.CENTER);
        JPanel saveBut = new JPanel();
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
