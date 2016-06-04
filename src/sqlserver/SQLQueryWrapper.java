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
		for(String s : list){
			builder.append(s + "\n");
		}
		
		return builder.toString();
	}
	
}
