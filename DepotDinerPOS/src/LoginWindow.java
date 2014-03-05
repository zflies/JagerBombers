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
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;


public class LoginWindow extends JFrame {

	private JPanel contentPane;
	private static LoginWindow frame;
	private JPasswordField txtEmployeePin;
	private static JButton btnSubmit;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new LoginWindow();
					frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
					frame.setVisible(true);
					frame.getRootPane().setDefaultButton(btnSubmit);

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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 554, 443);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 5, 5);
		gbc_panel.gridx = 4;
		gbc_panel.gridy = 2;
		contentPane.add(panel, gbc_panel);
		
		txtEmployeePin = new JPasswordField();
		txtEmployeePin.setHorizontalAlignment(SwingConstants.CENTER);
		txtEmployeePin.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		
		JLabel lblEmployeePin = new JLabel("Employee PIN:");
		lblEmployeePin.setHorizontalAlignment(SwingConstants.CENTER);
		lblEmployeePin.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		
		btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String pin = new  String(txtEmployeePin.getPassword());
				//access DB and return the associated employee

				Employee loggedInEmployee;
				try {
					loggedInEmployee = Employee.getEmployeeByPIN(pin);
				} catch (Exception e) {
					JOptionPane.showMessageDialog(frame, "Employee could not be found", "Invalid PIN", JOptionPane.ERROR_MESSAGE);
					return;
				}
				//check employee type and open corresponding window
				if(loggedInEmployee.getEmployeePosition() == Employee.EmployeePosition.Staff){
					//open orders window
					System.out.println("Opening Orders Window!");
					OrdersView Orders = new OrdersView();
					Orders.setVisible(true);
					Orders.setExtendedState(JFrame.MAXIMIZED_BOTH);
				}
				else if(loggedInEmployee.getEmployeePosition() == Employee.EmployeePosition.Kitchen){
					//open kitchen window
					System.out.println("Opening Kitchen Window!");
					KitchenView Kitchen = new KitchenView();
					Kitchen.setVisible(true);
					Kitchen.setExtendedState(JFrame.MAXIMIZED_BOTH);
				}
				else if(loggedInEmployee.getEmployeePosition() == Employee.EmployeePosition.Manager){
					//open manager window
					System.out.println("Opening Manager Window!");
					ManageView Manage = new ManageView();
					Manage.setVisible(true);
					Manage.setExtendedState(JFrame.MAXIMIZED_BOTH);
				}
				else{
					//error, pin couldn't be matched
					JOptionPane.showMessageDialog(frame, "Employee Position could not be resolved", "Invalid Employee Position", JOptionPane.ERROR_MESSAGE);
					return;
				}
				frame.setVisible(false);
				frame.dispose();
			}
		});
		btnSubmit.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap(88, Short.MAX_VALUE)
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblEmployeePin, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 136, Short.MAX_VALUE)
						.addComponent(txtEmployeePin, GroupLayout.PREFERRED_SIZE, 136, GroupLayout.PREFERRED_SIZE))
					.addGap(75))
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap(110, Short.MAX_VALUE)
					.addComponent(btnSubmit, GroupLayout.PREFERRED_SIZE, 93, GroupLayout.PREFERRED_SIZE)
					.addGap(96))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(27)
					.addComponent(lblEmployeePin, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(txtEmployeePin, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnSubmit)
					.addContainerGap(132, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
	}
}
