

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;

import javax.swing.SwingConstants;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.text.DecimalFormat;
import javax.swing.LayoutStyle.ComponentPlacement;

public class CashPaymentDialog extends JDialog {
	
	private static final double taxRate = 1.08;

	private final JPanel contentPanel = new JPanel();
	private JLabel lblCash;
	private JLabel lblSubTotal;
	private JLabel lblChange;
	private JTextField txtCash;
	private JButton btnPay;
	private DecimalFormat df = new DecimalFormat("0.00");
	private JLabel lblTax;
	private JLabel lblTotal;


	/**
	 * Create the dialog.
	 */
	public CashPaymentDialog( final Order curOrder ) {
		
		if ( curOrder == null )
		{
			System.out.println("NULL Exception - CashPaymentDialog(); curOrder = null");
			dispose();
		}
		
		setResizable(false);
		setTitle("CASH - TABLE " + curOrder.getTableNumber() + " Payment");
		setBounds(100, 100, 342, 319);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		txtCash = new JTextField();
		txtCash.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				
				double cashReceived = Double.parseDouble( txtCash.getText() );
				double totalCost = curOrder.getTotal() * taxRate; // 8% Tax
				
				if( cashReceived >= totalCost )
				{
					btnPay.setEnabled( true );
					lblChange.setText( "CHANGE: $" + String.valueOf( df.format( cashReceived - totalCost ) ) );
				}
				else
				{
					JOptionPane optionPane = new JOptionPane();
					optionPane.setMessage("Not enough money to pay.");
					JDialog dialog = optionPane.createDialog("Insufficient Funds");
					dialog.setAlwaysOnTop(true);
					dialog.setVisible(true);
					
				}
				
			}});
		txtCash.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		txtCash.setColumns(10);
		
		lblCash = new JLabel("CASH:");
		lblCash.setHorizontalAlignment(SwingConstants.LEFT);
		lblCash.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		
		lblSubTotal = new JLabel("Subtotal: $" + df.format( curOrder.getTotal() ) );
		lblSubTotal.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSubTotal.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		
		lblChange = new JLabel("CHANGE: $0.00");
		lblChange.setHorizontalAlignment(SwingConstants.CENTER);
		lblChange.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		
		btnPay = new JButton("PAY");
		btnPay.setEnabled(false);
		btnPay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String query = String.format("UPDATE Orders SET Status ='paid', Total = '%s' WHERE ID ='%s';", curOrder.getTotal() * taxRate, curOrder.getOrderId() );
				
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
				
				curOrder.setStatus( Order.Status.Paid );
				curOrder.setTotal( curOrder.getTotal() * taxRate );
				
				dispose();
			}
		});
		btnPay.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		
		lblTax = new JLabel( "Tax: $" + df.format( curOrder.getTotal() * (taxRate - 1) ) );
		lblTax.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTax.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		
		lblTotal = new JLabel( "Total: $" + df.format( curOrder.getTotal() * taxRate ) );
		lblTotal.setHorizontalAlignment(SwingConstants.CENTER);
		lblTotal.setFont(new Font("Lucida Grande", Font.PLAIN, 28));
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_contentPanel.createSequentialGroup()
					.addContainerGap(127, Short.MAX_VALUE)
					.addComponent(btnPay)
					.addGap(124))
				.addGroup(Alignment.TRAILING, gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblTotal, GroupLayout.DEFAULT_SIZE, 320, Short.MAX_VALUE)
					.addContainerGap())
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGap(12)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblCash)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPanel.createSequentialGroup()
									.addGap(24)
									.addComponent(lblChange, GroupLayout.PREFERRED_SIZE, 159, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPanel.createSequentialGroup()
									.addGap(18)
									.addComponent(txtCash, GroupLayout.PREFERRED_SIZE, 173, GroupLayout.PREFERRED_SIZE))))
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
							.addComponent(lblSubTotal, GroupLayout.PREFERRED_SIZE, 211, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblTax, GroupLayout.PREFERRED_SIZE, 226, GroupLayout.PREFERRED_SIZE)))
					.addGap(76))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblSubTotal, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblTax)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblTotal, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtCash, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblCash, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblChange, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnPay, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
					.addGap(31))
		);
		contentPanel.setLayout(gl_contentPanel);
	}
}
