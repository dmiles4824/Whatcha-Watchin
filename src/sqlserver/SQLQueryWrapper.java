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
	
	public ArrayList<ArrayList<String>> handleMultiple(String query) throws SQLException {
		
		//run query
		ResultSet rs = executeQuery(query);
		
		//Return results
		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		boolean hasReachedEnd = false;
		for(int i = 0; i < Integer.MAX_VALUE && rs.next(); i++){
			list.add(new ArrayList<String>());
			for(int j = 1; j < Integer.MAX_VALUE && !hasReachedEnd; j++){
				try{
					list.get(i).add(rs.getString(j));
				}
				catch(SQLException e) {
					hasReachedEnd = true;
				}
			}
		}
		
		return list;
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
