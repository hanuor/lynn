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
import javax.swing.JPanel;
import javax.swing.JTextField;

public class TemplateDialog extends JDialog{

	public TemplateDialog(JPanel parent) {
		//super();
		
		 setLayout(new GridLayout(2,1));
		 setSize(1000,1000);
		 JPanel panel = new JPanel();
         setLayout(new BorderLayout());
         JTextField temp = new JTextField();
         temp.setPreferredSize(new Dimension(500, 500));
         panel.add(temp, BorderLayout.CENTER);
         
         add(panel);
         
         addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent){
               dispose();
            }
         });
		
	}

}
