package adbwrapper;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

public class AdbWrapper {

	public AdbWrapper() {

	}

	ArrayList<String> getAllDevicesName() {
		String adbPath = getADBpath();
		String brand, model;
		ArrayList<String> alldevicesName = new ArrayList<String>();
		ArrayList<String> alldevicesId = new ArrayList<String>();
		alldevicesId = getAllDevicesId();
		for (String deviceId : alldevicesId) {
			try {
				Process proc = Runtime.getRuntime()
						.exec(adbPath + "/adb -s " + deviceId + " shell getprop ro.product.brand");
				BufferedReader stdInput = new BufferedReader(new InputStreamReader(proc.getInputStream()));
				brand = stdInput.readLine();
				Process proc2 = Runtime.getRuntime()
						.exec(adbPath + "/adb -s " + deviceId + " shell getprop ro.product.model");
				BufferedReader stdInput2 = new BufferedReader(new InputStreamReader(proc2.getInputStream()));
				model = stdInput2.readLine();
				alldevicesName.add(brand + " " + model + " (" + deviceId + ")");
				stdInput.close();
				stdInput2.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return alldevicesName;

	}

	ArrayList<String> getAllDevicesId() {
		ArrayList<String> alldevicesId = new ArrayList<String>();
		String s = null, deviceId;
		String adbPath = getADBpath();
		try {
			int i = 0, deviceStart;
			Process proc = Runtime.getRuntime().exec(adbPath + "/adb devices");
			BufferedReader stdInput = new BufferedReader(new InputStreamReader(proc.getInputStream()));
			while ((s = stdInput.readLine()) != null) {
				i = i + 1;

				if (i != 1) {
					deviceStart = s.indexOf("device");
					if (deviceStart > 0) {
						deviceId = s.substring(0, deviceStart);
						alldevicesId.add(deviceId.trim());
					}
				}
			}
			stdInput.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return alldevicesId;
	}

	void installAll(String filePath, boolean replace) {
		String adbPath = getADBpath();
		ArrayList<String> alldevicesId = getAllDevicesId();

		for (String deviceId : alldevicesId) {

			try {
				if (replace) {
					Runtime.getRuntime().exec(adbPath + "/adb -s " + deviceId + " install -r " + filePath);
				} else {
					Runtime.getRuntime().exec(adbPath + "/adb -s " + deviceId + " install " + filePath);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}

	void installOne(String filePath, String deviceId, boolean replace) {
		String adbPath = getADBpath();
		ArrayList<String> alldevicesId = getAllDevicesId();

		try {
			if (replace) {
				Runtime.getRuntime().exec(adbPath + "/adb -s " + deviceId + " install -r " + filePath);
			} else {
				Runtime.getRuntime().exec(adbPath + "/adb -s " + deviceId + " install " + filePath);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	void uninstallOne(String processname, String deviceId) {
		String adbPath = getADBpath();

		try {
			Runtime.getRuntime().exec(adbPath + "/adb -s " + deviceId + " uninstall " + processname);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	void uninstallAll(String processname) {
		String adbPath = getADBpath();
		ArrayList<String> alldevicesId = getAllDevicesId();

		for (String deviceId : alldevicesId) {

			try {
				Runtime.getRuntime().exec(adbPath + "/adb -s " + deviceId + " uninstall " + processname);
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}

	String getADBpath() {
		String adbPath = "";
		try {
			File settings = new File("settings.cfg");
			StringBuffer settingsContent = new StringBuffer();

			Scanner sc = new Scanner(settings);

			while (sc.hasNextLine()) {
				settingsContent.append(sc.nextLine());
			}

			int adbPathSettingsStart = settingsContent.indexOf("ADB_PATH");
			int adbPathEnd = settingsContent.indexOf(";", adbPathSettingsStart);
			int adbPathSettingsEnd = adbPathSettingsStart + 7;
			adbPath = settingsContent.substring(adbPathSettingsEnd + 2, adbPathEnd);
			sc.close();

		} catch (IOException e) {
			System.out.println("Can not find the file!");
			e.printStackTrace();
		}
		return adbPath;

	}

	ArrayList<String> getProcesses() {
		ArrayList<String> processes = new ArrayList<String>();
		try {
			File settings = new File("settings.cfg");
			StringBuffer settingsContent = new StringBuffer();

			Scanner sc = new Scanner(settings);

			while (sc.hasNextLine()) {
				settingsContent.append(sc.nextLine());
			}

			int porcessesStart = settingsContent.indexOf("PROCESSES") + 10;
			int processesEnd = settingsContent.indexOf(";", porcessesStart);
			String processString = settingsContent.substring(porcessesStart, processesEnd);
			String[] proc = processString.split(",");
			for (String pr : proc) {
				processes.add(pr);
			}
			sc.close();

		} catch (IOException e) {
			System.out.println("Can not find the file!");
			e.printStackTrace();
		}
		return processes;

	}

}
