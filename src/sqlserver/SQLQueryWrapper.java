/*******Package*******/
package sqlserver;


/*******Imports*******/
import java.util.ArrayList;
import java.sql.SQLException;

public class SQLQueryWrapper {
	
	
	/*******Static methods*******/
	
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
	
}
