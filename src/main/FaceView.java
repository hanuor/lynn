package main;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.*;

import org.json.JSONException;
import org.json.JSONObject;

import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;

import core.DatabasePing;
import core.GetMac;
public class FaceView {
	
	public FaceView(){

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
	                frame.add(new TestPane());
	                frame.add(new MessagePane());
	                frame.add(new SenderPane());
	                //frame.pack();
	                frame.setLocationRelativeTo(null);
	                frame.setVisible(true);
	            }
	        });
	}
		
		
	public class TestPane extends JPanel {

        public TestPane() {
        ArrayList<String> _arrStr;
            setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
           
            JPanel tempSel = new JPanel();
            tempSel.setLayout(new BorderLayout());
            JButton jb= new JButton("Hi");
            tempSel.add(jb); 
            JPanel panel = new JPanel();
            panel.setLayout(new GridBagLayout());
            _arrStr = DatabasePing.getEmailandPassword(GetMac.getMac());
            System.out.println("Heya");
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.insets = new Insets(2, 2, 2, 2);

            panel.add(new JLabel("Your email"), gbc);
            gbc.gridx++;
            panel.add(new JLabel("Your password"), gbc);

            gbc.gridx = 0;
            gbc.gridy++;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            JTextField email = new JTextField(10);
            if(_arrStr.size()!=0){
            	email.setText(_arrStr.get(0).toString());
            }else{
            	email.setText("");      
            }
           
            panel.add(email, gbc);
            gbc.gridx++;
            JTextField password = new JTextField(10);
            if(_arrStr.size()!=0){
            	password.setText(_arrStr.get(1).toString());
            }else{
            	password.setText("");      
            }
            panel.add(password, gbc);
            gbc.gridx = 0;
            gbc.gridy++;
            gbc.gridwidth = 2;
            JButton jbt = new JButton("Register!");
            jbt.setVisible(true);
            panel.add(jbt, gbc);
            if(_arrStr.size()==0 && _arrStr.size()==0)
            {
            	panel.setVisible(true);
            }else{
            	panel.setVisible(false);
            }
            add(panel);
            add(tempSel);
            jbt.addActionListener(new ActionListener() { 
            	  public void actionPerformed(ActionEvent e) { 
            		 
            		  TemplateDialog tmpD = new TemplateDialog(tempSel);
            		  tmpD.setVisible(true);
            		  
            		  // DatabasePing.userRegistration(email.getText(), password.getText());
            	  }
            	});
        }
        
       
    }
	public static void main(String args[]){
		new FaceView();
	}

}
