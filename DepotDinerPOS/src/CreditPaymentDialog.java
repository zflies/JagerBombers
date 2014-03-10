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

	private final JPanel contentPanel = new JPanel();
	private JTextField txtTip;
	private JTextField txtTotal;
	private JLabel lblTotalCharge;
	private JButton btnPay;
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
		setBounds(100, 100, 342, 279);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		txtTip = new JTextField();
		txtTip.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		txtTip.setColumns(10);
		
		txtTotal = new JTextField();
		txtTotal.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		txtTotal.setColumns(10);
		
		JLabel lblTotal = new JLabel("TOTAL: $" + df.format( curOrder.getTotal() ));
		lblTotal.setHorizontalAlignment(SwingConstants.CENTER);
		lblTotal.setFont(new Font("Lucida Grande", Font.PLAIN, 25));
		
		final JLabel lblTip = new JLabel("TIP:");
		lblTip.setHorizontalAlignment(SwingConstants.LEFT);
		lblTip.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		
		lblTotalCharge = new JLabel("TOTAL:");
		lblTotalCharge.setHorizontalAlignment(SwingConstants.LEFT);
		lblTotalCharge.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		
		btnPay = new JButton("PAY");
		btnPay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				double totalCharge = Double.parseDouble( txtTotal.getText() );
				double totalCost = curOrder.getTotal();
				double tipAmount = Double.parseDouble( txtTip.getText() );
				
				// TODO: Need Form Validation
				if (  totalCharge != totalCost + tipAmount )
				{
					if ( totalCharge < totalCost )
					{
						JOptionPane optionPane = new JOptionPane();
						optionPane.setMessage("Charged amount does not cover ticket.");
						JDialog dialog = optionPane.createDialog("Insufficient Funds");
						dialog.setAlwaysOnTop(true);
						dialog.setVisible(true);
					}
					else
					{
						JOptionPane optionPane = new JOptionPane();
						optionPane.setMessage("The amount being charged does not equal the sum of the tip and ticket cost.");
						JDialog dialog = optionPane.createDialog("Incorrect Entry");
						dialog.setAlwaysOnTop(true);
						dialog.setVisible(true);
					}	
				}
				else
				{
					String query = String.format("UPDATE Orders SET Status ='paid' WHERE ID ='%s';", curOrder.getOrderId() );
					
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
					
					dispose();
				}
			}
		});
		btnPay.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(lblTotal, GroupLayout.DEFAULT_SIZE, 326, Short.MAX_VALUE)
								.addGroup(gl_contentPanel.createSequentialGroup()
									.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
										.addComponent(lblTotalCharge)
										.addComponent(lblTip, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING, false)
										.addGroup(gl_contentPanel.createSequentialGroup()
											.addGap(18)
											.addComponent(txtTip, GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE))
										.addGroup(gl_contentPanel.createSequentialGroup()
											.addGap(18)
											.addComponent(txtTotal, 0, 0, Short.MAX_VALUE)))
									.addPreferredGap(ComponentPlacement.RELATED, 81, Short.MAX_VALUE)))
							.addContainerGap())
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addComponent(btnPay, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE)
							.addGap(121))))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblTotal, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtTip, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblTip, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtTotal, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblTotalCharge, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
					.addComponent(btnPay, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		contentPanel.setLayout(gl_contentPanel);
	}

}
