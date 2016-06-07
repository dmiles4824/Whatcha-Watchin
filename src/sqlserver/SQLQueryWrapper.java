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
	
	//	Find
	
	public boolean findUser(String username) throws SQLException {
		
		//Build query
		String query = SQLQueries.findUser(username);
		
		//Run query
		ResultSet rs = executeQuery(query);
		
		//Return results
		return rs.next();
	}
	
	public boolean findGroup(int group_id) throws SQLException {
		
		//Build query
		String query = SQLQueries.findGroup(group_id);
		
		//Run query
		ResultSet rs = executeQuery(query);
		
		//Return results
		return rs.next();
	}
	
	public boolean findMovie(String title, int year) throws SQLException {
		
		//Build query
		String query = SQLQueries.findMovie(title, year);
		
		//Run query
		ResultSet rs = executeQuery(query);
		
		//Return results
		return rs.next();
	}
	
	public boolean findMember(String username, int group_id) throws SQLException {
		
		//Build query
		String query = SQLQueries.findMember(username, group_id);
		
		//Run query
		ResultSet rs = executeQuery(query);
		
		//Return results
		return rs.next();
	}
	
	//
	
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
	
	public ArrayList<String> getUsersInGroup(int group_id) throws SQLException{
		
		//Build query
		String query = SQLQueries.getUsersInGroup(group_id);
		
		//Run query
		ResultSet rs = executeQuery(query);
		
		//Return results
		ArrayList<String> list = new ArrayList<String>();
		while(rs.next()){
			list.add(rs.getString(1));
		}
		
		return list;
	}

	public String getGroupName(int group_id) throws SQLException{
		
		//Build query
		String query = SQLQueries.getGroupName(group_id);
		
		//Run query
		ResultSet rs = executeQuery(query);
		
		//Return results
		if(rs.next()){
			return rs.getString(1);
		}
		else{
			return "";
		}
		
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
	
	//	Group
	
	public boolean addGroup(String group_name) throws SQLException {
		
		//Build query
		String query = SQLQueries.addGroup(group_name);
		
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
	
	public boolean removeGroup(int group_id) throws SQLException {
		
		//Build query
		String query = SQLQueries.removeGroup(group_id);
		
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
	
	//	Movie
	
	public boolean addMovie(String title, int year) throws SQLException {
		
		//Build query
		String query = SQLQueries.addMovie(title, year);
		
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
	
	public boolean removeMovie(String title, int year) throws SQLException {
		
		//Build query
		String query = SQLQueries.removeMovie(title, year);
		
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
	
	//	Member
	
	public boolean addMember(String username, int group_id) throws SQLException {
		
		//Build query
		String query = SQLQueries.addMember(username, group_id);
		
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
	
	public boolean removeMember(String username, int group_id) throws SQLException {
		
		//Build query
		String query = SQLQueries.removeMember(username, group_id);
		
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
