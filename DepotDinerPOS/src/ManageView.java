import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ListSelectionModel;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JDesktopPane;

import org.eclipse.wb.swing.FocusTraversalOnArray;

import java.awt.Color;
import java.awt.event.KeyEvent;


public class ManageView extends JFrame implements WindowFocusListener{

	/* MANAGE Tab */
	private JPanel contentPane;
	private JDialog dialogAddEmployee;
	private JDialog dialogReport;
	private static ManageView frame;
	private String employeeName;
	private JTable employeeTable;
	private JLabel lblEmployeeName;
	private JButton btnClockIn;
	private JButton btnClockOut;
	private JButton btnDelete;
	private String selectedEmployeeFirstName;
	private String selectedEmployeeLastName;
	private SharedListSelectionHandler selectionHandler;
	private ReservationDialog Reservation;
	ListSelectionModel listSelectionModel;

	String column_names[]= {"Last Name","First Name"};

	/* ORDERS Tab */
	private JButton btnExit;
	private JLabel lblEmployee;
	private JPanel panelOrders;
	private JDesktopPane desktopPaneAllOrders;
	private JDesktopPane desktopPaneViewOrder;
	private JButton btnCreateOrder;
	private JButton btnPayment;
	private JButton btnAddToOrder;
	private JButton btnHistory;
	private JScrollPane scrollPaneAllOrders;
	private JLabel lblTable;
	private static JLabel lblTotal;
	private static JLabel lblTableNumber;
	private JScrollPane scrollPaneViewOrder;
	private JDialog dialogPayment;
	private JDialog dialogHistory;
	private static TableColumnModel columns;
	private static TableColumn column;
	private static JTable tableAllOrders;
	private JTable tableViewOrder;
	
	private static final double DINNER_DISCOUNT = 1.00;
	private static final double BREAKFAST_DISCOUNT = 0.50;

	private static final int BREAKFAST_HOUR = 11;

	private Calendar calendar = Calendar.getInstance();

	private static Vector<String> columnNamesViewOrder = new Vector<String>();					//< Column Names for the View Order table
	private static Vector<String> columnNamesAllOrders = new Vector<String>();	//< Column Names for the View All Orders table

	private static Vector<Order> EmployeeAllOrders = new Vector<Order>(); //< Holds every active order for the logged in Employee
	private static Vector<Vector<String>> EmployeeAllOrdersTableData = new Vector<Vector<String>>(); //< Holds the row data for the ViewAllOrders table
	private static Vector<Vector<String>> EmployeeViewOrderTableData = new Vector<Vector<String>>();	//< Holds the row data for the ViewOrder table
	private ArrayList<Double> ItemCosts = new ArrayList<Double>();	//< Used to keep track of item prices for split ticket usage when passed to the Payments Dialog

	private static DecimalFormat df = new DecimalFormat("0.00");
	private Employee loggedInEmployee;
	private JButton btnViewResrvations;


