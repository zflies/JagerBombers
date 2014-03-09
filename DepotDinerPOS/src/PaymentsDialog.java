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
import javax.swing.JScrollPane;

import java.awt.Font;

import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;

public class PaymentsDialog extends JDialog implements WindowFocusListener{

	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private JLabel lblTotal;
	private static TableColumnModel columns;
	private static TableColumn column;
	private JDialog dialogCashPayment;
	private JDialog dialogCreditPayment;
	private JDialog dialogGiftPayment;
	
	private static Vector<Vector<String>> EmployeeViewOrderTableData = new Vector<Vector<String>>();
	private static ArrayList<Double> ItemCosts = new ArrayList<Double>();	//< Used to keep track of item prices for split ticket usage
	
	private DecimalFormat df = new DecimalFormat("0.00");
	
	private Order curOrder;
	
	/**
	 * Create the dialog.
	 */
	public PaymentsDialog( Order order, ArrayList<Double> itemCosts ) {
		
		setResizable(false);
		
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
	
		lblTotal = new JLabel("TOTAL: $" +  df.format( curOrder.getTotal() ) );
		lblTotal.setFont(new Font("Lucida Grande", Font.PLAIN, 37));
		
		refreshReceipt( curOrder );
		
		setTitle("Payment for TABLE " + curOrder.getTableNumber() );
		setBounds(100, 100, 675, 526);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		JScrollPane scrollPane = new JScrollPane();
		JButton btnNewButton = new JButton("CASH");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				dialogCashPayment = new CashPaymentDialog( curOrder );
				dialogCashPayment.setVisible(true);
				
				dialogCashPayment.setLocationRelativeTo(null);
				dialogCashPayment.setTitle("CASH - TABLE " + curOrder.getTableNumber() + " PAYMENT");
				
				dialogCashPayment.setAlwaysOnTop(true);	
			}
		});
		btnNewButton.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		JButton btnCreditCard = new JButton("CREDIT CARD");
		btnCreditCard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				dialogCreditPayment = new CreditPaymentDialog( curOrder );
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
				
				dialogGiftPayment = new GiftPaymentDialog( curOrder );
				dialogGiftPayment.setVisible(true);
				
				dialogGiftPayment.setLocationRelativeTo(null);
				dialogGiftPayment.setTitle("GIFT - TABLE " + curOrder.getTableNumber() + " PAYMENT");
				
				dialogGiftPayment.setAlwaysOnTop(true);
			}
		});
		btnGiftCard.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		JButton btnSplitTicket = new JButton("SPLIT TICKET");
		btnSplitTicket.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		
		JButton btnPrint = new JButton("PRINT RECEIPT");
		btnPrint.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		
		JButton btnCancel = new JButton("CANCEL");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancel.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addComponent(lblTotal, GroupLayout.DEFAULT_SIZE, 460, Short.MAX_VALUE)
							.addGap(95)
							.addComponent(btnCancel, GroupLayout.PREFERRED_SIZE, 99, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 457, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(btnPrint, GroupLayout.PREFERRED_SIZE, 191, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnSplitTicket, GroupLayout.PREFERRED_SIZE, 191, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
									.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 191, GroupLayout.PREFERRED_SIZE)
									.addComponent(btnCreditCard, GroupLayout.PREFERRED_SIZE, 191, GroupLayout.PREFERRED_SIZE)
									.addComponent(btnGiftCard, GroupLayout.PREFERRED_SIZE, 191, GroupLayout.PREFERRED_SIZE)))))
					.addGap(5))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(btnCreditCard, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(btnGiftCard, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE)
							.addGap(90)
							.addComponent(btnSplitTicket, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(btnPrint, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE))
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 383, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblTotal, GroupLayout.PREFERRED_SIZE, 94, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnCancel, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE))
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

	@Override
	public void windowGainedFocus(WindowEvent e) {

		//System.out.println("Window gained focus");
		if ( curOrder.getStatus() == Order.Status.Paid )
		{
			dispose();
		}

	}

	@Override
	public void windowLostFocus(WindowEvent e) {}

}
