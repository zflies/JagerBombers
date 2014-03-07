import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextPane;
import javax.swing.JButton;

import java.awt.Color;

import javax.swing.SwingConstants;

import java.awt.Font;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.Vector;

import javax.swing.JSeparator;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class DinnerView extends JFrame {

private JPanel contentPane;
private static JButton btnCreate;
private JTable ordersTable;
private JLabel lblTotal;
private String column_names[]= {"Item","Notes","Price"};
private DecimalFormat df = new DecimalFormat("#.00");


/**
* Create the frame.
*/
public DinnerView( final Employee loggedInEmployee ) {
	setTitle("Steve's Depot Diner");
	setExtendedState(JFrame.MAXIMIZED_BOTH);
	setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	setBounds(100, 100, 1585, 965);
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
	btnCancel.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			dispose();
		}
	});
	btnCancel.setForeground(Color.BLACK);
	
	btnCreate = new JButton("Create");
	btnCreate.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			
			String employeePIN = "(SELECT PIN FROM Employees WHERE FirstName = '" + loggedInEmployee.getFirstName() +"' " +
															"AND LastName = '" + loggedInEmployee.getLastName() + "')";
			int tableNumber = tableNumberComboBox.getSelectedIndex() + 1;
			String itemsCSV = createOrderCSV();
			double totalPrice = getCurrentTotal();
			String query = String.format("INSERT INTO `avalenti`.`Orders` (`E_PIN`, `Table_No`, `Items`, `Status`, `Total`) VALUES (%s, '%s', '%s', 'entered', '%s');", employeePIN, tableNumber, itemsCSV, totalPrice);
			placeOrder(query);
			dispose();
		}
	});
	
	JLabel lblAppetizers = new JLabel("Appetizers");
	lblAppetizers.setFont(new Font("Lucida Grande", Font.PLAIN, 30));
	lblAppetizers.setHorizontalAlignment(SwingConstants.LEFT);
	
	JButton btnSpinachArtichoke = new JButton("Spinach & Artichoke");
	btnSpinachArtichoke.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			updateTotal("SpinachArtichoke");
		}
	});
	btnSpinachArtichoke.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
	
	JButton btnMozzarellaSticks = new JButton("Mozz Sticks");
	btnMozzarellaSticks.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			updateTotal("MozzarellaSticks");
		}
	});
	btnMozzarellaSticks.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
	
	JButton btnChickenFingers = new JButton("Chicken Fingers");
	btnChickenFingers.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			updateTotal("ChickenFingers");
		}
	});
	btnChickenFingers.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
	
	JLabel lblSalads = new JLabel("Salads");
	lblSalads.setFont(new Font("Lucida Grande", Font.PLAIN, 30));
	
	JButton btnChickenStrip = new JButton("Chicken Strip");
	btnChickenStrip.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			updateTotal("ChickenStrip");
		}
	});
	btnChickenStrip.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
	
	JButton btnChef = new JButton("Chef");
	btnChef.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			updateTotal("Chef");
		}
	});
	btnChef.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
	
	JButton btnCaesar = new JButton("Caesar");
	btnCaesar.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			updateTotal("Caesar");
		}
	});
	btnCaesar.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
	
	JButton btnSide = new JButton("Side");
	btnSide.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			updateTotal("Side");
		}
	});
	btnSide.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
	
	JLabel lblHotSandwhiches = new JLabel("Hot Sandwhiches");
	lblHotSandwhiches.setFont(new Font("Lucida Grande", Font.PLAIN, 30));
	
	JButton btnTunaMelt = new JButton("Tuna Melt");
	btnTunaMelt.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			updateTotal("TunaMelt");
		}
	});
	btnTunaMelt.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
	
	JButton btnGrilledChicken = new JButton("Grilled Chicken");
	btnGrilledChicken.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			updateTotal("GrilledChicken");
		}
	});
	btnGrilledChicken.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
	
	JButton btnPhillySteak = new JButton("Philly Steak");
	btnPhillySteak.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			updateTotal("PhillySteak");
		}
	});
	btnPhillySteak.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
	
	JButton btnPhillyChicken = new JButton("Philly Chicken");
	btnPhillyChicken.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			updateTotal("PhillyChicken");
		}
	});
	btnPhillyChicken.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
	
	JButton btnVeggie = new JButton("Veggie");
	btnVeggie.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			updateTotal("Veggie");
		}
	});
	btnVeggie.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
	
	JButton btnFish = new JButton("Fish");
	btnFish.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			updateTotal("Fish");
		}
	});
	btnFish.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
	
	JButton btnHamCheese = new JButton("Ham & Cheese");
	btnHamCheese.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			updateTotal("HamCheese");
		}
	});
	btnHamCheese.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
	
	JLabel lblColdSandwiches = new JLabel("Cold Sandwiches");
	lblColdSandwiches.setFont(new Font("Lucida Grande", Font.PLAIN, 30));
	
	JButton btnRoastBeef = new JButton("Roast Beef");
	btnRoastBeef.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			updateTotal("RoastBeef");
		}
	});
	btnRoastBeef.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
	
	JButton btnClassicClub = new JButton("Classic Club");
	btnClassicClub.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			updateTotal("ClassicClub");
		}
	});
	btnClassicClub.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
	
	JButton btnTurkey = new JButton("Turkey");
	btnTurkey.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			updateTotal("Turkey");
		}
	});
	btnTurkey.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
	
	JButton btnChickenSalad = new JButton("Chicken Salad");
	btnChickenSalad.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			updateTotal("ChickenSalad");
		}
	});
	btnChickenSalad.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
	
	JLabel lblBurgers = new JLabel("Burgers");
	lblBurgers.setFont(new Font("Lucida Grande", Font.PLAIN, 30));
	
	JButton btnClassic = new JButton("Classic");
	btnClassic.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			updateTotal("Classic");
		}
	});
	btnClassic.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
	
	JButton btnAmtrac = new JButton("AMTRAC");
	btnAmtrac.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			updateTotal("Amtrac");
		}
	});
	btnAmtrac.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
	
	JButton btnSantaFe = new JButton("Santa Fe");
	btnSantaFe.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			updateTotal("SantaFe");
		}
	});
	btnSantaFe.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
	
	JButton btnBnsf = new JButton("BNSF");
	btnBnsf.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			updateTotal("Bnsf");
		}
	});
	btnBnsf.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
	
	JLabel lblEntrees = new JLabel("Entrees");
	lblEntrees.setFont(new Font("Lucida Grande", Font.PLAIN, 30));
	
	JButton btnFriedSteak = new JButton("Fried Steak");
	btnFriedSteak.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			updateTotal("FriedSteak");
		}
	});
	btnFriedSteak.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
	
	JButton btnChickenStrips = new JButton("Chicken Strips");
	btnChickenStrips.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			updateTotal("ChickenStrips");
		}
	});
	btnChickenStrips.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
	
	JButton btnCodFilets = new JButton("Cod Filets");
	btnCodFilets.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			updateTotal("CodFilets");
		}
	});
	btnCodFilets.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
	
	JButton btnKcStrip = new JButton("KC Strip");
	btnKcStrip.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			updateTotal("KcStrip");
		}
	});
	btnKcStrip.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
	
	JButton btnRibeye = new JButton("Ribeye");
	btnRibeye.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			updateTotal("Ribeye");
		}
	});
	btnRibeye.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
	
	JButton btnFishChips = new JButton("Fish & Chips");
	btnFishChips.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			updateTotal("FishChips");
		}
	});
	btnFishChips.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
	
	JButton btnChickenParmesan = new JButton("Chicken Parm");
	btnChickenParmesan.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			updateTotal("ChickenParmesan");
		}
	});
	btnChickenParmesan.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
	
	JButton btnMeatloaf = new JButton("Meatloaf");
	btnMeatloaf.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			updateTotal("Meatloaf");
		}
	});
	btnMeatloaf.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
	
	JButton btnAlfredo = new JButton("Alfredo");
	btnAlfredo.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			updateTotal("Alfredo");
		}
	});
	btnAlfredo.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
	
	JButton btnChickenAlfredo = new JButton("Chicken Alfredo");
	btnChickenAlfredo.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			updateTotal("ChickenAlfredo");
		}
	});
	btnChickenAlfredo.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
	
	JButton btnShrimpAlfredo = new JButton("Shrimp Alfredo");
	btnShrimpAlfredo.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			updateTotal("ShrimpAlfredo");
		}
	});
	btnShrimpAlfredo.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
	
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
	
	lblTotal  = new JLabel("Total: $0.00");
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
				.addGap(42)
				.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_contentPane.createSequentialGroup()
						.addGap(2)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 321, GroupLayout.PREFERRED_SIZE))
					.addComponent(lblTotal, GroupLayout.PREFERRED_SIZE, 323, GroupLayout.PREFERRED_SIZE))
				.addGap(149)
				.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
					.addComponent(lblAppetizers)
					.addGroup(gl_contentPane.createSequentialGroup()
						.addComponent(btnSpinachArtichoke, GroupLayout.PREFERRED_SIZE, 184, GroupLayout.PREFERRED_SIZE)
						.addGap(12)
						.addComponent(btnMozzarellaSticks, GroupLayout.PREFERRED_SIZE, 145, GroupLayout.PREFERRED_SIZE)
						.addGap(12)
						.addComponent(btnChickenFingers, GroupLayout.PREFERRED_SIZE, 151, GroupLayout.PREFERRED_SIZE))
					.addComponent(lblSalads, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE)
					.addGroup(gl_contentPane.createSequentialGroup()
						.addComponent(btnChickenStrip, GroupLayout.PREFERRED_SIZE, 124, GroupLayout.PREFERRED_SIZE)
						.addGap(12)
						.addComponent(btnChef, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE)
						.addGap(12)
						.addComponent(btnCaesar, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE)
						.addGap(12)
						.addComponent(btnSide, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE))
					.addComponent(lblHotSandwhiches, GroupLayout.PREFERRED_SIZE, 261, GroupLayout.PREFERRED_SIZE)
					.addGroup(gl_contentPane.createSequentialGroup()
						.addComponent(btnTunaMelt, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE)
						.addGap(23)
						.addComponent(btnGrilledChicken, GroupLayout.PREFERRED_SIZE, 132, GroupLayout.PREFERRED_SIZE)
						.addGap(12)
						.addComponent(btnPhillySteak, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE)
						.addGap(12)
						.addComponent(btnPhillyChicken, GroupLayout.PREFERRED_SIZE, 126, GroupLayout.PREFERRED_SIZE))
					.addGroup(gl_contentPane.createSequentialGroup()
						.addComponent(btnVeggie, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE)
						.addGap(23)
						.addComponent(btnFish, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE)
						.addGap(27)
						.addComponent(btnHamCheese, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE))
					.addComponent(lblColdSandwiches, GroupLayout.PREFERRED_SIZE, 246, GroupLayout.PREFERRED_SIZE)
					.addComponent(lblBurgers, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE)
					.addGroup(gl_contentPane.createSequentialGroup()
						.addComponent(btnClassic, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE)
						.addGap(23)
						.addComponent(btnAmtrac, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE)
						.addGap(12)
						.addComponent(btnSantaFe, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE)
						.addGap(12)
						.addComponent(btnBnsf, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE))
					.addComponent(lblEntrees, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btnRoastBeef, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE)
							.addGap(23)
							.addComponent(btnClassicClub, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE)
							.addGap(12)
							.addComponent(btnTurkey, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE)
							.addGap(12)
							.addComponent(btnChickenSalad, 0, 0, Short.MAX_VALUE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btnFriedSteak, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(btnChickenStrips, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnCodFilets, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(btnKcStrip, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE))))
				.addGap(147)
				.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_contentPane.createSequentialGroup()
						.addGap(12)
						.addComponent(lblDrinks, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE))
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
				.addGap(5)
				.addComponent(btnDecaf, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE))
			.addGroup(gl_contentPane.createSequentialGroup()
				.addGap(42)
				.addComponent(btnCancel, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE)
				.addGap(355)
				.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
					.addComponent(btnRibeye, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE)
					.addComponent(btnAlfredo, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE))
				.addGap(18)
				.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
					.addComponent(btnFishChips, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE)
					.addComponent(btnChickenAlfredo, GroupLayout.PREFERRED_SIZE, 139, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
					.addGroup(gl_contentPane.createSequentialGroup()
						.addGap(3)
						.addComponent(btnChickenParmesan, GroupLayout.PREFERRED_SIZE, 123, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(btnMeatloaf, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED, 422, Short.MAX_VALUE))
					.addGroup(gl_contentPane.createSequentialGroup()
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(btnShrimpAlfredo, GroupLayout.PREFERRED_SIZE, 131, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)))
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
						.addGap(9)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 616, GroupLayout.PREFERRED_SIZE)
						.addGap(18)
						.addComponent(lblTotal))
					.addGroup(gl_contentPane.createSequentialGroup()
						.addComponent(lblAppetizers)
						.addGap(31)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_contentPane.createSequentialGroup()
								.addGap(1)
								.addComponent(btnSpinachArtichoke, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
							.addComponent(btnMozzarellaSticks)
							.addComponent(btnChickenFingers))
						.addGap(33)
						.addComponent(lblSalads, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
						.addGap(36)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
							.addComponent(btnChickenStrip)
							.addComponent(btnChef)
							.addComponent(btnCaesar)
							.addComponent(btnSide))
						.addGap(32)
						.addComponent(lblHotSandwhiches, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
						.addGap(24)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
							.addComponent(btnTunaMelt)
							.addComponent(btnGrilledChicken)
							.addComponent(btnPhillySteak)
							.addComponent(btnPhillyChicken))
						.addGap(12)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_contentPane.createSequentialGroup()
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
									.addComponent(btnVeggie)
									.addComponent(btnFish))
								.addGap(33)
								.addComponent(lblColdSandwiches, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
							.addComponent(btnHamCheese))
						.addGap(31)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_contentPane.createSequentialGroup()
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
									.addComponent(btnRoastBeef)
									.addComponent(btnClassicClub)
									.addComponent(btnTurkey))
								.addGap(22)
								.addComponent(lblBurgers))
							.addComponent(btnChickenSalad))
						.addGap(29)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_contentPane.createSequentialGroup()
								.addGap(1)
								.addComponent(btnClassic))
							.addGroup(gl_contentPane.createSequentialGroup()
								.addGap(1)
								.addComponent(btnAmtrac))
							.addGroup(gl_contentPane.createSequentialGroup()
								.addGap(1)
								.addComponent(btnSantaFe))
							.addComponent(btnBnsf))
						.addGap(25)
						.addComponent(lblEntrees, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
						.addGap(12)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
							.addComponent(btnChickenStrips)
							.addComponent(btnFriedSteak)
							.addComponent(btnCodFilets)
							.addComponent(btnKcStrip)))
					.addGroup(gl_contentPane.createSequentialGroup()
						.addGap(11)
						.addComponent(lblDrinks, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addGap(31)
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
				.addGap(18)
				.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_contentPane.createSequentialGroup()
						.addGap(15)
						.addComponent(btnCancel, GroupLayout.PREFERRED_SIZE, 61, GroupLayout.PREFERRED_SIZE))
					.addGroup(gl_contentPane.createSequentialGroup()
						.addComponent(btnRibeye)
						.addGap(18)
						.addComponent(btnAlfredo))
					.addGroup(gl_contentPane.createSequentialGroup()
						.addComponent(btnFishChips)
						.addGap(18)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
							.addComponent(btnChickenAlfredo)
							.addComponent(btnShrimpAlfredo)))
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnChickenParmesan)
						.addComponent(btnMeatloaf))
					.addGroup(gl_contentPane.createSequentialGroup()
						.addGap(15)
						.addComponent(btnCreate, GroupLayout.PREFERRED_SIZE, 61, GroupLayout.PREFERRED_SIZE))))
	);
	
	DefaultTableModel table_model= new DefaultTableModel(column_names, 0);
	ordersTable = new JTable(table_model);
	ordersTable.setFont(new Font("Lucida Grande", Font.BOLD, 15));
	scrollPane.setViewportView(ordersTable);
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
		DefaultTableModel model = (DefaultTableModel) ordersTable.getModel();
		Vector<Vector> outer = model.getDataVector();
		for(int i = 0; i < outer.size(); i++){
			Vector inner = outer.elementAt(i);
			if ( i > 0 )
			{
				csv += ", "; // Add the comma here.  This will prevent appending ',' on the last item	
			}
			csv += inner.elementAt(0);
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
		DefaultTableModel model = (DefaultTableModel) ordersTable.getModel();
		model.addRow(new Object[]{itemName, "", df.format(itemPrice)});
	}
}
