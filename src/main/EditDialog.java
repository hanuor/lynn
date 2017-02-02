package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import core.ControlPanelMethods;

public class EditDialog extends JDialog{
	private JTextField subText;
	private JTextField emailText;
	private ArrayList<String> _retArr;
	public EditDialog(JFrame parent){
		setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		 setSize(500,500);
		 final Toolkit toolkit = Toolkit.getDefaultToolkit();
		 final Dimension screenSize = toolkit.getScreenSize();
		 final int x = (screenSize.width - this.getWidth()) / 2;
		 final int y = (screenSize.height - this.getHeight()) / 2;
		 this.setLocation(x, y);
		 this.setVisible(true);
		JTextField jj = new JTextField();
		//add(jj);
		
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
         countryList.addListSelectionListener(new ListSelectionListener() {
             @Override
             public void valueChanged(ListSelectionEvent e) {
            	 System.out.println("Vamos hn");
            	 
             }
         });
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
         add(jPan);
	}
	
	public int  showDialog(){
		setVisible(true);
		return 5;
	}
}
