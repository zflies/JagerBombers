

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

public class CashPaymentDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JLabel lblCash;
	private JLabel lblTotal;
	private JLabel lblChange;
	private JTextField txtCash;
	private JButton btnPay;
	private DecimalFormat df = new DecimalFormat("0.00");


	/**
	 * Create the dialog.
	 */
	public CashPaymentDialog( final Order curOrder ) {
		setResizable(false);
		setTitle("CASH - TABLE " + curOrder.getTableNumber() + " Payment");
		setBounds(100, 100, 342, 279);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		txtCash = new JTextField();
		txtCash.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				
				double cashReceived = Double.parseDouble( txtCash.getText() );
				double totalCost = curOrder.getTotal();
				
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
		
		lblTotal = new JLabel("TOTAL: $" + df.format( curOrder.getTotal() ) );
		lblTotal.setHorizontalAlignment(SwingConstants.CENTER);
		lblTotal.setFont(new Font("Lucida Grande", Font.PLAIN, 25));
		
		lblChange = new JLabel("CHANGE: $0.00");
		lblChange.setHorizontalAlignment(SwingConstants.CENTER);
		lblChange.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		
		btnPay = new JButton("PAY");
		btnPay.setEnabled(false);
		btnPay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

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
		});
		btnPay.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
							.addComponent(lblTotal, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 408, Short.MAX_VALUE)
							.addGroup(gl_contentPanel.createSequentialGroup()
								.addComponent(lblCash, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
								.addGap(18)
								.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
									.addComponent(lblChange, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(txtCash, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 168, Short.MAX_VALUE))
								.addGap(75)))
						.addGroup(Alignment.TRAILING, gl_contentPanel.createSequentialGroup()
							.addComponent(btnPay)
							.addGap(113))))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblTotal, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCash, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(txtCash, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(lblChange, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
					.addGap(30)
					.addComponent(btnPay, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		contentPanel.setLayout(gl_contentPanel);
	}
}
