/*******Package*******/

package sqlserver;


/*******Imports*******/
import java.sql.*;

public class SQLQueries {
	
	
	
	/*******Static methods*******/
	
	
	//Queries
	
	public static String findUser(String username) {
		
		String query = 	  "select * "
						+ "from `User` "
						+ "where `username` = '"
						+ username
						+ "'"
						+ ";"
						;
		
		return query;
	}
	
	public static String getUsersGroups(String username) {
		
		//Form query
		String query = 	"select `group_name` "
						+ "from `Group` G, `Member` M "
						+ "where G.`group_id` = M.`group_id` AND "
						+		"M.username = '" + username + "'"
						+ ";"
						;
		
		return query;
	}
	
	
	//Updates
	
	
	//	User
	
	public static String addUser(String username, String password) throws SQLException{
		
		String query = "insert into `User` (`username`, `password`) values ('"
						+ username
						+ "', '"
						+ password
						+ "')"
						+ ";"
						;
		
		return query;
	}
	
	public static String removeUser(String username) throws SQLException {
		
		String query = 	  "delete from `User` "
						+ "where `username` = '"
						+ username
						+ "' limit 1"
						+ ";"
						;
		
		return query;
	}
	
	//Group
	
	public static String addGroup(String group_name) throws SQLException{
		
		String query = "insert into `Group` (`group_name`) values ('"
				+ group_name
				+ "')"
				+ ";"
				;
				
		return query;
		
	}
	
	
}
