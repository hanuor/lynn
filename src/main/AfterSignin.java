package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.SwingWorker;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import core.ControlPanelMethods;
import core.GetSet;
import main.FaceView.TestPane;

public class AfterSignin {
	List<String> tempSelected = null;
	ArrayList<String> _retArr;
	ArrayList<String> _data;
	GetSet gs;
	
	public AfterSignin(){
		gs = new GetSet();
		 EventQueue.invokeLater(new Runnable() {
	            @Override
	            public void run() {
	                try {
	                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
	                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
	                }
	                _data  = new ArrayList<String>();
	                JFrame frame = new JFrame("Lynn - A crappy but useful template messenger");

	              
	                frame.getContentPane().setLayout(
	                	    new BoxLayout(frame.getContentPane(), BoxLayout.PAGE_AXIS)
	                	);
	                frame.setSize(900, 500);
	                headerView(frame);
	                System.out.println(gs.getSelectedKey());
	                middlePane(frame);
	            //    frame.add(new HeaderPanel());
	                //frame.add(new MessagePane());
	                sendPane(frame);
	                //frame.add(new SenderPane());
	                //frame.pack();
	                frame.setLocationRelativeTo(null);
	                frame.setVisible(true);
	                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		               
	            }
	        });
	}
	
	public void headerView(JFrame frame){
		      JLabel subHeading = new JLabel(); 
		      subHeading.setText("Subject");
	          JTextField subtext = new JTextField();
	          JLabel emailHeading = new JLabel();
	          JTextField emailText = new JTextField();
	          emailHeading.setText("Email");
	          JPanel invi = new JPanel(); 
	          ArrayList<String> _arrStr;
			  
	          
	          JPanel parent = new JPanel();
	          parent.setLayout(new BoxLayout(parent, BoxLayout.Y_AXIS));
	          parent.setBackground(Color.decode("#C9A798"));
	          JPanel theme = new JPanel();
	          theme.setLayout(new BorderLayout());
	          theme.setBackground(Color.decode("#C9A798"));
	          JLabel cp = new JLabel("Panel");
	          cp.setVerticalAlignment(JLabel.CENTER);
	          cp.setHorizontalAlignment(JLabel.CENTER);
	          Font font = cp.getFont();
	          cp.add(new JSeparator(SwingConstants.VERTICAL));
	          Font boldFont = new Font(font.getFontName(), Font.BOLD, font.getSize());
	          cp.setFont(boldFont);
	          parent.add(cp);
	          JPanel tempSel = new JPanel();
	          tempSel.setLayout(new BoxLayout(tempSel, BoxLayout.X_AXIS));
	          tempSel.setBackground(Color.decode("#C9A798"));
	          JButton jb= new JButton("View saved templates");
	          tempSel.add(jb); 
	          JButton jbt = new JButton("Add a template");
	          jbt.setVisible(true);
	          tempSel.add(jbt);
	          JButton ref = new JButton("Refresh Panel");
	          ref.setVisible(true);
	          tempSel.add(ref, BorderLayout.WEST);
	          parent.add(tempSel);
	          DefaultListModel<String> listModel = new DefaultListModel<>();
	          
	          _retArr = ControlPanelMethods.getList();
	          if(_retArr == null){
	        	  System.out.println("HEre!!!");
	        	  _retArr = new ArrayList<String>();
	        	  _retArr.add("Nothing here. Click on 'Add a template' to add templates. Or click refresh");
	        	  for(int i = 0; i< _retArr.size(); i++){
	        		  listModel.addElement(_retArr.get(i).toString());
	        	  }
	          }else{

	        	  System.out.println("Noit nul");
	        	  for(int i = 0; i< _retArr.size(); i++){
	        		  listModel.addElement(_retArr.get(i).toString());
	        	  }
	          }
	          JPanel llheader = new JPanel();
	          llheader.setLayout(new BoxLayout(llheader, BoxLayout.X_AXIS));
	          llheader.setBackground(Color.decode("#C9A798"));
	          JSeparator separator = new JSeparator();
	          parent.add(separator);
	          JLabel listHeader = new JLabel("Select a template");
	          Font font0 = listHeader.getFont();
	          listHeader.add(new JSeparator(SwingConstants.VERTICAL));
	          Font boldFont0 = new Font(font.getFontName(), Font.BOLD, font.getSize());
	          listHeader.setFont(boldFont0);
	          listHeader.setHorizontalAlignment(SwingConstants.CENTER);
	          llheader.add(listHeader);
	          parent.add(llheader);
	          JList countryList = new JList<>(listModel);
	          
	          countryList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
	          countryList.setLayoutOrientation(JList.VERTICAL_WRAP);
	          countryList.setVisibleRowCount(-1);
	          countryList.setSelectedIndex(1); 
	         
	          invi.setLayout(new BoxLayout(invi, BoxLayout.PAGE_AXIS));
	          invi.setBackground(Color.decode("#C9A798"));
	          invi.setVisible(true);
	          invi.add(subHeading);
	          invi.add(subtext);
	          invi.add(emailHeading);
	          invi.add(emailText);
	          countryList.addListSelectionListener(new ListSelectionListener() {
	              @Override
	              public void valueChanged(ListSelectionEvent e) {
	                  if (!e.getValueIsAdjusting()) {
	                	  _data.add("Hey");
	                	
	                	
                		 // gs.setEmailText(mmp.get("email").toString());
	                	  //System.out.println(ControlPanelMethods.getCount(mmp.get("subject")));
	                	 // if(tempSelected.size()==1){
	                		  SwingWorker<Boolean, Void> worker = new SwingWorker<Boolean, Void>(){

								@Override
								protected Boolean doInBackground()
										throws Exception {
									// TODO Auto-generated method stub
									  tempSelected = countryList.getSelectedValuesList();
				                	  gs.setSelectedKey(tempSelected.get(0).toString());
				                	  System.out.println("Swing woker thread");
				                	  
				                	  HashMap<String, String> mmp = ControlPanelMethods.getSubEmail(gs.getSelectedKey().toString());
				                	  gs.setSubCount(ControlPanelMethods.getCount(mmp.get("subject")));
				                	  gs.setEmailCount(ControlPanelMethods.getCount(mmp.get("email")));
				                	  gs.setSubText(mmp.get("subject").toString());
				                	  gs.setEmailText(mmp.get("message").toString());
				                				
									return true;
								}

								@Override
								protected void done() {
									// TODO Auto-generated method stub
									super.done();
									System.out.println("Swing woker thread");
				                	  
									System.out.println(" dsdsdsds"+gs.getSubCount());
			        	        	  ControlPanelMethods.separatorToFields(gs.getSubText());
			        	        	  
									//subHeading.setText("Subject");
									subtext.setText(ControlPanelMethods.separatorToFields(gs.getSubText()));
									emailText.setText(ControlPanelMethods.separatorToFields(gs.getEmailText()));
									 /* if(ControlPanelMethods.getCount(g == 0){
				                		  JOptionPane.showOptionDialog(null, "No fields to enter in the email message", "Warning",
				             					 JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
				             					 null, null, null);
				                	  }else if(ControlPanelMethods.getCount(mmp.get("email")) == 0 && ControlPanelMethods.getCount(mmp.get("subject")) == 0){
				                		  JOptionPane.showOptionDialog(null, "No fields to enter in the subject & email message", "Warning",
					             					 JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
					             					 null, null, null);
				                	  }*/
									//invi.setVisible(true);
									System.out.println("After hoie " + gs.getSubText());
									 FieldsDialog fDialog = new FieldsDialog(parent,gs.getSubCount(),gs.getEmailCount(),gs.getSubText(), gs.getEmailText());
			        	        	 HashMap<String, String> results = new HashMap<String, String>();
			        	        	 results = fDialog.showDialog(true);
									
								}
								@Override
								protected void process(List<Void> arg0) {
									// TODO Auto-generated method stub
									super.process(arg0);
								}
							 };
	                		  worker.execute();
	                		  //add a dialog for entering the fields
	        	        	  //panelControl(true, invi);
	        	        	
	        	          }
	                  //}
	              }
	          });
	        
	          JScrollPane listScroller = new JScrollPane(countryList);
	          listScroller.setBackground(Color.decode("#C9A798"));
	          listScroller.setPreferredSize(new Dimension(250, 80));

	        parent.add(listScroller);
	          jbt.addActionListener(new ActionListener() { 
	          	  public void actionPerformed(ActionEvent e) {   
	       		  TemplateDialog tmpD = new TemplateDialog(frame, tempSel);
	          		  tmpD.setVisible(true);
	         		  
	          	  }
	          	});
	          parent.add(new JSeparator(SwingConstants.VERTICAL));
	          parent.add(invi);
	          ref.addActionListener(new ActionListener() { 
	          	  public void actionPerformed(ActionEvent e) {   
	          		 System.out.println("Beeeee");
	          		 _retArr.clear();
	                 _retArr = ControlPanelMethods.getList();
	               frame.revalidate();
	          	  }
	          	});
	         
	          frame.add(parent);
		//return tempSelected.get(0).toString();
	}
	public void panelControl(boolean check, JPanel frame){
		if(check){
			JPanel cpanel = new JPanel();
			cpanel.setLayout(new BoxLayout(cpanel, BoxLayout.PAGE_AXIS));
			JLabel head = new JLabel();
			head.setText("Subject Panel");
			for(int i = 1; i<= gs.getSubCount(); i++){
				JPanel fields = new JPanel();
				fields.setLayout(new BoxLayout(fields, BoxLayout.X_AXIS));
				JLabel fName = new JLabel();
				fName.setText("Field #"+i);
				fields.add(fName);
				JTextField ftext = new JTextField();
				fields.add(ftext);
				cpanel.add(fields);
			}
			cpanel.setVisible(true);
			
		}
	}
	public void sendPane(JFrame frame){
		JPanel parent = new JPanel();
		parent.setLayout(new BoxLayout(parent, BoxLayout.Y_AXIS));
		JTextField bSave = new JTextField();
		JButton bPattern = new JButton("Send!");
		parent.add(bSave);
		parent.add(bPattern);
		 frame.add(parent);
        bPattern.addActionListener(new ActionListener() { 
        	  public void actionPerformed(ActionEvent e) { 
        		  System.out.println("Dos santos " + gs.getSelectedKey());
        		 System.out.println( ControlPanelMethods.getSubEmail(gs.getSelectedKey().toString()));
        		  
        		//  DatabasePing.userRegistration(email.getText(), password.getText());
        	  }
        	});
	}
	protected void middlePane(JFrame frame) {
		// TODO Auto-generated method stub
		JPanel jpanel = new JPanel();
		jpanel.setLayout(new BoxLayout(jpanel, BoxLayout.Y_AXIS));
		JSeparator separator = new JSeparator();
        jpanel.add(separator);
        
	}
}
