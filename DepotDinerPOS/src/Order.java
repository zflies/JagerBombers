import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

public class Order {
	public static enum Status {
		Entered,
		Served,
		Paid;
	}
	
	private int OrderId;
	private int EmployeePin;
	private int TableNumber;
	private String Items;
	private Status State;
	private double Total;
	
	public Order(int orderId, int employeePin, int tableNumber, String items, String status, double total){
		this.OrderId = orderId;
		this.EmployeePin = employeePin;
		this.TableNumber = tableNumber;
		this.Items = items;
		if(status == "entered")
			this.State = Status.Entered;
		else if(status == "paid")
			this.State = Status.Paid;
		else if(status == "served")
			this.State = Status.Served;
		else 
			this.State = Status.Entered;
		this.Total = total;
	}
	
	public int getOrderId(){
		return this.OrderId;
	}
	
	public int getEmployeePin(){
		return this.EmployeePin;
	}
	
	public int getTableNumber(){
		return this.TableNumber;
	}
	
	public String getItems(){
		return this.Items;
	}
	
	public Status getStatus(){
		return this.State;
	}
	
	public String getStringStatus(){
		switch(this.State){
			case Entered:
				return "Entered";
			case Served:
					return "Served";
			case Paid:
					return "Paid";
			default:
					return null;
		}
	}
	
	public double getTotal(){
		return this.Total;
	}
	
	public void setItems(String items){
		this.Items = items;
	}
	
	public void setTableNumber(int newTableNumber){
		this.TableNumber = newTableNumber;
	}
	
	public void setStatus(Status newStatus){
		this.State = newStatus;
	}
	
	/**
	 * Returns all orders for the logged in employee that have a status of paid.
	 * @param loggedInEmployee
	 * @return OrderVector
	 * @throws Exception
	 */
	public static Vector<Order> getEmployeePaidOrders( Employee loggedInEmployee ) throws Exception {
		
		Vector<Order> OrderVector = new Vector<Order>();
		Statement state = DBConnection.OpenConnection();
		String commandstring = "SELECT * FROM Orders WHERE Status = 'paid'" +
							   						"AND E_PIN = (SELECT PIN FROM Employees " + 
							   										"WHERE FirstName = '" + loggedInEmployee.getFirstName() +"' " +
							   										"AND LastName = '" + loggedInEmployee.getLastName() + "');";
		int ID = 0;
		int E_PIN = 0;
		int Table_No = 0;
		String Items = "";
		String Status = "";
		double Total = 0.0;
		if(state != null){
			try {
				ResultSet rs = state.executeQuery(commandstring);
				while(rs.next()) {
					ID = rs.getInt("ID");
					E_PIN = rs.getInt("E_PIN");
					Table_No = rs.getInt("Table_No");
					Items = rs.getString("Items");
					Status = rs.getString("Status");
					Total = rs.getDouble("Total");
					Order new_order = new Order(ID, E_PIN, Table_No, Items, Status, Total);
					OrderVector.add(new_order);
				}
				if(OrderVector.size() == 0) {
					throw new Exception("No Pending Orders");
				}
			} catch (SQLException e) {
				System.out.println(e.getMessage());
				throw new Exception("Error in SQL Execution");
				}
		}
		else
			System.err.println("Statement was null.  No connection?");
		
		state.close();
		return OrderVector;
	}
	
	
/**
 * Returns all orders for the logged in employee that have a status of entered or served.
 * @param loggedInEmployee
 * @return OrderVector
 * @throws Exception
 */
	public static Vector<Order> getEmployeeOrdersInProgress( Employee loggedInEmployee ) throws Exception {
		
		Vector<Order> OrderVector = new Vector<Order>();
		Statement state = DBConnection.OpenConnection();
		String commandstring = "SELECT * FROM Orders WHERE Status = 'entered' OR Status = 'servered' " +
							   						"AND E_PIN = (SELECT PIN FROM Employees " + 
							   										"WHERE FirstName = '" + loggedInEmployee.getFirstName() +"' " +
							   										"AND LastName = '" + loggedInEmployee.getLastName() + "');";
		int ID = 0;
		int E_PIN = 0;
		int Table_No = 0;
		String Items = "";
		String Status = "";
		double Total = 0.0;
		if(state != null){
			try {
				ResultSet rs = state.executeQuery(commandstring);
				while(rs.next()) {
					ID = rs.getInt("ID");
					E_PIN = rs.getInt("E_PIN");
					Table_No = rs.getInt("Table_No");
					Items = rs.getString("Items");
					Status = rs.getString("Status");
					Total = rs.getDouble("Total");
					Order new_order = new Order(ID, E_PIN, Table_No, Items, Status, Total);
					OrderVector.add(new_order);
				}
				if(OrderVector.size() == 0) {
					throw new Exception("No Pending Orders");
				}
			} catch (SQLException e) {
				System.out.println(e.getMessage());
				throw new Exception("Error in SQL Execution");
				}
		}
		else
			System.err.println("Statement was null.  No connection?");
		
		state.close();
		return OrderVector;
	}
	
