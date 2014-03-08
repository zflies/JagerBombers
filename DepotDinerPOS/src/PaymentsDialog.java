import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.List;

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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;

public class PaymentsDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private JLabel lblTotal;
	private static TableColumnModel columns;
	private static TableColumn column;
	
	private static Vector<Vector<String>> EmployeeViewOrderTableData = new Vector<Vector<String>>();
	private static ArrayList<Double> ItemCosts = new ArrayList<Double>();	//< Used to keep track of item prices for split ticket usage
	
	private DecimalFormat df = new DecimalFormat("#.00");
	
	/**
	 * Create the dialog.
	 */
	public PaymentsDialog( Order curOrder, ArrayList<Double> itemCosts ) {
		
		ItemCosts = itemCosts;
		
		Vector<String> columnNamesReceipt = new Vector<String>();
		columnNamesReceipt.add("ITEM");
		columnNamesReceipt.add("PRICE");
		
		if ( EmployeeViewOrderTableData.isEmpty() )
		{
			Vector<String> order = new Vector<String>();
			order.add("");
			order.add("");
			
			EmployeeViewOrderTableData.add(order);
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
	
		lblTotal = new JLabel("TOTAL: $0.00");
		lblTotal.setFont(new Font("Lucida Grande", Font.PLAIN, 37));
		
		refreshReceipt( curOrder );
		
		setTitle("Payment for TABLE " + curOrder.getTableNumber() );
		setBounds(100, 100, 675, 520);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		JScrollPane scrollPane = new JScrollPane();
		JButton btnNewButton = new JButton("CASH");
		btnNewButton.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		JButton btnCreditCard = new JButton("CREDIT CARD");
		btnCreditCard.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		JButton btnGiftCard = new JButton("GIFT CARD");
		btnGiftCard.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		JButton btnSplitTicket = new JButton("SPLIT TICKET");
		btnSplitTicket.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, gl_contentPanel.createSequentialGroup()
							.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 456, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_contentPanel.createSequentialGroup()
									.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 191, GroupLayout.PREFERRED_SIZE)
									.addContainerGap())
								.addGroup(gl_contentPanel.createSequentialGroup()
									.addComponent(btnCreditCard, GroupLayout.PREFERRED_SIZE, 191, GroupLayout.PREFERRED_SIZE)
									.addContainerGap())
								.addGroup(gl_contentPanel.createSequentialGroup()
									.addComponent(btnSplitTicket, GroupLayout.PREFERRED_SIZE, 191, GroupLayout.PREFERRED_SIZE)
									.addContainerGap())
								.addGroup(gl_contentPanel.createSequentialGroup()
									.addComponent(btnGiftCard, GroupLayout.PREFERRED_SIZE, 191, GroupLayout.PREFERRED_SIZE)
									.addContainerGap())))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addComponent(lblTotal, GroupLayout.DEFAULT_SIZE, 653, Short.MAX_VALUE)
							.addContainerGap())))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(btnCreditCard, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(btnGiftCard, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 159, Short.MAX_VALUE)
							.addComponent(btnSplitTicket, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE))
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 389, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblTotal, GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE))
		);
		
		
		scrollPane.setViewportView(table);
		contentPanel.setLayout(gl_contentPanel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	
	/**
	 * Refreshes the receipt.
	 * @param order
	 */
	private void refreshReceipt( Order curOrder ){
		
		lblTotal.setText( "TOTAL: $" + df.format( curOrder.getTotal() ) );
		
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
}
