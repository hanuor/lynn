package main;

import java.awt.EventQueue;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import controlpanel.HeaderPanel;
import main.FaceView.TestPane;

public class AfterSignin {
	public AfterSignin(){
		 EventQueue.invokeLater(new Runnable() {
	            @Override
	            public void run() {
	                try {
	                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
	                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
	                }

	                JFrame frame = new JFrame("Lynn - A crappy but useful template messenger");
	                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	                frame.getContentPane().setLayout(
	                	    new BoxLayout(frame.getContentPane(), BoxLayout.PAGE_AXIS)
	                	);
	                frame.setSize(900, 900);
	                frame.add(new HeaderPanel());
	                //frame.add(new MessagePane());
	                frame.add(new SenderPane());
	                //frame.pack();
	                frame.setLocationRelativeTo(null);
	                frame.setVisible(true);
	            }
	        });
	}
	public void lulu(){
		
	}
}
