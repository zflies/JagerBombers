import java.awt.BorderLayout;
import java.awt.FlowLayout;

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
import java.text.DecimalFormat;


public class GiftPaymentDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtCardNumber;
	private JLabel lblSecurityCode;
	private JTextField txtSecurityCode;
	private JButton btnPay;
	private JLabel lblTotalRemaining;
	private JButton btnApply;
	private DecimalFormat df = new DecimalFormat("#.00");


	/**
	 * Create the dialog.
	 */
	public GiftPaymentDialog( Order curOrder ) {
		setResizable(false);
		setTitle("GIFT - TABLE " + curOrder.getTableNumber() + " Payment");
		setBounds(100, 100, 342, 279);
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
		btnPay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO: Change status to paid
				dispose();
			}
		});
		btnPay.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		
		lblTotalRemaining = new JLabel("Total Remaining: $" + df.format( curOrder.getTotal() ) );
		lblTotalRemaining.setHorizontalAlignment(SwingConstants.CENTER);
		lblTotalRemaining.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		
		btnApply = new JButton("APPLY");
		btnApply.setFont(new Font("Lucida Grande", Font.PLAIN, 17));
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblTotalRemaining, GroupLayout.DEFAULT_SIZE, 320, Short.MAX_VALUE)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addComponent(lblCardNumber, GroupLayout.DEFAULT_SIZE, 124, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtCardNumber, GroupLayout.PREFERRED_SIZE, 190, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addComponent(lblSecurityCode, GroupLayout.PREFERRED_SIZE, 124, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(btnPay, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_contentPanel.createSequentialGroup()
									.addComponent(txtSecurityCode, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(btnApply, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE)))))
					.addContainerGap())
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_contentPanel.createSequentialGroup()
					.addGap(16)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblCardNumber, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE)
						.addComponent(txtCardNumber, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
							.addComponent(lblSecurityCode, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
							.addComponent(txtSecurityCode, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE))
						.addComponent(btnApply, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(lblTotalRemaining, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
					.addGap(20)
					.addComponent(btnPay, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		contentPanel.setLayout(gl_contentPanel);
	}

}
