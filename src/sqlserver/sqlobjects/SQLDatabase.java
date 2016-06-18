/*******Package*******/
package sqlserver.sqlobjects;


/*******Imports*******/
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import com.healthmarketscience.sqlbuilder.dbspec.basic.*;

public class SQLDatabase {
	
	/*******Member fields*******/
	
	private String dbLocation;
	private DbSpec dbSpec;
	
	
	/*******Constructors*******/
	
	public SQLDatabase(String dbLocation, DbSpec dbSpec) {
		this.dbLocation = dbLocation;
		this.dbSpec = dbSpec;
	}
	
	
	/*******Get/Set*******/
	//s
	public String getDbLocation() {
		return dbLocation;
	}


	public void setDbLocation(String dbLocation) {
		this.dbLocation = dbLocation;
	}


	public DbSpec getDbSpec() {
		return dbSpec;
	}


	public void setDbSpec(DbSpec dbSpec) {
		this.dbSpec = dbSpec;
	}
	//e
	
	
	/*******Member methods*******/
	
	public Statement createStatement(String username, String password) throws SQLException {
		
		
		Connection conn = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver"); 
			
			//Brian-RP
			conn = DriverManager.getConnection("jdbc:mysql://" + getDbLocation(), username, password);
			
		}
		
		catch (SQLException e) {
			throw e;
		}
		
		catch(Exception e){
			throw new SQLException();
		}
		
		return conn.createStatement();
		
	}
	
	
	/*******Static methods*******/
	
	public static SQLDatabase newWWDatabase() {
		
		System.out.println("	In newWWDatabase()");
		
		String dbLocation = "localhost:3306/wwschema";
		
		/*
		
	    // create default schema
	    DbSpec spec = new DbSpec();
	    DbSchema schema = spec.addDefaultSchema();
	 
	    // add table with basic customer info
	    DbTable userTable = schema.addTable("User");
	    userTable.addColumn("username", "varchar", 32);
	    userTable.addColumn("password", "varchar", 32);
	    
	    DbTable groupTable = schema.addTable("Group");
	    groupTable.addColumn("group_id", "int", null);
	    groupTable.addColumn("group_name", "varchar", 32);
	    
	    DbTable movieTable = schema.addTable("Movie");
	    movieTable.addColumn("title", "varchar", 32);
	    movieTable.addColumn("year", "int", null);
	    
	    DbTable memberTable = schema.addTable("Member");
	    memberTable.addColumn("username", "varchar", 32);
	    memberTable.addColumn("group_id", "int", null);
	    
	    */
	    
	    return new SQLDatabase(dbLocation, null);
	}
	
}
