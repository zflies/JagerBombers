import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnection {
	static String username;
	static String password;
	static String configURL;

	
	public static void main(String[] args) {
		Statement state = OpenConnection();
		String commandstring = "SELECT * FROM Employees";
		if(state != null){
			try {
				ResultSet rs = state.executeQuery(commandstring);
				while (rs.next()){
					String firstname = rs.getString("FirstName");
					System.out.println(firstname);
					
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		else
			System.err.println("Statement was null.  No connection?");		
	}
	
	public static Statement OpenConnection(){
		try {
			ConfigHandler();
		} catch (IOException e1) {
			System.err.println("Error: " + e1.getMessage());
			System.err.println("Please place config.txt under your project Root folder.");
		}
		
		//String url = "jdbc:mysql://" + configURL + username; //server + database string
		//String url = "jdbc:mysql://localhost/" + username; //***This is connecting to the local MySQL server

		/* This is for Zach */
		username = "root";
		password = "";
		String url = "jdbc:mysql://localhost/" + "avalenti"; 
	    
		
		Statement stmt = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}
		try {
			Connection con = DriverManager.getConnection(url, username, password); //creates a connection with the server
			stmt = con.createStatement();
			}
		catch (SQLException e) {
				System.out.println(e.getMessage());
		}
		return(stmt);
		
	}
	
	public static void CloseConnection(ResultSet rs, Statement stmt){
		try {
			if (rs == null){
				stmt.close();
			}
			else{
				rs.close();
				stmt.close();
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
	}
	
	public static void CloseConnection(ResultSet rs){
		try {
			rs.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
	}
	
	private static void ConfigHandler() throws IOException{
		
		FileInputStream fis = new FileInputStream("DBConfig.txt");
		DataInputStream in = new DataInputStream(fis);
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		String strLine;
		
		while((strLine = br.readLine()) != null){
			if(strLine.contains("username") && !strLine.contains("*")){
				strLine = strLine.substring(9);
				while(strLine.charAt(0) == ' '){
					strLine = strLine.substring(1);
				}
				username = strLine;
			}
			else if(strLine.contains("password") && !strLine.contains("*")){
				strLine = strLine.substring(9);
				while(strLine.charAt(0) == ' '){
					strLine = strLine.substring(1);
				}
				password = strLine;
			}
			else if(strLine.contains("url") && !strLine.contains("*")){
				strLine = strLine.substring(4);
				while(strLine.charAt(0) == ' '){
					strLine = strLine.substring(1);
				}
				configURL = strLine;
			}
		}
		in.close();
		
	}
	
}
