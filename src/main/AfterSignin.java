package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.SwingWorker;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import core.ControlPanelMethods;
import core.GetSet;
import core.SendPOJO;

public class AfterSignin {
	List<String> tempSelected = null;
	ArrayList<String> _retArr;
	ArrayList<String> _data;
	GetSet gs;
	private SendPOJO sendpojo = new SendPOJO();

	public AfterSignin() {
		gs = new GetSet();
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					for (LookAndFeelInfo info : UIManager
							.getInstalledLookAndFeels()) {
						if ("Nimbus".equals(info.getName())) {
							UIManager.setLookAndFeel(info.getClassName());
							break;
						}
					}
				} catch (Exception e) {
					// If Nimbus is not available, fall back to cross-platform
					try {
						UIManager.setLookAndFeel(UIManager
								.getCrossPlatformLookAndFeelClassName());
					} catch (Exception exception) {
						// not worth my time
						System.out.println("Exception occured" + exception);
					}
				}
				_data = new ArrayList<String>();
				JFrame frame = new JFrame(
						"Lynn - A crappy but useful template messenger");

				frame.getContentPane().setLayout(
						new BoxLayout(frame.getContentPane(),
								BoxLayout.PAGE_AXIS));
				frame.setSize(900, 500);
				headerView(frame);
				sendPane(frame);
				frame.setLocationRelativeTo(null);
				frame.setVisible(true);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

			}
		});
	}

	public void headerView(JFrame frame) {
		JLabel subHeading = new JLabel();
		subHeading.setText("Subject");
		JTextField subtext = new JTextField();
		JLabel emailHeading = new JLabel();
		JTextArea emailText = new JTextArea();
		JScrollPane jspane = new JScrollPane(emailText);
		jspane.setPreferredSize(new Dimension(900, 800));
		emailHeading.setText("Email body");
		JPanel invi = new JPanel();
		ArrayList<String> _arrStr;
		JPanel parent = new JPanel();
		parent.setLayout(new BoxLayout(parent, BoxLayout.Y_AXIS));
		parent.setBackground(Color.decode("#E0E0E0"));
		JPanel theme = new JPanel();
		theme.setLayout(new BorderLayout());
		theme.setBackground(Color.decode("#E0E0E0"));
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
		tempSel.setBackground(Color.decode("#E0E0E0"));
		JButton jb = new JButton("Edit a template");
		tempSel.add(jb);
		JButton jbt = new JButton("Add a template");
		jbt.setVisible(true);
		tempSel.add(jbt);
		JButton refresh = new JButton("Refresh Panel");
		refresh.setVisible(true);
		tempSel.add(refresh, BorderLayout.WEST);
		parent.add(tempSel);
		DefaultListModel<String> listModel = new DefaultListModel<>();

		_retArr = ControlPanelMethods.getList();
		if (_retArr == null) {
			_retArr = new ArrayList<String>();
			_retArr.add("Nothing here. Click on 'Add a template' to add templates. Or click refresh");
			for (int i = 0; i < _retArr.size(); i++) {
				listModel.addElement(_retArr.get(i).toString());
			}
		} else {
			for (int i = 0; i < _retArr.size(); i++) {
				listModel.addElement(_retArr.get(i).toString());
			}
		}
		JPanel llheader = new JPanel();
		llheader.setLayout(new BoxLayout(llheader, BoxLayout.X_AXIS));
		llheader.setBackground(Color.decode("#E0E0E0"));
		JSeparator separator = new JSeparator();
		parent.add(separator);
		JLabel listHeader = new JLabel("Select a template");
		Font font0 = listHeader.getFont();
		listHeader.add(new JSeparator(SwingConstants.VERTICAL));
		Font boldFont0 = new Font(font.getFontName(), Font.BOLD, font.getSize());
		listHeader.setFont(boldFont0);
		listHeader.setHorizontalAlignment(SwingConstants.CENTER);
		llheader.add(listHeader);
		parent.add(llheader);

		invi.setLayout(new BoxLayout(invi, BoxLayout.PAGE_AXIS));
		invi.setBackground(Color.decode("#E0E0E0"));
		invi.setVisible(true);
		invi.add(subHeading);
		JPanel subPanel = new JPanel(new BorderLayout());
		subPanel.add(subtext, BorderLayout.NORTH);
		invi.add(subPanel);
		invi.add(emailHeading);
		invi.add(jspane);
		JList countryList = listPanel(listModel);

		countryList.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (!e.getValueIsAdjusting()) {
					_data.add("Hey");
					SwingWorker<Boolean, Void> worker = new SwingWorker<Boolean, Void>() {

						@SuppressWarnings("unchecked")
						@Override
						protected Boolean doInBackground() throws Exception {
							// TODO Auto-generated method stub
							tempSelected = countryList.getSelectedValuesList();
							gs.setSelectedKey(tempSelected.get(0).toString());
							HashMap<String, String> mmp = ControlPanelMethods
									.getSubEmail(gs.getSelectedKey().toString());
							gs.setSubCount(ControlPanelMethods.getCount(mmp
									.get("subject")));
							gs.setEmailCount(ControlPanelMethods.getCount(mmp
									.get("message")));
							gs.setSubText(mmp.get("subject").toString());
							gs.setEmailText(mmp.get("message").toString());

							return true;
						}

						@Override
						protected void done() {
							// TODO Auto-generated method stub
							super.done();
							ControlPanelMethods.separatorToFields(gs
									.getSubText());
							subtext.setText(ControlPanelMethods
									.separatorToFields(gs.getSubText()));
							emailText.setText(ControlPanelMethods
									.separatorToFields(gs.getEmailText()));
							FieldsDialog fDialog = new FieldsDialog(parent, gs
									.getSubCount(), gs.getEmailCount(), gs
									.getSubText(), gs.getEmailText(), subtext,
									emailText, sendpojo);
							fDialog.setModal(true);
							HashMap<String, String> results = new HashMap<String, String>();
							results = fDialog.showDialog(true);
						}

						@Override
						protected void process(List<Void> arg0) {
							// TODO Auto-generated method stub
							super.process(arg0);
						}
					};
					worker.execute();

				}
			}
		});

		JScrollPane listScroller = new JScrollPane(countryList);
		listScroller.setBackground(Color.decode("#E0E0E0"));
		listScroller.setPreferredSize(new Dimension(250, 80));
		parent.add(listScroller);
		jb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EditDialog edtD = new EditDialog(frame);

			}
		});
		jbt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TemplateDialog tmpD = new TemplateDialog(frame, tempSel);
				tmpD.setVisible(true);

			}
		});
		parent.add(new JSeparator(SwingConstants.VERTICAL));
		parent.add(invi);
		refresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				SwingWorker<Boolean, Void> worker = new SwingWorker<Boolean, Void>() {

					@Override
					protected Boolean doInBackground() throws Exception {
						// TODO Auto-generated method stub

						return true;
					}

					@Override
					protected void done() {
						// TODO Auto-generated method stub
						_retArr = ControlPanelMethods.getList();
						listModel.clear();
						if (_retArr == null) {
							_retArr = new ArrayList<String>();
							_retArr.add("Nothing here. Click on 'Add a template' to add templates. Or click refresh");
							for (int i = 0; i < _retArr.size(); i++) {
								listModel.addElement(_retArr.get(i).toString());
							}
						} else {
							for (int i = 0; i < _retArr.size(); i++) {
								listModel.addElement(_retArr.get(i).toString());
							}
						}
						listPanel(listModel);

						super.done();

					}

					@Override
					protected void process(List<Void> arg0) {
						// TODO Auto-generated method stub
						super.process(arg0);
					}
				};
				worker.execute();
				_retArr.clear();
				_retArr = ControlPanelMethods.getList();
				frame.revalidate();
			}
		});

		frame.add(parent);
	}

	public JList listPanel(DefaultListModel<String> listModel) {
		JList countryList = new JList<>(listModel);
		countryList
				.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		countryList.setLayoutOrientation(JList.VERTICAL_WRAP);
		countryList.setVisibleRowCount(-1);
		countryList.setSelectedIndex(1);
		return countryList;
	}

	public void panelControl(boolean check, JPanel frame) {
		if (check) {
			JPanel cpanel = new JPanel();
			cpanel.setLayout(new BoxLayout(cpanel, BoxLayout.PAGE_AXIS));
			JLabel head = new JLabel();
			head.setText("Subject Panel");
			for (int i = 1; i <= gs.getSubCount(); i++) {
				JPanel fields = new JPanel();
				fields.setLayout(new BoxLayout(fields, BoxLayout.X_AXIS));
				JLabel fName = new JLabel();
				fName.setText("Field #" + i);
				fields.add(fName);
				JTextField ftext = new JTextField();
				fields.add(ftext);
				cpanel.add(fields);
			}
			cpanel.setVisible(true);

		}
	}

	public void sendPane(JFrame frame) {
		JPanel parent = new JPanel();
		parent.setLayout(new BoxLayout(parent, BoxLayout.Y_AXIS));

		JButton bPattern = new JButton("Continue to add recepients");
		// parent.add(bSave);
		parent.add(bPattern);
		frame.add(parent);
		bPattern.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
//					System.out.println("Exception causing statement {{DON'T DELETE" + sendpojo.getPackageData());
					if(sendpojo.getPackageData() != null){
						AddRecepientsDialog addRecep = new AddRecepientsDialog(
								sendpojo.getPackageData());
						addRecep.setVisible(true);
					}else{
						JOptionPane.showMessageDialog(null, "Something went wrong",
								"Error", JOptionPane.INFORMATION_MESSAGE);
					}
					
				} catch (Exception e1) {
					// TODO Auto-generated catch block

					e1.printStackTrace();
				}

			}
		});
		frame.getRootPane().setDefaultButton(bPattern);
	}

	protected void middlePane(JFrame frame) {
		// TODO Auto-generated method stub
		JPanel jpanel = new JPanel();
		jpanel.setLayout(new BoxLayout(jpanel, BoxLayout.Y_AXIS));
		JSeparator separator = new JSeparator();
		jpanel.add(separator);

	}
}
