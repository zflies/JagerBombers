import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextPane;
import javax.swing.JButton;

import java.awt.Color;

import javax.swing.SwingConstants;

import java.awt.Font;

import javax.swing.JSeparator;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;


public class DinnerView extends JFrame {

private JPanel contentPane;
private static JButton btnCreate;

/**
* Launch the application.
*/
public static void main(String[] args) {
	EventQueue.invokeLater(new Runnable() {
		public void run() {
			try {
				DinnerView frame = new DinnerView();
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
public DinnerView() {
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
	JComboBox comboBox = new JComboBox(tables);
	comboBox.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
	
	JTextPane textPane = new JTextPane();
	
	JButton btnCancel = new JButton("Cancel");
	btnCancel.setForeground(Color.BLACK);
	
	btnCreate = new JButton("Create");
	
	JLabel lblAppetizers = new JLabel("Appetizers");
	lblAppetizers.setFont(new Font("Lucida Grande", Font.PLAIN, 30));
	lblAppetizers.setHorizontalAlignment(SwingConstants.LEFT);
	
	JButton btnSpinacheArtichoke = new JButton("Spinach & Artichoke");
	btnSpinacheArtichoke.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
	
	JButton btnMozzarellaSticks = new JButton("Mozz Sticks");
	btnMozzarellaSticks.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
	
	JButton btnChickenFingers = new JButton("Chicken Fingers");
	btnChickenFingers.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
	
	JLabel lblSalads = new JLabel("Salads");
	lblSalads.setFont(new Font("Lucida Grande", Font.PLAIN, 30));
	
	JButton btnChickenStrip = new JButton("Chicken Strip");
	btnChickenStrip.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
	
	JButton btnChef = new JButton("Chef");
	btnChef.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
	
	JButton btnCaesar = new JButton("Caesar");
	btnCaesar.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
	
	JButton btnSide = new JButton("Side");
	btnSide.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
	
	JLabel lblHotSandwhiches = new JLabel("Hot Sandwhiches");
	lblHotSandwhiches.setFont(new Font("Lucida Grande", Font.PLAIN, 30));
	
	JButton btnTunaMelt = new JButton("Tuna Melt");
	
	JButton btnGrilledChicken = new JButton("Grilled Chicken");
	
	JButton btnPhillySteak = new JButton("Philly Steak");
	
	JButton btnPhillyChicken = new JButton("Philly Chicken");
	
	JButton btnVeggie = new JButton("Veggie");
	
	JButton btnFish = new JButton("Fish");
	
	JButton btnHamCheese = new JButton("Ham & Cheese");
	
	JLabel lblColdSandwiches = new JLabel("Cold Sandwiches");
	lblColdSandwiches.setFont(new Font("Lucida Grande", Font.PLAIN, 30));
	
	JButton btnRoastBeef = new JButton("Roast Beef");
	
	JButton btnClassicClub = new JButton("Classic Club");
	
	JButton btnTurkey = new JButton("Turkey");
	
	JButton btnChickenSalad = new JButton("Chicken Salad");
	
	JLabel lblBurgers = new JLabel("Burgers");
	lblBurgers.setFont(new Font("Lucida Grande", Font.PLAIN, 30));
	
	JButton btnClassic = new JButton("Classic");
	
	JButton btnAmtrac = new JButton("AMTRAC");
	
	JButton btnSantaFe = new JButton("Santa Fe");
	
	JButton btnBnsf = new JButton("BNSF");
	btnBnsf.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
	
	JLabel lblEntrees = new JLabel("Entrees");
	lblEntrees.setFont(new Font("Lucida Grande", Font.PLAIN, 30));
	
	JButton btnFriedSteak = new JButton("Fried Steak");
	
	JButton btnChickenStrips = new JButton("Chicken Strips");
	
	JButton btnCodFilets = new JButton("Cod Filets");
	
	JButton btnNewButton = new JButton("KC Strip");
	
	JButton btnRibeye = new JButton("Ribeye");
	
	JButton btnFishChips = new JButton("Fish & Chips");
	
	JButton btnChickenParmesan = new JButton("Chicken Parm");
	
	JButton btnMeatloaf = new JButton("Meatloaf");
	
	JButton btnAlfredo = new JButton("Alfredo");
	
	JButton btnChickenAlfredo = new JButton("Chicken Alfredo");
	
	JButton btnShrimpAlfredo = new JButton("Shrimp Alfredo");
	
	JLabel lblDrinks = new JLabel("Drinks");
	lblDrinks.setFont(new Font("Lucida Grande", Font.PLAIN, 30));
	
	JButton btnSoftDrink = new JButton("Soft Drink");
	btnSoftDrink.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
	
	JButton btnCoffee = new JButton("Coffee");
	btnCoffee.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
	
	JButton btnDecaf = new JButton("Decaf");
	btnDecaf.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
	
	JButton btnTea = new JButton("Hot Tea");
	btnTea.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
	
	JButton btnIcedTea = new JButton("Iced Tea");
	btnIcedTea.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
	
	JButton btnSmMilk = new JButton("SM Milk");
	btnSmMilk.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
	
	JButton btnLgMilk = new JButton("LG Milk");
	btnLgMilk.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
	
	JButton btnSmOrange = new JButton("SM Orange");
	btnSmOrange.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
	
	JButton btnLgOrange = new JButton("LG Orange");
	btnLgOrange.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
	
	JButton btnSmApple = new JButton("SM Apple");
	btnSmApple.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
	
	JButton btnLgApple = new JButton("LG Apple");
	btnLgApple.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
	
	JButton btnSmCranberry = new JButton("SM Cranberry");
	btnSmCranberry.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
	
	JButton btnLgCranberry = new JButton("LG Cranberry");
	btnLgCranberry.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
	
	JLabel lblTotal = new JLabel("Total: $0.00");
	lblTotal.setFont(new Font("Lucida Grande", Font.PLAIN, 30));
	GroupLayout gl_contentPane = new GroupLayout(contentPane);
	gl_contentPane.setHorizontalGroup(
		gl_contentPane.createParallelGroup(Alignment.LEADING)
			.addGroup(gl_contentPane.createSequentialGroup()
				.addGap(44)
				.addComponent(lblTable, GroupLayout.PREFERRED_SIZE, 87, GroupLayout.PREFERRED_SIZE)
				.addGap(138)
				.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE))
			.addGroup(gl_contentPane.createSequentialGroup()
				.addGap(42)
				.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
					.addComponent(btnCancel, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE)
					.addComponent(textPane, GroupLayout.DEFAULT_SIZE, 323, Short.MAX_VALUE)
					.addComponent(lblTotal, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				.addGap(149)
				.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
					.addComponent(lblAppetizers)
					.addGroup(gl_contentPane.createSequentialGroup()
						.addComponent(btnSpinacheArtichoke, GroupLayout.PREFERRED_SIZE, 184, GroupLayout.PREFERRED_SIZE)
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
						.addComponent(btnPhillyChicken, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE))
					.addGroup(gl_contentPane.createSequentialGroup()
						.addComponent(btnVeggie, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE)
						.addGap(23)
						.addComponent(btnFish, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE)
						.addGap(27)
						.addComponent(btnHamCheese, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE))
					.addComponent(lblColdSandwiches, GroupLayout.PREFERRED_SIZE, 246, GroupLayout.PREFERRED_SIZE)
					.addGroup(gl_contentPane.createSequentialGroup()
						.addComponent(btnRoastBeef, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE)
						.addGap(23)
						.addComponent(btnClassicClub, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE)
						.addGap(12)
						.addComponent(btnTurkey, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE)
						.addGap(12)
						.addComponent(btnChickenSalad, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE))
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
					.addGroup(gl_contentPane.createSequentialGroup()
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_contentPane.createSequentialGroup()
								.addComponent(btnRibeye, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE)
								.addGap(18)
								.addComponent(btnFishChips, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE)
								.addGap(18)
								.addComponent(btnChickenParmesan, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE)
								.addGap(18)
								.addComponent(btnMeatloaf, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE))
							.addGroup(gl_contentPane.createSequentialGroup()
								.addComponent(btnFriedSteak, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE)
								.addGap(18)
								.addComponent(btnChickenStrips, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE)
								.addGap(18)
								.addComponent(btnCodFilets, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE)
								.addGap(18)
								.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE))
							.addGroup(gl_contentPane.createSequentialGroup()
								.addComponent(btnAlfredo, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE)
								.addGap(18)
								.addComponent(btnChickenAlfredo, GroupLayout.PREFERRED_SIZE, 132, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(btnShrimpAlfredo, GroupLayout.PREFERRED_SIZE, 124, GroupLayout.PREFERRED_SIZE)))))
				.addPreferredGap(ComponentPlacement.RELATED, 156, Short.MAX_VALUE)
				.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
					.addGroup(gl_contentPane.createSequentialGroup()
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
						.addComponent(btnCreate, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE)
						.addGap(52))))
	);
	gl_contentPane.setVerticalGroup(
		gl_contentPane.createParallelGroup(Alignment.LEADING)
			.addGroup(gl_contentPane.createSequentialGroup()
				.addGap(89)
				.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
					.addComponent(lblTable, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
					.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addGap(1)
				.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_contentPane.createSequentialGroup()
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
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
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
									.addGroup(gl_contentPane.createSequentialGroup()
										.addGap(67)
										.addComponent(btnDecaf))
									.addGroup(gl_contentPane.createSequentialGroup()
										.addComponent(lblAppetizers)
										.addGap(31)
										.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
											.addGroup(gl_contentPane.createSequentialGroup()
												.addGap(1)
												.addComponent(btnSpinacheArtichoke, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
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
											.addComponent(btnVeggie)
											.addComponent(btnFish)
											.addComponent(btnHamCheese))
										.addGap(33)
										.addComponent(lblColdSandwiches, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
										.addGap(31)
										.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
											.addComponent(btnRoastBeef)
											.addComponent(btnClassicClub)
											.addComponent(btnTurkey)
											.addComponent(btnChickenSalad))
										.addGap(22)
										.addComponent(lblBurgers)
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
										.addComponent(lblEntrees, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
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
										.addComponent(btnLgCranberry)))
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
									.addComponent(btnChickenStrips)
									.addComponent(btnCodFilets)
									.addComponent(btnNewButton)
									.addComponent(btnFriedSteak))
								.addGap(18)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
									.addComponent(btnRibeye)
									.addComponent(btnFishChips)
									.addComponent(btnChickenParmesan)
									.addComponent(btnMeatloaf))))
						.addGap(18)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
							.addComponent(btnAlfredo)
							.addComponent(btnChickenAlfredo)
							.addComponent(btnShrimpAlfredo)))
					.addGroup(gl_contentPane.createSequentialGroup()
						.addGap(7)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
							.addComponent(btnCreate, GroupLayout.PREFERRED_SIZE, 61, GroupLayout.PREFERRED_SIZE)
							.addGroup(gl_contentPane.createSequentialGroup()
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(textPane, GroupLayout.DEFAULT_SIZE, 638, Short.MAX_VALUE)
								.addGap(18)
								.addComponent(lblTotal)
								.addGap(46)
								.addComponent(btnCancel, GroupLayout.PREFERRED_SIZE, 61, GroupLayout.PREFERRED_SIZE)))))
				.addGap(159))
	);
	contentPane.setLayout(gl_contentPane);
	}

}