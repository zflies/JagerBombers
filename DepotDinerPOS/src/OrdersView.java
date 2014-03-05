import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.LayoutStyle.ComponentPlacement;

import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JList;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.Color;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.swing.JDesktopPane;

import java.util.Calendar;


public class OrdersView extends JFrame {

	private static OrdersView frame;
	private JPanel contentPaneOrders;
	private JButton btnExit;
	private JLabel lblEmployee;
	private JTabbedPane tabbedPaneOrders;
	private JPanel panelOrders;
	private JDesktopPane desktopPaneAllOrders;
	private JDesktopPane desktopPaneViewOrder;
	private JButton btnCreateOrder;
	private JButton btnPayment;
	private JButton btnAddToOrder;
	private JScrollPane scrollPaneAllOrders;
	private JLabel lblTable;
	private JLabel lblTotal;
	private JLabel lblTableNumber;
	private JScrollPane scrollPaneViewOrder;
	private JList list;
	private JTable tableAllOrders;
	private JTable tableViewOrder;
	private JDialog dialogPayment;
	private JDialog dialogHistory;
	private int nTempCount = 0;		//< Temporary until the DB connection is worked out

	
	private static final int BREAKFAST_HOUR = 11;
	
	private Calendar calendar = Calendar.getInstance();

	private Object columnNamesViewOrder[] = {"Item", "Notes", "Price"};

	private Object rowDataViewOrderTable4 [][] = {
			{"Chicken Strips", "Honey Mustard", "$7.50"}, 
			{"KC Strip","","$12.95"},
			{"Chicken Parmesan ","No Mushrooms","$9.50"},
			{"Iced Tea","","$2.45"},
			{"Iced Tea","","$2.45"},
			{"Drink","","$1.50"}};
	
	private Object rowDataViewOrderTable7 [][] = {
			{"Meat Loaf", "", "$8.95"}, 
			{"Country Fried Steak","","$9.85"},
			{"Catfish and Chips","Extra Sauce","$8.50"},
			{"Iced Tea","","$2.45"},
			{"Drink","","$1.50"}};
	
	private Object rowDataViewOrderTable10 [][] = {
			{"Southwest Omelette", "Extra Cheese", "$7.95"}, 
			{"8oz. KC Strip & Eggs","","$10.15"},
			{"Milk","","$1.75"},
			{"Drink","","$1.50"},
			{"Coffee","","$2.50"}};
	
	
	private Object rowDataAllOrders[][] = { 
			{ "10", "Southwest Omelette, 8oz. KC Strip & Eggs, Milk, Soft Drink, Coffee", "$23.95"},
			{ "7", "Meat Loaf, Country Fried Steak, Catfish and Chips, Iced Tea, Soft Drink", "$29.86"},
			{ "4", "Chicken Strips, KC Strip, Chicken Parmesan, Iced Tea, Iced, Tea, Soft Drink", "$21.14"}};

