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
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.SwingConstants;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.UIManager;


public class LoginWindow extends JFrame {

	private JPanel contentPane;
	private static LoginWindow frame;
	private JPasswordField txtEmployeePin;

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
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 363, 252);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addContainerGap(650, Short.MAX_VALUE)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 407, GroupLayout.PREFERRED_SIZE)
					.addGap(613))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addContainerGap(242, Short.MAX_VALUE)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 464, GroupLayout.PREFERRED_SIZE)
					.addGap(212))
		);
		
		JLabel lblEmployeePin = new JLabel("Employee PIN:");
		lblEmployeePin.setHorizontalAlignment(SwingConstants.CENTER);
		lblEmployeePin.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String pin = new  String(txtEmployeePin.getPassword());
				//access DB and return the associated employee
				
				//check employee type and open corresponding window
				if(pin.compareTo("1") == 0){
					//open orders window
					System.out.println("Opening Orders Window!");
					OrdersView Orders = new OrdersView();
					Orders.setVisible(true);
					Orders.setExtendedState(JFrame.MAXIMIZED_BOTH);
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
				frame.setVisible(false);
				frame.dispose();
			}
		});
		
		txtEmployeePin = new JPasswordField();
		txtEmployeePin.setHorizontalAlignment(SwingConstants.CENTER);
		txtEmployeePin.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(115)
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(lblEmployeePin, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(txtEmployeePin, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 136, Short.MAX_VALUE))
					.addContainerGap(102, Short.MAX_VALUE))
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(139)
					.addComponent(btnSubmit)
					.addContainerGap(124, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(55)
					.addComponent(lblEmployeePin)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(txtEmployeePin, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnSubmit)
					.addContainerGap(62, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		contentPane.setLayout(gl_contentPane);
	}
}
