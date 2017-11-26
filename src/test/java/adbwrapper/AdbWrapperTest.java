package adbwrapper;

import java.util.ArrayList;

import org.testng.Assert;
import org.testng.annotations.Test;

public class AdbWrapperTest {
	AdbWrapper adb = new AdbWrapper();

	@Test
	public void getADBpathTest() {
		String adbpath = adb.getADBpath();
		Assert.assertEquals(adbpath, "/Users/alexander/Desktop/Work/platform-tools", "Wrong Adb Path! ");
	}

	@Test
	public void getAllDevicesIdTest() {
		ArrayList<String> alldevicesId;
		alldevicesId = adb.getAllDevicesId();
		Assert.assertEquals(alldevicesId.get(0), "520365e0f0c0a3cd", "Wrong devices id list! ");
	}

	@Test
	public void getAllDevicesNameTest() {
		ArrayList<String> alldevicesName;
		alldevicesName = adb.getAllDevicesName();
		Assert.assertEquals(alldevicesName.get(0), "samsung SM-J710F", "Wrong devices name list! ");
	}

	@Test
	public void installAll() {
		adb.installAll("/Users/alexander/Desktop/Work/Builds/Android/betternet_v3990_debug_11-15.apk", false);
	}

	@Test
	public void getProcessTest() {
		ArrayList<String> processes;
		processes = adb.getProcesses();
		Assert.assertEquals(processes.get(0), "tech.hexa");
		Assert.assertEquals(processes.get(1), "com.freevpnintouch");

	}

	// @Test
	// public void uninstallAll() {
	// adb.uninstallAll("tech.hexa");
	// }
}
