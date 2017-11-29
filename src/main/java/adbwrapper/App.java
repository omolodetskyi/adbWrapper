package adbwrapper;

public class App {
	public static void main(String[] args) {
		Controller controller = new Controller();
		GUI gui = new GUI();
		AdbWrapper adb = new AdbWrapper();
		controller.runFlow(adb, gui);
	}
}
