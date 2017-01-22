package main;

import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import core.ControlPanelMethods;

public class EditDialog extends JDialog{
	ArrayList<String> _retArr;
	public EditDialog(JFrame parent){
		setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		JTextField jj = new JTextField();
		add(jj);
		
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
	}
	
	public int  showDialog(){
		setVisible(true);
		return 5;
	}
}
