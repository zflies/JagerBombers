import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.Vector;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.mysql.jdbc.Statement;


public class BreakfastView extends JFrame {

	private JPanel contentPane;
	private JLabel lblTotal;
	private static JButton btnCreate;
	private DecimalFormat df = new DecimalFormat("#.00");
	private JTable OrdersTable;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BreakfastView frame = new BreakfastView();
					frame.setVisible(true);
					frame.getRootPane().setDefaultButton(btnCreate);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public BreakfastView() {
		setTitle("Steve's Depot Diner");
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1643, 878);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		String[] tables = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15",
				"16", "17", "18", "19", "20", "21", "22", "23", "24", "25"};
		
		JLabel lblTable = new JLabel("Table");
		lblTable.setFont(new Font("Lucida Grande", Font.PLAIN, 30));
		final JComboBox tableNumberComboBox = new JComboBox(tables);
		tableNumberComboBox.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				OrdersView ordersView = new OrdersView();
				ordersView.setVisible(true);
				ordersView.setExtendedState(JFrame.MAXIMIZED_BOTH);
				dispose();
			}
		});
		btnCancel.setForeground(Color.BLACK);
		
		btnCreate = new JButton("Create");
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Go through all rows in ordersTable and get the itemName, notes (if any) and itemprice
				//Form query consisting of Employee PIN, Table No, items/notes (as CSV), status = entered, and totalprice
				int employeePin = 1111; //Need to figure out how we are going to pass the employee from view to view Singleton??
				int tableNumber = tableNumberComboBox.getSelectedIndex() + 1;
				String itemsCSV = createOrderCSV();
				double totalPrice = getCurrentTotal();
				String query = String.format("INSERT INTO `avalenti`.`Orders` (`E_PIN`, `Table_No`, `Items`, `Status`, `Total`) VALUES ('%s', '%s', '%s', 'entered', '%s');", employeePin, tableNumber, itemsCSV, totalPrice);
				placeOrder(query);
			}
		});
				
		JLabel lblCombinations = new JLabel("Combinations");
		lblCombinations.setFont(new Font("Lucida Grande", Font.PLAIN, 30));
		lblCombinations.setHorizontalAlignment(SwingConstants.LEFT);
		
		final JButton btnBaconEggs = new JButton("Bacon & Eggs");
		btnBaconEggs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateTotal("BaconEggs");
			}
		});
		btnBaconEggs.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		
		JButton btnSausageEggs = new JButton("Sausage & Eggs");
		btnSausageEggs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateTotal("SausageEggs");
			}
		});
		btnSausageEggs.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		
		JButton btnHamEggs = new JButton("Ham & Eggs");
		btnHamEggs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateTotal("HamEggs");
			}
		});
		btnHamEggs.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		
		JLabel lblOmelets = new JLabel("Omelets");
		lblOmelets.setFont(new Font("Lucida Grande", Font.PLAIN, 30));
		
		JButton btnDenver = new JButton("Denver");
		btnDenver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateTotal("Denver");
			}
		});
		btnDenver.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		
		JButton btnCheese = new JButton("Cheese");
		btnCheese.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateTotal("Cheese");
			}
		});
		btnCheese.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		
		JButton btnVeggie = new JButton("Veggie");
		btnVeggie.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateTotal("Veggie");
			}
		});
		btnVeggie.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		
		JButton btnSouthwest = new JButton("Southwest");
		btnSouthwest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateTotal("Southwest");
			}
		});
		btnSouthwest.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		
		JLabel lblSpecialties = new JLabel("Specialties");
		lblSpecialties.setFont(new Font("Lucida Grande", Font.PLAIN, 30));
		
		JButton btnExpress = new JButton("Express");
		btnExpress.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateTotal("Express");
			}
		});
		btnExpress.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		
		JButton btnExpress2Egg = new JButton("Express 2 Egg");
		btnExpress2Egg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateTotal("Express2Egg");
			}
		});
		btnExpress2Egg.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		
		JButton btnFrenchToast = new JButton("French Toast");
		btnFrenchToast.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateTotal("FrenchToast");
			}
		});
		btnFrenchToast.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		
		JButton btnTripStack = new JButton("Trip Stack");
		btnTripStack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateTotal("TripStack");
			}
		});
		btnTripStack.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		
		JButton btnTripStackBlue = new JButton("Trip Stack Blue");
		btnTripStackBlue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateTotal("TripStackBlue");
			}
		});
		btnTripStackBlue.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		
		JButton btnBiscGravy = new JButton("Biscs & Gravy");
		btnBiscGravy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateTotal("BiscGravy");
			}
		});
		btnBiscGravy.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		
		JButton btnWaffle = new JButton("Waffle");
		btnWaffle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateTotal("Waffle");
			}
		});
		btnWaffle.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		
		JButton btnWaffleCombo = new JButton("Waffle Combo");
		btnWaffleCombo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateTotal("WaffleCombo");
			}
		});
		btnWaffleCombo.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		
		JLabel lblSides = new JLabel("Sides");
		lblSides.setFont(new Font("Lucida Grande", Font.PLAIN, 30));
		
		JButton btnHashBrown = new JButton("Hash Brown");
		btnHashBrown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateTotal("HashBrown");
			}
		});
		btnHashBrown.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		
		JButton btnCinnamonRoll = new JButton("Cinnamon Roll");
		btnCinnamonRoll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateTotal("CinnamonRoll");
			}
		});
		btnCinnamonRoll.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		
		JButton btnBacon = new JButton("Bacon");
		btnBacon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateTotal("Bacon");
			}
		});
		btnBacon.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		
		JButton btnSausage = new JButton("Sausage");
		btnSausage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateTotal("Sausage");
			}
		});
		btnSausage.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		
		JButton btnBiscuitGravy = new JButton("Biscuit & Gravy");
		btnBiscuitGravy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateTotal("BiscuitGravy");
			}
		});
		btnBiscuitGravy.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		
		JButton btnToast = new JButton("Toast");
		btnToast.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateTotal("Toast");
			}
		});
		btnToast.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		
		JLabel lblDrinks = new JLabel("Drinks");
		lblDrinks.setFont(new Font("Lucida Grande", Font.PLAIN, 30));
		
		JButton btnSoftDrink = new JButton("Soft Drink");
		btnSoftDrink.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateTotal("SoftDrink");
			}
		});
		btnSoftDrink.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		
		JButton btnCoffee = new JButton("Coffee");
		btnCoffee.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateTotal("Coffee");
			}
		});
		btnCoffee.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		
		JButton btnDecaf = new JButton("Decaf");
		btnDecaf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateTotal("Decaf");
			}
		});
		btnDecaf.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		
		JButton btnTea = new JButton("Hot Tea");
		btnTea.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateTotal("Tea");
			}
		});
		btnTea.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		
		JButton btnIcedTea = new JButton("Iced Tea");
		btnIcedTea.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateTotal("IcedTea");
			}
		});
		btnIcedTea.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		
		JButton btnSmMilk = new JButton("SM Milk");
		btnSmMilk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateTotal("SmMilk");
			}
		});
		btnSmMilk.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		
		JButton btnLgMilk = new JButton("LG Milk");
		btnLgMilk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateTotal("LgMilk");
			}
		});
		btnLgMilk.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		
		JButton btnSmOrange = new JButton("SM Orange");
		btnSmOrange.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateTotal("SmOrange");
			}
		});
		btnSmOrange.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		
		JButton btnLgOrange = new JButton("LG Orange");
		btnLgOrange.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateTotal("LgOrange");
			}
		});
		btnLgOrange.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		
		JButton btnSmApple = new JButton("SM Apple");
		btnSmApple.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateTotal("SmApple");
			}
		});
		btnSmApple.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		
		JButton btnLgApple = new JButton("LG Apple");
		btnLgApple.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateTotal("LgApple");
			}
		});
		btnLgApple.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		
		JButton btnSmCranberry = new JButton("SM Cranberry");
		btnSmCranberry.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateTotal("SmCranberry");
			}
		});
		btnSmCranberry.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		
		JButton btnLgCranberry = new JButton("LG Cranberry");
		btnLgCranberry.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateTotal("LgCranberry");
			}
		});
		btnLgCranberry.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		
		JButton btnSteakAndEggs = new JButton("Steak & Eggs");
		btnSteakAndEggs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateTotal("SteakAndEggs");
			}
		});
		btnSteakAndEggs.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		
		JButton btnWaffleBlue = new JButton("Waffle Blue");
		btnWaffleBlue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateTotal("WaffleBlue");
			}
		});
		btnWaffleBlue.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		
		lblTotal = new JLabel("Total: $0.00");
		lblTotal.setFont(new Font("Lucida Grande", Font.PLAIN, 30));
		
		JScrollPane scrollPane = new JScrollPane();
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(44)
					.addComponent(lblTable, GroupLayout.PREFERRED_SIZE, 87, GroupLayout.PREFERRED_SIZE)
					.addGap(138)
					.addComponent(tableNumberComboBox, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(54)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 326, GroupLayout.PREFERRED_SIZE)
					.addGap(149)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblCombinations, GroupLayout.PREFERRED_SIZE, 212, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btnBaconEggs, GroupLayout.PREFERRED_SIZE, 124, GroupLayout.PREFERRED_SIZE)
							.addGap(16)
							.addComponent(btnSausageEggs, GroupLayout.PREFERRED_SIZE, 143, GroupLayout.PREFERRED_SIZE)
							.addGap(12)
							.addComponent(btnHamEggs, GroupLayout.PREFERRED_SIZE, 113, GroupLayout.PREFERRED_SIZE)
							.addGap(12)
							.addComponent(btnSteakAndEggs, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE))
						.addComponent(lblOmelets, GroupLayout.PREFERRED_SIZE, 124, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btnDenver, GroupLayout.PREFERRED_SIZE, 124, GroupLayout.PREFERRED_SIZE)
							.addGap(12)
							.addComponent(btnCheese, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE)
							.addGap(12)
							.addComponent(btnVeggie, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE)
							.addGap(12)
							.addComponent(btnSouthwest, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE))
						.addComponent(lblSpecialties, GroupLayout.PREFERRED_SIZE, 160, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btnExpress, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE)
							.addGap(23)
							.addComponent(btnExpress2Egg, GroupLayout.PREFERRED_SIZE, 132, GroupLayout.PREFERRED_SIZE)
							.addGap(12)
							.addComponent(btnFrenchToast, GroupLayout.PREFERRED_SIZE, 132, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btnTripStack, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE)
							.addGap(23)
							.addComponent(btnTripStackBlue, GroupLayout.PREFERRED_SIZE, 132, GroupLayout.PREFERRED_SIZE)
							.addGap(12)
							.addComponent(btnBiscGravy, GroupLayout.PREFERRED_SIZE, 132, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btnWaffle, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE)
							.addGap(23)
							.addComponent(btnWaffleBlue, GroupLayout.PREFERRED_SIZE, 132, GroupLayout.PREFERRED_SIZE)
							.addGap(12)
							.addComponent(btnWaffleCombo, GroupLayout.PREFERRED_SIZE, 132, GroupLayout.PREFERRED_SIZE))
						.addComponent(lblSides, GroupLayout.PREFERRED_SIZE, 87, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btnHashBrown, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE)
							.addGap(19)
							.addComponent(btnCinnamonRoll, GroupLayout.PREFERRED_SIZE, 136, GroupLayout.PREFERRED_SIZE)
							.addGap(12)
							.addComponent(btnBacon, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btnSausage, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE)
							.addGap(19)
							.addComponent(btnBiscuitGravy, GroupLayout.PREFERRED_SIZE, 136, GroupLayout.PREFERRED_SIZE)
							.addGap(12)
							.addComponent(btnToast, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE)))
					.addGap(122)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblDrinks, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnSoftDrink)
						.addComponent(btnTea, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnSmMilk, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnSmOrange, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnSmApple, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnSmCranberry, GroupLayout.PREFERRED_SIZE, 124, GroupLayout.PREFERRED_SIZE))
					.addGap(5)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(btnCoffee, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnIcedTea, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnLgMilk, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnLgOrange, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnLgApple, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnLgCranberry, GroupLayout.PREFERRED_SIZE, 124, GroupLayout.PREFERRED_SIZE))
					.addGap(6)
					.addComponent(btnDecaf, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(49)
					.addComponent(lblTotal, GroupLayout.PREFERRED_SIZE, 321, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(49)
					.addComponent(btnCancel, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE)
					.addGap(1281)
					.addComponent(btnCreate, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(89)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblTable, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
						.addComponent(tableNumberComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(1)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(7)
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 574, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblCombinations)
							.addGap(31)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(1)
									.addComponent(btnBaconEggs, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
								.addComponent(btnSausageEggs)
								.addComponent(btnHamEggs)
								.addComponent(btnSteakAndEggs))
							.addGap(33)
							.addComponent(lblOmelets, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
							.addGap(36)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(btnDenver)
								.addComponent(btnCheese)
								.addComponent(btnVeggie)
								.addComponent(btnSouthwest))
							.addGap(12)
							.addComponent(lblSpecialties, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
							.addGap(24)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(btnExpress)
								.addComponent(btnExpress2Egg)
								.addComponent(btnFrenchToast))
							.addGap(12)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(btnTripStack)
								.addComponent(btnTripStackBlue)
								.addComponent(btnBiscGravy))
							.addGap(12)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(btnWaffle)
								.addComponent(btnWaffleBlue)
								.addComponent(btnWaffleCombo))
							.addGap(39)
							.addComponent(lblSides, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
							.addGap(23)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(btnHashBrown)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(1)
									.addComponent(btnCinnamonRoll))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(1)
									.addComponent(btnBacon)))
							.addGap(11)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(btnSausage)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(1)
									.addComponent(btnBiscuitGravy))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(1)
									.addComponent(btnToast))))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(6)
							.addComponent(lblDrinks, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
							.addGap(36)
							.addComponent(btnSoftDrink)
							.addGap(12)
							.addComponent(btnTea)
							.addGap(12)
							.addComponent(btnSmMilk)
							.addGap(12)
							.addComponent(btnSmOrange)
							.addGap(12)
							.addComponent(btnSmApple)
							.addGap(12)
							.addComponent(btnSmCranberry))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(67)
							.addComponent(btnCoffee)
							.addGap(12)
							.addComponent(btnIcedTea)
							.addGap(12)
							.addComponent(btnLgMilk)
							.addGap(12)
							.addComponent(btnLgOrange)
							.addGap(12)
							.addComponent(btnLgApple)
							.addGap(12)
							.addComponent(btnLgCranberry))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(67)
							.addComponent(btnDecaf)))
					.addGap(24)
					.addComponent(lblTotal, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
					.addGap(30)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(btnCancel, GroupLayout.PREFERRED_SIZE, 61, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnCreate, GroupLayout.PREFERRED_SIZE, 61, GroupLayout.PREFERRED_SIZE)))
		);
		
		String column_names[]= {"Item","Notes","Price"};
		DefaultTableModel table_model= new DefaultTableModel(column_names, 0);
		OrdersTable = new JTable(table_model);
		OrdersTable.setFont(new Font("Lucida Grande", Font.BOLD, 15));
		scrollPane.setViewportView(OrdersTable);
		contentPane.setLayout(gl_contentPane);
	}
	
	private double getCurrentTotal(){
		String totalText = lblTotal.getText();
		String[] totalSplit = totalText.split("\\$");
		return Double.parseDouble(totalSplit[1]);
	}
	
	private void updateTotal(String itemName){
		double itemPrice = getItemPrice(itemName);
		updateTicket(itemName, itemPrice);
		double currentTotal = getCurrentTotal();
		currentTotal += itemPrice;
		lblTotal.setText("Total: \t $" + df.format(currentTotal));
	}
	
	private String createOrderCSV(){
		String csv = "";
		DefaultTableModel model = (DefaultTableModel) OrdersTable.getModel();
		Vector<Vector> outer = model.getDataVector();
		for(int i = 0; i < outer.size(); i++){
			Vector inner = outer.elementAt(i);
			for(int j = 0; j < inner.size(); j++){
				if(inner.elementAt(j) != ""){
					csv = csv + inner.elementAt(j) + ",";
				}
			}
		}
		return csv;
	}
	
	private double getItemPrice(String itemName){
		java.sql.Statement state = DBConnection.OpenConnection();
		String commandstring = "SELECT * FROM avalenti.Menu WHERE Item = '" + itemName + "';";
		double itemPrice = 0.00;
		if(state != null){
			try {
				ResultSet rs = state.executeQuery(commandstring);
				if(rs.next() == true) {
					String item = rs.getString("Price");
					itemPrice = Double.parseDouble(rs.getString("Price"));
				}
			} catch (SQLException e) {
				System.err.println("Error in SQL Execution");
				}
		}
		else
			System.err.println("Statement was null.  No connection?");
		
		return itemPrice;
	
	}
	
	private void placeOrder(String query){
		java.sql.Statement state = DBConnection.OpenConnection();
		double itemPrice = 0.00;
		if(state != null){
			try {
				state.execute(query);
			} catch (SQLException e) {
				System.err.println("Error in SQL Execution");
				}
		}
		else
			System.err.println("Statement was null.  No connection?");	
	}
	
	private void updateTicket(String itemName, double itemPrice){
		DefaultTableModel model = (DefaultTableModel) OrdersTable.getModel();
		model.addRow(new Object[]{itemName, "", df.format(itemPrice)});
	}
}
