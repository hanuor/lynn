package main;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingWorker;

import core.ControlPanelMethods;

public class EmailDialog extends JDialog{
	
	private String _finalEmail = null;
	private HashMap<String, String> _getAns = new HashMap<String, String>();
	private ArrayList<JTextField> _txtFields = new ArrayList<JTextField> ();	
	
	public EmailDialog(Container parent, int emailCount, String emailText, String dataFromPrevious, JTextField subObject, JTextArea emailObject){
		 setLayout(new BorderLayout());
		 setTitle("Enter email fields");
		 setSize(500,500);
		 final Toolkit toolkit = Toolkit.getDefaultToolkit();
		 final Dimension screenSize = toolkit.getScreenSize();
		 final int x = (screenSize.width - this.getWidth()) / 2;
		 final int y = (screenSize.height - this.getHeight()) / 2;
		 this.setLocation(x, y);
		 this.setVisible(true);
		 JPanel parentFrame = new JPanel();
		 parentFrame.setLayout(new BorderLayout());
		 JPanel cpanel = new JPanel();
			cpanel.setLayout(new BoxLayout(cpanel, BoxLayout.PAGE_AXIS));
			JLabel head = new JLabel();
			head.setText("Email Panel");
			cpanel.add(head);
			 for(int i = 1; i<= emailCount; i++){
				JPanel fields = new JPanel();
				fields.setLayout(new BoxLayout(fields, BoxLayout.X_AXIS));
				JLabel fName = new JLabel();
				fName.setText("Field #"+i);
				fields.add(fName);
				JTextField ftext = new JTextField();
				ftext.setName("Field " + i);
				_txtFields.add(ftext);
				
				fields.add(ftext);
				cpanel.add(fields);
			}
			cpanel.setVisible(true);
			parentFrame.add(cpanel, BorderLayout.NORTH );
			JScrollPane scroll = new JScrollPane(parentFrame);
			scroll.setPreferredSize(new Dimension(450,400));
			getContentPane().add(scroll, BorderLayout.NORTH);
			
			JPanel extraP = new JPanel();
			extraP.setLayout(new BorderLayout());
			JButton save = new JButton("Save");
			extraP.add(save, BorderLayout.SOUTH);
			getContentPane().add(extraP, BorderLayout.SOUTH);
			save.addActionListener(new ActionListener() { 
	        	  public void actionPerformed(ActionEvent e) { 
	        		  ArrayList<String> answers = new ArrayList<String>();
	        		  for(int i = 0; i< _txtFields.size();i++){
	        			  answers.add(_txtFields.get(i).getText());
	        		  }
	        		  ArrayList<String> _arr = new ArrayList<String>();
	        		  convertToproper(0,emailText, _arr, answers);
	        		 	  
	        		  setVisible(false);
	        		  //Update the aferSign panel
	        		  SwingWorker<Boolean, Void> worker = new SwingWorker<Boolean, Void>(){

							@Override
							protected Boolean doInBackground()
									throws Exception {
								// TODO Auto-generated method stub
			                				
								return true;
							}

							@Override
							protected void done() {
								// TODO Auto-generated method stub
								super.done();
								  
								
							}
							@Override
							protected void process(List<Void> arg0) {
								// TODO Auto-generated method stub
								super.process(arg0);
							}
						 };
              		  worker.execute();
	        		  
	        		  
	        		  
	        		  dispose();
	        		  
	        	  }
	        	});
			
			getRootPane().setDefaultButton(save);
	}

	
	public void convertToproper(int pivvot, String _sub, ArrayList<String> _arr, ArrayList<String> answers){
		int beginIndex = _sub.indexOf("#*#");
		int newIndex = beginIndex+3;
		int pivotIndex = 0;
		String newS = null;
		String remS = null;
		if(beginIndex!=-1){
		if(newIndex > (_sub.length()-1)){
			StringBuilder buildStr = new StringBuilder();
			_arr.add(_sub.substring(pivotIndex, beginIndex+3));
			 for(int i=0; i< _arr.size(); i++){
				 buildStr.append(_arr.get(i).replace("#*#", answers.get(i).toString()));
     		  }
			 _getAns.put("email", buildStr.toString());
			return;
		}else{
			
			newS = _sub.substring(pivotIndex, beginIndex+3);
			pivvot = beginIndex+3;
			remS = _sub.substring(pivvot);
			_arr.add(newS);
							}
			}else{
				StringBuilder buildStr = new StringBuilder();
					 for(int i=0; i< _arr.size(); i++){
						 buildStr.append(_arr.get(i).replace("#*#", answers.get(i).toString()));
		     		  }
					 buildStr.append(_sub);
					 _getAns.put("email", buildStr.toString());
					 _finalEmail = buildStr.toString();
					return;
			}
		convertToproper(pivvot, remS, _arr, answers);
	}
	public HashMap<String, String> showDialog(boolean _check){
		setVisible(_check);
		return _getAns;
		
	}
}
