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
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.Popup;
import javax.swing.PopupFactory;
import javax.swing.SwingWorker;

import net.java.balloontip.BalloonTip;

import com.hanuor.main.Hub;

import core.DatabasePing;
import core.GetMac;
import core.SmtpCorelogic;

public class AddRecepientsDialog extends JDialog {
	private HashMap<String, String> recepPacket = new HashMap<String, String>();

	public AddRecepientsDialog(ArrayList<String> packageData,
			ArrayList<String> hints) {
		setLayout(new BoxLayout(getContentPane(), BoxLayout.PAGE_AXIS));
		setTitle("Enter email fields");
		setSize(500, 500);
		final Toolkit toolkit = Toolkit.getDefaultToolkit();
		final Dimension screenSize = toolkit.getScreenSize();
		final int x = (screenSize.width - this.getWidth()) / 2;
		final int y = (screenSize.height - this.getHeight()) / 2;
		this.setLocation(x, y);
		this.setVisible(true);
		JLabel addEmails = new JLabel();
		addEmails
				.setText("Add recepient emails (To add multiple emails separate each email with a semicolon ';')");
		add(addEmails);
		StringBuilder sbuilder = new StringBuilder();

		JTextArea addRecep = new JTextArea();

		JScrollPane jScrollPane = new JScrollPane(addRecep);
		jScrollPane.getViewport().setPreferredSize(new Dimension(470, 500));
		if (hints != null) {
			int counter = 0;
			int upto = 0;
			if (hints.size() >= 3) {
				upto = 2;
			} else {
				upto = hints.size();
			}
			for (int i = 0; i <= upto; i++) {
				sbuilder.append(hints.get(i));
				sbuilder.append("\t");
				if (counter < upto) {
					sbuilder.append(", ");
				}
				counter++;
			}
			BalloonTip balloon = new BalloonTip(jScrollPane, ""
					+ sbuilder.toString());

		}
		add(jScrollPane);
		JLabel CcText = new JLabel("Cc: ");
		add(CcText);

		HashMap<String, String> sender = DatabasePing.getSenderDetails();
		JPanel header = new JPanel();
		header.setLayout(new BorderLayout());
		JTextArea CCTextField = new JTextArea(1, 1);
		JScrollPane ccScroller = new JScrollPane(CCTextField);
		ccScroller.getViewport().setPreferredSize(new Dimension(470, 500));
		add(ccScroller);

		JLabel BccText = new JLabel("Bcc:");
		add(BccText);
		JTextArea BCCTextField = new JTextArea(1, 1);
		JScrollPane bccScroller = new JScrollPane(BCCTextField);
		bccScroller.getViewport().setPreferredSize(new Dimension(470, 500));
		add(bccScroller);
		JPanel footer = new JPanel();
		footer.setLayout(new BorderLayout());
		JButton sendPackage = new JButton();
		sendPackage.setText("Deploy");
		footer.add(sendPackage, BorderLayout.SOUTH);
		add(header);
		add(footer);
		sendPackage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SwingWorker<Boolean, Void> worker = new SwingWorker<Boolean, Void>() {

					@Override
					protected Boolean doInBackground() throws Exception {
						// TODO Auto-generated method stub
						String recepData = addRecep.getText();
						System.out.println(BCCTextField.getText());
						recepPacket.put("CcRecepients", CCTextField.getText());
						recepPacket.put("BccRecepients", BCCTextField.getText());

						ArrayList<String> sendRecep = stringtoArrayList(recepData);
						ArrayList<String> ccRecep = stringtoArrayList(CCTextField
								.getText());
						ArrayList<String> bccRecep = stringtoArrayList(BCCTextField
								.getText());
						String macId = GetMac.getMac();

						try {
							for (String recepEmail : sendRecep) {
								recepPacket.put("recepientEmail", recepEmail);
								SmtpCorelogic.sendMessage(packageData, sender,
										recepPacket);
							}
						} catch (Exception e) {
							// TODO Auto-generated catch block

							e.printStackTrace();
						} finally {
							if (sendRecep.size() != 0) {

								Hub.initializeNodes(sendRecep, macId, macId
										+ "/data/sendMail", 1);
							}
							if (ccRecep.size() != 0) {

								Hub.initializeNodes(ccRecep, macId, macId
										+ "/data/ccMail", 1);
							}
							if (bccRecep.size() != 0) {

								Hub.initializeNodes(bccRecep, macId, macId
										+ "/data/bccMail", 1);
							}
						}
						return true;
					}

					@Override
					protected void done() {
						// TODO Auto-generated method stub
						super.done();
						dispose();

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
