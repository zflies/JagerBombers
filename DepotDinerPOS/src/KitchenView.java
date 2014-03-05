import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.GridBagLayout;

import javax.swing.JLabel;

import java.awt.GridBagConstraints;

import javax.swing.JTabbedPane;

import java.awt.Insets;

import javax.swing.border.BevelBorder;
import javax.swing.SpringLayout;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.JTable;


public class KitchenView extends JFrame {

	private JPanel contentPane;
	private static KitchenView frame;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new KitchenView();
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
	public KitchenView() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabbedPane);
		
		JPanel OrdersTab = new JPanel();
		tabbedPane.addTab("Orders", null, OrdersTab, null);
		OrdersTab.setLayout(new BorderLayout(0, 0));
		
		table = new JTable();
		OrdersTab.add(table, BorderLayout.CENTER);
		
		//Get orders and print into table
		Vector<Order> Orders;
		try {
			Orders = Order.getEnteredOrders();
			for(int i = 0; i < Orders.size(); i++){
				Order curOrder = Orders.elementAt(i);
				System.out.println("Order ID: " + curOrder.getOrderId());
				System.out.println("Employee Pin: " + curOrder.getEmployeePin());
				System.out.println("Table Number:" + curOrder.getTableNumber());
				System.out.println("Items: " + curOrder.getItems());
				System.out.println("Status: " + curOrder.getStringStatus());
				System.out.println("Total: " + curOrder.getTotal() + "\n");
			}
		} catch (Exception e) {
			//Some error occurred in either connecting to DB or there weren't any orders to be cooked
			JOptionPane.showMessageDialog(frame, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			return ;
		}
	}
}
