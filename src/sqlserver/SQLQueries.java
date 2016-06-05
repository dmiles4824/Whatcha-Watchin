/*******Package*******/

package sqlserver;


/*******Imports*******/
import java.sql.*;
import java.util.ArrayList;
import com.mysql.jdbc.Driver;

public class SQLQueries {
	
	
	
	/*******Static methods*******/
	
	public static ArrayList<String> getUsersGroups(String username) throws SQLException{
		
		//Retrieve a connection
		Statement stmt = connector();
		
		//Form query
		String query = 	"select `group_name` "
						+ "from `Group` G, `Member` M "
						+ "where G.`group_id` = M.`group_id` AND "
						+		"M.username = " + username
						+ ";"
						;
		
		System.out.println("Query is:\n" + query);
		
		//Execute query
		ResultSet rs = stmt.executeQuery(query);
		
		//Prepare to read results
		ArrayList<String> list = new ArrayList<String>();
		
		//Read Results
		while(rs.next()){
			list.add(rs.getString(0));
		}
		
		return list;
	}
	
	
	/* Helper method that connects the program to the SQL database.
	 * @throws SQLException */
	private static Statement connector() throws SQLException {
		
		Connection conn = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver"); 
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/wwschema", "root", "M05sokker~"); 
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
