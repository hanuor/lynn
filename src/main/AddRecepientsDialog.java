package main;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JTextField;
import javax.swing.SwingWorker;

import core.DatabasePing;
import core.SmtpCorelogic;

public class AddRecepientsDialog extends JDialog {
	public AddRecepientsDialog(ArrayList<String> packageData) {
		setLayout(new BorderLayout());
		setTitle("Enter email fields");
		setSize(500, 500);
		final Toolkit toolkit = Toolkit.getDefaultToolkit();
		final Dimension screenSize = toolkit.getScreenSize();
		final int x = (screenSize.width - this.getWidth()) / 2;
		final int y = (screenSize.height - this.getHeight()) / 2;
		this.setLocation(x, y);
		this.setVisible(true);
		System.out.println("Sleep on the floiee" + DatabasePing.getSenderDetails());
		ArrayList<String> sender = new ArrayList<String>();
		sender.add("shantanu@internshala.com");
		sender.add("hanuor11");
		JTextField addRecep = new JTextField();
		add(addRecep, BorderLayout.NORTH);
		JButton sendPackage = new JButton();
		sendPackage.setText("Deploy");
		add(sendPackage, BorderLayout.SOUTH);
		sendPackage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SwingWorker<Boolean, Void> worker = new SwingWorker<Boolean, Void>() {

					@Override
					protected Boolean doInBackground() throws Exception {
						// TODO Auto-generated method stub
						String recepData = addRecep.getText();

						ArrayList<String> sendRecep = stringtoArrayList(recepData);
						for (String recepEmail : sendRecep) {
							SmtpCorelogic.sendMessage(packageData, sender,
									recepEmail);
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
