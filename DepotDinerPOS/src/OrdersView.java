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
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.JTable;
import javax.swing.border.MatteBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableColumn;

import java.awt.Color;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLayeredPane;
import javax.swing.JSplitPane;
import javax.swing.ScrollPaneConstants;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import javax.swing.JDesktopPane;
import java.awt.SystemColor;
import javax.swing.UIManager;


public class OrdersView extends JFrame {

	private static OrdersView frame;
	private JPanel contentPane;
	private JButton btnExit;
	private JLabel lblEmployee;
	private JTable table_1;
	private JTable table_2;
	private JTabbedPane tabbedPaneOrders;
	private JPanel panelOrders;
	private JDesktopPane desktopPane;
	private JDesktopPane desktopPane_1;
	private JButton button_1;
	private JButton btnOrderHistory;
	private JButton btnPayment;
	private JButton btnAddToOrder;
	private JScrollPane scrollPane;
	private JLabel lblNewLabel;
	private JLabel lblTotal;
	private JScrollPane scrollPane_1;

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
	
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
		lblEmployee.setFont(new Font("Lucida Grande", Font.PLAIN, 30));
		lblEmployee.setHorizontalAlignment(SwingConstants.LEFT);
		
		tabbedPaneOrders = new JTabbedPane(JTabbedPane.TOP);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblEmployee, GroupLayout.PREFERRED_SIZE, 340, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 436, Short.MAX_VALUE)
					.addComponent(btnExit, GroupLayout.PREFERRED_SIZE, 78, GroupLayout.PREFERRED_SIZE)
					.addGap(14))
				.addComponent(tabbedPaneOrders, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 874, Short.MAX_VALUE)
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(btnExit, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblEmployee, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(tabbedPaneOrders, GroupLayout.DEFAULT_SIZE, 592, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		panelOrders = new JPanel();
		tabbedPaneOrders.addTab("ORDERS", null, panelOrders, null);
		
		desktopPane = new JDesktopPane();
		desktopPane.setBackground(Color.LIGHT_GRAY);
		
		desktopPane_1 = new JDesktopPane();
		desktopPane_1.setBackground(Color.LIGHT_GRAY);
		GroupLayout gl_panelOrders = new GroupLayout(panelOrders);
		gl_panelOrders.setHorizontalGroup(
			gl_panelOrders.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelOrders.createSequentialGroup()
					.addContainerGap()
					.addComponent(desktopPane, GroupLayout.DEFAULT_SIZE, 409, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(desktopPane_1, GroupLayout.DEFAULT_SIZE, 409, Short.MAX_VALUE)
					.addGap(5))
		);
		gl_panelOrders.setVerticalGroup(
			gl_panelOrders.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelOrders.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelOrders.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelOrders.createSequentialGroup()
							.addComponent(desktopPane_1, GroupLayout.DEFAULT_SIZE, 549, Short.MAX_VALUE)
							.addGap(7))
						.addGroup(gl_panelOrders.createSequentialGroup()
							.addComponent(desktopPane, GroupLayout.DEFAULT_SIZE, 550, Short.MAX_VALUE)
							.addGap(7))))
		);
		
		btnPayment = new JButton("PAYMENT");
		btnPayment.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		
		btnAddToOrder = new JButton("ADD TO ORDER");
		btnAddToOrder.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		
		lblNewLabel = new JLabel("TABLE:");
		lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 25));
		
		lblTotal = new JLabel("TOTAL: $0.00");
		lblTotal.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTotal.setFont(new Font("Lucida Grande", Font.PLAIN, 25));
		
		scrollPane_1 = new JScrollPane();
		GroupLayout gl_desktopPane_1 = new GroupLayout(desktopPane_1);
		gl_desktopPane_1.setHorizontalGroup(
			gl_desktopPane_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_desktopPane_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_desktopPane_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_desktopPane_1.createSequentialGroup()
							.addGroup(gl_desktopPane_1.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(lblNewLabel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(btnAddToOrder, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 194, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
							.addGroup(gl_desktopPane_1.createParallelGroup(Alignment.TRAILING)
								.addComponent(btnPayment, GroupLayout.PREFERRED_SIZE, 194, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblTotal, GroupLayout.PREFERRED_SIZE, 194, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_desktopPane_1.createSequentialGroup()
							.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 394, Short.MAX_VALUE)
							.addGap(3)))
					.addContainerGap())
		);
		gl_desktopPane_1.setVerticalGroup(
			gl_desktopPane_1.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_desktopPane_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_desktopPane_1.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 52, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblTotal, GroupLayout.PREFERRED_SIZE, 52, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 408, Short.MAX_VALUE)
					.addGap(14)
					.addGroup(gl_desktopPane_1.createParallelGroup(Alignment.LEADING)
						.addComponent(btnAddToOrder, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnPayment, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		desktopPane_1.setLayout(gl_desktopPane_1);
		
		button_1 = new JButton("CREATE ORDER");
		button_1.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		
		btnOrderHistory = new JButton("ORDER HISTORY");
		btnOrderHistory.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		
		scrollPane = new JScrollPane();
		GroupLayout gl_desktopPane = new GroupLayout(desktopPane);
		gl_desktopPane.setHorizontalGroup(
			gl_desktopPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_desktopPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_desktopPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_desktopPane.createSequentialGroup()
							.addComponent(btnOrderHistory, GroupLayout.PREFERRED_SIZE, 194, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
							.addComponent(button_1))
						.addGroup(gl_desktopPane.createSequentialGroup()
							.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 395, Short.MAX_VALUE)
							.addGap(2)))
					.addContainerGap())
		);
		gl_desktopPane.setVerticalGroup(
			gl_desktopPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_desktopPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 473, Short.MAX_VALUE)
					.addGap(7)
					.addGroup(gl_desktopPane.createParallelGroup(Alignment.LEADING, false)
						.addComponent(btnOrderHistory, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(button_1, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 47, Short.MAX_VALUE))
					.addContainerGap())
		);
		desktopPane.setLayout(gl_desktopPane);
		panelOrders.setLayout(gl_panelOrders);
		
		// Temp row data 
		Object rowData[][] = { { "10", "Row1-Column2", "Row1-Column3"},
		                       { "7", "Row2-Column2", "Row2-Column3"},
		                       { "4", "Row3-Column2", "Row3-Column3"},
		                       { "1", "Row4-Column2", "Row4-Column3"},
		                       { "15", "Row5-Column2", "Row5-Column3"},
		                       { "21", "Row6-Column2", "Row6-Column3"},
		                       { "2", "Row7-Column2", "Row7-Column3"},
		                       { "3", "Row8-Column2", "Row8-Column3"},
		                       { "8", "Row9-Column2", "Row9-Column3"},
		                       { "5", "Row10-Column2", "Row10-Column3"} };
		
		// Temp column names
		Object columnNames[] = { "Table", "Order Description", "Total"};
		contentPane.setLayout(gl_contentPane);
		
		
	}
}