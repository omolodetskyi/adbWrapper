package adbwrapper;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.ListModel;

public class Controller {
	private AdbWrapper adb;
	private GUI gui;

	public ListModel addProcessesToList() {
		DefaultListModel listModel = new DefaultListModel();
		ArrayList<String> apps;
		apps = adb.getProcesses();
		int size_app = apps.size();

		String[] appToList = new String[size_app];
		int j = 0;
		for (String app : apps) {
			appToList[j] = app;
			listModel.addElement(appToList[j]);
			j = j + 1;
		}
		return (ListModel) listModel;
	}

	public ListModel addDevicesToList() {
		ArrayList<String> devices;
		DefaultListModel listModel = new DefaultListModel();
		devices = adb.getAllDevicesName();
		int size = devices.size();

		String[] devicesToList = new String[size];
		int i = 0;
		for (String device : devices) {
			devicesToList[i] = device;
			listModel.addElement(devicesToList[i]);
			i = i + 1;
		}
		return (ListModel) listModel;
	}

	public void runFlow(AdbWrapper adb, GUI gui) {
		this.adb = adb;
		this.gui = gui;
		gui.fillList(gui.lstProcesses, addProcessesToList());
		gui.fillList(gui.lstDevices, addDevicesToList());
		gui.addRefreshDevicesListener(new RefreshDevices());
		gui.addRefreshAppsListener(new RefreshApps());
		gui.addUninstallApp(new UninstallApp());
		gui.addInstallApp(new InstallApp());
		gui.addSelectApk(new SelectApk());
		gui.addExit(new actExit());

	}

	class RefreshDevices implements ActionListener {

		public void actionPerformed(ActionEvent e) {

			gui.fillList(gui.lstDevices, addDevicesToList());

		}
	}

	class RefreshApps implements ActionListener {

		public void actionPerformed(ActionEvent e) {

			gui.fillList(gui.lstProcesses, addProcessesToList());

		}

	}

	class UninstallApp implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			List processnames = gui.getSelectedApps();
			List devicesnames = gui.getSelectedDevices();
			String deviceId, devicename;
			for (int i = 0; i < processnames.size(); i++) {
				for (int j = 0; j < devicesnames.size(); j++) {
					devicename = (String) devicesnames.get(j);
					int start = devicename.indexOf("(");
					int end = devicename.indexOf(")");
					deviceId = devicename.substring(start + 1, end);
					System.out.println(deviceId);
					adb.uninstallOne((String) processnames.get(i), deviceId);
				}
			}

		}

	}

	class InstallApp implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			String deviceId, devicename;
			List devicesnames = gui.getSelectedDevices();

			for (int i = 0; i < devicesnames.size(); i++) {
				devicename = (String) devicesnames.get(i);
				int start = devicename.indexOf("(");
				int end = devicename.indexOf(")");
				deviceId = devicename.substring(start + 1, end);
				adb.installOne(gui.getApkPath(), deviceId, false);
			}

		}

	}

	class SelectApk implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			String[] selectedFile;

			gui.showSelectDirectoryDialog();

		}

	}

	class actExit implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}

	}

}
