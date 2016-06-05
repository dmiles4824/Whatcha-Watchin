/*******Package*******/

package sqlserver;


/*******Imports*******/
import java.sql.*;
import java.util.ArrayList;

public class SQLQueries {
	
	
	
	/*******Static methods*******/
	
	
	//Queries
	
	public static ArrayList<String> getUsersGroups(String username) throws SQLException{
		
		//Form query
		String query = 	"select `group_name` "
						+ "from `Group` G, `Member` M "
						+ "where G.`group_id` = M.`group_id` AND "
						+		"M.username = '" + username + "'"
						+ ";"
						;
		
		return SQLTools.oneDQuery(query);
	}
	
	
	//Updates
	
	
	//	User
	
	public static int addUser(String username, String password) throws SQLException{
		
		String query = "insert into `User` (`username`, `password`) values ('"
						+ username
						+ "', '"
						+ password
						+ "')"
						+ ";"
						;
		
		return SQLTools.voidUpdate(query);
	}
	
	public static int removeUser(String username) throws SQLException {
		
		String query = 	  "delete from `User`"
						+ "where `username` = "
						+ username
						+ " limit 1"
						+ ";"
						;
		
		return SQLTools.voidUpdate(query);
	}
	
	//public static void addGroup(String group_name) throws SQLException
	
}
