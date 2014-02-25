import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;

import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JList;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JTabbedPane;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Calendar;
import java.util.Date;


public class OrdersView extends JFrame {

	private static OrdersView frame;
	private JPanel contentPane;
	private JButton btnExit;
	private JLabel lblEmployee;
	private JPanel panelOrders;
	private JButton button;
	private JButton button_1;
	private JList list;
	private static final int BREAKFAST_HOUR = 0;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new OrdersView();
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
	public OrdersView() {
	
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 884, 677);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		btnExit = new JButton("EXIT");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginWindow Login = new LoginWindow();
				Login.setVisible(true);
				Login.setExtendedState(JFrame.MAXIMIZED_BOTH);
				frame.setVisible(false);
				frame.dispose();
			}
		});
		btnExit.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		
		lblEmployee = new JLabel("EMPLOYEE:  ZACH FLIES");
		lblEmployee.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		lblEmployee.setHorizontalAlignment(SwingConstants.RIGHT);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(442)
							.addComponent(lblEmployee, GroupLayout.DEFAULT_SIZE, 330, Short.MAX_VALUE)
							.addGap(18)
							.addComponent(btnExit, GroupLayout.PREFERRED_SIZE, 78, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(tabbedPane, GroupLayout.DEFAULT_SIZE, 862, Short.MAX_VALUE)))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
						.addComponent(lblEmployee, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnExit, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(tabbedPane, GroupLayout.DEFAULT_SIZE, 586, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		panelOrders = new JPanel();
		tabbedPane.addTab("ORDERS", null, panelOrders, null);
		
		button = new JButton("HISTORY");
		button.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		
		button_1 = new JButton("CREATE ORDER");
		button_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				Calendar calendar = Calendar.getInstance();
				if(calendar.get(Calendar.HOUR_OF_DAY) <= BREAKFAST_HOUR){
					System.out.println("Opening Breakfast Menu!");
					BreakfastView breakfast = new BreakfastView();
					breakfast.setVisible(true);
					breakfast.setExtendedState(JFrame.MAXIMIZED_BOTH);
				}
				else{
					System.out.println("Opening Dinner Menu!");
					DinnerView dinner = new DinnerView();
					dinner.setVisible(true);
					dinner.setExtendedState(JFrame.MAXIMIZED_BOTH);
				}				
			}
		});
		button_1.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		
		list = new JList();
		GroupLayout gl_panelOrders = new GroupLayout(panelOrders);
		gl_panelOrders.setHorizontalGroup(
			gl_panelOrders.createParallelGroup(Alignment.TRAILING)
				.addGap(0, 862, Short.MAX_VALUE)
				.addGroup(gl_panelOrders.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelOrders.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panelOrders.createSequentialGroup()
							.addComponent(button, GroupLayout.PREFERRED_SIZE, 162, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 526, Short.MAX_VALUE)
							.addComponent(button_1))
						.addComponent(list, GroupLayout.DEFAULT_SIZE, 850, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_panelOrders.setVerticalGroup(
			gl_panelOrders.createParallelGroup(Alignment.LEADING)
				.addGap(0, 580, Short.MAX_VALUE)
				.addGroup(gl_panelOrders.createSequentialGroup()
					.addContainerGap()
					.addComponent(list, GroupLayout.DEFAULT_SIZE, 494, Short.MAX_VALUE)
					.addGap(28)
					.addGroup(gl_panelOrders.createParallelGroup(Alignment.LEADING)
						.addComponent(button_1, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
						.addComponent(button, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		panelOrders.setLayout(gl_panelOrders);
		contentPane.setLayout(gl_contentPane);
		
		
	}
}
