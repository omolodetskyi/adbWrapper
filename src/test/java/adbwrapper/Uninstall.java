package adbwrapper;

import org.testng.annotations.Test;

public class Uninstall {
	AdbWrapper adb = new AdbWrapper();

	@Test
	public void uninstallAll() {
		adb.uninstallAll("tech.hexa");
	}
}
