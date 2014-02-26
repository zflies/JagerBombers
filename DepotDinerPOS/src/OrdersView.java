import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.Color;
import javax.swing.table.DefaultTableModel;
import javax.swing.JDesktopPane;
import java.util.Calendar;


public class OrdersView extends JFrame {

	private static OrdersView frame;
	private JPanel contentPaneOrders;
	private JButton btnExit;
	private JLabel lblEmployee;
	private JTabbedPane tabbedPaneOrders;
	private JPanel panelOrders;
	private JDesktopPane desktopPaneAllOrders;
	private JDesktopPane desktopPaneViewOrder;
	private JButton btnCreateOrder;
	private JButton btnOrderHistory;
	private JButton btnPayment;
	private JButton btnAddToOrder;
	private JScrollPane scrollPaneAllOrders;
	private JLabel lblTable;
	private JLabel lblTotal;
	private JLabel lblTableNumber;
	private JScrollPane scrollPaneViewOrder;
	private JList list;
	private static final int BREAKFAST_HOUR = 11;
	private JTable tableAllOrders;
	private JTable tableViewOrder;
	private JDialog dialogPayment;
	
	private Object columnNamesViewOrder[] = {"Item", "Notes", "Price"};

	private Object rowDataViewOrderTable4 [][] = {
			{"Chicken Strips", "Honey Mustard", "$7.50"}, 
			{"KC Strip","","$12.95"},
			{"Chicken Parmesan ","No Mushrooms","$9.50"},
			{"Iced Tea","","$2.45"},
			{"Iced Tea","","$2.45"},
			{"Drink","","$1.50"}};
	
	private Object rowDataViewOrderTable7 [][] = {
			{"Meat Loaf", "", "$8.95"}, 
			{"Country Fried Steak","","$9.85"},
			{"Catfish and Chips","Extra Sauce","$8.50"},
			{"Iced Tea","","$2.45"},
			{"Drink","","$1.50"}};
	
	private Object rowDataViewOrderTable10 [][] = {
			{"Southwest Omelette", "Extra Cheese", "$7.95"}, 
			{"8oz. KC Strip & Eggs","","$10.15"},
			{"Milk","","$1.75"},
			{"Drink","","$1.50"},
			{"Coffee","","$2.50"}};
	
	
	private Object rowDataAllOrders[][] = { 
			{ "10", "Southwest Omelette, 8oz. KC Strip & Eggs, Milk, Soft Drink, Coffee", "$23.95"},
			{ "7", "Meat Loaf, Country Fried Steak, Catfish and Chips, Iced Tea, Soft Drink", "$29.86"},
			{ "4", "Chicken Strips, KC Strip, Chicken Parmesan, Iced Tea, Iced, Tea, Soft Drink", "21.14"}};

