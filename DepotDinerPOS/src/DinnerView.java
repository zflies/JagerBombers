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
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyledDocument;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class DinnerView extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DinnerView frame = new DinnerView();
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
	public DinnerView() {
		setTitle("Steve's Depot Diner");
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		String[] tables = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15",
				"16", "17", "18", "19", "20", "21", "22", "23", "24", "25"};
		
		JLabel lblTable = new JLabel("Table");
		lblTable.setFont(new Font("Lucida Grande", Font.PLAIN, 30));
		lblTable.setBounds(49, 94, 87, 23);
		contentPane.add(lblTable);
		JComboBox comboBox = new JComboBox(tables);
		comboBox.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		comboBox.setBounds(274, 94, 96, 27);
		contentPane.add(comboBox);
		
		JTextPane textPane = new JTextPane();
		textPane.setBounds(47, 129, 323, 785);
		contentPane.add(textPane);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setForeground(Color.BLACK);
		btnCancel.setBounds(47, 926, 117, 61);
		contentPane.add(btnCancel);
		
		JButton btnCreate = new JButton("Create");
		btnCreate.setBounds(1540, 926, 117, 61);
		contentPane.add(btnCreate);
		
		JLabel lblAppetizers = new JLabel("Appetizers");
		lblAppetizers.setFont(new Font("Lucida Grande", Font.PLAIN, 30));
		lblAppetizers.setHorizontalAlignment(SwingConstants.LEFT);
		lblAppetizers.setBounds(519, 122, 157, 36);
		contentPane.add(lblAppetizers);
		
		JButton btnSpinacheArtichoke = new JButton("Spinach & Artichoke");
		btnSpinacheArtichoke.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		btnSpinacheArtichoke.setBounds(519, 190, 184, 27);
		contentPane.add(btnSpinacheArtichoke);
		
		JButton btnMozzarellaSticks = new JButton("Mozz Sticks");
		btnMozzarellaSticks.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		btnMozzarellaSticks.setBounds(715, 189, 145, 29);
		contentPane.add(btnMozzarellaSticks);
		
		JButton btnChickenFingers = new JButton("Chicken Fingers");
		btnChickenFingers.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		btnChickenFingers.setBounds(872, 189, 151, 29);
		contentPane.add(btnChickenFingers);
		
		JLabel lblSalads = new JLabel("Salads");
		lblSalads.setFont(new Font("Lucida Grande", Font.PLAIN, 30));
		lblSalads.setBounds(519, 251, 96, 23);
		contentPane.add(lblSalads);
		
		JButton btnChickenStrip = new JButton("Chicken Strip");
		btnChickenStrip.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		btnChickenStrip.setBounds(519, 310, 124, 29);
		contentPane.add(btnChickenStrip);
		
		JButton btnChef = new JButton("Chef");
		btnChef.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		btnChef.setBounds(655, 310, 117, 29);
		contentPane.add(btnChef);
		
		JButton btnCaesar = new JButton("Caesar");
		btnCaesar.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		btnCaesar.setBounds(784, 310, 117, 29);
		contentPane.add(btnCaesar);
		
		JButton btnSide = new JButton("Side");
		btnSide.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		btnSide.setBounds(913, 310, 117, 29);
		contentPane.add(btnSide);
		
		JLabel lblHotSandwhiches = new JLabel("Hot Sandwhiches");
		lblHotSandwhiches.setFont(new Font("Lucida Grande", Font.PLAIN, 30));
		lblHotSandwhiches.setBounds(519, 371, 261, 23);
		contentPane.add(lblHotSandwhiches);
		
		JButton btnTunaMelt = new JButton("Tuna Melt");
		btnTunaMelt.setBounds(519, 418, 117, 29);
		contentPane.add(btnTunaMelt);
		
		JButton btnGrilledChicken = new JButton("Grilled Chicken");
		btnGrilledChicken.setBounds(659, 418, 132, 29);
		contentPane.add(btnGrilledChicken);
		
		JButton btnPhillySteak = new JButton("Philly Steak");
		btnPhillySteak.setBounds(803, 418, 117, 29);
		contentPane.add(btnPhillySteak);
		
		JButton btnPhillyChicken = new JButton("Philly Chicken");
		btnPhillyChicken.setBounds(932, 418, 117, 29);
		contentPane.add(btnPhillyChicken);
		
		JButton btnVeggie = new JButton("Veggie");
		btnVeggie.setBounds(519, 459, 117, 29);
		contentPane.add(btnVeggie);
		
		JButton btnFish = new JButton("Fish");
		btnFish.setBounds(659, 459, 117, 29);
		contentPane.add(btnFish);
		
		JButton btnHamCheese = new JButton("Ham & Cheese");
		btnHamCheese.setBounds(803, 459, 117, 29);
		contentPane.add(btnHamCheese);
		
		JLabel lblColdSandwiches = new JLabel("Cold Sandwiches");
		lblColdSandwiches.setFont(new Font("Lucida Grande", Font.PLAIN, 30));
		lblColdSandwiches.setBounds(519, 521, 246, 23);
		contentPane.add(lblColdSandwiches);
		
		JButton btnRoastBeef = new JButton("Roast Beef");
		btnRoastBeef.setBounds(519, 575, 117, 29);
		contentPane.add(btnRoastBeef);
		
		JButton btnClassicClub = new JButton("Classic Club");
		btnClassicClub.setBounds(659, 575, 117, 29);
		contentPane.add(btnClassicClub);
		
		JButton btnTurkey = new JButton("Turkey");
		btnTurkey.setBounds(788, 575, 117, 29);
		contentPane.add(btnTurkey);
		
		JButton btnChickenSalad = new JButton("Chicken Salad");
		btnChickenSalad.setBounds(917, 575, 117, 29);
		contentPane.add(btnChickenSalad);
		
		JLabel lblBurgers = new JLabel("Burgers");
		lblBurgers.setFont(new Font("Lucida Grande", Font.PLAIN, 30));
		lblBurgers.setBounds(519, 626, 117, 36);
		contentPane.add(lblBurgers);
		
		JButton btnClassic = new JButton("Classic");
		btnClassic.setBounds(519, 692, 117, 29);
		contentPane.add(btnClassic);
		
		JButton btnAmtrac = new JButton("AMTRAC");
		btnAmtrac.setBounds(659, 692, 117, 29);
		contentPane.add(btnAmtrac);
		
		JButton btnSantaFe = new JButton("Santa Fe");
		btnSantaFe.setBounds(788, 692, 117, 29);
		contentPane.add(btnSantaFe);
		
		JButton btnBnsf = new JButton("BNSF");
		btnBnsf.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		btnBnsf.setBounds(917, 691, 117, 29);
		contentPane.add(btnBnsf);
		
		JLabel lblEntrees = new JLabel("Entrees");
		lblEntrees.setFont(new Font("Lucida Grande", Font.PLAIN, 30));
		lblEntrees.setBounds(519, 746, 117, 27);
		contentPane.add(lblEntrees);
		
		JButton btnFriedSteak = new JButton("Fried Steak");
		btnFriedSteak.setBounds(519, 803, 117, 29);
		contentPane.add(btnFriedSteak);
		
		JButton btnChickenStrips = new JButton("Chicken Strips");
		btnChickenStrips.setBounds(659, 803, 117, 29);
		contentPane.add(btnChickenStrips);
		
		JButton btnCodFilets = new JButton("Cod Filets");
		btnCodFilets.setBounds(803, 803, 117, 29);
		contentPane.add(btnCodFilets);
		
		JButton btnNewButton = new JButton("KC Strip");
		btnNewButton.setBounds(932, 803, 117, 29);
		contentPane.add(btnNewButton);
		
		JButton btnRibeye = new JButton("Ribeye");
		btnRibeye.setBounds(519, 844, 117, 29);
		contentPane.add(btnRibeye);
		
		JButton btnFishChips = new JButton("Fish & Chips");
		btnFishChips.setBounds(659, 844, 117, 29);
		contentPane.add(btnFishChips);
		
		JButton btnChickenParmesan = new JButton("Chicken Parm");
		btnChickenParmesan.setBounds(803, 844, 117, 29);
		contentPane.add(btnChickenParmesan);
		
		JButton btnMeatloaf = new JButton("Meatloaf");
		btnMeatloaf.setBounds(932, 844, 117, 29);
		contentPane.add(btnMeatloaf);
		
		JButton btnAlfredo = new JButton("Alfredo");
		btnAlfredo.setBounds(519, 885, 117, 29);
		contentPane.add(btnAlfredo);
		
		JButton btnChickenAlfredo = new JButton("Chicken Alfredo");
		btnChickenAlfredo.setBounds(659, 885, 132, 29);
		contentPane.add(btnChickenAlfredo);
		
		JButton btnShrimpAlfredo = new JButton("Shrimp Alfredo");
		btnShrimpAlfredo.setBounds(803, 885, 124, 29);
		contentPane.add(btnShrimpAlfredo);
		
		JLabel lblDrinks = new JLabel("Drinks");
		lblDrinks.setFont(new Font("Lucida Grande", Font.PLAIN, 30));
		lblDrinks.setBounds(1190, 133, 96, 25);
		contentPane.add(lblDrinks);
		
		JButton btnSoftDrink = new JButton("Soft Drink");
		btnSoftDrink.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		btnSoftDrink.setBounds(1178, 189, 117, 29);
		contentPane.add(btnSoftDrink);
		
		JButton btnCoffee = new JButton("Coffee");
		btnCoffee.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		btnCoffee.setBounds(1307, 189, 117, 29);
		contentPane.add(btnCoffee);
		
		JButton btnDecaf = new JButton("Decaf");
		btnDecaf.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		btnDecaf.setBounds(1436, 189, 117, 29);
		contentPane.add(btnDecaf);
		
		JButton btnTea = new JButton("Hot Tea");
		btnTea.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		btnTea.setBounds(1178, 230, 117, 29);
		contentPane.add(btnTea);
		
		JButton btnIcedTea = new JButton("Iced Tea");
		btnIcedTea.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		btnIcedTea.setBounds(1307, 230, 117, 29);
		contentPane.add(btnIcedTea);
		
		JButton btnSmMilk = new JButton("SM Milk");
		btnSmMilk.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		btnSmMilk.setBounds(1178, 271, 117, 29);
		contentPane.add(btnSmMilk);
		
		JButton btnLgMilk = new JButton("LG Milk");
		btnLgMilk.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		btnLgMilk.setBounds(1307, 271, 117, 29);
		contentPane.add(btnLgMilk);
		
		JButton btnSmOrange = new JButton("SM Orange");
		btnSmOrange.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		btnSmOrange.setBounds(1178, 312, 117, 29);
		contentPane.add(btnSmOrange);
		
		JButton btnLgOrange = new JButton("LG Orange");
		btnLgOrange.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		btnLgOrange.setBounds(1307, 312, 117, 29);
		contentPane.add(btnLgOrange);
		
		JButton btnSmApple = new JButton("SM Apple");
		btnSmApple.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		btnSmApple.setBounds(1178, 353, 117, 29);
		contentPane.add(btnSmApple);
		
		JButton btnLgApple = new JButton("LG Apple");
		btnLgApple.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		btnLgApple.setBounds(1307, 353, 117, 29);
		contentPane.add(btnLgApple);
		
		JButton btnSmCranberry = new JButton("SM Cranberry");
		btnSmCranberry.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		btnSmCranberry.setBounds(1178, 394, 124, 29);
		contentPane.add(btnSmCranberry);
		
		JButton btnLgCranberry = new JButton("LG Cranberry");
		btnLgCranberry.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		btnLgCranberry.setBounds(1307, 394, 124, 29);
		contentPane.add(btnLgCranberry);
		
		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setForeground(Color.DARK_GRAY);
		separator.setBackground(Color.WHITE);
		separator.setBounds(1099, 59, 12, 903);
		contentPane.add(separator);
	}

}
