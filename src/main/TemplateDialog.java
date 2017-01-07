package main;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.Frame;
import java.awt.Panel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JDialog;
import javax.swing.JPanel;

public class TemplateDialog extends JDialog{

	public TemplateDialog(JPanel parent) {
		//super();
		
		 setLayout(new BorderLayout());
         Panel panel = new Panel();
         panel.add(new Button("Close"));
         add("South", panel);
         setSize(500,500);

         addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent){
               dispose();
            }
         });
		
	}

}
