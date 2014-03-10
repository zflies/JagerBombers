import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;

import javax.swing.JTabbedPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


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
	
	private Action delete = new AbstractAction()
	{
	    public void actionPerformed(ActionEvent e)
	    {
	    	//grab row information
	        JTable table = (JTable)e.getSource();
	        int modelRow = Integer.valueOf( e.getActionCommand() );
	        String tableNumber = (String) ((DefaultTableModel)table.getModel()).getValueAt(modelRow, 0);
	        String items = (String) ((DefaultTableModel)table.getModel()).getValueAt(modelRow, 1);
	        
	        //update order in DB to "served"
	        Statement state = DBConnection.OpenConnection();
			String commandstring = "UPDATE Orders SET status = 'served' WHERE Table_No = " + tableNumber + " AND Items = '" + items + "' AND status = 'entered';";
			if(state != null) {
				try {
					int result = state.executeUpdate(commandstring);
					if(result > 0) {
						//delete row from table
				        ((DefaultTableModel)table.getModel()).removeRow(modelRow);
				        state.close();
				        return;
					}
					else {
						return;
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
					return;
					}
			}
			else {
				System.err.println("Statement was null.  No connection?");
			}
	        
	        
	    }
	};
	
	private JTable getOrders() throws Exception{
		JTable table = new JTable();
		Vector<Order> Orders;
		DefaultTableModel newModel = new DefaultTableModel();
		newModel.addColumn("TableNumber");
		newModel.addColumn("Items");
		newModel.addColumn("Complete");
		try {
			Orders = Order.getEnteredOrders();
			//add rows to table
			for(int i = 0; i < Orders.size(); i++) {
				Order curOrder = Orders.elementAt(i);
				Vector<String> Row = new Vector<String>();
				Row.add(String.valueOf(curOrder.getTableNumber()));
				Row.add(curOrder.getItems());
				Row.add("Complete");
				newModel.addRow(Row);
			}
			
			table.setModel(newModel);
			
			//add button column to table
			ButtonColumn buttonColumn = new ButtonColumn(table, delete, 2);
			
			return table;
		}
		catch (Exception e) {
			//Some error occurred in either connecting to DB or there weren't any orders to be cooked
			//throw exception up
			throw e;
		}
		
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
		
		
		
		//create initial table
		try {
			table = getOrders();
		} catch (Exception e2) {
			JOptionPane.showMessageDialog(frame, e2.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		OrdersTab.add(table);
		
		//create and start timer to re-create table every 5 secs
		int delay = 5000;
		ActionListener task = new ActionListener() {
		      public void actionPerformed(ActionEvent evt) {
		    	  try {
					JTable newTable = getOrders();
					table.setModel(newTable.getModel());
					ButtonColumn buttonColumn = new ButtonColumn(table, delete, 2);
				} catch (Exception e) {
					JOptionPane.showMessageDialog(frame, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
		      }
		  };
		Timer timer = new Timer(delay, task);
		timer.setRepeats(true);
		timer.start();
		
		
	}
}
