
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
		//TODO: FINISH THIS
		return "PartTime";
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
	
}
