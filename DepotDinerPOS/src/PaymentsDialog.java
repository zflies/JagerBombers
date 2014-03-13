import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;

import java.awt.Font;

import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;

public class PaymentsDialog extends JDialog implements WindowFocusListener{
	
	private static final double taxRate = 1.08;
	
	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private JLabel lblSubTotal;
	private static TableColumnModel columns;
	private static TableColumn column;
	private JDialog dialogCashPayment;
	private JDialog dialogCreditPayment;
	private JDialog dialogGiftPayment;
	private ApplyDepositDialog depositDialog;
	
	private static Vector<Vector<String>> EmployeeViewOrderTableData = new Vector<Vector<String>>();
	private static ArrayList<Double> ItemCosts = new ArrayList<Double>();	//< Used to keep track of item prices for split ticket usage
	
	private DecimalFormat df = new DecimalFormat("0.00");
	
	private Order curOrder;
	private Order splitOrder;
	private Employee loggedInEmployee;
	
	private boolean needSplit;
	
	/**
	 * Create the dialog.
	 */
	public PaymentsDialog( Employee employee, Order order, ArrayList<Double> itemCosts ) {
		
		needSplit = false;
		
		setResizable(false);
		
		loggedInEmployee = employee;
		curOrder = order;
		ItemCosts = itemCosts;
		
		Vector<String> columnNamesReceipt = new Vector<String>();
		columnNamesReceipt.add("ITEM");
		columnNamesReceipt.add("PRICE");
		
		if ( EmployeeViewOrderTableData.isEmpty() )
		{
			Vector<String> temp = new Vector<String>();
			temp.add("");
			temp.add("");
			
			EmployeeViewOrderTableData.add(temp);
		}
		
		table = new JTable(new DefaultTableModel(EmployeeViewOrderTableData, columnNamesReceipt) {
			boolean[] columnEditables = new boolean[] {
					false, false
				};
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
			});
		table.setRowSelectionAllowed(false);
		table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		table.setFont(new Font("Lucida Grande", Font.PLAIN, 28));
		table.setRowHeight(table.getRowHeight() + 17);
	
		lblSubTotal = new JLabel( "Subtotal: $" +  df.format( curOrder.getTotal() ) );
		lblSubTotal.setHorizontalAlignment(SwingConstants.LEFT);
		lblSubTotal.setFont(new Font("Lucida Grande", Font.PLAIN, 33));
		
		refreshReceipt( curOrder );
		
		setTitle("Payment for TABLE " + curOrder.getTableNumber() );
		setBounds(100, 100, 675, 496);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		JScrollPane scrollPane = new JScrollPane();
		JButton btnCash = new JButton("CASH");
		btnCash.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int[] selection = table.getSelectedRows();
				
				// If no items were selected or if All items are selected, then do not split ticket
				if ( selection.length < 1 || selection.length == EmployeeViewOrderTableData.size() )
				{
					dialogCashPayment = new CashPaymentDialog( curOrder );
				}
				else
				{
					createSplitOrder( selection );
					dialogCashPayment = new CashPaymentDialog( splitOrder );	
				}

				dialogCashPayment.setVisible(true);
				
				dialogCashPayment.setLocationRelativeTo(null);
				dialogCashPayment.setTitle("CASH - TABLE " + curOrder.getTableNumber() + " PAYMENT");
				
				dialogCashPayment.setAlwaysOnTop(true);	
			}
		});
		btnCash.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		JButton btnCreditCard = new JButton("CREDIT CARD");
		btnCreditCard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int[] selection = table.getSelectedRows();
				
				// If no items were selected or if All items are selected, then do not split ticket
				if ( selection.length < 1 || selection.length == EmployeeViewOrderTableData.size() )
				{
					dialogCreditPayment = new CreditPaymentDialog( curOrder );
				}
				else
				{
					createSplitOrder( selection );
					dialogCreditPayment = new CreditPaymentDialog( splitOrder );	
				}
				dialogCreditPayment.setVisible(true);
				
				dialogCreditPayment.setLocationRelativeTo(null);
				dialogCreditPayment.setTitle("CREDIT - TABLE " + curOrder.getTableNumber() + " PAYMENT");
				
				dialogCreditPayment.setAlwaysOnTop(true);	
			}
		});
		btnCreditCard.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		JButton btnGiftCard = new JButton("GIFT CARD");
		btnGiftCard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int[] selection = table.getSelectedRows();
				
				// If no items were selected or if All items are selected, then do not split ticket
				if ( selection.length < 1 || selection.length == EmployeeViewOrderTableData.size() )
				{
					dialogGiftPayment = new GiftPaymentDialog( curOrder );
				}
				else
				{
					createSplitOrder( selection );
					dialogGiftPayment = new GiftPaymentDialog( splitOrder );	
				}
				
				dialogGiftPayment.setVisible(true);
				
				dialogGiftPayment.setLocationRelativeTo(null);
				dialogGiftPayment.setTitle("GIFT - TABLE " + curOrder.getTableNumber() + " PAYMENT");
				
				dialogGiftPayment.setAlwaysOnTop(true);
			}
		});
		btnGiftCard.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		final JButton btnSplitTicket = new JButton("SPLIT TICKET");
		btnSplitTicket.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if ( table.getRowSelectionAllowed() )
				{
					table.setRowSelectionAllowed( false );
				}
				else
				{
					table.setRowSelectionAllowed( true );
					// TODO: Alert user (tooltip?) to select items for split ticket
					//btnSplitTicket.setToolTipText("Hold Ctrl to select mutiple items.");
				}
			}
		});
		btnSplitTicket.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		
		JButton btnPrint = new JButton("PRINT RECEIPT");
		btnPrint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(contentPanel, "Printing...", "", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		btnPrint.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		
		JButton btnCancel = new JButton("CANCEL");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancel.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		
		JButton btnDeposit = new JButton("APPLY DEPOSIT");
		btnDeposit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				depositDialog = new ApplyDepositDialog();
				depositDialog.setVisible(true);
				depositDialog.setLocationRelativeTo(null);
				depositDialog.setAlwaysOnTop(true);	
			}
		});
		btnDeposit.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 456, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_contentPanel.createSequentialGroup()
									.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
										.addComponent(btnPrint, GroupLayout.PREFERRED_SIZE, 191, GroupLayout.PREFERRED_SIZE)
										.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
											.addComponent(btnCash, GroupLayout.PREFERRED_SIZE, 191, GroupLayout.PREFERRED_SIZE)
											.addComponent(btnCreditCard, GroupLayout.PREFERRED_SIZE, 191, GroupLayout.PREFERRED_SIZE)
											.addComponent(btnGiftCard, GroupLayout.PREFERRED_SIZE, 191, GroupLayout.PREFERRED_SIZE)))
									.addGap(5))
								.addGroup(gl_contentPanel.createSequentialGroup()
									.addComponent(btnDeposit, GroupLayout.PREFERRED_SIZE, 191, GroupLayout.PREFERRED_SIZE)
									.addContainerGap())
								.addGroup(gl_contentPanel.createSequentialGroup()
									.addComponent(btnSplitTicket, GroupLayout.PREFERRED_SIZE, 191, GroupLayout.PREFERRED_SIZE)
									.addContainerGap())))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(9)
							.addComponent(lblSubTotal, GroupLayout.PREFERRED_SIZE, 515, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
							.addComponent(btnCancel, GroupLayout.PREFERRED_SIZE, 99, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addComponent(btnCash, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(btnCreditCard, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(btnGiftCard, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(btnDeposit, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(btnSplitTicket, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnPrint, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE))
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 383, GroupLayout.PREFERRED_SIZE))
					.addGap(26)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblSubTotal)
						.addComponent(btnCancel, GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE))
					.addContainerGap())
		);
		
		
		scrollPane.setViewportView(table);
		contentPanel.setLayout(gl_contentPanel);
		
        addWindowFocusListener(this);
	}
	
	/**
	 * Refreshes the receipt.
	 * @param order
	 */
	private void refreshReceipt( Order curOrder ){
		
		String items = curOrder.getItems();
        
        EmployeeViewOrderTableData.clear();
        
        String item = "";
        double cost = 0.0;
        int i = 0;
        int itemCount = 0;
        
        for ( int j = 0; j < items.length(); j++ )
        {
        	if ( items.charAt(j) == ',' )
        	{
        		item = items.substring(i, j);
        		
        		cost = ItemCosts.get(itemCount);
        		
	            Vector<String> curItem = new Vector<String>();  	            
	            curItem.add( item );
	            curItem.add( String.valueOf( df.format( cost ) ) ); // TODO: Query for item price?
	            
        		EmployeeViewOrderTableData.addElement( curItem );
        		i = j + 2;
        		itemCount++;
        	}
        }
        
        item = items.substring(i);
		cost = ItemCosts.get(itemCount);

        Vector<String> curItem = new Vector<String>();
        curItem.add( item );
        curItem.add( String.valueOf( df.format( cost ) ) ); // TODO: Query for item price?
		EmployeeViewOrderTableData.add( curItem );
       	    
		Vector<String> columnNamesReceipt = new Vector<String>();
		columnNamesReceipt.add("ITEM");
		columnNamesReceipt.add("PRICE");
		
	    ((DefaultTableModel) table.getModel()).setDataVector(EmployeeViewOrderTableData, columnNamesReceipt); // Sets the data in the View Order table

	    columns = table.getColumnModel();
		
		column = columns.getColumn(1);
		column.setMinWidth(130);
		column.setMaxWidth(250);
	
	}
	
	/**
	 *  Returns an Order object of the selected items
	 */
	private void createSplitOrder( int[] selection ){
		
		needSplit = true;
		
		String items = "";
		double totalPrice = 0.00;
		
		for ( int i = 0; i < selection.length; i ++ )
		{
			if ( i > 0 )
			{
				items += ", ";
			}
			items += EmployeeViewOrderTableData.get( selection[i] ).firstElement();
			totalPrice += ItemCosts.get( selection[i] );
		}
		
		String employeePIN = "(SELECT PIN FROM Employees WHERE FirstName = '" + loggedInEmployee.getFirstName() +"' " +
				"AND LastName = '" + loggedInEmployee.getLastName() + "')";
		int tableNumber = curOrder.getTableNumber();
		String query = String.format("INSERT INTO `avalenti`.`Orders` (`E_PIN`, `Table_No`, `Items`, `Status`, `Total`) VALUES (%s, '%s', '%s', 'split', '%s');", employeePIN, tableNumber, items, totalPrice);
		
		java.sql.Statement state = DBConnection.OpenConnection();
		if(state != null){
			try {
				state.execute(query);
			} catch (SQLException e) {
				System.err.println("Error in SQL Execution");
				}
		}
		else
			System.err.println("Statement was null.  No connection?");	
				
		try {
			splitOrder = Order.getEmployeeSplitOrder( loggedInEmployee ).firstElement();
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
	/**
	 * This will retrieve the deposit info from the user's reservation selection and apply the deposit to the orders total.
	 */
	private void getDepositInfo() {
		
		double deposit = depositDialog.getDeposit();
		
		double newTotal = curOrder.getTotal() - deposit;				
		
		if ( newTotal < 0.00 )
		{
			newTotal = 0.00;
		}
		
		String query = String.format("UPDATE Orders SET `Total` = '%s', `Deposit` = '%s' WHERE ID = %s;", newTotal, deposit, curOrder.getOrderId() );

		java.sql.Statement state = DBConnection.OpenConnection();

		if(state != null){
			try {
				state.execute(query);
			} catch (SQLException e1) {
				System.err.println("Error in SQL Execution");
			}
		}
		else
			System.err.println("Statement was null.  No connection?");

		curOrder.setTotal( newTotal );

		lblSubTotal.setText( "Subtotal: $" +  df.format( newTotal ) );
		
		refreshReceipt( curOrder );
		
		depositDialog = null;
	}

	@Override
	public void windowGainedFocus(WindowEvent e) {
		
		if ( depositDialog != null )
		{
			getDepositInfo();
		}
		
		if ( curOrder.getStatus() == Order.Status.Paid )
		{
			dispose();
		}
		else if ( splitOrder != null && needSplit )
		{
			needSplit = false;

			if ( splitOrder.getStatus() == Order.Status.Paid )
			{
				// Modify curOrder to remove the split order items and update the DB
				int[] selection = table.getSelectedRows();
				int itemCount = 0;
				String items = "";
				boolean isSelected;

				for ( int i = 0; i < EmployeeViewOrderTableData.size(); i++ )
				{
					isSelected = false;
					for ( int j = 0; j < selection.length; j ++ )
					{
						if ( i == selection[j] )
						{
							isSelected = true;
						}
					}

					if ( !isSelected )
					{
						if ( itemCount > 0 )
						{
							items += ", ";
						}
						items += EmployeeViewOrderTableData.get( i ).firstElement();
						itemCount++;
					}
				}

				double total = curOrder.getTotal() - ( splitOrder.getTotal() / taxRate );

				String query = String.format("UPDATE Orders SET `Items` = '%s', `Total` = '%s' WHERE ID = %s;", items, total, curOrder.getOrderId() );

				java.sql.Statement state = DBConnection.OpenConnection();

				if(state != null){
					try {
						state.execute(query);
					} catch (SQLException e1) {
						System.err.println("Error in SQL Execution");
					}
				}
				else
					System.err.println("Statement was null.  No connection?");

				curOrder.setItems( items );
				curOrder.setTotal( total );
				lblSubTotal.setText( "Subtotal: $" +  df.format( curOrder.getTotal() ) );
				
				refreshReceipt( curOrder );
			}
			else
			{
				// Delete splitOrder from the DB since the operation was aborted
				String query = String.format("DELETE FROM Orders WHERE ID ='%s';", splitOrder.getOrderId() );

				java.sql.Statement state = DBConnection.OpenConnection();

				if(state != null){
					try {
						state.execute(query);
						state.close();
					} catch (SQLException e1) {
						System.err.println("Error in SQL Execution");
					}
				}
				else
					System.err.println("Statement was null.  No connection?");
				

			}
		}
	}


	@Override
	public void windowLostFocus(WindowEvent e) {
		
	}
}
