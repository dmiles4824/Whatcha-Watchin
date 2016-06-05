/*******Package*******/
package sqlserver;


/*******Imports*******/
import java.util.ArrayList;
import java.sql.SQLException;

public class SQLQueryWrapper {
	
	
	/*******Static methods*******/
	
	
	//Queries
	
	
	public static String getUsersGroups(String username) throws SQLException{
		
		ArrayList<String> list = SQLQueries.getUsersGroups(username);
		
		StringBuilder builder = new StringBuilder();
		
		if(list.size() == 0){
			builder.append("User is not in any groups.");
		}
		else {
			for(String s : list){
				builder.append(s + "\n");
			}
		}
		
		return builder.toString();
	}
	
	
	//Updates
	
	public static String addUser(String username, String password) throws SQLException {
		
		int result = SQLQueries.addUser(username, password);
		
		if(result == 0){
			return "Could not add specified user.";
		}
		else {
			return "User " + username + " successfully added.";
		}
		
	}
	
	public static String removeUser(String username) throws SQLException {
		
		int result = SQLQueries.removeUser(username);
		
		if(result == 0) {
			return "No such user";
		}
		else {
			return "User " + username + " successfully removed.";
		}
	}
	
}