	/**
	 * Create the frame.
	 */
	public ManageView( Employee employee ) {

		loggedInEmployee = employee;

		employeeName = loggedInEmployee.getFullName();

		setTitle("Steven's Depot Diner");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 884, 677);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);

		lblEmployee = new JLabel("Employee: " + employeeName);
		lblEmployee.setFont(new Font("Lucida Grande", Font.PLAIN, 30));

		btnExit = new JButton("EXIT");
		btnExit.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		btnExit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				dispose();
				LoginWindow Login = new LoginWindow();
				Login.setVisible(true);
				Login.setExtendedState(JFrame.MAXIMIZED_BOTH);
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
				gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
						.addContainerGap()
						.addComponent(lblEmployee, GroupLayout.DEFAULT_SIZE, 690, Short.MAX_VALUE)
						.addGap(85)
						.addComponent(btnExit)
						.addGap(15))
						.addGroup(gl_contentPane.createSequentialGroup()
								.addComponent(tabbedPane, GroupLayout.DEFAULT_SIZE, 868, Short.MAX_VALUE)
								.addContainerGap())
				);
		gl_contentPane.setVerticalGroup(
				gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
										.addGap(6)
										.addComponent(lblEmployee))
										.addComponent(btnExit, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(tabbedPane, GroupLayout.DEFAULT_SIZE, 593, Short.MAX_VALUE)
										.addGap(5))
				);

		JPanel panel_manage = new JPanel();
		tabbedPane.addTab("MANAGE", null, panel_manage, null);

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

		lblEmployeeName = new JLabel("");
		lblEmployeeName.setHorizontalAlignment(SwingConstants.CENTER);
		lblEmployeeName.setFont(new Font("Lucida Grande", Font.PLAIN, 20));

		btnClockIn = new JButton("Clock In");
		btnClockIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int pin = getEmployeePin();
				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Date date = new Date();

				java.sql.Statement state = DBConnection.OpenConnection();
				String query = String.format("INSERT INTO `avalenti`.`Hours` (`E_PIN`, `C_IN`, `C_OUT`) VALUES (%s, '%s', '0000-00-00 00:00:00');", pin, dateFormat.format(date).toString());
				if(state != null){
					try {
						state.execute(query);
					} catch (SQLException exception) {
						System.err.println("Error in SQL Execution");
					}
				}
				else
					System.err.println("Statement was null.  No connection?");

				btnClockOut.setEnabled(true);
				btnClockIn.setEnabled(false);
			}
		});

		btnClockOut = new JButton("Clock Out");
		btnClockOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int pin = getEmployeePin();
				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Date date = new Date();

				java.sql.Statement state = DBConnection.OpenConnection();
				String query = String.format("UPDATE `avalenti`.`Hours` SET `C_OUT`='%s' WHERE `E_PIN`='%s' AND `C_OUT`='0000-00-00 00:00:00';", dateFormat.format(date).toString(), pin);
				if(state != null){
					try {
						state.execute(query);
					} catch (SQLException exception) {
						System.err.println("Error in SQL Execution");
					}
				}
				else
					System.err.println("Statement was null.  No connection?");
				btnClockOut.setEnabled(false);
				btnClockIn.setEnabled(true);
			}
		});

		JButton btnViewOrders = new JButton("View Orders");

		JButton btnViewPayrollhours = new JButton("View Payroll/Hours");

		btnDelete = new JButton("Delete");

		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int result = JOptionPane.showConfirmDialog(frame, "Are you sure you want to delete this employee?", 
					       "Delete Employee", JOptionPane.INFORMATION_MESSAGE);
				if (result != JOptionPane.OK_OPTION){
					return;
				}
				int pin = getEmployeePin();
				java.sql.Statement state = DBConnection.OpenConnection();
				String query = String.format("DELETE FROM `avalenti`.`Employees` WHERE `PIN`= %s;", pin);
				if(state != null){
					try {
						state.execute(query);
					} catch (SQLException exception) {
						System.err.println("Error in SQL Execution");
						}
				}
				else
					System.err.println("Statement was null.  No connection?");
				refreshEmployeeTable();
				lblEmployeeName.setText("Select Employee");
				btnDelete.setEnabled(false);
			}
		});
		
		JButton btnGenerateReport = new JButton("Generate Report");
		btnGenerateReport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dialogReport = new ReportDialog();
				dialogReport.setVisible(true);

				dialogReport.setLocationRelativeTo(null);
				dialogReport.setTitle("Daily Report");

				dialogReport.setAlwaysOnTop(true);	
			}
		});

		JScrollPane scrollPane = new JScrollPane();
		
		btnViewResrvations = new JButton("View Reservations");
		btnViewResrvations.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Reservation = new ReservationDialog();
				Reservation.setVisible(true);
				Reservation.setLocationRelativeTo(null);
				Reservation.setAlwaysOnTop(true);
			}
		});
		GroupLayout gl_panel_manage = new GroupLayout(panel_manage);
		gl_panel_manage.setHorizontalGroup(
			gl_panel_manage.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_manage.createSequentialGroup()
					.addGroup(gl_panel_manage.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_manage.createSequentialGroup()
							.addGap(89)
							.addComponent(btnAddEmployee))
						.addGroup(gl_panel_manage.createSequentialGroup()
							.addGap(20)
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 311, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.RELATED, 166, Short.MAX_VALUE)
					.addGroup(gl_panel_manage.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, gl_panel_manage.createParallelGroup(Alignment.TRAILING)
							.addGroup(gl_panel_manage.createSequentialGroup()
								.addGroup(gl_panel_manage.createParallelGroup(Alignment.LEADING, false)
									.addComponent(btnViewOrders, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addGroup(gl_panel_manage.createSequentialGroup()
										.addComponent(btnClockIn, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE)
										.addGap(77)
										.addComponent(btnClockOut))
									.addComponent(btnViewPayrollhours, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
								.addGap(85))
							.addGroup(gl_panel_manage.createSequentialGroup()
								.addComponent(btnDelete)
								.addGap(183))
							.addGroup(gl_panel_manage.createSequentialGroup()
								.addComponent(lblEmployeeName)
								.addGap(176)))
						.addGroup(Alignment.TRAILING, gl_panel_manage.createSequentialGroup()
							.addGroup(gl_panel_manage.createParallelGroup(Alignment.LEADING, false)
								.addComponent(btnViewResrvations, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(btnGenerateReport, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE))
							.addGap(127))))
		);
		gl_panel_manage.setVerticalGroup(
			gl_panel_manage.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_manage.createSequentialGroup()
					.addGap(22)
					.addGroup(gl_panel_manage.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_manage.createSequentialGroup()
							.addComponent(lblEmployeeName)
							.addGap(32)
							.addGroup(gl_panel_manage.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnClockIn)
								.addComponent(btnClockOut))
							.addGap(31)
							.addComponent(btnViewOrders)
							.addGap(17)
							.addComponent(btnViewPayrollhours)
							.addGap(14)
							.addComponent(btnDelete)
							.addPreferredGap(ComponentPlacement.RELATED, 248, Short.MAX_VALUE)
							.addComponent(btnViewResrvations))
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 483, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_manage.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnAddEmployee)
						.addComponent(btnGenerateReport))
					.addGap(11))
		);

		DefaultTableModel table_model= new DefaultTableModel(column_names, 0);
		employeeTable = new JTable(table_model);
		employeeTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				/*
				 * Things that need to happen:
				 * 1. Update employee name to selected employee
				 * 2. Show/Hide clock in/out accordingly
				 */
				System.out.println("ACTUALLY CLICKED ENTRY");
				updateButtons();
			}
		});
		listSelectionModel = employeeTable.getSelectionModel();
		selectionHandler = new SharedListSelectionHandler();
		listSelectionModel.addListSelectionListener(selectionHandler);
		employeeTable.setSelectionModel(listSelectionModel);
		employeeTable.setFont(new Font("Lucida Grande", Font.BOLD, 15));
		scrollPane.setViewportView(employeeTable);
		panel_manage.setLayout(gl_panel_manage);
		//set delete button to be disabled by default
		btnDelete.setEnabled(false);




		/* ---------------------------------------------------------------------------------------------------------
		 * ---------------------------------------------------------------------------------------------------------
		 * ORDERS TAB 
		 * ---------------------------------------------------------------------------------------------------------
		 * ---------------------------------------------------------------------------------------------------------*/


		//Get the orders for the logged in employee								
		try {

			EmployeeAllOrders = Order.getEmployeeOrdersInProgress( loggedInEmployee ); //< First get the entered orders

			EmployeeAllOrdersTableData.clear();

			// Find all entered and served orders that correspond to the logged in employee
			for(int i = 0; i < EmployeeAllOrders.size(); i++){

				Order curOrder = EmployeeAllOrders.elementAt(i);

				Vector<String> order = new Vector<String>();

				order.add( "" + curOrder.getTableNumber() );
				order.add( curOrder.getItems() );
				order.add( "$" + df.format( curOrder.getTotal()) );

				EmployeeAllOrdersTableData.add(order);
			}
		} catch (Exception e) {
			//Some error occurred in either connecting to DB or there weren't any orders to be cooked
			JOptionPane.showMessageDialog(frame, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			return ;
		}

		columnNamesAllOrders.clear();
		columnNamesAllOrders.add("TABLE");
		columnNamesAllOrders.add("ORDER DESCRIPTION");
		columnNamesAllOrders.add("SUBTOTAL");

		columnNamesViewOrder.clear();
		columnNamesViewOrder.add("ITEM");
		columnNamesViewOrder.add("NOTES");
		columnNamesViewOrder.add("PRICE");


		panelOrders = new JPanel();
		tabbedPane.addTab("ORDERS", null, panelOrders, null);

		desktopPaneAllOrders = new JDesktopPane();
		desktopPaneAllOrders.setBackground(Color.LIGHT_GRAY);

		btnHistory = new JButton("ORDER HISTORY");
		btnHistory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				dialogHistory = new OrderHistoryDialog( loggedInEmployee );
				dialogHistory.setVisible(true);
				dialogHistory.setLocationRelativeTo(null);
				dialogHistory.setTitle(  (calendar.get(Calendar.MONTH) + 1) + "-" + calendar.get(Calendar.DAY_OF_MONTH) + "-" + calendar.get(Calendar.YEAR) + " Order History");
				dialogHistory.setAlwaysOnTop(true);	
			}
		});
		btnHistory.setMnemonic(KeyEvent.VK_H);
		btnHistory.setFont(new Font("Lucida Grande", Font.PLAIN, 20));

		btnCreateOrder = new JButton("CREATE ORDER");
		btnCreateOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Calendar calendar = Calendar.getInstance();
				if(calendar.get(Calendar.HOUR_OF_DAY) <= BREAKFAST_HOUR){
					System.out.println("Opening Breakfast Menu!");
					BreakfastView breakfast = new BreakfastView( loggedInEmployee, null );
					breakfast.setVisible(true);
					breakfast.setExtendedState(JFrame.MAXIMIZED_BOTH);					
				}
				else{
					System.out.println("Opening Dinner Menu!");
					DinnerView dinner = new DinnerView( loggedInEmployee, null );
					dinner.setVisible(true);
					dinner.setExtendedState(JFrame.MAXIMIZED_BOTH);
				}		
			}
		});
		btnCreateOrder.setMnemonic(KeyEvent.VK_C);
		btnCreateOrder.setFont(new Font("Lucida Grande", Font.PLAIN, 20));

		scrollPaneAllOrders = new JScrollPane();
		GroupLayout gl_desktopPaneAllOrders = new GroupLayout(desktopPaneAllOrders);
		gl_desktopPaneAllOrders.setHorizontalGroup(
				gl_desktopPaneAllOrders.createParallelGroup(Alignment.TRAILING)
				.addGap(0, 415, Short.MAX_VALUE)
				.addGroup(gl_desktopPaneAllOrders.createSequentialGroup()
						.addContainerGap()
						.addGroup(gl_desktopPaneAllOrders.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_desktopPaneAllOrders.createSequentialGroup()
										.addComponent(btnHistory, GroupLayout.PREFERRED_SIZE, 191, Short.MAX_VALUE)
										.addGap(18)
										.addComponent(btnCreateOrder, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
										.addComponent(scrollPaneAllOrders, GroupLayout.DEFAULT_SIZE, 403, Short.MAX_VALUE))
										.addContainerGap())
				);
		gl_desktopPaneAllOrders.setVerticalGroup(
				gl_desktopPaneAllOrders.createParallelGroup(Alignment.TRAILING)
				.addGap(0, 533, Short.MAX_VALUE)
				.addGroup(gl_desktopPaneAllOrders.createSequentialGroup()
						.addContainerGap()
						.addComponent(scrollPaneAllOrders, GroupLayout.DEFAULT_SIZE, 467, Short.MAX_VALUE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_desktopPaneAllOrders.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnCreateOrder, GroupLayout.DEFAULT_SIZE, 47, Short.MAX_VALUE)
								.addComponent(btnHistory, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE))
								.addGap(7))
				);

		lblTable = new JLabel("TABLE:");
		lblTable.setFont(new Font("Lucida Grande", Font.PLAIN, 30));

		lblTableNumber = new JLabel("0");
		lblTableNumber.setFont(new Font("Lucida Grande", Font.PLAIN, 30));

		lblTotal = new JLabel("Subtotal: $0.00");
		lblTotal.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTotal.setFont(new Font("Lucida Grande", Font.PLAIN, 30));

		btnAddToOrder = new JButton("ADD TO ORDER");
		btnAddToOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(calendar.get(Calendar.HOUR_OF_DAY) <= BREAKFAST_HOUR){
					System.out.println("Opening Breakfast Menu!");
					BreakfastView breakfast = new BreakfastView( loggedInEmployee, EmployeeAllOrders.get( tableAllOrders.getSelectedRow() ) );
					breakfast.setVisible(true);
					breakfast.setExtendedState(JFrame.MAXIMIZED_BOTH);
				}
				else{
					System.out.println("Opening Dinner Menu!");
					DinnerView dinner = new DinnerView( loggedInEmployee, EmployeeAllOrders.get( tableAllOrders.getSelectedRow() ) );
					dinner.setVisible(true);
					dinner.setExtendedState(JFrame.MAXIMIZED_BOTH);
				}	
			}
		});
		btnAddToOrder.setMnemonic(KeyEvent.VK_A);
		btnAddToOrder.setFont(new Font("Lucida Grande", Font.PLAIN, 20));

		btnPayment = new JButton("PAYMENT");
		btnPayment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if ( EmployeeAllOrders.isEmpty() )
				{
					return;
				}	            

				int row = tableAllOrders.getSelectedRow();
				Order curOrder;

				if ( row == -1 )
				{
					curOrder = EmployeeAllOrders.get( 0 );
				}
				else
				{
					curOrder = EmployeeAllOrders.get( tableAllOrders.getSelectedRow() );
				}

				dialogPayment = new PaymentsDialog( loggedInEmployee, curOrder, ItemCosts );
				dialogPayment.setVisible(true);

				dialogPayment.setLocationRelativeTo(null);
				dialogPayment.setTitle("TABLE " + lblTableNumber.getText() + " PAYMENT");

				dialogPayment.setAlwaysOnTop(true);	
			}
		});
		btnPayment.setMnemonic(KeyEvent.VK_P);
		btnPayment.setFont(new Font("Lucida Grande", Font.PLAIN, 20));

		desktopPaneViewOrder = new JDesktopPane();
		desktopPaneViewOrder.setBackground(Color.LIGHT_GRAY);

		scrollPaneViewOrder = new JScrollPane();
		GroupLayout gl_desktopPaneViewOrder = new GroupLayout(desktopPaneViewOrder);
		gl_desktopPaneViewOrder.setHorizontalGroup(
				gl_desktopPaneViewOrder.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_desktopPaneViewOrder.createSequentialGroup()
						.addContainerGap()
						.addGroup(gl_desktopPaneViewOrder.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_desktopPaneViewOrder.createSequentialGroup()
										.addComponent(btnAddToOrder, GroupLayout.PREFERRED_SIZE, 194, Short.MAX_VALUE)
										.addGap(15)
										.addComponent(btnPayment, GroupLayout.DEFAULT_SIZE, 191, Short.MAX_VALUE))
										.addGroup(gl_desktopPaneViewOrder.createSequentialGroup()
												.addGroup(gl_desktopPaneViewOrder.createParallelGroup(Alignment.TRAILING)
														.addGroup(gl_desktopPaneViewOrder.createSequentialGroup()
																.addComponent(lblTable)
																.addPreferredGap(ComponentPlacement.RELATED)
																.addComponent(lblTableNumber, GroupLayout.DEFAULT_SIZE, 82, Short.MAX_VALUE)
																.addGap(3)
																.addComponent(lblTotal, GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE))
																.addComponent(scrollPaneViewOrder, GroupLayout.DEFAULT_SIZE, 397, Short.MAX_VALUE))
																.addGap(3)))
																.addContainerGap())
				);
		gl_desktopPaneViewOrder.setVerticalGroup(
				gl_desktopPaneViewOrder.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_desktopPaneViewOrder.createSequentialGroup()
						.addContainerGap()
						.addGroup(gl_desktopPaneViewOrder.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblTableNumber, GroupLayout.PREFERRED_SIZE, 52, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblTotal, GroupLayout.PREFERRED_SIZE, 52, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblTable, GroupLayout.PREFERRED_SIZE, 52, GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(scrollPaneViewOrder, GroupLayout.DEFAULT_SIZE, 402, Short.MAX_VALUE)
								.addGap(14)
								.addGroup(gl_desktopPaneViewOrder.createParallelGroup(Alignment.LEADING)
										.addComponent(btnAddToOrder, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE)
										.addComponent(btnPayment, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE))
										.addContainerGap())
				);


		if ( EmployeeViewOrderTableData.isEmpty() )
		{
			Vector<String> order = new Vector<String>();
			order.add("");
			order.add("");
			order.add("");
			EmployeeViewOrderTableData.add(order);
		}

		tableViewOrder = new JTable(new DefaultTableModel( EmployeeViewOrderTableData, columnNamesViewOrder ) {
			boolean[] columnEditables = new boolean[] {
					false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});

		columns = tableViewOrder.getColumnModel();
		column = columns.getColumn(1);
		column.setMinWidth(320);
		column.setMaxWidth(450);

		column = columns.getColumn(2);
		column.setMinWidth(130);
		column.setMaxWidth(250);

		tableViewOrder.setFont(new Font("Lucida Grande", Font.PLAIN, 30));
		tableViewOrder.setRowHeight(tableViewOrder.getRowHeight() + 20);
		scrollPaneViewOrder.setViewportView(tableViewOrder);
		desktopPaneViewOrder.setLayout(gl_desktopPaneViewOrder);


		tableAllOrders = new JTable(new DefaultTableModel(EmployeeAllOrdersTableData, columnNamesAllOrders) {
			boolean[] columnEditables = new boolean[] {
					false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});


		tableAllOrders.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
			public void valueChanged(ListSelectionEvent event) {

				if ( EmployeeAllOrders.isEmpty() )
				{
					return;
				}	            

				int row = tableAllOrders.getSelectedRow();
				Order curOrder;

				if ( row == -1 )
				{
					curOrder = EmployeeAllOrders.get( 0 );
				}
				else
				{
					curOrder = EmployeeAllOrders.get( tableAllOrders.getSelectedRow() );
				}

				lblTableNumber.setText( String.valueOf( curOrder.getTableNumber() ) );
				lblTotal.setText( "Subtotal: $" + df.format( curOrder.getTotal() ) );

				String items = curOrder.getItems();

				EmployeeViewOrderTableData.clear();
				ItemCosts.clear();

				String item = "";
				String note = "";
				double cost = 0.0;

				int i = 0;
				/* Parse for multiple items */
	            for ( int j = 0; j < items.length(); j++ )
	            {
	            	if ( items.charAt(j) == ',' )
	            	{
	            		item = items.substring( i, j );
	            		
	            		// Parse for the Note
	            		for ( int k = 0; k < item.length(); k++ )
	            		{
	            			if ( item.charAt(k) == '-' )
	            			{
	            				note = item.substring( k + 1 );
	            				item = item.substring( 0, k );
	            				break;
	            			}
	            		}
	  
	    	            Vector<String> curItem = new Vector<String>();  	            
	    	            curItem.add( item );
	    	            curItem.add( note ); 
	            		cost = getItemPrice( item );
	            		ItemCosts.add( cost );
	    	            curItem.add( df.format( cost ) );
	    	            
	            		EmployeeViewOrderTableData.addElement( curItem );
	            		i = j + 2;
	            	}
	            }
	            
	            /* Parse for the last item */
	            item = items.substring(i);
	            
	            // Parse for the Note
	            for ( int k = 0; k < item.length(); k ++ )
        		{
        			if ( item.charAt(k) == '-' )
        			{
        				note = item.substring( k + 1 );
        				item = item.substring( 0, k );
        				break;
        			}
        		}
	            
				Vector<String> curItem = new Vector<String>();
				curItem.add( item );
				curItem.add( note ); 
				
				cost = getItemPrice( item );
				ItemCosts.add( cost );
				curItem.add( df.format( cost ) ); 
				EmployeeViewOrderTableData.add( curItem );

				((DefaultTableModel) tableViewOrder.getModel()).fireTableDataChanged(); // Sets the data in the View Order table

				columns = tableViewOrder.getColumnModel();
				column = columns.getColumn(1);
				column.setMinWidth(320);
				column.setMaxWidth(450);

				column = columns.getColumn(2);
				column.setMinWidth(130);
				column.setMaxWidth(250);
			}
		});

		columns = tableAllOrders.getColumnModel();
		column = columns.getColumn(0);
		column.setMinWidth(60);
		column.setMaxWidth(100);

		column = columns.getColumn(2);
		column.setMinWidth(130);
		column.setMaxWidth(250);
		tableAllOrders.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableAllOrders.setFont(new Font("Lucida Grande", Font.PLAIN, 30));
		tableAllOrders.setRowHeight(tableAllOrders.getRowHeight() + 50);
		tableAllOrders.changeSelection(0, 0, false, false);
		scrollPaneAllOrders.setViewportView(tableAllOrders);
		desktopPaneAllOrders.setLayout(gl_desktopPaneAllOrders);

		GroupLayout gl_panelOrders = new GroupLayout(panelOrders);
		gl_panelOrders.setHorizontalGroup(
				gl_panelOrders.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelOrders.createSequentialGroup()
						.addContainerGap()
						.addComponent(desktopPaneAllOrders, GroupLayout.DEFAULT_SIZE, 413, Short.MAX_VALUE)
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addComponent(desktopPaneViewOrder, GroupLayout.DEFAULT_SIZE, 413, Short.MAX_VALUE)
						.addGap(5))
				);
		gl_panelOrders.setVerticalGroup(
				gl_panelOrders.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelOrders.createSequentialGroup()
						.addContainerGap()
						.addGroup(gl_panelOrders.createParallelGroup(Alignment.LEADING)
								.addComponent(desktopPaneViewOrder)
								.addComponent(desktopPaneAllOrders))
								.addGap(7))
				);
		panelOrders.setLayout(gl_panelOrders);
		contentPane.setLayout(gl_contentPane);
		//tabbedPane.setEnabledAt(1, false);
		refreshEmployeeTable();
		populateEmployeeName();
		btnClockIn.setEnabled(false);
		btnClockOut.setEnabled(false);

		setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{tableAllOrders, btnHistory, btnCreateOrder, tableViewOrder, btnAddToOrder, btnPayment, btnExit}));

		addWindowFocusListener(this);
	}

	private void refreshEmployeeTable(){
		clearEmployeeTable();
		List<String> employeeList = sortEmployeeList(getEmployees());
		DefaultTableModel model = (DefaultTableModel) employeeTable.getModel();
		for(int i = 0; i < employeeList.size(); i++){
			String[] employeeArray = employeeList.get(i).split(",");
			model.addRow(new Object[]{employeeArray[0], employeeArray[1]});
		}
	}
	
	private void clearEmployeeTable(){
		DefaultTableModel model = (DefaultTableModel) employeeTable.getModel();
		model.setRowCount(0);
	}
	
	private void populateEmployeeName(){
		List<String> employeeList = sortEmployeeList(getEmployees());
		lblEmployeeName.setText("Select Employee");
	}

	private List<String> getEmployees(){
		List<String> employeeList = new ArrayList<String> ();
		String entry;
		java.sql.Statement state = DBConnection.OpenConnection();
		String commandstring = "SELECT FirstName, LastName FROM avalenti.Employees;";
		if(state != null){
			try {
				ResultSet rs = state.executeQuery(commandstring);
				while(rs.next() == true) {
					String firstName = rs.getString("FirstName");
					String lastName = rs.getString("LastName");
					entry = lastName + "," + firstName;
					employeeList.add(entry);
				}
			} catch (SQLException e) {
				System.err.println("Error in SQL Execution");
			}
		}
		else
			System.err.println("Statement was null.  No connection?");

		return employeeList;
	}

	private List<String> sortEmployeeList(List<String> employeeList){
		java.util.Collections.sort(employeeList);
		return employeeList;
	}

	private void updateEmployeeName(){
		ListSelectionModel model = employeeTable.getSelectionModel();
		selectedEmployeeFirstName = (String) employeeTable.getValueAt(model.getLeadSelectionIndex(), 1);
		selectedEmployeeLastName = (String) employeeTable.getValueAt(model.getLeadSelectionIndex(), 0);
		lblEmployeeName.setText(selectedEmployeeLastName + ", " + selectedEmployeeFirstName);
	}

	private boolean isClockedIn(){
		/*
		 * Query database for employee PIN
		 * Check Hours table for that pin and today's date
		 */
		int pin = getEmployeePin();
		boolean clockedIn = false;

		java.sql.Statement state = DBConnection.OpenConnection();
		//select any row that matches the selected employee's pin and hasn't clocked out yet
		String query = String.format("SELECT * FROM avalenti.Hours WHERE E_PIN = %s AND C_OUT = '%s';", pin, "0000-00-00 00:00:00");
		if(state != null){
			try {
				ResultSet rs = state.executeQuery(query);
				if(rs.next() == true) {
					clockedIn = true;
				}
			} catch (SQLException e) {
				System.err.println("Error in SQL Execution");
			}
		}
		else
			System.err.println("Statement was null.  No connection?");

		return clockedIn;
	}

	private int getEmployeePin(){
		java.sql.Statement state = DBConnection.OpenConnection();
		int pin = 0;
		String query = String.format("SELECT PIN FROM avalenti.Employees WHERE FirstName = '%s' AND LastName = '%s';", selectedEmployeeFirstName, selectedEmployeeLastName);
		if(state != null){
			try {
				ResultSet rs = state.executeQuery(query);
				if(rs.next() == true) {
					pin =  Integer.parseInt(rs.getString("PIN"));
				}
			} catch (SQLException e) {
				System.err.println("Error in SQL Execution");
			}
		}
		else
			System.err.println("Statement was null.  No connection?");
		return pin;
	}

	private void updateButtons() {
		updateEmployeeName();
		btnDelete.setEnabled(true);
		if(isClockedIn()){
			btnClockIn.setEnabled(false);
			btnClockOut.setEnabled(true);
		}
		else{
			btnClockIn.setEnabled(true);
			btnClockOut.setEnabled(false);
		}
	}

	class SharedListSelectionHandler implements ListSelectionListener {
		public void valueChanged(ListSelectionEvent e) {
			ListSelectionModel lsm = (ListSelectionModel)e.getSource();
			int firstIndex = e.getFirstIndex();
			int lastIndex = e.getLastIndex();
			boolean isAdjusting = e.getValueIsAdjusting();
			System.out.println("Event for indexes "
					+ firstIndex + " - " + lastIndex
					+ "; isAdjusting is " + isAdjusting
					+ "; selected indexes:");

			if (lsm.isSelectionEmpty()) {
				System.out.println(" <none>");
			} else {
				// Find out which indexes are selected.
				int minIndex = lsm.getMinSelectionIndex();
				int maxIndex = lsm.getMaxSelectionIndex();
				for (int i = minIndex; i <= maxIndex; i++) {
					if (lsm.isSelectedIndex(i)) {
						System.out.println(" " + i);
						selectedIndex = i;
					}
				}
			}
			System.out.println();
		}

		public int selectedIndex;
	}

	private double getItemPrice(String itemName){
		java.sql.Statement state = DBConnection.OpenConnection();
		String commandstring = "SELECT * FROM avalenti.Menu WHERE Item = '" + itemName + "';";
		String special = "";
		double itemPrice = 0.00;
		if(state != null){
			try {
				ResultSet rs = state.executeQuery(commandstring);
				if(rs.next() == true) {
					String item = rs.getString("Price");
					special = rs.getString("Special");
					itemPrice = Double.parseDouble(rs.getString("Price"));
				}
			} catch (SQLException e) {
				System.err.println("Error in SQL Execution");
				}
		}
		else
			System.err.println("Statement was null.  No connection?");
		
		if(special.compareTo(getDay()) == 0){
			
			Calendar calendar = Calendar.getInstance();
			if(calendar.get(Calendar.HOUR_OF_DAY) <= BREAKFAST_HOUR){
				itemPrice -= BREAKFAST_DISCOUNT;
	
			}
			else
			{
				itemPrice -= DINNER_DISCOUNT;
			}				
		}
		
		return itemPrice;
	
	}
	
	private String getDay(){
		Calendar calendar = Calendar.getInstance();
		switch (calendar.get(Calendar.DAY_OF_WEEK))
		{
		   case Calendar.MONDAY:
			   return "Monday";
		   case Calendar.TUESDAY:
			   return "Tuesday";
		   case Calendar.WEDNESDAY:
			   return "Wednesday";
		   case Calendar.THURSDAY:
			   return "Thursday";
		   case Calendar.FRIDAY:
			   return "Friday";
		   case Calendar.SATURDAY:
			   return "Saturday";
		   default:
		   		return "Sunday";
		}
	}


	private static void RefreshTableData( Employee loggedInEmployee )
	{
		EmployeeAllOrdersTableData.clear();
		EmployeeViewOrderTableData.clear();
		lblTableNumber.setText( "0" );
		lblTotal.setText( "Subtotal: $0.00" );

		//Get the orders for the logged in employee								
		try {

			EmployeeAllOrders = Order.getEmployeeOrdersInProgress( loggedInEmployee ); //< First get the entered orders

			// Find all entered and served orders that correspond to the logged in employee
			for(int i = 0; i < EmployeeAllOrders.size(); i++){

				Order curOrder = EmployeeAllOrders.elementAt(i);

				Vector<String> order = new Vector<String>();

				order.add( Integer.toString(curOrder.getTableNumber()) );
				order.add( curOrder.getItems() );
				order.add( df.format( curOrder.getTotal()) );

				// TODO: Add the order into the vector based on orderID.
				// 	     This will ensure that older orders appear at the top of the list.
				EmployeeAllOrdersTableData.add(order);
			}
		} catch (Exception e) {
			//Some error occurred in either connecting to DB or there weren't any orders to be cooked
			JOptionPane.showMessageDialog(frame, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			return ;
		}

		((DefaultTableModel) tableAllOrders.getModel()).fireTableDataChanged();

		columns = tableAllOrders.getColumnModel();
		column = columns.getColumn(0);
		column.setMinWidth(60);
		column.setMaxWidth(100);
		column = columns.getColumn(2);
		column.setMinWidth(130);
		column.setMaxWidth(250);
	}

	@Override
	public void windowGainedFocus(WindowEvent e) {

		if ( loggedInEmployee != null )
		{
			int selectedRow = tableAllOrders.getSelectedRow();
			int numberOrders = tableAllOrders.getRowCount();

			RefreshTableData( loggedInEmployee );

			// If the row previously selected was paid for, no row will be selected after a refresh. 
			// Otherwise the refresh will wipe out the previously selected row
			if ( tableAllOrders.getRowCount() != numberOrders )
			{
				tableAllOrders.changeSelection(0, 0, false, false);
			}
			else
			{
				tableAllOrders.changeSelection(selectedRow, 0, false, false);
			}
		}
		tableAllOrders.requestFocus();
		
		//refreshing employee table in the manager tab
		refreshEmployeeTable();
	}

	@Override
	public void windowLostFocus(WindowEvent e) {}
}
