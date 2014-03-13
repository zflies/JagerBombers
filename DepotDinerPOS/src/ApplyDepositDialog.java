import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.sql.Timestamp;
import java.util.Vector;


public class ApplyDepositDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private DefaultTableModel Model;
	private String ColumnNames[] = {"Name", "Date/Time", "Size", "Deposit"};
	private Vector<Reservation> reservations;
	
	private double deposit = 0.00;


	/**
	 * Create the dialog.
	 */
	public ApplyDepositDialog() {
		setTitle("Reservations");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setResizable(false);
		setAlwaysOnTop(true);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		JButton btnSelect = new JButton("SELECT");
		btnSelect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				// TODO: Get row selection, determine deposit amount
				// 		 Query DB to remove this reservation
				//deposit = 
				
				setVisible(false);
				dispose();
			}
		});
		btnSelect.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		JButton btnCancel = new JButton("CANCEL");
		btnCancel.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				dispose();
			}
		});
		
		JScrollPane scrollPane = new JScrollPane();
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_contentPanel.createSequentialGroup()
					.addComponent(btnCancel, GroupLayout.PREFERRED_SIZE, 99, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 240, Short.MAX_VALUE)
					.addComponent(btnSelect))
				.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 440, Short.MAX_VALUE)
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 221, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(btnCancel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnSelect, GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)))
		);
		
		table = new JTable();
		
		Model = new DefaultTableModel(ColumnNames, 0);
		//get all reservations and add to table
		try {
			reservations = Reservation.getAllReservations();
			//add rows to table
			for(int i = 0; i < reservations.size(); i++) {
				Reservation curReservation = reservations.elementAt(i);
				Vector<String> Row = new Vector<String>();
				Row.add(curReservation.getName());
				Row.add((new Timestamp(curReservation.getDate().getTime()).toString()));
				Row.add(String.valueOf(curReservation.getSize()));
				Row.add(String.valueOf(curReservation.getDeposit()));
				Model.addRow(Row);
			}
			
			table.setModel(Model);
		} catch (Exception e1) {
			System.out.println("Error: ApplyDepositDialog.java - getAllReservations()");
		}
		
		scrollPane.setViewportView(table);
		contentPanel.setLayout(gl_contentPanel);
	}
		
	public void showDialog(){
		setVisible(true); 
		setLocationRelativeTo(null);
		setAlwaysOnTop(true);		
	}
	
	public double getDeposit(){
		return deposit;
	}
}
