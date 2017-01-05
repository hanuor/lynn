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

import javax.swing.*;

import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
public class FaceView {
	
	public FaceView(){

Backendless.initApp( "8662F7F0-FA42-2800-FFDB-8A331467EF00", "21B58D09-56A2-3158-FF75-EB1B1237E500", "v1" );
		   EventQueue.invokeLater(new Runnable() {
	            @Override
	            public void run() {
	                try {
	                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
	                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
	                }

	                JFrame frame = new JFrame("Testing");
	                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	                frame.setLayout(new BorderLayout());
	                frame.setSize(900, 900);
	                frame.add(new TestPane(),BorderLayout.NORTH);
	                frame.add(new MessagePane(), BorderLayout.CENTER);
	                frame.add(new SenderPane(),BorderLayout.SOUTH);
	                //frame.pack();
	                frame.setLocationRelativeTo(null);
	                frame.setVisible(true);
	            }
	        });
	}
		
		/*FaceView(){
			Lynn lynn = new Lynn();
			JFrame layout = new JFrame("Lynn - Your template messenger");
			layout.setLayout(new GridLayout(3,1));
			JPanel headerPanel = new JPanel();
			headerPanel.setLayout(new GridLayout(3,1));
			
			 JTextField senderEmail = new JTextField("Enter your email-id");
			 
			 
			JTextArea area=new JTextArea("");  
			area.setBounds(0, 0, 200, 100);
			layout.add(senderEmail);
			layout.add(area);
			headerPanel.add(senderEmail);
			headerPanel.add(area);
			   headerPanel.setLayout(new GridLayout(3,3));
			   layout.add(headerPanel);
		        JButton b=new JButton("Click Here");  
		        b.setBounds(50,100,95,30); 
		        layout.add(area);  
		        layout.add(b); 
		        layout.setSize(500,500);  
		        layout.setLayout(null);  
		        layout.setVisible(true); 
		        b.addActionListener(new ActionListener(){  
		        public void actionPerformed(ActionEvent e){  
		    	System.out.println(area.getText().toString());
		    	
		    	StringBuilder strBr = new StringBuilder();
		    	strBr.append(area.getText().toString());
		    	lynn.sendMessage(strBr.toString());
		    	
		    	
		            }  
		        }); */	
		       
		        
		     
		
	public class TestPane extends JPanel {

        public TestPane() {
            setLayout(new GridBagLayout());
            
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.insets = new Insets(2, 2, 2, 2);

            add(new JLabel("Your email"), gbc);
            gbc.gridx++;
            add(new JLabel("Your password"), gbc);

            gbc.gridx = 0;
            gbc.gridy++;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            JTextField email = new JTextField(10);
            add(email, gbc);
            gbc.gridx++;
            JTextField password = new JTextField(10);
            add(password, gbc);

            gbc.gridx = 0;
            gbc.gridy++;
            gbc.fill = GridBagConstraints.NONE;
            gbc.gridwidth = 2;
            JButton jbt = new JButton("Ok");
            add(jbt, gbc);
            jbt.addActionListener(new ActionListener() { 
            	  public void actionPerformed(ActionEvent e) { 
            		  
            		  BackendlessUser user = new BackendlessUser();
            		  user.setProperty( "email", email.getText().toString() );
            		  user.setPassword( password.getText().toString() );
            		   
            		  Backendless.UserService.register( user, new AsyncCallback<BackendlessUser>()
            		  {

						@Override
						public void handleFault(BackendlessFault bFault) {
							// TODO Auto-generated method stub
							System.out.println("Error " +bFault.getMessage());
							bFault.getMessage();
							
						}

						@Override
						public void handleResponse(BackendlessUser bUser) {
							// TODO Auto-generated method stub
							
							System.out.print(bUser + "");
							
						}
            		  
            		     
            		   
            		  } );
            		  } 
            });
        }

    }
	public static void main(String args[]){
		new FaceView();
	}

}