	private Object columnNamesAllOrders[] = { "TABLE", "ORDER DESCRIPTION", "TOTAL"};



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
		setTitle("Steve's Depot Diner");
	
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 884, 677);
		contentPaneOrders = new JPanel();
		contentPaneOrders.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPaneOrders);
		
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
		GroupLayout gl_contentPaneOrders = new GroupLayout(contentPaneOrders);
		gl_contentPaneOrders.setHorizontalGroup(
			gl_contentPaneOrders.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_contentPaneOrders.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblEmployee, GroupLayout.PREFERRED_SIZE, 340, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 436, Short.MAX_VALUE)
					.addComponent(btnExit, GroupLayout.PREFERRED_SIZE, 78, GroupLayout.PREFERRED_SIZE)
					.addGap(14))
				.addComponent(tabbedPaneOrders, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 874, Short.MAX_VALUE)
		);
		gl_contentPaneOrders.setVerticalGroup(
			gl_contentPaneOrders.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPaneOrders.createSequentialGroup()
					.addGroup(gl_contentPaneOrders.createParallelGroup(Alignment.LEADING)
						.addComponent(btnExit, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblEmployee, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(tabbedPaneOrders, GroupLayout.DEFAULT_SIZE, 592, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		panelOrders = new JPanel();
		tabbedPaneOrders.addTab("ORDERS", null, panelOrders, null);
		
		desktopPaneAllOrders = new JDesktopPane();
		desktopPaneAllOrders.setBackground(Color.LIGHT_GRAY);
		
		desktopPaneViewOrder = new JDesktopPane();
		desktopPaneViewOrder.setBackground(Color.LIGHT_GRAY);

		GroupLayout gl_panelOrders = new GroupLayout(panelOrders);
		gl_panelOrders.setHorizontalGroup(
			gl_panelOrders.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelOrders.createSequentialGroup()
					.addContainerGap()
					.addComponent(desktopPaneAllOrders, GroupLayout.DEFAULT_SIZE, 409, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(desktopPaneViewOrder, GroupLayout.DEFAULT_SIZE, 409, Short.MAX_VALUE)
					.addGap(5))
		);
		gl_panelOrders.setVerticalGroup(
			gl_panelOrders.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelOrders.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelOrders.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelOrders.createSequentialGroup()
							.addComponent(desktopPaneViewOrder, GroupLayout.DEFAULT_SIZE, 549, Short.MAX_VALUE)
							.addGap(7))
						.addGroup(gl_panelOrders.createSequentialGroup()
							.addComponent(desktopPaneAllOrders, GroupLayout.DEFAULT_SIZE, 550, Short.MAX_VALUE)
							.addGap(7))))
		);
		
		btnPayment = new JButton("PAYMENT");
		btnPayment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				dialogPayment = new PaymentsDialog();
				dialogPayment.setVisible(true);
				
				dialogPayment.setLocationRelativeTo(null);
				dialogPayment.setTitle("TABLE" + lblTableNumber.getText() + "PAYMENT");
				
				dialogPayment.setAlwaysOnTop(true);							
			}
		});
		btnPayment.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		
		btnAddToOrder = new JButton("ADD TO ORDER");
		btnAddToOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
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
		btnAddToOrder.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		
		lblTable = new JLabel("TABLE:");
		lblTable.setFont(new Font("Lucida Grande", Font.PLAIN, 25));
		
		lblTotal = new JLabel("TOTAL: $0.00");
		lblTotal.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTotal.setFont(new Font("Lucida Grande", Font.PLAIN, 25));
		
		scrollPaneViewOrder = new JScrollPane();
		
		lblTableNumber = new JLabel("10");
		lblTableNumber.setFont(new Font("Lucida Grande", Font.PLAIN, 25));
		GroupLayout gl_desktopPaneViewOrder = new GroupLayout(desktopPaneViewOrder);
		gl_desktopPaneViewOrder.setHorizontalGroup(
			gl_desktopPaneViewOrder.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_desktopPaneViewOrder.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_desktopPaneViewOrder.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_desktopPaneViewOrder.createSequentialGroup()
							.addGroup(gl_desktopPaneViewOrder.createParallelGroup(Alignment.LEADING)
								.addComponent(btnAddToOrder, GroupLayout.PREFERRED_SIZE, 194, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_desktopPaneViewOrder.createSequentialGroup()
									.addComponent(lblTable)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(lblTableNumber, GroupLayout.PREFERRED_SIZE, 82, GroupLayout.PREFERRED_SIZE)))
							.addPreferredGap(ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
							.addGroup(gl_desktopPaneViewOrder.createParallelGroup(Alignment.TRAILING)
								.addComponent(btnPayment, GroupLayout.PREFERRED_SIZE, 194, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblTotal, GroupLayout.PREFERRED_SIZE, 194, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_desktopPaneViewOrder.createSequentialGroup()
							.addComponent(scrollPaneViewOrder, GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
							.addGap(3)))
					.addContainerGap())
		);
		gl_desktopPaneViewOrder.setVerticalGroup(
			gl_desktopPaneViewOrder.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_desktopPaneViewOrder.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_desktopPaneViewOrder.createParallelGroup(Alignment.LEADING)
						.addComponent(lblTotal, GroupLayout.PREFERRED_SIZE, 52, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblTable, GroupLayout.PREFERRED_SIZE, 52, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblTableNumber, GroupLayout.PREFERRED_SIZE, 52, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPaneViewOrder, GroupLayout.DEFAULT_SIZE, 402, Short.MAX_VALUE)
					.addGap(14)
					.addGroup(gl_desktopPaneViewOrder.createParallelGroup(Alignment.LEADING)
						.addComponent(btnAddToOrder, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnPayment, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		
		
		
				
		tableViewOrder = new JTable(new DefaultTableModel(rowDataViewOrderTable10, columnNamesViewOrder) {
			boolean[] columnEditables = new boolean[] {
					false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		
	
		tableViewOrder.setFont(new Font("Lucida Grande", Font.PLAIN, 30));
		tableViewOrder.setRowHeight(tableViewOrder.getRowHeight() + 20);
			
		scrollPaneViewOrder.setViewportView(tableViewOrder);
		desktopPaneViewOrder.setLayout(gl_desktopPaneViewOrder);
		
		btnCreateOrder = new JButton("CREATE ORDER");
		btnCreateOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
		
		btnCreateOrder.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		
		btnOrderHistory = new JButton("ORDER HISTORY");
		btnOrderHistory.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		
		scrollPaneAllOrders = new JScrollPane();
		
		GroupLayout gl_desktopPaneAllOrders = new GroupLayout(desktopPaneAllOrders);
		gl_desktopPaneAllOrders.setHorizontalGroup(
			gl_desktopPaneAllOrders.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_desktopPaneAllOrders.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_desktopPaneAllOrders.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_desktopPaneAllOrders.createSequentialGroup()
							.addComponent(btnOrderHistory, GroupLayout.PREFERRED_SIZE, 194, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
							.addComponent(btnCreateOrder))
						.addGroup(gl_desktopPaneAllOrders.createSequentialGroup()
							.addComponent(scrollPaneAllOrders, GroupLayout.DEFAULT_SIZE, 395, Short.MAX_VALUE)
							.addGap(2)))
					.addContainerGap())
		);
		gl_desktopPaneAllOrders.setVerticalGroup(
			gl_desktopPaneAllOrders.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_desktopPaneAllOrders.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPaneAllOrders, GroupLayout.DEFAULT_SIZE, 473, Short.MAX_VALUE)
					.addGap(7)
					.addGroup(gl_desktopPaneAllOrders.createParallelGroup(Alignment.LEADING, false)
						.addComponent(btnOrderHistory, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnCreateOrder, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 47, Short.MAX_VALUE))
					.addContainerGap())
		);
		
	
				
		tableAllOrders = new JTable(new DefaultTableModel(rowDataAllOrders, columnNamesAllOrders) {
			boolean[] columnEditables = new boolean[] {
				false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
			
		});
		
		tableAllOrders.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
	        public void valueChanged(ListSelectionEvent event) {
	            // do some actions here, for example
	            // print first column value from selected row
	            System.out.println(tableAllOrders.getValueAt(tableAllOrders.getSelectedRow(), 0).toString());
	           
	        }
	    });
		
		
		tableAllOrders.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableAllOrders.setFont(new Font("Lucida Grande", Font.PLAIN, 30));
		tableAllOrders.setRowHeight(tableAllOrders.getRowHeight() + 50);
	
		
		
		
		scrollPaneAllOrders.setViewportView(tableAllOrders);
		desktopPaneAllOrders.setLayout(gl_desktopPaneAllOrders);
		panelOrders.setLayout(gl_panelOrders);
		contentPaneOrders.setLayout(gl_contentPaneOrders);
		
		
	}
}
