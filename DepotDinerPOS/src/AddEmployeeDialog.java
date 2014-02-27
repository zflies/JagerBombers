import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import java.awt.Font;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class AddEmployeeDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JButton cancelButton;
	private JTextField textFieldFirstName;
	private JTextField textFieldLastName;
	private JTextField textFieldPin;
	private JPanel buttonPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			AddEmployeeDialog dialog = new AddEmployeeDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public AddEmployeeDialog() {
		setTitle("Add Employee");
		String[] positions = { "Server", "Cook", "Manager"};
		setBounds(100, 100, 675, 520);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		JLabel lblAddEmployee = new JLabel("Add Employee");
		lblAddEmployee.setFont(new Font("Lucida Grande", Font.PLAIN, 30));
		
		JLabel lblFirstName = new JLabel("First Name:");
		lblFirstName.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		
		textFieldFirstName = new JTextField();
		textFieldFirstName.setColumns(10);
		
		JLabel lblLastName = new JLabel("Last Name:");
		lblLastName.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		
		textFieldLastName = new JTextField();
		textFieldLastName.setColumns(10);
		
		JLabel lblPin = new JLabel("PIN:");
		lblPin.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		
		textFieldPin = new JTextField();
		textFieldPin.setColumns(10);
		
		JLabel lblPosition = new JLabel("Position:");
		lblPosition.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		
		JComboBox comboBoxPosition = new JComboBox(positions);
		{
			buttonPane = new JPanel();
			{
				cancelButton = new JButton("Cancel");
				cancelButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent arg0) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
			}
			
			JButton btnAdd = new JButton("Add");
			GroupLayout gl_buttonPane = new GroupLayout(buttonPane);
			gl_buttonPane.setHorizontalGroup(
				gl_buttonPane.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_buttonPane.createSequentialGroup()
						.addContainerGap()
						.addComponent(cancelButton)
						.addPreferredGap(ComponentPlacement.RELATED, 485, Short.MAX_VALUE)
						.addComponent(btnAdd, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE)
						.addContainerGap())
			);
			gl_buttonPane.setVerticalGroup(
				gl_buttonPane.createParallelGroup(Alignment.TRAILING)
					.addGroup(gl_buttonPane.createSequentialGroup()
						.addContainerGap()
						.addGroup(gl_buttonPane.createParallelGroup(Alignment.LEADING)
							.addComponent(btnAdd, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE)
							.addComponent(cancelButton, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)))
			);
			buttonPane.setLayout(gl_buttonPane);
		}
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGap(38)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addComponent(lblPosition)
							.addContainerGap())
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_contentPanel.createSequentialGroup()
								.addComponent(lblPin)
								.addContainerGap())
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPanel.createSequentialGroup()
									.addComponent(lblLastName)
									.addContainerGap())
								.addGroup(gl_contentPanel.createSequentialGroup()
									.addComponent(lblFirstName, GroupLayout.PREFERRED_SIZE, 128, GroupLayout.PREFERRED_SIZE)
									.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
										.addGroup(gl_contentPanel.createSequentialGroup()
											.addGap(56)
											.addComponent(lblAddEmployee, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
											.addGap(235))
										.addGroup(Alignment.LEADING, gl_contentPanel.createSequentialGroup()
											.addGap(18)
											.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING, false)
												.addComponent(textFieldLastName, Alignment.LEADING)
												.addComponent(textFieldFirstName, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 414, Short.MAX_VALUE)
												.addComponent(comboBoxPosition, Alignment.LEADING, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addComponent(textFieldPin, Alignment.LEADING))
											.addGap(67))))))))
				.addGroup(Alignment.TRAILING, gl_contentPanel.createSequentialGroup()
					.addComponent(buttonPane, GroupLayout.PREFERRED_SIZE, 667, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addComponent(lblAddEmployee)
					.addGap(61)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblFirstName)
						.addComponent(textFieldFirstName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(40)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblLastName)
						.addComponent(textFieldLastName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(42)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPin)
						.addComponent(textFieldPin, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(41)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPosition)
						.addComponent(comboBoxPosition, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 96, Short.MAX_VALUE)
					.addComponent(buttonPane, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE))
		);
		contentPanel.setLayout(gl_contentPanel);
	}
}