	private Object columnNamesAllOrders[] = { "TABLE", "ORDER DESCRIPTION", "TOTAL"};
	private JButton btnHistory;



	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new OrdersView();
					frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
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
	public OrdersView() {
		
		setTitle("Steve's Depot Diner");
	
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 884, 677);
		contentPaneOrders = new JPanel();
		contentPaneOrders.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPaneOrders);
		
		btnExit = new JButton("EXIT");
		btnExit.setMnemonic(KeyEvent.VK_X);

		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				LoginWindow Login = new LoginWindow();
				Login.setVisible(true);
				Login.setExtendedState(JFrame.MAXIMIZED_BOTH);
			}
		});
		btnExit.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		
		lblEmployee = new JLabel("EMPLOYEE:  ZACH FLIES");
		lblEmployee.setFont(new Font("Lucida Grande", Font.PLAIN, 30));
		lblEmployee.setHorizontalAlignment(SwingConstants.LEFT);
		
		tabbedPaneOrders = new JTabbedPane(JTabbedPane.TOP);
		GroupLayout gl_contentPaneOrders = new GroupLayout(contentPaneOrders);
		gl_contentPaneOrders.setHorizontalGroup(
			gl_contentPaneOrders.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPaneOrders.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblEmployee, GroupLayout.DEFAULT_SIZE, 758, Short.MAX_VALUE)
					.addGap(18)
					.addComponent(btnExit, GroupLayout.PREFERRED_SIZE, 78, GroupLayout.PREFERRED_SIZE)
					.addGap(14))
				.addComponent(tabbedPaneOrders, GroupLayout.DEFAULT_SIZE, 874, Short.MAX_VALUE)
		);
		gl_contentPaneOrders.setVerticalGroup(
			gl_contentPaneOrders.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPaneOrders.createSequentialGroup()
					.addGroup(gl_contentPaneOrders.createParallelGroup(Alignment.LEADING)
						.addComponent(btnExit, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblEmployee, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(tabbedPaneOrders, GroupLayout.DEFAULT_SIZE, 592, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		panelOrders = new JPanel();
		tabbedPaneOrders.addTab("ORDERS", null, panelOrders, null);
		
		desktopPaneAllOrders = new JDesktopPane();
		desktopPaneAllOrders.setBackground(Color.LIGHT_GRAY);
		
		desktopPaneViewOrder = new JDesktopPane();
		desktopPaneViewOrder.setBackground(Color.LIGHT_GRAY);

		GroupLayout gl_panelOrders = new GroupLayout(panelOrders);
		gl_panelOrders.setHorizontalGroup(
			gl_panelOrders.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelOrders.createSequentialGroup()
					.addContainerGap()
					.addComponent(desktopPaneAllOrders, GroupLayout.DEFAULT_SIZE, 409, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(desktopPaneViewOrder, GroupLayout.DEFAULT_SIZE, 409, Short.MAX_VALUE)
					.addGap(5))
		);
		gl_panelOrders.setVerticalGroup(
			gl_panelOrders.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelOrders.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelOrders.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelOrders.createSequentialGroup()
							.addComponent(desktopPaneViewOrder, GroupLayout.DEFAULT_SIZE, 549, Short.MAX_VALUE)
							.addGap(7))
						.addGroup(gl_panelOrders.createSequentialGroup()
							.addComponent(desktopPaneAllOrders, GroupLayout.DEFAULT_SIZE, 550, Short.MAX_VALUE)
							.addGap(7))))
		);
		
		btnPayment = new JButton("PAYMENT");
		btnPayment.setMnemonic(KeyEvent.VK_P);

		btnPayment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				dialogPayment = new PaymentsDialog();
				dialogPayment.setVisible(true);
				
				dialogPayment.setLocationRelativeTo(null);
				dialogPayment.setTitle("TABLE" + lblTableNumber.getText() + "PAYMENT");
				
				dialogPayment.setAlwaysOnTop(true);							
			}
		});
		btnPayment.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		
		btnAddToOrder = new JButton("ADD TO ORDER");
		btnAddToOrder.setMnemonic(KeyEvent.VK_A);

		btnAddToOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(calendar.get(Calendar.HOUR_OF_DAY) <= BREAKFAST_HOUR){
					System.out.println("Opening Breakfast Menu!");
					BreakfastView breakfast = new BreakfastView();
					breakfast.setVisible(true);
					breakfast.setExtendedState(JFrame.MAXIMIZED_BOTH);
				}
				else{
					System.out.println("Opening Dinner Menu!");
					DinnerView dinner = new DinnerView();
					dinner.setVisible(true);
					dinner.setExtendedState(JFrame.MAXIMIZED_BOTH);
				}	
			}
		});
		btnAddToOrder.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		
		lblTable = new JLabel("TABLE:");
		lblTable.setFont(new Font("Lucida Grande", Font.PLAIN, 25));
		
		lblTotal = new JLabel("TOTAL: $0.00");
		lblTotal.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTotal.setFont(new Font("Lucida Grande", Font.PLAIN, 25));
		
		scrollPaneViewOrder = new JScrollPane();
		
		lblTableNumber = new JLabel("10");
		lblTableNumber.setFont(new Font("Lucida Grande", Font.PLAIN, 25));
		GroupLayout gl_desktopPaneViewOrder = new GroupLayout(desktopPaneViewOrder);
		gl_desktopPaneViewOrder.setHorizontalGroup(
			gl_desktopPaneViewOrder.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_desktopPaneViewOrder.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_desktopPaneViewOrder.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_desktopPaneViewOrder.createSequentialGroup()
							.addGroup(gl_desktopPaneViewOrder.createParallelGroup(Alignment.LEADING)
								.addComponent(btnAddToOrder, GroupLayout.PREFERRED_SIZE, 194, Short.MAX_VALUE)
								.addGroup(gl_desktopPaneViewOrder.createSequentialGroup()
									.addComponent(lblTable)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(lblTableNumber, GroupLayout.PREFERRED_SIZE, 82, GroupLayout.PREFERRED_SIZE)))
							.addGap(15)
							.addGroup(gl_desktopPaneViewOrder.createParallelGroup(Alignment.TRAILING)
								.addComponent(btnPayment, GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE)
								.addComponent(lblTotal, GroupLayout.PREFERRED_SIZE, 194, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_desktopPaneViewOrder.createSequentialGroup()
							.addComponent(scrollPaneViewOrder, GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
							.addGap(3)))
					.addContainerGap())
		);
		gl_desktopPaneViewOrder.setVerticalGroup(
			gl_desktopPaneViewOrder.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_desktopPaneViewOrder.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_desktopPaneViewOrder.createParallelGroup(Alignment.LEADING)
						.addComponent(lblTotal, GroupLayout.PREFERRED_SIZE, 52, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblTable, GroupLayout.PREFERRED_SIZE, 52, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblTableNumber, GroupLayout.PREFERRED_SIZE, 52, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPaneViewOrder, GroupLayout.DEFAULT_SIZE, 402, Short.MAX_VALUE)
					.addGap(14)
					.addGroup(gl_desktopPaneViewOrder.createParallelGroup(Alignment.LEADING)
						.addComponent(btnAddToOrder, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnPayment, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		
		
		
				
		tableViewOrder = new JTable(new DefaultTableModel(rowDataViewOrderTable10, columnNamesViewOrder) {
			boolean[] columnEditables = new boolean[] {
					false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		
		calcColumnWidths( tableViewOrder );
		
		TableColumnModel columns = tableViewOrder.getColumnModel();
		TableColumn column = columns.getColumn(1);
		column.setMinWidth(250);
		column.setMaxWidth(450);
		
		column = columns.getColumn(2);
		column.setMinWidth(130);
		column.setMaxWidth(250);
		tableViewOrder.setFont(new Font("Lucida Grande", Font.PLAIN, 30));
		tableViewOrder.setRowHeight(tableViewOrder.getRowHeight() + 20);
			
		scrollPaneViewOrder.setViewportView(tableViewOrder);
		desktopPaneViewOrder.setLayout(gl_desktopPaneViewOrder);
		
		btnCreateOrder = new JButton("CREATE ORDER");
		btnCreateOrder.setMnemonic(KeyEvent.VK_C);
		btnCreateOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Calendar calendar = Calendar.getInstance();
				if(calendar.get(Calendar.HOUR_OF_DAY) <= BREAKFAST_HOUR){
					System.out.println("Opening Breakfast Menu!");
					BreakfastView breakfast = new BreakfastView();
					breakfast.setVisible(true);
					breakfast.setExtendedState(JFrame.MAXIMIZED_BOTH);
				}
				else{
					System.out.println("Opening Dinner Menu!");
					DinnerView dinner = new DinnerView();
					dinner.setVisible(true);
					dinner.setExtendedState(JFrame.MAXIMIZED_BOTH);
				}				
			}
		});
		
		btnCreateOrder.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		
		scrollPaneAllOrders = new JScrollPane();
		
		btnHistory = new JButton("ORDER HISTORY");
		btnHistory.setMnemonic(KeyEvent.VK_H);

		btnHistory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				dialogHistory = new OrderHistoryDialog();
				dialogHistory.setVisible(true);
				dialogHistory.setLocationRelativeTo(null);
				dialogHistory.setTitle(  (calendar.get(Calendar.MONTH) + 1) + "-" + calendar.get(Calendar.DAY_OF_MONTH) + "-" + calendar.get(Calendar.YEAR) + " Order History");
				dialogHistory.setAlwaysOnTop(true);	
			}
		});
		btnHistory.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		
		GroupLayout gl_desktopPaneAllOrders = new GroupLayout(desktopPaneAllOrders);
		gl_desktopPaneAllOrders.setHorizontalGroup(
			gl_desktopPaneAllOrders.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_desktopPaneAllOrders.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_desktopPaneAllOrders.createParallelGroup(Alignment.TRAILING)
						.addGroup(Alignment.LEADING, gl_desktopPaneAllOrders.createSequentialGroup()
							.addComponent(btnHistory, GroupLayout.PREFERRED_SIZE, 190, Short.MAX_VALUE)
							.addGap(18)
							.addComponent(btnCreateOrder, GroupLayout.PREFERRED_SIZE, 193, Short.MAX_VALUE))
						.addComponent(scrollPaneAllOrders, GroupLayout.DEFAULT_SIZE, 401, Short.MAX_VALUE))
					.addGap(8))
		);
		gl_desktopPaneAllOrders.setVerticalGroup(
			gl_desktopPaneAllOrders.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_desktopPaneAllOrders.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPaneAllOrders, GroupLayout.DEFAULT_SIZE, 467, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_desktopPaneAllOrders.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnCreateOrder, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnHistory, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE))
					.addGap(7))
		);
		
	
		tableAllOrders = new JTable(new DefaultTableModel(rowDataAllOrders, columnNamesAllOrders) {
			boolean[] columnEditables = new boolean[] {
				false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		
		tableAllOrders.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
	        public void valueChanged(ListSelectionEvent event) {
	           	            
	            // TODO: Update View Order table based on All Orders table selection
	            
	            // tableAllOrders.getValueAt(tableAllOrders.getSelectedRow(), 0).toString() // Retrieves row selctions from All Orders table
	        	if ( nTempCount > 0)	
	        	{
		            ((DefaultTableModel) tableViewOrder.getModel()).setDataVector( rowDataViewOrderTable7, columnNamesViewOrder); // Sets the data in the View Order table
	        	}
	        	nTempCount++;
	        }
	    });
		
		calcColumnWidths( tableAllOrders);
		
		columns = tableAllOrders.getColumnModel();
		column = columns.getColumn(0);
		column.setMinWidth(60);
		column.setMaxWidth(100);
		
		column = columns.getColumn(2);
		column.setMinWidth(130);
		column.setMaxWidth(250);
		tableAllOrders.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableAllOrders.setFont(new Font("Lucida Grande", Font.PLAIN, 30));
		tableAllOrders.setRowHeight(tableAllOrders.getRowHeight() + 50);
		tableAllOrders.changeSelection(0, 0, false, false);
	
		scrollPaneAllOrders.setViewportView(tableAllOrders);
		desktopPaneAllOrders.setLayout(gl_desktopPaneAllOrders);
		panelOrders.setLayout(gl_panelOrders);
		contentPaneOrders.setLayout(gl_contentPaneOrders);
	}
	
	public static void calcColumnWidths(JTable table)
	{
	    JTableHeader header = table.getTableHeader();

	    TableCellRenderer defaultHeaderRenderer = null;

	    if (header != null)
	        defaultHeaderRenderer = header.getDefaultRenderer();

	    TableColumnModel columns = table.getColumnModel();
	    TableModel data = table.getModel();

	    int margin = columns.getColumnMargin(); 

	    int rowCount = data.getRowCount();

	    for (int i = columns.getColumnCount() - 1; i >= 0; --i)
	    {
	        TableColumn column = columns.getColumn(i);
	            
	        int columnIndex = column.getModelIndex();
	            
	        int width = -1; 

	        TableCellRenderer h = column.getHeaderRenderer();
	          
	        if (h == null)
	            h = defaultHeaderRenderer;
	            
	        if (h != null) 
	        {
	            Component c = h.getTableCellRendererComponent
	                   (table, column.getHeaderValue(),
	                    false, false, -1, i);
	                    
	            width = c.getPreferredSize().width;
	        }
	       
	        for (int row = rowCount - 1; row >= 0; --row)
	        {
	            TableCellRenderer r = table.getCellRenderer(row, i);
	                 
	            Component c = r.getTableCellRendererComponent
	               (table,
	                data.getValueAt(row, columnIndex),
	                false, false, row, i);
	        
	                width = Math.max(width, c.getPreferredSize().width);
	        }

	        if (width >= 0)
	        {
	            column.setPreferredWidth(width + margin); 
	        }
	    }
	}
}
