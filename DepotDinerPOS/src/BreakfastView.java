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
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;


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
		setBounds(100, 100, 1643, 878);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		String[] tables = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15",
				"16", "17", "18", "19", "20", "21", "22", "23", "24", "25"};
		
		JLabel lblTable = new JLabel("Table");
		lblTable.setFont(new Font("Lucida Grande", Font.PLAIN, 30));
		JComboBox comboBox = new JComboBox(tables);
		comboBox.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		
		final JTextPane textPane = new JTextPane();
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setForeground(Color.BLACK);
		
		JButton btnCreate = new JButton("Create");
		
		JLabel lblCombinations = new JLabel("Combinations");
		lblCombinations.setFont(new Font("Lucida Grande", Font.PLAIN, 30));
		lblCombinations.setHorizontalAlignment(SwingConstants.LEFT);
		
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
		
		JButton btnSausageEggs = new JButton("Sausage & Eggs");
		btnSausageEggs.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		
		JButton btnHamEggs = new JButton("Ham & Eggs");
		btnHamEggs.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		
		JLabel lblOmelets = new JLabel("Omelets");
		lblOmelets.setFont(new Font("Lucida Grande", Font.PLAIN, 30));
		
		JButton btnDenver = new JButton("Denver");
		btnDenver.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		
		JButton btnCheese = new JButton("Cheese");
		btnCheese.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		
		JButton btnVeggie = new JButton("Veggie");
		btnVeggie.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		
		JButton btnSouthwest = new JButton("Southwest");
		btnSouthwest.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		
		JLabel lblSpecialties = new JLabel("Specialties");
		lblSpecialties.setFont(new Font("Lucida Grande", Font.PLAIN, 30));
		
		JButton btnExpress = new JButton("Express");
		btnExpress.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		
		JButton btnExpress2Egg = new JButton("Express 2 Egg");
		btnExpress2Egg.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		
		JButton btnFrenchToast = new JButton("French Toast");
		btnFrenchToast.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		
		JButton btnTripStack = new JButton("Trip Stack");
		btnTripStack.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		
		JButton btnTripStackBlue = new JButton("Trip Stack Blue");
		btnTripStackBlue.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		
		JButton btnBiscGravy = new JButton("Biscs & Gravy");
		btnBiscGravy.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		
		JButton btnWaffle = new JButton("Waffle");
		btnWaffle.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		
		JButton btnWaffleCombo = new JButton("Waffle Combo");
		btnWaffleCombo.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		
		JLabel lblSides = new JLabel("Sides");
		lblSides.setFont(new Font("Lucida Grande", Font.PLAIN, 30));
		
		JButton btnHashBrown = new JButton("Hash Brown");
		btnHashBrown.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		
		JButton btnCinnamonRoll = new JButton("Cinnamon Roll");
		btnCinnamonRoll.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		
		JButton btnBacon = new JButton("Bacon");
		btnBacon.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		
		JButton btnSausage = new JButton("Sausage");
		btnSausage.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		
		JButton btnBiscuitGravy = new JButton("Biscuit & Gravy");
		btnBiscuitGravy.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		
		JButton btnToast = new JButton("Toast");
		btnToast.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		
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
		
		JButton btnSteakAndEggs = new JButton("Steak & Eggs");
		btnSteakAndEggs.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		
		JButton btnWaffleBlue = new JButton("Waffle Blue");
		btnWaffleBlue.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		
		lblTotal = new JLabel("Total: $0.00");
		lblTotal.setFont(new Font("Lucida Grande", Font.PLAIN, 30));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(5)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(44)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(btnCancel, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(lblTotal, GroupLayout.PREFERRED_SIZE, 321, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED, 0, Short.MAX_VALUE))))
						.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
							.addGap(42)
							.addComponent(textPane, GroupLayout.PREFERRED_SIZE, 323, GroupLayout.PREFERRED_SIZE)
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
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
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
										.addComponent(btnLgCranberry, GroupLayout.PREFERRED_SIZE, 124, GroupLayout.PREFERRED_SIZE)))
								.addComponent(lblDrinks, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE))))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(btnDecaf, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnCreate, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE))
					.addGap(85))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(44)
					.addComponent(lblTable, GroupLayout.PREFERRED_SIZE, 87, GroupLayout.PREFERRED_SIZE)
					.addGap(138)
					.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(1268, Short.MAX_VALUE))
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
							.addGap(7)
							.addComponent(textPane, GroupLayout.DEFAULT_SIZE, 574, Short.MAX_VALUE)
							.addGap(1))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblCombinations)
								.addComponent(lblDrinks, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
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
							.addGap(67)
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
					.addGap(23)
					.addComponent(lblTotal, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
					.addGap(30)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnCancel, GroupLayout.PREFERRED_SIZE, 61, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnCreate, GroupLayout.PREFERRED_SIZE, 61, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		contentPane.setLayout(gl_contentPane);
	}
	private void updateTotal(double itemPrice){
		String totalText = lblTotal.getText();
		String[] totalSplit = totalText.split("\\$");
		double currentTotal = Double.parseDouble(totalSplit[1]);
		currentTotal += itemPrice;
		lblTotal.setText("Total: \t $" + String.valueOf(currentTotal));
	}
}
