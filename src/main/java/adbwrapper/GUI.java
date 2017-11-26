package adbwrapper;

import javax.swing.JButton;
import javax.swing.JFrame;

public class GUI extends JFrame {
	JButton btnSelectAllDevices;
	JButton btnDeselectAllDevices;
	JButton btnSelectAllApp;
	JButton btnDeselectAllApp;
	JButton btnInstall;
	JButton btnUninstall;

	public GUI() {
		this.setSize(580, 200);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("ADB Wrapper");
		this.setVisible(true);
	}
}
