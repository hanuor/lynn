package main;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;

import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;

public class MessagePane extends JPanel {
	public MessagePane() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		JLabel yMsg = new JLabel();
		yMsg.setText("Email Subject");
		add(yMsg);

		// subField.setPreferredSize(new Dimension(10, getWidth()));
		JEditorPane email = new JEditorPane();
		JScrollPane editorScrollPane = new JScrollPane(email);
		editorScrollPane
				.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		editorScrollPane.setPreferredSize(new Dimension(750, 750));
		editorScrollPane.setMinimumSize(new Dimension(10, 10));
		add(editorScrollPane);

	}

}
