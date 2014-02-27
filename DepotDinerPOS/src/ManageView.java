import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JDialog;
import javax.swing.JTabbedPane;
import javax.swing.JDesktopPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JList;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class ManageView extends JFrame {

	private JPanel contentPane;
	private JDialog dialogAddEmployee;
	private static ManageView frame;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new ManageView();
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
	public ManageView() {
		setTitle("Steven's Depot Diner");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 887, 544);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		
		JLabel lblEmployee = new JLabel("Employee: ");
		lblEmployee.setFont(new Font("Lucida Grande", Font.PLAIN, 30));
		
		JButton btnExit = new JButton("Exit");
		btnExit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				LoginWindow Login = new LoginWindow();
				Login.setVisible(true);
				Login.setExtendedState(JFrame.MAXIMIZED_BOTH);
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(tabbedPane, GroupLayout.DEFAULT_SIZE, 871, Short.MAX_VALUE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblEmployee, GroupLayout.PREFERRED_SIZE, 699, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 91, Short.MAX_VALUE)
							.addComponent(btnExit)))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(17)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblEmployee)
						.addComponent(btnExit, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE))
					.addGap(31)
					.addComponent(tabbedPane, GroupLayout.DEFAULT_SIZE, 413, Short.MAX_VALUE)
					.addGap(0))
		);
		
		JPanel panel_manage = new JPanel();
		tabbedPane.addTab("Manage", null, panel_manage, null);
		
		JList list = new JList();
		
		JButton btnAddEmployee = new JButton("Add Employee");
		btnAddEmployee.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dialogAddEmployee = new AddEmployeeDialog();
				dialogAddEmployee.setVisible(true);
				
				dialogAddEmployee.setLocationRelativeTo(null);
				dialogAddEmployee.setTitle("Add Employee");
				
				dialogAddEmployee.setAlwaysOnTop(true);	
			}
		});
		
		JLabel lblNewLabel = new JLabel("Jeffrey Allen Rondeau");
		lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		
		JButton btnClockIn = new JButton("Clock In");
		
		JButton btnClockOut = new JButton("Clock Out");
		
		JButton btnViewOrders = new JButton("View Orders");
		
		JButton btnViewPayrollhours = new JButton("View Payroll/Hours");
		
		JButton btnDelete = new JButton("Delete");
		
		JButton btnGenerateReport = new JButton("Generate Report");
		GroupLayout gl_panel_manage = new GroupLayout(panel_manage);
		gl_panel_manage.setHorizontalGroup(
			gl_panel_manage.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_manage.createSequentialGroup()
					.addGap(21)
					.addComponent(list, GroupLayout.PREFERRED_SIZE, 272, GroupLayout.PREFERRED_SIZE)
					.addGroup(gl_panel_manage.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_manage.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_panel_manage.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel_manage.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED, 226, Short.MAX_VALUE)
									.addComponent(lblNewLabel)
									.addGap(126))
								.addGap(237))
							.addGroup(Alignment.TRAILING, gl_panel_manage.createSequentialGroup()
								.addGap(183)
								.addGroup(gl_panel_manage.createParallelGroup(Alignment.LEADING, false)
									.addComponent(btnViewOrders, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addGroup(gl_panel_manage.createSequentialGroup()
										.addComponent(btnClockIn, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE)
										.addGap(77)
										.addComponent(btnClockOut))
									.addComponent(btnViewPayrollhours, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
								.addGap(75)
								.addGap(10)))
						.addGroup(Alignment.TRAILING, gl_panel_manage.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnDelete)
							.addGap(183))))
				.addGroup(gl_panel_manage.createSequentialGroup()
					.addGap(89)
					.addComponent(btnAddEmployee)
					.addPreferredGap(ComponentPlacement.RELATED, 321, Short.MAX_VALUE)
					.addComponent(btnGenerateReport, GroupLayout.PREFERRED_SIZE, 160, GroupLayout.PREFERRED_SIZE)
					.addGap(147))
		);
		gl_panel_manage.setVerticalGroup(
			gl_panel_manage.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_manage.createSequentialGroup()
					.addGap(22)
					.addGroup(gl_panel_manage.createParallelGroup(Alignment.BASELINE)
						.addComponent(list, GroupLayout.DEFAULT_SIZE, 289, Short.MAX_VALUE)
						.addGroup(gl_panel_manage.createSequentialGroup()
							.addComponent(lblNewLabel)
							.addGap(22)
							.addGroup(gl_panel_manage.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnClockIn)
								.addComponent(btnClockOut))
							.addGap(31)
							.addComponent(btnViewOrders)
							.addGap(17)
							.addComponent(btnViewPayrollhours)
							.addGap(14)
							.addComponent(btnDelete)))
					.addGap(18)
					.addGroup(gl_panel_manage.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnAddEmployee)
						.addComponent(btnGenerateReport))
					.addGap(12))
		);
		panel_manage.setLayout(gl_panel_manage);
		
		JPanel panel_orders = new JPanel();
		tabbedPane.addTab("Orders", null, panel_orders, null);
		contentPane.setLayout(gl_contentPane);
		//tabbedPane.setEnabledAt(1, false);
	}
}
