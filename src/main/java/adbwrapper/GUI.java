package adbwrapper;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;

public class GUI extends JFrame {

	JPanel panelMain;
	JLabel lblDevices;
	JLabel lblProcesses;
	JLabel lblSelectedApk;
	JButton btnSelectAllDevices;
	JButton btnDeselectAllDevices;
	JButton btnRefreshDevices;
	JButton btnSelectAllApp;
	JButton btnDeselectAllApp;
	JButton btnInstall;
	JButton btnSelectApk;
	JButton btnUninstall;
	JButton btnRefreshProcesses;
	JButton btnExit;
	JList lstDevices;
	JList lstProcesses;
	Box boxDevices;
	Box boxDevicesButtons;
	Box boxProcesses;
	Box boxProcessesButtons;
	// File selector
	JFileChooser selectApk;

	public GUI() {
		AdbWrapper adb = new AdbWrapper();

		this.setSize(1200, 600);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("ADB Wrapper");
		panelMain = new JPanel();
		panelMain.setLayout(new GridBagLayout());

		lblDevices = new JLabel("Select devices you would like ot use in the list below:");

		lstDevices = new JList();
		lstDevices.setLayoutOrientation(JList.HORIZONTAL_WRAP);
		lstDevices.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		lblSelectedApk = new JLabel("");
		selectApk = new JFileChooser();
		btnSelectApk = new JButton("Select apk file");

		boxDevices = Box.createVerticalBox();
		boxDevices.add(lblDevices);
		boxDevices.add(lstDevices);
		boxDevices.add(Box.createRigidArea(new Dimension(0, 100)));
		boxDevices.add(lblSelectedApk);
		boxDevices.add(btnSelectApk);

		btnSelectAllDevices = new JButton("Select all devices");
		btnSelectAllDevices.addActionListener(new SelectAllDevices());
		btnDeselectAllDevices = new JButton("Deselect all devices");
		btnDeselectAllDevices.addActionListener(new DeselectAllDevices());
		btnRefreshDevices = new JButton("Refresh devices list");

		btnInstall = new JButton("Install");

		boxDevicesButtons = Box.createVerticalBox();
		boxDevicesButtons.add(btnSelectAllDevices);
		boxDevicesButtons.add(btnDeselectAllDevices);
		boxDevicesButtons.add(btnRefreshDevices);
		boxDevicesButtons.add(btnInstall);

		lstProcesses = new JList();

		lblProcesses = new JLabel("Select application to uninstall:");
		boxProcesses = Box.createVerticalBox();
		boxProcesses.add(lblProcesses);
		boxProcesses.add(lstProcesses);

		btnSelectAllApp = new JButton("Select all applications");
		btnSelectAllApp.addActionListener(new SelectAllApps());
		btnDeselectAllApp = new JButton("Deselect all applications");
		btnDeselectAllApp.addActionListener(new DeselectAllApps());
		btnRefreshProcesses = new JButton("Refresh applications list");
		btnUninstall = new JButton("Uninstall");

		boxProcessesButtons = Box.createVerticalBox();
		boxProcessesButtons.add(btnSelectAllApp);
		boxProcessesButtons.add(btnDeselectAllApp);
		boxProcessesButtons.add(btnRefreshProcesses);
		boxProcessesButtons.add(btnUninstall);

		componentAdd(panelMain, boxDevices, 0, 0, 1, 1, GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL);
		componentAdd(panelMain, boxDevicesButtons, 1, 0, 1, 1, GridBagConstraints.NORTHWEST,
				GridBagConstraints.HORIZONTAL);
		componentAdd(panelMain, boxProcesses, 2, 0, 1, 1, GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL);
		componentAdd(panelMain, boxProcessesButtons, 3, 0, 1, 1, GridBagConstraints.NORTHWEST,
				GridBagConstraints.HORIZONTAL);

		this.add(panelMain);
		// this.pack();
		this.setVisible(true);
	}

	private void componentAdd(JPanel thePanel, JComponent comp, int xPos, int yPos, int compWidth, int compHeight,
			int place, int stretch) {
		GridBagConstraints gridConstraints = new GridBagConstraints();
		gridConstraints.gridx = xPos;

		gridConstraints.gridy = yPos;

		gridConstraints.ipadx = 5;
		gridConstraints.ipady = 5;

		gridConstraints.gridwidth = compWidth;

		gridConstraints.gridheight = compHeight;

		gridConstraints.weightx = 100;

		gridConstraints.weighty = 100;

		gridConstraints.insets = new Insets(5, 5, 5, 5);

		gridConstraints.anchor = place;

		gridConstraints.fill = stretch;

		thePanel.add(comp, gridConstraints);

	}

	public void fillList(JList list, ListModel listModel) {
		int i = listModel.getSize();
		list.setModel(listModel);
		list.setVisibleRowCount(i);

	}

	class SelectAllDevices implements ActionListener {

		public void actionPerformed(ActionEvent e) {

			int start = 0;
			int end = lstDevices.getModel().getSize() - 1;
			if (end >= 0) {
				lstDevices.setSelectionInterval(start, end);
			}

		}

	}

	class SelectAllApps implements ActionListener {

		public void actionPerformed(ActionEvent e) {

			int start = 0;
			int end = lstProcesses.getModel().getSize() - 1;
			if (end >= 0) {
				lstProcesses.setSelectionInterval(start, end);
			}

		}

	}

	class DeselectAllDevices implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			lstDevices.clearSelection();

		}

	}

	class DeselectAllApps implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			lstProcesses.clearSelection();

		}

	}

	void addRefreshDevicesListener(ActionListener RefreshDevices) {
		btnRefreshDevices.addActionListener(RefreshDevices);
	}

	void addRefreshAppsListener(ActionListener RefreshApps) {
		btnRefreshProcesses.addActionListener(RefreshApps);
	}

	void addUninstallApp(ActionListener UninstallApp) {
		btnUninstall.addActionListener(UninstallApp);
	}

	void addInstallApp(ActionListener InstallApp) {
		btnInstall.addActionListener(InstallApp);
	}

	List getSelectedApps() {

		List selectedApps = lstProcesses.getSelectedValuesList();

		return selectedApps;
	}

	List getSelectedDevices() {
		List selectedDevices = lstDevices.getSelectedValuesList();

		return selectedDevices;
	}

	String getApkPath() {
		String apkPath = lblSelectedApk.getText();
		return apkPath;
	}

	void setFilePathLabel(String filepath) {
		lblSelectedApk.setText(filepath);
	}

	void addSelectApk(ActionListener SelectApk) {
		btnSelectApk.addActionListener(SelectApk);
	}

	void showSelectDirectoryDialog() {
		int select = selectApk.showDialog(null, "Select Directory");
		String selectedfilePath;
		String selectedfileName;
		if (select == selectApk.APPROVE_OPTION) {
			selectedfilePath = (selectApk.getSelectedFile()).getParentFile().getPath();
			selectedfileName = (selectApk.getSelectedFile()).getName();
			setFilePathLabel(selectedfilePath + "/" + selectedfileName);
		} else {
			selectedfilePath = "";
			selectedfileName = "";
		}
	}
}
