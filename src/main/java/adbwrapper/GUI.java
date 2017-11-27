package adbwrapper;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class GUI extends JFrame {

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
	JCheckBox chkDevices;
	JCheckBox chkProcesses;
	Box boxDevices;
	Box boxDevicesButtons;
	Box boxProcesses;
	Box boxProcessesButtons;

	public GUI() {
		this.setSize(580, 200);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("ADB Wrapper");
		lblDevices = new JLabel("Select devices you would like ot use in the list below:");
		chkDevices = new JCheckBox("Device1");
		lblSelectedApk = new JLabel("");
		boxDevices = Box.createVerticalBox();
		boxDevices.add(lblDevices);
		boxDevices.add(chkDevices);
		boxDevices.add(lblSelectedApk);

		btnSelectAllDevices = new JButton("Select all devices");
		btnDeselectAllDevices = new JButton("Deselect all devices");
		btnRefreshDevices = new JButton("Refresh devices");
		btnSelectApk = new JButton("Select apk file");
		btnInstall = new JButton("Install");
		boxDevicesButtons = Box.createVerticalBox();
		boxDevicesButtons.add(btnSelectAllDevices);
		boxDevicesButtons.add(btnDeselectAllDevices);
		boxDevicesButtons.add(btnRefreshDevices);
		boxDevicesButtons.add(btnInstall);

		boxProcesses = Box.createVerticalBox();
		this.setVisible(true);
	}
}
