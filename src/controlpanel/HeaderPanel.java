package controlpanel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import core.ControlPanelMethods;
import main.TemplateDialog;

public class HeaderPanel extends JPanel{
	List<String> tempSelected = null;
	public HeaderPanel(){
		 ArrayList<String> _arrStr;
          setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
          JPanel theme = new JPanel();
          theme.setLayout(new BorderLayout());
          JLabel cp = new JLabel("Panel");
          cp.setVerticalAlignment(JLabel.CENTER);
          cp.setHorizontalAlignment(JLabel.CENTER);
          Font font = cp.getFont();
          cp.add(new JSeparator(SwingConstants.VERTICAL));
          Font boldFont = new Font(font.getFontName(), Font.BOLD, font.getSize());
          cp.setFont(boldFont);
          add(cp);
          JPanel tempSel = new JPanel();
          tempSel.setLayout(new BoxLayout(tempSel, BoxLayout.X_AXIS));
          JButton jb= new JButton("View saved templates");
          tempSel.add(jb); 
          JButton jbt = new JButton("Add a template");
          jbt.setVisible(true);
          tempSel.add(jbt);
          JPanel mpanel = new JPanel();
          JButton ref = new JButton("Refresh Panel");
          ref.setVisible(true);
          tempSel.add(ref, BorderLayout.WEST);
          add(tempSel);
          DefaultListModel<String> listModel = new DefaultListModel<>();
          ArrayList<String> _retArr;
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
          JList countryList = new JList<>(listModel);
          countryList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
          countryList.setLayoutOrientation(JList.VERTICAL_WRAP);
          countryList.setVisibleRowCount(-1);

          countryList.addListSelectionListener(new ListSelectionListener() {
              @Override
              public void valueChanged(ListSelectionEvent e) {
                  if (!e.getValueIsAdjusting()) {
                	  tempSelected = countryList.getSelectedValuesList();
                  }
              }
          });
          JScrollPane listScroller = new JScrollPane(countryList);
          listScroller.setPreferredSize(new Dimension(250, 80));

        add(listScroller);
          jbt.addActionListener(new ActionListener() { 
          	  public void actionPerformed(ActionEvent e) {   
       		  TemplateDialog tmpD = new TemplateDialog(tempSel);
          		  tmpD.setVisible(true);
         		  
          	  }
          	});
          add(new JSeparator(SwingConstants.VERTICAL));
          ref.addActionListener(new ActionListener() { 
          	  public void actionPerformed(ActionEvent e) {   
          		 // listModel.addElement("Blah");
          		  revalidate();
          		  
          	  }
          	});
	}
}
