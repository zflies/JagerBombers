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


public class BreakfastView extends JFrame {

	private JPanel contentPane;
	private JLabel lblTotal;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BreakfastView frame = new BreakfastView();
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
	public BreakfastView() {
		setTitle("Steve's Depot Diner");
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 796, 675);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
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
		
		final JTextPane textPane = new JTextPane();
		textPane.setBounds(47, 129, 323, 700);
		contentPane.add(textPane);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setForeground(Color.BLACK);
		btnCancel.setBounds(47, 926, 117, 61);
		contentPane.add(btnCancel);
		
		JButton btnCreate = new JButton("Create");
		btnCreate.setBounds(1540, 926, 117, 61);
		contentPane.add(btnCreate);
		
		JLabel lblCombinations = new JLabel("Combinations");
		lblCombinations.setFont(new Font("Lucida Grande", Font.PLAIN, 30));
		lblCombinations.setHorizontalAlignment(SwingConstants.LEFT);
		lblCombinations.setBounds(519, 122, 212, 36);
		contentPane.add(lblCombinations);
		
		JButton btnBaconEggs = new JButton("Bacon & Eggs");
		btnBaconEggs.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				StyledDocument doc = textPane.getStyledDocument();
				SimpleAttributeSet keyWord = new SimpleAttributeSet();
				try {
					doc.insertString(doc.getLength(), "Bacon and Eggs \t $6.25\n", keyWord);
					updateTotal(6.25);
				} catch (BadLocationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnBaconEggs.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		btnBaconEggs.setBounds(519, 190, 124, 27);
		contentPane.add(btnBaconEggs);
		
		JButton btnSausageEggs = new JButton("Sausage & Eggs");
		btnSausageEggs.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		btnSausageEggs.setBounds(659, 189, 143, 29);
		contentPane.add(btnSausageEggs);
		
		JButton btnHamEggs = new JButton("Ham & Eggs");
		btnHamEggs.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		btnHamEggs.setBounds(814, 189, 113, 29);
		contentPane.add(btnHamEggs);
		
		JLabel lblOmelets = new JLabel("Omelets");
		lblOmelets.setFont(new Font("Lucida Grande", Font.PLAIN, 30));
		lblOmelets.setBounds(519, 251, 124, 23);
		contentPane.add(lblOmelets);
		
		JButton btnDenver = new JButton("Denver");
		btnDenver.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		btnDenver.setBounds(519, 310, 124, 29);
		contentPane.add(btnDenver);
		
		JButton btnCheese = new JButton("Cheese");
		btnCheese.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		btnCheese.setBounds(655, 310, 117, 29);
		contentPane.add(btnCheese);
		
		JButton btnVeggie = new JButton("Veggie");
		btnVeggie.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		btnVeggie.setBounds(784, 310, 117, 29);
		contentPane.add(btnVeggie);
		
		JButton btnSouthwest = new JButton("Southwest");
		btnSouthwest.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		btnSouthwest.setBounds(913, 310, 117, 29);
		contentPane.add(btnSouthwest);
		
		JLabel lblSpecialties = new JLabel("Specialties");
		lblSpecialties.setFont(new Font("Lucida Grande", Font.PLAIN, 30));
		lblSpecialties.setBounds(519, 351, 160, 43);
		contentPane.add(lblSpecialties);
		
		JButton btnExpress = new JButton("Express");
		btnExpress.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		btnExpress.setBounds(519, 418, 117, 29);
		contentPane.add(btnExpress);
		
		JButton btnExpress2Egg = new JButton("Express 2 Egg");
		btnExpress2Egg.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		btnExpress2Egg.setBounds(659, 418, 132, 29);
		contentPane.add(btnExpress2Egg);
		
		JButton btnFrenchToast = new JButton("French Toast");
		btnFrenchToast.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		btnFrenchToast.setBounds(803, 418, 132, 29);
		contentPane.add(btnFrenchToast);
		
		JButton btnTripStack = new JButton("Trip Stack");
		btnTripStack.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		btnTripStack.setBounds(519, 459, 117, 29);
		contentPane.add(btnTripStack);
		
		JButton btnTripStackBlue = new JButton("Trip Stack Blue");
		btnTripStackBlue.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		btnTripStackBlue.setBounds(659, 459, 132, 29);
		contentPane.add(btnTripStackBlue);
		
		JButton btnBiscGravy = new JButton("Biscs & Gravy");
		btnBiscGravy.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		btnBiscGravy.setBounds(803, 459, 132, 29);
		contentPane.add(btnBiscGravy);
		
		JButton btnWaffle = new JButton("Waffle");
		btnWaffle.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		btnWaffle.setBounds(519, 500, 117, 29);
		contentPane.add(btnWaffle);
		
		JButton btnWaffleCombo = new JButton("Waffle Combo");
		btnWaffleCombo.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		btnWaffleCombo.setBounds(803, 500, 132, 29);
		contentPane.add(btnWaffleCombo);
		
		JLabel lblSides = new JLabel("Sides");
		lblSides.setFont(new Font("Lucida Grande", Font.PLAIN, 30));
		lblSides.setBounds(519, 568, 87, 27);
		contentPane.add(lblSides);
		
		JButton btnHashBrown = new JButton("Hash Brown");
		btnHashBrown.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		btnHashBrown.setBounds(519, 618, 117, 29);
		contentPane.add(btnHashBrown);
		
		JButton btnCinnamonRoll = new JButton("Cinnamon Roll");
		btnCinnamonRoll.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		btnCinnamonRoll.setBounds(655, 619, 136, 29);
		contentPane.add(btnCinnamonRoll);
		
		JButton btnBacon = new JButton("Bacon");
		btnBacon.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		btnBacon.setBounds(803, 619, 117, 29);
		contentPane.add(btnBacon);
		
		JButton btnSausage = new JButton("Sausage");
		btnSausage.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		btnSausage.setBounds(519, 659, 117, 29);
		contentPane.add(btnSausage);
		
		JButton btnBiscuitGravy = new JButton("Biscuit & Gravy");
		btnBiscuitGravy.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		btnBiscuitGravy.setBounds(655, 660, 136, 29);
		contentPane.add(btnBiscuitGravy);
		
		JButton btnToast = new JButton("Toast");
		btnToast.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		btnToast.setBounds(803, 660, 117, 29);
		contentPane.add(btnToast);
		
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
		
		JButton btnSteakAndEggs = new JButton("Steak & Eggs");
		btnSteakAndEggs.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		btnSteakAndEggs.setBounds(939, 189, 117, 29);
		contentPane.add(btnSteakAndEggs);
		
		JButton btnWaffleBlue = new JButton("Waffle Blue");
		btnWaffleBlue.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		btnWaffleBlue.setBounds(659, 500, 132, 29);
		contentPane.add(btnWaffleBlue);
		
		lblTotal = new JLabel("Total: $0.00");
		lblTotal.setFont(new Font("Lucida Grande", Font.PLAIN, 30));
		lblTotal.setBounds(49, 862, 321, 27);
		contentPane.add(lblTotal);
	}
	private void updateTotal(double itemPrice){
		String totalText = lblTotal.getText();
		String[] totalSplit = totalText.split("\\$");
		double currentTotal = Double.parseDouble(totalSplit[1]);
		currentTotal += itemPrice;
		lblTotal.setText("Total: \t $" + String.valueOf(currentTotal));
	}
}
