package main;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;

public class MessagePane extends JPanel {
	public MessagePane(){
		setLayout(new GridLayout(3,3));
		  JTextField email = new JTextField(10);
		  add(email);
		  JPanel senderFrame = new JPanel();
		  setLayout(new GridBagLayout());
          
          GridBagConstraints gbc = new GridBagConstraints();
          gbc.gridx = 0;
          gbc.gridy = 0;
          gbc.insets = new Insets(2, 2, 2, 2);

          senderFrame.add(new JLabel("Send to? (email)"), gbc);
          gbc.gridx++;
          //add(new JLabel("Your password"), gbc);

          gbc.gridx = 0;
          gbc.gridy++;
          gbc.fill = GridBagConstraints.HORIZONTAL;
          JTextField senderEmail = new JTextField(10);
          senderFrame.add(email, gbc);
          gbc.gridx++;
          JTextField password = new JTextField(10);
          senderFrame.add(password, gbc);

          gbc.gridx = 0;
          gbc.gridy++;
          gbc.fill = GridBagConstraints.NONE;
          gbc.gridwidth = 2;
          JButton jbt = new JButton("Ok");
          senderFrame.add(jbt, gbc);
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
          	add(senderFrame);
		  
		  
		/*  JLabel senderText = new JLabel("Send this message to: ");
		  add(senderText);
		  JTextField sender = new JTextField(10);
		  add(sender);*/
		  
	}

}
