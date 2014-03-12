import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Reservation {
	private String Name;
	private Date Date;
	private int Size;
	private double Deposit;
	
	public static double SmallDeposit = 50.00;
	public static double LargeDeposit = 75.00;
	public static int SmallPartyLimit = 20;
	
	public Reservation(String name, Date date, int size){
		this.Name = name;
		this.Date = date;
		this.Size = size;
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
}
