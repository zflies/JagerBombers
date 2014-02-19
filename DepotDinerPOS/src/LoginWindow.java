import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JComboBox;
import javax.swing.JPasswordField;


public class LoginWindow {

	private JFrame frame;
	private JPasswordField txtEmployeePin;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginWindow window = new LoginWindow();
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
	public LoginWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 363, 252);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		txtEmployeePin = new JPasswordField();
		txtEmployeePin.setBounds(84, 91, 171, 33);
		frame.getContentPane().add(txtEmployeePin);
		
		JLabel lblEmployeePin = new JLabel("Employee Pin:");
		lblEmployeePin.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblEmployeePin.setBounds(84, 64, 109, 20);
		frame.getContentPane().add(lblEmployeePin);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.setBounds(248, 180, 89, 23);
		frame.getContentPane().add(btnSubmit);
	}
}
