import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.JDialog;
import javax.swing.JTabbedPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ListSelectionModel;

import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;


public class ManageView extends JFrame {

	private JPanel contentPane;
	private JDialog dialogAddEmployee;
	private static ManageView frame;
	
	private String employeeName;
	private JTable employeeTable;
	private JLabel lblEmployeeName;
	private String selectedEmployeeFirstName;
	private String selectedEmployeeLastName;
	private SharedListSelectionHandler selectionHandler;
	ListSelectionModel listSelectionModel;
	String column_names[]= {"First Name","Last Name"};

	/**
	 * Create the frame.
	 */
	public ManageView( Employee loggedInEmployee ) {
		
		employeeName = loggedInEmployee.getFullName();
		
		setTitle("Steven's Depot Diner");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 887, 544);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		
		JLabel lblEmployee = new JLabel("Employee: " + employeeName);
		lblEmployee.setFont(new Font("Lucida Grande", Font.PLAIN, 30));
		
		JButton btnExit = new JButton("Exit");
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
		
		JButton btnClockIn = new JButton("Clock In");
		
		JButton btnClockOut = new JButton("Clock Out");
		
		JButton btnViewOrders = new JButton("View Orders");
		
		JButton btnViewPayrollhours = new JButton("View Payroll/Hours");
		
		JButton btnDelete = new JButton("Delete");
		
		JButton btnGenerateReport = new JButton("Generate Report");
		
		JScrollPane scrollPane = new JScrollPane();
		GroupLayout gl_panel_manage = new GroupLayout(panel_manage);
		gl_panel_manage.setHorizontalGroup(
			gl_panel_manage.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_manage.createSequentialGroup()
					.addGap(20)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 311, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 145, Short.MAX_VALUE)
					.addGroup(gl_panel_manage.createParallelGroup(Alignment.TRAILING)
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
							.addGap(176))))
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
					.addGroup(gl_panel_manage.createParallelGroup(Alignment.TRAILING)
						.addGroup(Alignment.LEADING, gl_panel_manage.createSequentialGroup()
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
							.addGap(79))
						.addGroup(gl_panel_manage.createSequentialGroup()
							.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 308, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)))
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
				System.out.println("ACTUALLY CLICKED ENTRY");
				updateEmployeeName();
			}
		});
		listSelectionModel = employeeTable.getSelectionModel();
		selectionHandler = new SharedListSelectionHandler();
        listSelectionModel.addListSelectionListener(selectionHandler);
        employeeTable.setSelectionModel(listSelectionModel);
		employeeTable.setFont(new Font("Lucida Grande", Font.BOLD, 15));
		scrollPane.setViewportView(employeeTable);
		panel_manage.setLayout(gl_panel_manage);
		
		JPanel panel_orders = new JPanel();
		tabbedPane.addTab("Orders", null, panel_orders, null);
		contentPane.setLayout(gl_contentPane);
		//tabbedPane.setEnabledAt(1, false);
		refreshEmployeeTable();
		populateEmployeeName();
	}
	
	private void refreshEmployeeTable(){
		List<String> employeeList = sortEmployeeList(getEmployees());
		DefaultTableModel model = (DefaultTableModel) employeeTable.getModel();
		for(int i = 0; i < employeeList.size(); i++){
			String[] employeeArray = employeeList.get(i).split(",");
			model.addRow(new Object[]{employeeArray[0], employeeArray[1]});
		}
	}
	
	private void populateEmployeeName(){
		List<String> employeeList = sortEmployeeList(getEmployees());
		lblEmployeeName.setText(employeeList.get(0));
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
	
}
