import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JSpinner;

import java.awt.event.InputMethodListener;
import java.awt.event.InputMethodEvent;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import javax.swing.SpinnerNumberModel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;


public class AddReservationDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textFieldName;
	private JTextField textFieldDate;
	private JTextField textFieldDepositPrice;
	private JTextField textFieldTime;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			AddReservationDialog dialog = new AddReservationDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	final JSpinner spinnerSize = new JSpinner();
	
	private boolean checkFields() throws Exception{
		boolean name = false;
		boolean date = false;
		boolean time = false;
		boolean size = false;
		
		String Name = textFieldName.getText();
		String Date = textFieldDate.getText();
		String Time = textFieldTime.getText();
		int Size = (Integer) spinnerSize.getValue();
		String dateRegex = "\\d{4}-\\d{2}-\\d{2}";
		String timeRegex = "([01]?[0-9]|2[0-3]):[0-5][0-9]";
		
		if(Name.compareTo("") != 0)
			name = true;
		else
			throw(new Exception("Name field is required"));
		if(Date.matches(dateRegex))
			date = true;
		else
			throw(new Exception("Date field must match YYYY-MM-DD"));
		if(Time.matches(timeRegex))
			time = true;
		else
			throw(new Exception("Time field must match TT:TT (ex. 14:00, 8:30)"));
		if(Size != 0)
			size = true;
		else
			throw(new Exception("Reservation Size Must be greater than 0"));
		
		return name & date & time & size;
		
	}

	/**
	 * Create the dialog.
	 */
	public AddReservationDialog() {
		setResizable(false);
		setTitle("Add Reservation");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		textFieldName = new JTextField();
		textFieldName.setColumns(10);
		
		JLabel lblName = new JLabel("Name:");
		lblName.setFont(new Font("Dialog", Font.PLAIN, 16));
		
		JLabel lblDate = new JLabel("Date:");
		lblDate.setFont(new Font("Dialog", Font.PLAIN, 16));
		
		textFieldDate = new JTextField();
		textFieldDate.setColumns(10);
		
		JLabel lblSize = new JLabel("Size:");
		lblSize.setFont(new Font("Dialog", Font.PLAIN, 16));
		
		
		spinnerSize.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
		spinnerSize.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				int size = (Integer) spinnerSize.getValue();
				DecimalFormat Formatter = new DecimalFormat("$##.##");
				//System.out.println(size);
				if(size == 0){
					textFieldDepositPrice.setText("$0.00");
				}
				else if(size <= Reservation.SmallPartyLimit){
					textFieldDepositPrice.setText(Formatter.format(Reservation.SmallDeposit));
				}
				else{
					textFieldDepositPrice.setText(Formatter.format(Reservation.LargeDeposit));
				}
			}
		});
		
		JLabel lblDeposit = new JLabel("Deposit:");
		lblDeposit.setFont(new Font("Dialog", Font.PLAIN, 16));
		
		textFieldDepositPrice = new JTextField();
		textFieldDepositPrice.setEditable(false);
		textFieldDepositPrice.setText("$0.00");
		textFieldDepositPrice.setColumns(10);
		
		JLabel lblTime = new JLabel("Time:");
		lblTime.setFont(new Font("Dialog", Font.PLAIN, 16));
		
		textFieldTime = new JTextField();
		textFieldTime.setColumns(10);
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGap(20)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblDate)
						.addComponent(lblName)
						.addComponent(lblSize)
						.addComponent(lblDeposit))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(spinnerSize, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
						.addComponent(textFieldDepositPrice, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING, false)
							.addGroup(gl_contentPanel.createSequentialGroup()
								.addComponent(textFieldDate, GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addComponent(lblTime)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(textFieldTime))
							.addComponent(textFieldName, GroupLayout.PREFERRED_SIZE, 276, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(74, Short.MAX_VALUE))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGap(37)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblName)
						.addComponent(textFieldName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblDate)
						.addComponent(lblTime)
						.addComponent(textFieldDate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(textFieldTime, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblSize)
						.addComponent(spinnerSize, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblDeposit)
						.addComponent(textFieldDepositPrice, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(46, Short.MAX_VALUE))
		);
		contentPanel.setLayout(gl_contentPanel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						//make sure all fields are complete
						try {
							checkFields();
						} catch (Exception e1) {
							System.err.println("Error: AddReservationDialog OK button Action Listener");
							return;
						}
						//add new reservation to DB
						String name = textFieldName.getText();
						int size = (Integer) spinnerSize.getValue();
						String date = textFieldDate.getText();
						String time = textFieldTime.getText();
						Date Date;
						//parse date and time to get a Date object
						try {
							Date = new SimpleDateFormat("yyyy-MM-d k:m").parse(date + " " + time);
							System.out.println(Date.toString());
						} catch (ParseException e1) {
							e1.printStackTrace();
							System.out.println(e1.getMessage());
							return;
						}
						Reservation newReservation = new Reservation(name, Date, size);
						try {
							newReservation.addToDB();
						} catch (Exception e1) {
							e1.printStackTrace();
							System.out.println("Error adding reservation to DB");
						}
						
						//dispose
						dispose();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
