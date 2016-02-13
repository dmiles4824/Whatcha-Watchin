
/* This class handles queries to our SQL/iMDB database. It then returns these queries to Java-friendly language. Fun for kids! */
import java.sql.*;


public class DatabaseQuerier {
		
	
	public static void main(String[] args) {
	
		try {	
			String host = "";
			String uName = "Your_Username";
			String pass = "Your_Pass";
			Connection con = DriverManager.getConnection( "host", "username", "password" );
			
			Statement stmt = conn.createStatement();
			
			String srtSelect = "Select username from user";
			System.out.println("The SQL query is: " + strSelect); //Echo for debugging :D
			System.out.println();
			
			ResultSet rset = stmt.executeQuery(strSelect);
			
			System.out.println("The usernames selected are:");
			int rowCount = 0;
			while (rset.next()) {
				String username = rset.getString("username");
				System.out.println(username);
				++rowCount;
			}
			System.out.println("Total number of users = " + rowCount);
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
	}
}



