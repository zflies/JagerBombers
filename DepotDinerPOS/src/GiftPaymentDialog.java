import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;

import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.LayoutStyle.ComponentPlacement;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class GiftPaymentDialog extends JDialog {
	
	private static final double taxRate = 1.08;

	private final JPanel contentPanel = new JPanel();
	private JTextField txtCardNumber;
	private JLabel lblSecurityCode;
	private JTextField txtSecurityCode;
	private JButton btnPay;
	private JLabel lblTotalRemaining;
	private JButton btnApply;
	private DecimalFormat df = new DecimalFormat("0.00");
	private JTextField txtTip;


	/**
	 * Create the dialog.
	 */
	public GiftPaymentDialog( final Order curOrder ) {
		
		if ( curOrder == null )
		{
			System.out.println("NULL Exception - CashPaymentDialog(); curOrder = null");
			dispose();
		}
		
		setResizable(false);
		setTitle("GIFT - TABLE " + curOrder.getTableNumber() + " Payment");
		setBounds(100, 100, 342, 306);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		txtCardNumber = new JTextField();
		txtCardNumber.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		txtCardNumber.setColumns(10);
		JLabel lblCardNumber = new JLabel("Card Number:");
		lblCardNumber.setHorizontalAlignment(SwingConstants.LEFT);
		lblCardNumber.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		
		lblSecurityCode = new JLabel("Security Code:");
		lblSecurityCode.setHorizontalAlignment(SwingConstants.LEFT);
		lblSecurityCode.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		
		txtSecurityCode = new JTextField();
		txtSecurityCode.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		txtSecurityCode.setColumns(10);
		
		btnPay = new JButton("PAY");
		btnPay.setEnabled(false);
		btnPay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				// TODO: Need Form Validation
				
				int pin = curOrder.getEmployeePin();

				double tax = curOrder.getTotal() * ( taxRate - 1);
				double price = curOrder.getTotal();
				double tip = Double.parseDouble( txtTip.getText().toString() );
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
		
		lblTotalRemaining = new JLabel("Total Remaining: $" + df.format( curOrder.getTotal() * taxRate ) );
		lblTotalRemaining.setHorizontalAlignment(SwingConstants.CENTER);
		lblTotalRemaining.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		
		btnApply = new JButton("APPLY");
		btnApply.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				// TODO: Subtract card balance from ticket cost
				btnPay.setEnabled( true );
				
			}
		});
		btnApply.setFont(new Font("Lucida Grande", Font.PLAIN, 17));
		
		txtTip = new JTextField();
		txtTip.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		txtTip.setColumns(10);
		
		JLabel lblTip = new JLabel("TIP:");
		lblTip.setHorizontalAlignment(SwingConstants.CENTER);
		lblTip.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblTotalRemaining, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 326, Short.MAX_VALUE)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblTip, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtTip, GroupLayout.PREFERRED_SIZE, 133, GroupLayout.PREFERRED_SIZE))
						.addGroup(Alignment.TRAILING, gl_contentPanel.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblSecurityCode, GroupLayout.PREFERRED_SIZE, 124, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblCardNumber, GroupLayout.DEFAULT_SIZE, 124, Short.MAX_VALUE))
							.addGap(18)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING, false)
								.addGroup(gl_contentPanel.createSequentialGroup()
									.addComponent(txtSecurityCode, GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(btnApply, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE))
								.addComponent(txtCardNumber, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap())
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap(127, Short.MAX_VALUE)
					.addComponent(btnPay, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE)
					.addGap(124))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGap(16)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(txtCardNumber, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblCardNumber, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtSecurityCode, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnApply, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblSecurityCode, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE))
					.addGap(21)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblTip)
						.addComponent(txtTip, GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblTotalRemaining, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(btnPay, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		contentPanel.setLayout(gl_contentPanel);
	}
}
