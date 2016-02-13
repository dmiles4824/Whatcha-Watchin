
/* This class handles queries to our SQL/iMDB database. It then returns these queries to Java-friendly language. Fun for kids! */
import java.sql.*;


public class DatabaseQuerier {
	
	public static int dbWriteUser(String change, String username) {
		try {
			Connection conn = DriverManager.getConnection(
					"jdbc:mysql\\localhost:3306/WWDB", "root", "M05sokker~");
					
			Statement stmt = conn.createStatement();
			
			String strUpdate = "update WWDB set username = " + change + " where username = " + username;
			System.out.println("The SQL query is: " + strUpdate);
			int countUpdated = stmt.executeUpdate(strUpdate);
			System.out.println(countUpdated + " records affected.");
			
			String strSelect = "select * from WWDB where username = " + change;
			System.out.println("The SQL query is: " + strSelect);
			ResultSet rset = stmt.executeQuery(strSelect);
			while(rset.next()) {
				System.out.println(rset.getString("username"));
				}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return 0;
	}
		
	
	public static void main(String[] args) {
	
		try {	
			String host = "";
			String uName = "Your_Username";
			String pass = "Your_Pass";
			Connection conn = DriverManager.getConnection( "jdbc:mysql\\localhost:3306/WWDB", "root", "M05sokker~" );
			
			Statement stmt = conn.createStatement();
			
			String strSelect = "Select username from user";
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



