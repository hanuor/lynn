package main;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
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

	                JFrame frame = new JFrame("Lynn - A crappy but useful template messenger");
	                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	                frame.getContentPane().setLayout(
	                	    new BoxLayout(frame.getContentPane(), BoxLayout.PAGE_AXIS)
	                	);
	                frame.setSize(900, 900);
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
	            }
	        });
	}
	
	public void headerView(JFrame frame){
		      
			  ArrayList<String> _arrStr;
			  JPanel parent = new JPanel();
	          parent.setLayout(new BoxLayout(parent, BoxLayout.Y_AXIS));
	          JPanel theme = new JPanel();
	          theme.setLayout(new BorderLayout());
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
	          JButton jb= new JButton("View saved templates");
	          tempSel.add(jb); 
	          JButton jbt = new JButton("Add a template");
	          jbt.setVisible(true);
	          tempSel.add(jbt);
	          JPanel mpanel = new JPanel();
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
	          
	          JSeparator separator = new JSeparator();
	          parent.add(separator);
	          JLabel listHeader = new JLabel("Select a template");
	          Font font0 = listHeader.getFont();
	          listHeader.add(new JSeparator(SwingConstants.VERTICAL));
	          Font boldFont0 = new Font(font.getFontName(), Font.BOLD, font.getSize());
	          listHeader.setFont(boldFont0);
	          listHeader.setHorizontalAlignment(SwingConstants.CENTER);
	         
	          
	         // listHeader.setText();
	          llheader.add(listHeader);
	          parent.add(llheader);
	          JList countryList = new JList<>(listModel);
	          countryList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
	          countryList.setLayoutOrientation(JList.VERTICAL_WRAP);
	          countryList.setVisibleRowCount(-1);

	          countryList.addListSelectionListener(new ListSelectionListener() {
	              @Override
	              public void valueChanged(ListSelectionEvent e) {
	                  if (!e.getValueIsAdjusting()) {
	                	  tempSelected = countryList.getSelectedValuesList();
	                	  gs.setSelectedKey(tempSelected.get(0).toString());
	                	  
	                  }
	              }
	          });
	          JScrollPane listScroller = new JScrollPane(countryList);
	          listScroller.setPreferredSize(new Dimension(250, 80));

	        parent.add(listScroller);
	          jbt.addActionListener(new ActionListener() { 
	          	  public void actionPerformed(ActionEvent e) {   
	       		  TemplateDialog tmpD = new TemplateDialog(tempSel);
	          		  tmpD.setVisible(true);
	         		  
	          	  }
	          	});
	          parent.add(new JSeparator(SwingConstants.VERTICAL));
	          ref.addActionListener(new ActionListener() { 
	          	  public void actionPerformed(ActionEvent e) {   
	          		 System.out.println("Beeeee");
	          		 _retArr.clear();
	                 _retArr = ControlPanelMethods.getList();
	                 parent.revalidate();
	          		/*SwingUtilities.updateComponentTreeUI(getRootPane());
	          		 validate();
	         		  
	         		  repaint();*/
	          		/* invalidate();
	          		
	          		*/  
	          	  }
	          	});
	         
	          frame.add(parent);
		//return tempSelected.get(0).toString();
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
         parent.add(separator);
		
	}
}
