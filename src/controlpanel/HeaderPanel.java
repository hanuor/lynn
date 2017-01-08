package controlpanel;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

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
        
          jbt.addActionListener(new ActionListener() { 
          	  public void actionPerformed(ActionEvent e) {   
       		  TemplateDialog tmpD = new TemplateDialog(tempSel);
          		  tmpD.setVisible(true);
         		  
          	  }
          	});
          add(new JSeparator(SwingConstants.VERTICAL));
	}
}
