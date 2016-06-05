/*******Package*******/
package sqlserver;


/*******Imports*******/
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class SQLTools {
	
	
	/*******Static methods*******/
	
	protected static ArrayList<String> oneDQuery(String query) throws SQLException{
		
		//Retrieve a connection
		Statement stmt = connector();
		
		//Execute query
		ResultSet rs = stmt.executeQuery(query);
		
		//Prepare to read results
		ArrayList<String> list = new ArrayList<String>();
		
		//Read results
		while(rs.next()){
			list.add(rs.getString(1));
		}
		
		return list;
		
	}
	
	protected static boolean voidQuery(String query) throws SQLException{	
		return connector().execute(query);
	}
	
	protected static int voidUpdate(String query) throws SQLException{
		return connector().executeUpdate(query);
	}
	
	
	/* Helper method that connects the program to the SQL database.
	 * @throws SQLException */
	protected static Statement connector() throws SQLException {
		
		Connection conn = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver"); 
			
			//Brian-RP
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/wwschema", "root", "M05sokker~");
			
			//Brian-LT
			//conn = DriverManager.getConnection("jdbc:mysql://192.168.1.24:3306/wwschema", "javabrianroot", "biscotti");
			 
		}
		
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		catch(Exception e){
			e.printStackTrace();
		}
		
		return conn.createStatement();
	}
	
}
