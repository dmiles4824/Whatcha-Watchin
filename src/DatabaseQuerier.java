
/* This class handles queries to our SQL/iMDB database. It then returns these queries to Java-friendly language. Fun for kids! */
import java.sql.*;


public class DatabaseQuerier {
		
	//DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver();
	


	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub	
		try {	
			Connection con = DriverManager.getConnection( "host", "username", "password" );
			}
			catch (SQLException e) {
				;
			}
	}
}



