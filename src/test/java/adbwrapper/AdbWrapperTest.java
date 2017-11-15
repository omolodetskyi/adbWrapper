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
		Assert.assertEquals(alldevicesId.get(1), "PSEDU17127003751", "Wrong devices id list! ");
		Assert.assertEquals(alldevicesId.get(0), "520366e4ec5b835f", "Wrong devices id list! ");
	}

	@Test
	public void installAll() {
		adb.installAll("/Users/alexander/Desktop/Work/Builds/Android/betternet_v3990_debug_11-15.apk");
	}

	// @Test
	// public void uninstallAll() {
	// adb.uninstallAll("com.freevpnintouch");
	// }
}