	public static Vector<Order> getEnteredOrders() throws Exception{
		Vector<Order> OrderVector = new Vector<Order>();
		Statement state = DBConnection.OpenConnection();
		String commandstring = "SELECT * FROM Orders WHERE Status = 'entered'; ";//AND E_PIN = {SELECT PIN FROM Employee WHERE FirstName = fname AND LastName = lastname;};";
		int ID = 0;
		int E_PIN = 0;
		int Table_No = 0;
		String Items = "";
		String Status = "";
		double Total = 0.0;
		if(state != null){
			try {
				ResultSet rs = state.executeQuery(commandstring);
				while(rs.next()) {
					ID = rs.getInt("ID");
					E_PIN = rs.getInt("E_PIN");
					Table_No = rs.getInt("Table_No");
					Items = rs.getString("Items");
					Status = rs.getString("Status");
					Total = rs.getDouble("Total");
					Order new_order = new Order(ID, E_PIN, Table_No, Items, Status, Total);
					OrderVector.add(new_order);
				}
				if(OrderVector.size() == 0) {
					throw new Exception("No Pending Orders");
				}
			} catch (SQLException e) {
				System.out.println(e.getMessage());
				throw new Exception("Error in SQL Execution");
				}
		}
		else
			System.err.println("Statement was null.  No connection?");
		
		state.close();
		return OrderVector;
	}
	
	public static Vector<Order> getServedOrders() throws Exception{
		Vector<Order> OrderVector = new Vector<Order>();
		Statement state = DBConnection.OpenConnection();
		String commandstring = "SELECT * FROM Orders WHERE Status = 'served';";
		int ID = 0;
		int E_PIN = 0;
		int Table_No = 0;
		String Items = "";
		String Status = "";
		double Total = 0.0;
		if(state != null){
			try {
				ResultSet rs = state.executeQuery(commandstring);
				while(rs.next()) {
					ID = rs.getInt("ID");
					E_PIN = rs.getInt("E_PIN");
					Table_No = rs.getInt("Table_No");
					Items = rs.getString("Items");
					Status = rs.getString("Status");
					Total = rs.getDouble("Total");
					Order new_order = new Order(ID, E_PIN, Table_No, Items, Status, Total);
					OrderVector.add(new_order);
				}
				if(OrderVector.size() == 0) {
					throw new Exception("No Pending Orders");
				}
			} catch (SQLException e) {
				System.out.println(e.getMessage());
				throw new Exception("Error in SQL Execution");
				}
		}
		else
			System.err.println("Statement was null.  No connection?");
		
		state.close();
		return OrderVector;
	}
	
	public static Vector<Order> getPaidOrders() throws Exception{
		Vector<Order> OrderVector = new Vector<Order>();
		Statement state = DBConnection.OpenConnection();
		String commandstring = "SELECT * FROM Orders WHERE Status = 'paid';";
		int ID = 0;
		int E_PIN = 0;
		int Table_No = 0;
		String Items = "";
		String Status = "";
		double Total = 0.0;
		if(state != null){
			try {
				ResultSet rs = state.executeQuery(commandstring);
				while(rs.next()) {
					ID = rs.getInt("ID");
					E_PIN = rs.getInt("E_PIN");
					Table_No = rs.getInt("Table_No");
					Items = rs.getString("Items");
					Status = rs.getString("Status");
					Total = rs.getDouble("Total");
					Order new_order = new Order(ID, E_PIN, Table_No, Items, Status, Total);
					OrderVector.add(new_order);
				}
				if(OrderVector.size() == 0) {
					throw new Exception("No Pending Orders");
				}
			} catch (SQLException e) {
				System.out.println(e.getMessage());
				throw new Exception("Error in SQL Execution");
				}
		}
		else
			System.err.println("Statement was null.  No connection?");
		
		state.close();
		return OrderVector;
	}
}
