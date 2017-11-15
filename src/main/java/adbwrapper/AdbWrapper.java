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

		} catch (IOException e) {
			e.printStackTrace();
		}
		return alldevicesId;
	}

	void installAll(String filePath) {
		String adbPath = getADBpath();
		ArrayList<String> alldevicesId = getAllDevicesId();

		for (String deviceId : alldevicesId) {

			try {
				Runtime.getRuntime().exec(adbPath + "/adb -s " + deviceId + " install " + filePath);
				// System.out.println(adbPath + "/adb -s " + deviceId + "
				// install " + filePath);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

	void uninstallAll(String processname) {
		String adbPath = getADBpath();
		ArrayList<String> alldevicesId = getAllDevicesId();

		for (String deviceId : alldevicesId) {

			try {
				Runtime.getRuntime().exec(adbPath + "/adb -s " + deviceId + " uninstall " + processname);
				System.out.println(adbPath + "/adb -s " + deviceId + " uninstall " + processname);
			} catch (IOException e) {
				// TODO Auto-generated catch block
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

}
