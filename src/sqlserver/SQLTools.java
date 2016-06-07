/*******Package*******/
package sqlserver;


/*******Imports*******/
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


public class SQLTools {
	
	
	/*******Static methods*******/
	
	
	/* Helper method that connects the program to the SQL database.
	 * @throws SQLException */
	protected static Statement connector(String databaseLocation, String username, String password) throws SQLException {
		
		System.out.println("		Entered connector method");
		
		Connection conn = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver"); 
			
			System.out.println("		Driver registered");
			
			//Brian-RP
			System.out.println("		jdbc:mysql://" + databaseLocation);
			
			conn = DriverManager.getConnection("jdbc:mysql://" + databaseLocation, username, password);
			
			System.out.println("		Connection retrieved");
		}
		
		catch (SQLException e) {
			throw e;
		}
		
		catch(Exception e){
			throw new SQLException();
		}
		
		return conn.createStatement();
	}
	
}
