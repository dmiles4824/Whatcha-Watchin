/*******Package*******/
package sqlserver;


/*******Imports*******/
import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLQueryWrapper {
	
	/*******Constants*******/
	private final static String defaultDatabaseLocation = "localhost:3306/wwschema";
	private final static String defaultUsername = "javabrianroot";
	private final static String defaultPassword = "biscotti";
	
	/*******Member fields*******/
	Statement statement;
	
	
	/*******Contstructors*******/
	
	public SQLQueryWrapper(String databaseLocation, String username, String password) throws SQLException{
		this.statement = SQLTools.connector(databaseLocation, username, password);
	}
	
	public SQLQueryWrapper() throws SQLException{
		this(defaultDatabaseLocation, defaultUsername, defaultPassword);
	}
	
	/*******Get/Set Methods*******/
	
	//s
	public Statement getStatement() {
		return statement;
	}

	public void setStatement(Statement statement) {
		this.statement = statement;
	}
	//e
	
	/*******Member methods*******/
	
	//Queries
	
	public boolean findUser(String username) throws SQLException {
		
		//Build query
		String query = SQLQueries.findUser(username);
		
		//Run query
		ResultSet rs = executeQuery(query);
		
		//Return results
		return rs.next();
	}
	
	public ArrayList<String> getUsersGroups(String username) throws SQLException{
		
		//Build query
		String query = SQLQueries.getUsersGroups(username);
		
		//Run query
		ResultSet rs = executeQuery(query);
		
		//Return results
		ArrayList<String> list = new ArrayList<String>();
		while(rs.next()){
			list.add(rs.getString(1));
		}
		
		return list;
	}
	
	//Updates
	
	//	User
	
	public boolean addUser(String username, String password) throws SQLException {
		
		//Build query
		String query = SQLQueries.addUser(username, password);
		
		//Run query
		int numChanged = executeUpdate(query);
		
		//Return result
		if(numChanged == 1) {
			return true;
		}
		else {
			return false;
		}
		
	}
	
	public boolean removeUser(String username) throws SQLException {
		
		//Build query
		String query = SQLQueries.removeUser(username);
		
		//Run query
		int numChanged = executeUpdate(query);
		
		//Return result
		if(numChanged == 1) {
			return true;
		}
		else {
			return false;
		}
		
	}
	
	
	
	/*******Helper methods*******/
	
	private ResultSet executeQuery(String query) throws SQLException{
		return getStatement().executeQuery(query);
	}
	
	private int executeUpdate(String query) throws SQLException {
		return getStatement().executeUpdate(query);
	}
	
	/*******Static methods*******/
	
	
}
