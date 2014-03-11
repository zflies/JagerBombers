import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
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
import java.text.DecimalFormat;


public class CreditPaymentDialog extends JDialog {

	private static final double taxRate = 1.08;

	private final JPanel contentPanel = new JPanel();
	private JTextField txtTip;
	private JButton btnPay;
	private JLabel lblSubtotal;
	private JLabel lblTotal;
	private JLabel lblTax;
	private DecimalFormat df = new DecimalFormat("0.00");


	/**
	 * Create the dialog.
	 */
	public CreditPaymentDialog( final Order curOrder ) {
		
		if ( curOrder == null )
		{
			System.out.println("NULL Exception - CreditPaymentDialog(); curOrder = null");
			dispose();
		}
		
		setResizable(false);
		setTitle("CREDIT - TABLE " + curOrder.getTableNumber() + " Payment");
		setBounds(100, 100, 342, 308);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.SOUTH);
		
		txtTip = new JTextField();
		txtTip.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				
				lblTotal.setText("Total: $" + df.format( ( curOrder.getTotal() * taxRate ) + Double.parseDouble( txtTip.getText().toString() ) ) );
			
				btnPay.setEnabled( true );
			};
		});
			
		txtTip.setHorizontalAlignment(SwingConstants.RIGHT);
		txtTip.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		txtTip.setColumns(10);
		
		lblSubtotal = new JLabel("Subtotal: $" + df.format( curOrder.getTotal() ) );
		lblSubtotal.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSubtotal.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		
		final JLabel lblTip = new JLabel("TIP:");
		lblTip.setHorizontalAlignment(SwingConstants.CENTER);
		lblTip.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		
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
		
		lblTotal = new JLabel( "Total: $" + df.format( curOrder.getTotal() * taxRate ) );
		lblTotal.setHorizontalAlignment(SwingConstants.CENTER);
		lblTotal.setFont(new Font("Lucida Grande", Font.PLAIN, 28));
		
		lblTax = new JLabel( "Tax: $" + df.format( curOrder.getTotal() * (taxRate - 1) ) );
		lblTax.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTax.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(123)
							.addComponent(btnPay, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblTotal, GroupLayout.PREFERRED_SIZE, 320, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(12)
							.addComponent(lblTip, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtTip, GroupLayout.PREFERRED_SIZE, 162, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING, false)
							.addComponent(lblTax, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(lblSubtotal, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 239, Short.MAX_VALUE)))
					.addContainerGap())
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblSubtotal, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblTax)
					.addGap(23)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtTip, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblTip, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(lblTotal, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(btnPay, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		contentPanel.setLayout(gl_contentPanel);
	}

}
