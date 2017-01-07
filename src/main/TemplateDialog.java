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
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class TemplateDialog extends JDialog{

	public TemplateDialog(JPanel parent) {
		//super();
		
		 setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		 setSize(800,800);
		
         JLabel yMsg = new JLabel();
 		yMsg.setText("Email Subject");
 		add(yMsg);
         JEditorPane temp = new JEditorPane();
         
 		JScrollPane editorScrollPane = new JScrollPane(temp);
 		editorScrollPane.setVerticalScrollBarPolicy(
 		                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
 		editorScrollPane.setPreferredSize(new Dimension(750, 750));
 		editorScrollPane.setMinimumSize(new Dimension(10, 10));
       //  temp.setPreferredSize(new Dimension(750, 750));
        add( editorScrollPane, BorderLayout.CENTER);
         
         
        
         
         addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent){
               dispose();
            }
         });
		
	}

}
