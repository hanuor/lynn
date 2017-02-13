package main;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingWorker;

import core.DatabasePing;
import core.SmtpCorelogic;

public class AddRecepientsDialog extends JDialog {
	public AddRecepientsDialog(ArrayList<String> packageData) {
		setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		setTitle("Enter email fields");
		setSize(500, 500);
		final Toolkit toolkit = Toolkit.getDefaultToolkit();
		final Dimension screenSize = toolkit.getScreenSize();
		final int x = (screenSize.width - this.getWidth()) / 2;
		final int y = (screenSize.height - this.getHeight()) / 2;
		this.setLocation(x, y);
		this.setVisible(true);
		HashMap<String, String> sender = DatabasePing.getSenderDetails();
		JPanel header = new JPanel();
		header.setLayout(new BorderLayout());
		JLabel addEmails = new JLabel("Add emails (To add multiple emails separate each email with a ';')");
		add(addEmails);
		JTextField addRecep = new JTextField();
		header.add(addRecep, BorderLayout.NORTH);
		JButton sendPackage = new JButton();
		sendPackage.setText("Deploy");
		header.add(sendPackage, BorderLayout.SOUTH);
		add(header);
		sendPackage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SwingWorker<Boolean, Void> worker = new SwingWorker<Boolean, Void>() {

					@Override
					protected Boolean doInBackground() throws Exception {
						// TODO Auto-generated method stub
						String recepData = addRecep.getText();

						ArrayList<String> sendRecep = stringtoArrayList(recepData);
						try {
							for (String recepEmail : sendRecep) {
								SmtpCorelogic.sendMessage(packageData, sender,
										recepEmail);
							}
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						return true;
					}

					@Override
					protected void done() {
						// TODO Auto-generated method stub
						super.done();

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

	public ArrayList<String> stringtoArrayList(String recepients) {
		ArrayList<String> returnArray = new ArrayList<String>();
		String[] recep = recepients.split(";");
		for (int i = 0; i < recep.length; i++) {
			returnArray.add(recep[i]);
		}
		return returnArray;
	}
}
