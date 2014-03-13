import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.text.DecimalFormat;
import java.util.Vector;

import javax.swing.JTable;


public class OrderHistoryDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private static TableColumnModel columns;
	private static TableColumn column;
	private static Vector<Order> EmployeeAllOrders = new Vector<Order>(); //< Holds every active order for the logged in Employee
	private static Vector<Vector<String>> EmployeePaidOrderTableData = new Vector<Vector<String>>();
	private JTable table;
	
	private DecimalFormat df = new DecimalFormat("0.00");

	/**
	 * Create the dialog.
	 */
	public OrderHistoryDialog( Employee loggedInEmployee ) {
		
		double totalSales = 0.00;
		
		try {

			EmployeeAllOrders = Order.getEmployeePaidOrders( loggedInEmployee ); //< First get the entered orders
			
			EmployeePaidOrderTableData.clear();

			// Find all entered and served orders that correspond to the logged in employee
			for(int i = 0; i < EmployeeAllOrders.size(); i++){

				Order curOrder = EmployeeAllOrders.elementAt(i);
				
				double total = curOrder.getTotal();
				Vector<String> order = new Vector<String>();

				order.add( Integer.toString(curOrder.getTableNumber()) );
				order.add( curOrder.getItems() );
				order.add( df.format( total ) );
				
				totalSales += total;

				EmployeePaidOrderTableData.add(order);
			}
		} catch (Exception e) {
			//Some error occurred in either connecting to DB or there weren't any orders to be cooked
			System.err.println("Error: OrderHistoryDialog - getEmployeePaidOrder()");
			return ;
		}
		
		
		setTitle("<Current Date> ORDERS");
		setBounds(100, 100, 567, 497);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		JScrollPane scrollPane = new JScrollPane();
		JLabel lblTotalSales = new JLabel("TOTAL SALES: $" + df.format( totalSales ) );
		lblTotalSales.setFont(new Font("Lucida Grande", Font.PLAIN, 22));
		lblTotalSales.setHorizontalAlignment(SwingConstants.LEFT);
		
		Vector<String> columnNames = new Vector<String>();
		columnNames.add("TABLE");
		columnNames.add("ITEM DESCRIPTION");
		columnNames.add("TOTAL");
		
			
		
		JButton btnDone = new JButton("Done");
		btnDone.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, gl_contentPanel.createSequentialGroup()
							.addComponent(lblTotalSales, GroupLayout.DEFAULT_SIZE, 462, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnDone, GroupLayout.PREFERRED_SIZE, 77, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 545, GroupLayout.PREFERRED_SIZE)
							.addGap(6))))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 406, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(btnDone, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblTotalSales, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		
		table = new JTable(new DefaultTableModel( EmployeePaidOrderTableData, columnNames ) {
			boolean[] columnEditables = new boolean[] {
					false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
				
		columns = table.getColumnModel();
		
		column = columns.getColumn(0);
		column.setMinWidth(50);
		column.setMaxWidth(50);
		
		column = columns.getColumn(1);
		column.setMinWidth(380);
		column.setMaxWidth(450);
		
		column = columns.getColumn(2);
		column.setMinWidth(90);
		column.setMaxWidth(90);

		table.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		table.setRowHeight(table.getRowHeight() + 12);
		
		scrollPane.setViewportView(table);
		contentPanel.setLayout(gl_contentPanel);
	}
}
