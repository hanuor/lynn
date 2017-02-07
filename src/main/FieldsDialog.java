package main;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import core.DatabasePing;

public class FieldsDialog extends JDialog {
	private HashMap<String, String> _ans;
	private String _finalSub;

	public FieldsDialog(JPanel parent, int subCount, int emailCount,
			String subText, String emailText, JTextField subObject,
			JTextArea emailObject) {

		setLayout(new BorderLayout());
		setTitle("Enter subject fields");
		setSize(500, 500);
		ArrayList<JTextField> textFields = new ArrayList<JTextField>();
		final Toolkit toolkit = Toolkit.getDefaultToolkit();
		final Dimension screenSize = toolkit.getScreenSize();
		final int x = (screenSize.width - this.getWidth()) / 2;
		final int y = (screenSize.height - this.getHeight()) / 2;
		this.setLocation(x, y);
		this.setVisible(true);
		// panelControl(true, parent, subCount, emailCount);
		JPanel parentFrame = new JPanel();
		parentFrame.setLayout(new BorderLayout());
		JPanel cpanel = new JPanel();
		cpanel.setLayout(new BoxLayout(cpanel, BoxLayout.PAGE_AXIS));
		JLabel head = new JLabel();
		head.setText("Subject Panel");
		cpanel.add(head);
		System.out.println("HEERA");
		// getContentPane().add(scroll);
		for (int i = 1; i <= subCount; i++) {
			JPanel fields = new JPanel();
			fields.setLayout(new BoxLayout(fields, BoxLayout.X_AXIS));
			JLabel fName = new JLabel();
			fName.setText("Field #" + i);

			fields.add(fName);
			JTextField ftext = new JTextField();
			ftext.setName("Field " + i);
			textFields.add(ftext);

			fields.add(ftext);
			cpanel.add(fields);
		}
		System.out.println("TExt fields created " + textFields.size());
		for (int i = 0; i < textFields.size(); i++) {
			System.out.println("TExt fields created "
					+ textFields.get(i).getName());
		}
		cpanel.setVisible(true);
		parentFrame.add(cpanel, BorderLayout.NORTH);
		JScrollPane scroll = new JScrollPane(parentFrame);
		scroll.setPreferredSize(new Dimension(450, 400));
		getContentPane().add(scroll, BorderLayout.NORTH);

		JPanel extraP = new JPanel();
		extraP.setLayout(new BorderLayout());
		JButton save = new JButton("Save");
		extraP.add(save, BorderLayout.SOUTH);
		getContentPane().add(extraP, BorderLayout.SOUTH);
		System.out.println("inders " + subText);
		save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				_finalSub = null;
				// System.out.println("inders SSSEEE "+ subText);
				ArrayList<String> answers = new ArrayList<String>();
				for (int i = 0; i < textFields.size(); i++) {
					answers.add(textFields.get(i).getText());
					// System.out.println("Update   " +
					// textFields.get(i).getText());
				}
				ArrayList<String> _arr = new ArrayList<String>();
				convertToproper(0, subText, _arr, answers);

				EmailDialog eDialog = new EmailDialog(getContentPane(),
						emailCount, emailText, _finalSub, subObject,
						emailObject);
				eDialog.setModal(true);
				_ans = eDialog.showDialog(true);
				if (_ans != null) {
					_ans.put("subject", _finalSub);
					setVisible(false);
					dispose();
				}
				if (emailCount != 0) {

				} else {

					dispose();
				}
			}
		});
		// Close the fielddialog on button 'enter'. If done through keyboard
		getRootPane().setDefaultButton(save);
	}

	public void convertToproper(int pivvot, String _sub,
			ArrayList<String> _arr, ArrayList<String> answers) {
		int beginIndex = _sub.indexOf("#*#");
		int newIndex = beginIndex + 3;
		int pivotIndex = 0;
		String newS = null;
		String remS = null;
		if (beginIndex != -1) {
			if (newIndex > (_sub.length() - 1)) {
				StringBuilder buildStr = new StringBuilder();
				_arr.add(_sub.substring(pivotIndex, beginIndex + 3));
				for (int i = 0; i < _arr.size(); i++) {
					buildStr.append(_arr.get(i).replace("#*#",
							answers.get(i).toString()));
				}
				_finalSub = buildStr.toString();
				return;
			} else {

				newS = _sub.substring(pivotIndex, beginIndex + 3);
				pivvot = beginIndex + 3;
				remS = _sub.substring(pivvot);
				_arr.add(newS);
			}
		} else {
			StringBuilder buildStr = new StringBuilder();
			for (int i = 0; i < _arr.size(); i++) {
				buildStr.append(_arr.get(i).replace("#*#",
						answers.get(i).toString()));
			}
			buildStr.append(_sub);
			_finalSub = buildStr.toString();
			return;
		}
		convertToproper(pivvot, remS, _arr, answers);
	}

	public HashMap<String, String> showDialog(boolean check) {
		setVisible(check);
		return _ans;
	}

}
