import java.awt.EventQueue;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

import javax.swing.JFrame;


public class LoginBackground {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginBackground window = new LoginBackground();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public LoginBackground() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(5, 5, 100, 200);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		LoginWindow login = new LoginWindow();
		login.setAlwaysOnTop(true);
		login.setVisible(true);
		
		
		//GraphicsDevice device = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices()[0];
		//device.setFullScreenWindow(frame);
		
		
		
		
	}

}
