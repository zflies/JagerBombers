import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle.ComponentPlacement;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JTable;

public class ReportDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable reportTable;
	private DecimalFormat df = new DecimalFormat("0.00");
	private String column_names[]= {"Last Name","First Name","Weekly Hours","Daily Hours","Tips Earned"};

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ReportDialog dialog = new ReportDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ReportDialog() {
		setBounds(100, 100, 974, 456);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		JScrollPane scrollPane = new JScrollPane();
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		JButton btnPrint = new JButton("Print");
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 955, Short.MAX_VALUE)
							.addGap(3))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addComponent(btnCancel)
							.addPreferredGap(ComponentPlacement.RELATED, 791, Short.MAX_VALUE)
							.addComponent(btnPrint)
							.addContainerGap())))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGap(0)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 343, Short.MAX_VALUE)
					.addGap(30)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnCancel)
						.addComponent(btnPrint))
					.addGap(22))
		);
		
		DefaultTableModel tableModel= new DefaultTableModel(column_names, 0);
		reportTable = new JTable(tableModel);
		scrollPane.setViewportView(reportTable);
		contentPanel.setLayout(gl_contentPanel);
		
		generateReport();
	}
	
	private void generateReport(){
		String firstName;
		String lastName;
		String pin;
		String position;
		double hoursWorkedWeek;
		double hoursWorkedDay;
		double tipsEarned = 0.00;
		java.sql.Statement state = DBConnection.OpenConnection();
		DefaultTableModel model = (DefaultTableModel) reportTable.getModel();
		String commandstring = "SELECT * FROM avalenti.Employees;";
		if(state != null){
			try {
				ResultSet rs = state.executeQuery(commandstring);
				while(rs.next() == true) {
					firstName = rs.getString("FirstName");
					lastName = rs.getString("LastName");
					pin = rs.getString("PIN");
					position = rs.getString("Position");
					//boolean value here signifies whether you want the hours for the week or the current day
					//true - get hours for current day
					//false - get hours for current week
					hoursWorkedWeek = getHoursWorked(pin, false); 
					hoursWorkedDay = getHoursWorked(pin, true);
					if((position.compareTo("Staff") == 0) || (position.compareTo("Manager") == 0)){
						tipsEarned = getTips(pin);
					}
					model.addRow(new Object[]{lastName, firstName, df.format(hoursWorkedWeek), df.format(hoursWorkedDay), "$" + df.format(tipsEarned)});
				}
			} catch (SQLException e) {
				System.err.println("Error in SQL Execution");
			}
		}
		else
			System.err.println("Statement was null.  No connection?");
	}
	
	private double getTips(String pin){
		java.sql.Statement state = DBConnection.OpenConnection();
		double tipsTotal = 0.00;
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String query = String.format("SELECT Tip FROM avalenti.Payments WHERE E_PIN = %s AND Date = '%s';", pin, dateFormat.format(date).toString());
		if(state != null){
			try {
				ResultSet rs = state.executeQuery(query);
				while(rs.next() == true) {
					double tips = Double.parseDouble(rs.getString("Tip"));
					tipsTotal += tips;
				}
			} catch (SQLException e) {
				System.err.println("Error in SQL Execution");
			}
		}
		else
			System.err.println("Statement was null.  No connection?");
		
		return tipsTotal;
	}
	
	private double getHoursWorked(String pin, boolean getDay){
		java.sql.Statement state = DBConnection.OpenConnection();
		double hoursTotal = 0.00;
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		c.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		Date date = c.getTime();
		String query = String.format("SELECT C_IN, C_OUT FROM avalenti.Hours WHERE E_PIN = %s AND C_IN >= '%s' AND C_OUT > '%s';", pin, dateFormat.format(date).toString() + " 00:00:00", "0000-00-00 00:00:00");
		if(getDay){
			date = new Date();
			query = String.format("SELECT C_IN, C_OUT FROM avalenti.Hours WHERE E_PIN = %s AND C_IN >= '%s' AND C_OUT > '%s';", pin, dateFormat.format(date).toString() + " 00:00:00", "0000-00-00 00:00:00");
		}
		if(state != null){
			try {
				ResultSet rs = state.executeQuery(query);
				while(rs.next() == true) {
					String clockIn = rs.getString("C_IN");
					String clockOut = rs.getString("C_OUT");
					double hoursWorked = calculateElapsedTime(clockIn, clockOut);
					hoursTotal += hoursWorked;
				}
			} catch (SQLException e) {
				System.out.println(e.getMessage());
				System.err.println("Error in SQL Execution");
			}
		}
		else
			System.err.println("Statement was null.  No connection?");
		
		return hoursTotal;
	}
	
	private double calculateElapsedTime(String clockIn, String clockOut){
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	 
		try { 
			Date startDate = formatter.parse(clockIn);
			Date endDate = formatter.parse(clockOut);
			
			return endDate.getHours() - startDate.getHours() + ((endDate.getMinutes() - startDate.getMinutes()) / 60.00);
	 
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return 0.00;
	}

}
