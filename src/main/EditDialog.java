package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingWorker;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import core.ControlPanelMethods;
import core.DatabasePing;
import core.GetSet;

public class EditDialog extends JDialog{
	private JTextField subText;
	private JTextField emailText;
	private List<String> tempSelected = null;
	private ArrayList<String> _retArr;
	private GetSet gs = new GetSet();
	public EditDialog(JFrame parent){
		setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		 setSize(500,500);
		 final Toolkit toolkit = Toolkit.getDefaultToolkit();
		 final Dimension screenSize = toolkit.getScreenSize();
		 final int x = (screenSize.width - this.getWidth()) / 2;
		 final int y = (screenSize.height - this.getHeight()) / 2;
		 this.setLocation(x, y);
		 this.setVisible(true);
		JLabel mySelect = new JLabel();
		mySelect.setText("Select a template to modify");
		add(mySelect);
		
		 DefaultListModel<String> listModel = new DefaultListModel<>();
         
         _retArr = ControlPanelMethods.getList();
         if(_retArr == null){
       	 // System.out.println("HEre!!!");
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
         JList countryList = new JList<>(listModel);
         
         countryList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
         countryList.setLayoutOrientation(JList.VERTICAL_WRAP);
         countryList.setVisibleRowCount(-1);
         countryList.setSelectedIndex(0); 
         JScrollPane listScroller = new JScrollPane(countryList);
         listScroller.setBackground(Color.decode("#C9A798"));
         listScroller.setPreferredSize(new Dimension(250, 80));
         add(listScroller);
         
         JLabel subHeading = new JLabel();
         subHeading.setText("Subject");
         add(subHeading);
         subText = new JTextField();
         add(subText);
         JLabel emailHeading = new JLabel();
         emailHeading.setText("Body");
         add(emailHeading);
         emailText = new JTextField();
         add(emailText);
         JPanel jPan = new JPanel();
         JButton jButton = new JButton();
         jButton.setText("Save");
         jPan.add(jButton);
         add(jPan);
         jButton.addActionListener(new ActionListener() { 
       	  public void actionPerformed(ActionEvent e) { 
       		  System.out.println("   " + gs.getSelectedKey());
       		  DatabasePing.saveTemplateMessage(gs.getSelectedKey(), subText.getText().toString(), emailText.getText().toString());
       		  dispose();
       	  }
         });
         
         countryList.addListSelectionListener(new ListSelectionListener() {
             @Override
             public void valueChanged(ListSelectionEvent e) {
            	 System.out.println("Vamos hn");
            	  SwingWorker<Boolean, Void> worker = new SwingWorker<Boolean, Void>(){

						@Override
						protected Boolean doInBackground()
								throws Exception {
							// TODO Auto-generated method stub
							tempSelected = countryList.getSelectedValuesList();
		                	  gs.setSelectedKey(tempSelected.get(0).toString());
		                	  HashMap<String, String> mmp = ControlPanelMethods.getSubEmail(gs.getSelectedKey().toString());
		                	  gs.setSubCount(ControlPanelMethods.getCount(mmp.get("subject")));
		                	  gs.setEmailCount(ControlPanelMethods.getCount(mmp.get("message")));
		                	  gs.setSubText(mmp.get("subject").toString());
		                	  gs.setEmailText(mmp.get("message").toString());			
							return true;
						}

						@Override
						protected void done() {
							// TODO Auto-generated method stub
							super.done();
	        	        	  ControlPanelMethods.separatorToFields(gs.getSubText());
							subText.setText(ControlPanelMethods.separatorToFields(gs.getSubText()));
							emailText.setText(ControlPanelMethods.separatorToFields(gs.getEmailText()));
							
						}
						@Override
						protected void process(List<Void> arg0) {
							// TODO Auto-generated method stub
							super.process(arg0);
						}
					 };
          		  worker.execute();
            	 
             }
         });
	}
	

}
