import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;


public class ReservationDialog extends JDialog {

	private JPanel contentPane;
	private JTable table;
	private JDialog addReservation;
	private DefaultTableModel Model;
	private String ColumnNames[] = {"Name", "Date/Time", "Size", "Deposit"};
	private Vector<Reservation> reservations;

	private void refreshTable(){
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
			JOptionPane.showMessageDialog(contentPane, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ReservationDialog frame = new ReservationDialog();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ReservationDialog() {
		addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				refreshTable();
			}
		});
		setResizable(false);
		setTitle("Reservations");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 585, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		
		JButton btnNew = new JButton("New");
		btnNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addReservation = new AddReservationDialog();
				addReservation.setVisible(true);
				addReservation.setLocationRelativeTo(null);
				addReservation.setAlwaysOnTop(true);	
			}
		});
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(table.getSelectedRowCount() > 1){
					JOptionPane.showMessageDialog(contentPane, "Only one row can be deleted at a time.", "", JOptionPane.ERROR_MESSAGE);
					return;
				}
				if(table.getSelectedRowCount() == 0){
					JOptionPane.showMessageDialog(contentPane, "No row selected.", "", JOptionPane.ERROR_MESSAGE);
					return;
				}
				int selectedRow = table.getSelectedRow();
				DefaultTableModel cur_model = (DefaultTableModel) table.getModel();
				String name = (String) cur_model.getValueAt(selectedRow, 0);
				String date = (String) cur_model.getValueAt(selectedRow, 1);
				int Size = Integer.valueOf((String) cur_model.getValueAt(selectedRow, 2));
				double Deposit = Double.valueOf((String) cur_model.getValueAt(selectedRow, 3));
				//parse date string into a Date object
				Date Date;
				try {
					Date = new SimpleDateFormat("yyyy-MM-d k:m").parse(date);
					System.out.println(Date.toString());
					
					//delete this object from the table
					((DefaultTableModel)table.getModel()).removeRow(selectedRow);
					//delete this object from DB
					Statement state = DBConnection.OpenConnection();
					String commandstring = "DELETE FROM Reservations WHERE "
							+ "Name = '"+ name + "' AND DateTime = '" + new Timestamp(Date.getTime()) + "' "
							+ "AND Size = "+ Size + " AND Deposit = "+ Deposit + ";";
					
					if(state != null){
							state.execute(commandstring);
					}
					else
						System.err.println("Statement was null.  No connection?");
					
					state.close();
					
				} catch (ParseException e) {
					e.printStackTrace();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
			}
		});
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addComponent(panel, GroupLayout.DEFAULT_SIZE, 462, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(btnCancel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnDelete, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnNew, GroupLayout.DEFAULT_SIZE, 91, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(86)
							.addComponent(btnNew)
							.addGap(18)
							.addComponent(btnDelete)
							.addGap(68)
							.addComponent(btnCancel))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(panel, GroupLayout.DEFAULT_SIZE, 244, Short.MAX_VALUE)))
					.addGap(7))
		);
		
		JScrollPane scrollPane = new JScrollPane();
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 233, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 212, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		panel.setLayout(gl_panel);
		contentPane.setLayout(gl_contentPane);
		
		refreshTable();
	}
}
