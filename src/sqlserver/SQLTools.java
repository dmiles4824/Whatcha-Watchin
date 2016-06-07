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
		
		Connection conn = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver"); 
			
			//Brian-RP
			conn = DriverManager.getConnection("jdbc:mysql://" + databaseLocation, username, password);
			 
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
