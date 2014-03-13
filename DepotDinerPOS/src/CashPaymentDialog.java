

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
import java.util.Date;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

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
	private JTextField txtTip;
	private JLabel lblTip;


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
		setBounds(100, 100, 331, 380);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		txtCash = new JTextField();
		txtCash.setHorizontalAlignment(SwingConstants.RIGHT);
		txtCash.setText("0");
		txtCash.setEnabled(false);
		txtCash.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				
				double cashReceived = Double.parseDouble( txtCash.getText() );
				double totalCost = curOrder.getTotal() * taxRate + Double.parseDouble( txtTip.getText() ); // 8% Tax
				
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
		
				int pin = curOrder.getEmployeePin();

				double tax = curOrder.getTotal() * ( taxRate - 1);
				double price = curOrder.getTotal();
				double tip = Double.parseDouble( txtTip.getText() );
				double total = price + tax + tip;
				
				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				Date date = new Date();
				
				String queryStatus = String.format("UPDATE Orders SET Status ='paid', Total = '%s' WHERE ID ='%s';", curOrder.getTotal() * taxRate, curOrder.getOrderId() );
	
				String queryPayment = String.format("INSERT INTO `avalenti`.`Payments` (`Order_ID`, `E_PIN`, `Date`, `Tip`, `Price`, `Tax`, `Total`) VALUES (%s, %s, '%s', %s, %s, %s, %s);", curOrder.getOrderId(), pin, dateFormat.format(date).toString(), tip, price , tax, total);

				java.sql.Statement state = DBConnection.OpenConnection();
				
				if(state != null){
					try {
						state.execute(queryStatus);
						state.execute(queryPayment);
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
		
		txtTip = new JTextField();
		txtTip.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				
				double tip = Double.parseDouble( txtTip.getText() );
				lblTotal.setText( "Total: $" + df.format( ( curOrder.getTotal() * taxRate ) + tip) );
				txtCash.setEnabled( true );
				txtCash.requestFocus();
				
			}});
		txtTip.setHorizontalAlignment(SwingConstants.RIGHT);
		txtTip.setText("0");
		txtTip.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		txtTip.setColumns(10);
		
		lblTotal = new JLabel( "Total: $" + df.format( curOrder.getTotal() * taxRate ) );
		lblTotal.setHorizontalAlignment(SwingConstants.CENTER);
		lblTotal.setFont(new Font("Lucida Grande", Font.PLAIN, 28));
		
		lblTip = new JLabel("TIP:");
		lblTip.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTip.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(126)
							.addComponent(btnPay))
						.addComponent(lblTotal, GroupLayout.PREFERRED_SIZE, 328, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblCash)
							.addGap(24)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblChange, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE)
								.addComponent(txtCash, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE))
							.addGap(83))
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING, false)
							.addGroup(gl_contentPanel.createSequentialGroup()
								.addGap(8)
								.addComponent(lblTax, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
							.addGroup(gl_contentPanel.createSequentialGroup()
								.addGap(23)
								.addComponent(lblSubTotal, GroupLayout.PREFERRED_SIZE, 211, GroupLayout.PREFERRED_SIZE))))
					.addGap(4))
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblTip, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE)
					.addGap(22)
					.addComponent(txtTip, GroupLayout.PREFERRED_SIZE, 162, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(89, Short.MAX_VALUE))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblSubTotal, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblTax)
					.addGap(18)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(txtTip, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblTip, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE))
					.addGap(15)
					.addComponent(lblTotal, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCash, GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE)
						.addComponent(txtCash, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblChange, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
					.addGap(12)
					.addComponent(btnPay, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		contentPanel.setLayout(gl_contentPanel);
	}
}
