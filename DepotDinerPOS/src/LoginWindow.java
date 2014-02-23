import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class LoginWindow extends JFrame {

	private JPanel contentPane;
	private JPasswordField txtEmployeePin;
	private static LoginWindow frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new LoginWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LoginWindow() {
		setTitle("Steve's Depot Diner");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 363, 252);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtEmployeePin = new JPasswordField();
		txtEmployeePin.setBounds(84, 91, 171, 33);
		contentPane.add(txtEmployeePin);
		
		JLabel lblEmployeePin = new JLabel("Employee Pin:");
		lblEmployeePin.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblEmployeePin.setBounds(84, 64, 109, 20);
		contentPane.add(lblEmployeePin);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String pin = new  String(txtEmployeePin.getPassword());
				//access DB and return the associated employee
				
				//check employee type and open corresponding window
				if(pin.compareTo("1") == 0){
					//open orders window
					System.out.println("Opening Orders Window!");
				}
				else if(pin.compareTo("2") == 0){
					//open kitchen window
					System.out.println("Opening Kitchen Window!");
					KitchenView Kitchen = new KitchenView();
					Kitchen.setVisible(true);
					Kitchen.setExtendedState(JFrame.MAXIMIZED_BOTH);
				}
				else if(pin.compareTo("3") == 0){
					//open manager window
					System.out.println("Opening Manager Window!");
				}
				else{
					//error, pin couldn't be matched
					JOptionPane.showMessageDialog(frame, "Employee Type could not be resolved", "Employee Type Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				frame.dispose();
			}
		});
		btnSubmit.setBounds(248, 180, 89, 23);
		contentPane.add(btnSubmit);
	}
}
