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

import main.TemplateDialog;

public class HeaderPanel extends JPanel{
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
          add(tempSel);
          DefaultListModel<String> listModel = new DefaultListModel<>();
          listModel.addElement("USA");
          listModel.addElement("India");
          listModel.addElement("Vietnam");
          listModel.addElement("Canada");
          listModel.addElement("Denmark");
          listModel.addElement("France");
          listModel.addElement("Great Britain");
          listModel.addElement("Japan");
          JList countryList = new JList<>(listModel);
          countryList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
          countryList.setLayoutOrientation(JList.VERTICAL_WRAP);
          countryList.setVisibleRowCount(-1);
          countryList.addListSelectionListener(new ListSelectionListener() {
              @Override
              public void valueChanged(ListSelectionEvent e) {
                  if (!e.getValueIsAdjusting()) {
                      final List<String> selectedValuesList = countryList.getSelectedValuesList();
                      System.out.println(selectedValuesList);
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
	}
}
