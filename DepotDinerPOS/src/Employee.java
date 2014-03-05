import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Employee {
	
	public static enum EmployeePosition {
		Staff,
		Manager,
		Kitchen;
	}
	
	public static enum EmployeeType {
		FullTime,
		PartTime;
	}
	
	private final String FirstName;
	private final String LastName;
	private final EmployeePosition EPosition;
	private final EmployeeType EType;
	
	
	public Employee(String FirstName, String LastName, String EPos, String EType){
		this.FirstName = FirstName;
		this.LastName = LastName;
		switch (EPos) {
			case "Staff": 
				this.EPosition = EmployeePosition.Staff;
				break;
			case "Manager":
				this.EPosition = EmployeePosition.Manager;
				break;
			case "Kitchen":
				this.EPosition = EmployeePosition.Kitchen;
				break;
			default: 
				this.EPosition = EmployeePosition.Staff;
		}
		switch (EType) {
			case "FullTime": 
				this.EType = EmployeeType.FullTime;
				break;
			case "PartTime":
				this.EType = EmployeeType.PartTime;
				break;
			default: 
				this.EType = EmployeeType.PartTime;
	}
	}
	
	public String getFirstName(){
		return FirstName;
	}
	
	public String getLastName(){
		return LastName;
	}
	
	public EmployeeType getEmployeeType(){
		return EType;
	}
	
	public String getStringEmployeeType(){
		switch (EType) {
		case FullTime: 
			return "FullTime";
		case PartTime:
			return "PartTime";
		default: 
			return "PartTime";
		}
	}
	
	public EmployeePosition getEmployeePosition(){
		return EPosition;
	}
	
	public String getStringEmployeePosition(){
		switch (EPosition) {
			case Staff: 
				return "Staff";
			case Manager:
				return "Manager";
			case Kitchen:
				return "Kitchen";
			default: 
				return "Staff";
		}
	}
	
	public String getFullName(){
		return FirstName + " " + LastName;
	}
	
	public static Employee getEmployeeByPIN(String PIN) throws Exception{
		Statement state = DBConnection.OpenConnection();
		String commandstring = "SELECT * FROM Employees WHERE PIN = " + PIN + ";";
		String firstname = "";
		String lastname = "";
		String position = "";
		String type = "";
		if(state != null){
			try {
				ResultSet rs = state.executeQuery(commandstring);
				if(rs.next() == true) {
					firstname = rs.getString("FirstName");
					lastname = rs.getString("LastName");
					position = rs.getString("Position");
					type = rs.getString("Type");
					//System.out.println(firstname);
					//System.out.println(lastname);
					//System.out.println(position);
					//System.out.println(type);
				}
				else {
					throw new Exception("Employee not found");
				}
			} catch (SQLException e) {
				throw new Exception("Error in SQL Execution");
				}
		}
		else
			System.err.println("Statement was null.  No connection?");
		
		return new Employee(firstname, lastname, position, type);
	}
	
}
