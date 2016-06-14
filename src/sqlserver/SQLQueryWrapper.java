/*******Package*******/
package sqlserver;


/*******Imports*******/
import java.util.ArrayList;

import sqlserver.sqlobjects.SQLDatabase;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLQueryWrapper {
	
	/*******Constants*******/
	
	
	/*******Member fields*******/
	Statement statement;
	SQLDatabase database;
	
	/*******Contstructors*******/
	
	public SQLQueryWrapper(SQLDatabase database, String username, String password) throws SQLException{
		this.database = database;
		this.statement = database.createStatement(username, password);
	}
	
	
	/*******Get/Set Methods*******/
	
	//s
	public Statement getStatement() {
		return statement;
	}

	public void setStatement(Statement statement) {
		this.statement = statement;
	}
	
	public SQLDatabase getDatabase() {
		return database;
	}


	public void setDatabase(SQLDatabase database) {
		this.database = database;
	}
	//e

	/*******Member methods*******/
	
	//Queries	
	
	
	public boolean handleUpdate(String query) throws SQLException{
		
		int numChanged = executeUpdate(query);
		
		if(numChanged == 1) {
			return true;
		}
		else {
			return false;
		}
		
	}
	
	public boolean handleFind(String query) throws SQLException{
		
		//Run query
		ResultSet rs = executeQuery(query);
		
		//Return results
		return rs.next();
	}
	
	public ArrayList<String> handle1D(String query) throws SQLException{
		
		//Run query
		ResultSet rs = executeQuery(query);
		
		//Return results
		ArrayList<String> list = new ArrayList<String>();
		while(rs.next()){
			list.add(rs.getString(1));
		}
		
		return list;
	}
	
	public String handleSingle(String query) throws SQLException{
		
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
	
	
	
	
	//	Find
	
	public boolean findUser(String username) throws SQLException {
		return handleFind(SQLQueries.findUser(username));
	}
	
	public boolean findGroup(int group_id) throws SQLException {
		return handleFind(SQLQueries.findGroup(group_id));
	}
	
	public boolean findMovie(String title, int year) throws SQLException {
		return handleFind(SQLQueries.findMovie(title, year));
	}
	
	public boolean findMember(String username, int group_id) throws SQLException {
		return handleFind(SQLQueries.findMember(username, group_id));
	}
	
	//
	
	public ArrayList<String> getUsersGroups(String username) throws SQLException{
		return handle1D(SQLQueries.getUsersGroups(username));
	}
	
	public ArrayList<String> getUsersInGroup(int group_id) throws SQLException{
		return handle1D(SQLQueries.getUsersInGroup(group_id));
	}

	public String getGroupName(int group_id) throws SQLException{
		return handleSingle(SQLQueries.getGroupName(group_id));
	}
	
	
	//Updates
	
	//	User
	
	public boolean addUser(String username, String password) throws SQLException {
		return handleUpdate(SQLQueries.addUser(username, password));
	}
	
	public boolean removeUser(String username) throws SQLException {
		return handleUpdate(SQLQueries.removeUser(username));
	}
	
	//	Group
	
	public boolean addGroup(String group_name) throws SQLException {
		return handleUpdate(SQLQueries.addGroup(group_name));
	}
	
	public boolean removeGroup(int group_id) throws SQLException {
		return handleUpdate(SQLQueries.removeGroup(group_id));
	}
	
	//	Movie
	
	public boolean addMovie(String title, int year) throws SQLException {
		return handleUpdate(SQLQueries.addMovie(title, year));
	}
	
	public boolean removeMovie(String title, int year) throws SQLException {
		return handleUpdate(SQLQueries.removeMovie(title, year));
	}
	
	//	Member
	
	public boolean addMember(String username, int group_id) throws SQLException {
		return handleUpdate(SQLQueries.addMember(username, group_id));
	}
	
	public boolean removeMember(String username, int group_id) throws SQLException {
		return handleUpdate(SQLQueries.removeMember(username, group_id));
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
