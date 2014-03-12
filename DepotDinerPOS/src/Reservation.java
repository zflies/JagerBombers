import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

public class Reservation {
	private String Name;
	private Date Date;
	private int Size;
	private double Deposit;
	private String Shown;
	
	public static double SmallDeposit = 50.00;
	public static double LargeDeposit = 75.00;
	public static int SmallPartyLimit = 20;
	
	public Reservation(String name, Date date, int size){
		this.Name = name;
		this.Date = date;
		this.Size = size;
		this.Shown = "No";
		if(size <= SmallPartyLimit)
			this.Deposit = SmallDeposit;
		else
			this.Deposit = LargeDeposit;
	}
	
	public Reservation(String name, Date date, int size, String shown){
		this.Name = name;
		this.Date = date;
		this.Size = size;
		this.Shown = shown;
		if(size <= SmallPartyLimit)
			this.Deposit = SmallDeposit;
		else
			this.Deposit = LargeDeposit;
	}
	
	public String getName(){
		return Name;
	}
	
	public Date getDate(){
		return Date;
	}
	
	public int getSize(){
		return Size;
	}
	
	public double getDeposit(){
		return Deposit;
	}
	
	public String getShown(){
		return Shown;
	}

	public void addToDB() throws Exception {
		Statement state = DBConnection.OpenConnection();
		String commandstring = "INSERT INTO Reservations (Name, DateTime, Size, Deposit) "
				+ "VALUES ('" + this.Name + "', '" + new java.sql.Timestamp(this.getDate().getTime()) + "', " + this.Size + ",  " + this.Deposit + ");";
		System.out.println(commandstring);
		
		if(state != null){
			try {
				state.execute(commandstring);
			} catch (SQLException e) {
				System.out.println(e.getMessage());
				throw new Exception("Error in SQL Execution");
				}
		}
		else
			System.err.println("Statement was null.  No connection?");
		
		state.close();
	}
	
	public void updateShown() throws Exception{
		 //update order in DB to "served"
		this.Shown = "Yes";
        Statement state = DBConnection.OpenConnection();
		String commandstring = "UPDATE Reservations SET Shown = 'Yes' WHERE Name = '" + this.Name + "' AND Size = " + this.Size + " AND DateTime = '" + new java.sql.Timestamp(this.getDate().getTime()) + "' AND Shown = 'No';";
		
		if(state != null){
			try {
				state.execute(commandstring);
			} catch (SQLException e) {
				System.out.println(e.getMessage());
				throw new Exception("Error in SQL Execution");
				}
		}
		else
			System.err.println("Statement was null.  No connection?");
		
		state.close();
	}
	
	public static Vector<Reservation> getTodayReservations() throws Exception{
		Vector<Reservation> ReservationVector = new Vector<Reservation>();
		Statement state = DBConnection.OpenConnection();
		String commandstring = "SELECT * FROM Reservations WHERE DATE_FORMAT(DateTime, '%Y-%m-%d') = CURDATE() AND Shown = 'No'; ";
		String Name;
		Date Date;
		int Size;
		if(state != null){
			try {
				ResultSet rs = state.executeQuery(commandstring);
				while(rs.next()) {
					Name = rs.getString("Name");
					Date = new Date(rs.getTimestamp("DateTime").getTime());
					Size = rs.getInt("Size");
					Reservation cur_reservation = new Reservation(Name, Date, Size);
					ReservationVector.add(cur_reservation);
				}
			} catch (SQLException e) {
				System.out.println(e.getMessage());
				throw new Exception("Error in SQL Execution");
				}
		}
		else
			System.err.println("Statement was null.  No connection?");
		
		state.close();
		return ReservationVector;
	}
	
	public static Vector<Reservation> getAllReservations() throws Exception{
		Vector<Reservation> ReservationVector = new Vector<Reservation>();
		Statement state = DBConnection.OpenConnection();
		String commandstring = "SELECT * FROM Reservations;";
		String Name;
		Date Date;
		String Shown;
		int Size;
		if(state != null){
			try {
				ResultSet rs = state.executeQuery(commandstring);
				while(rs.next()) {
					Name = rs.getString("Name");
					Date = new Date(rs.getTimestamp("DateTime").getTime());
					Size = rs.getInt("Size");
					Shown = rs.getString("Shown");
					Reservation cur_reservation = new Reservation(Name, Date, Size, Shown);
					ReservationVector.add(cur_reservation);
				}
			} catch (SQLException e) {
				System.out.println(e.getMessage());
				throw new Exception("Error in SQL Execution");
				}
		}
		else
			System.err.println("Statement was null.  No connection?");
		
		state.close();
		return ReservationVector;
	}
}
