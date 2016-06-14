/*******Package*******/

package sqlserver;


/*******Imports*******/
import java.sql.*;


public class SQLQueries {
	
	
	
	/*******Static methods*******/
	
	
	//Queries
	
	//	Find
	
	public static String findUser(String username) {
		
		String query = 	  "select * "
						+ "from `User` "
						+ "where `username` = '" + username + "'"
						+ ";"
						;
		
		return query;
	}
	
	public static String findGroup(int group_id) {
		String query = 	  "select * "
						+ "from `Group` "
						+ "where `group_id` = '"
						+ group_id
						+ "'"
						+ ";"
						;
		
		return query;
	}
	
	public static String findMovie(String title, int year) {
		
		String query = 	  "select * "
						+ "from `Movie` "
						+ "where `title` = '"
						+ title
						+ "' AND `year` = "
						+ year
						+ ";"
						;
		
		return query;
	}
	
	public static String findMember(String username, int group_id){
		String query =    "select * "
						+ "from `Member` "
						+ "where `username` = '"
						+ username
						+ "' AND `group_id` = "
						+ group_id
						+ ";"
						;
		
		return query;
	}
	
	//
	
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
	
	public static String getUsersInGroup(int group_id) {
		String query =    "select `username` "
						+ "from `Member`"
						+ "where `group_id` = "
						+ group_id
						+ ";"
						;
		
		return query;
	}

	public static String getGroupName(int group_id) {
		String query =    "select `group_name` "
						+ "from `Group` "
						+ "where `group_id` = "
						+ group_id
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
	
	//	Group
	
	public static String addGroup(String group_name) throws SQLException{
		
		String query =    "insert into `Group` (`group_name`) values ('"
						+ group_name
						+ "')"
						+ ";"
						;
						
		return query;
		
	}
	
	public static String removeGroup(int group_id) throws SQLException{
		String query =    "delete from `Group` "
						+ "where `group_id` = "
						+ group_id
						+ " limit 1"
						+ ";"
						;
		
		return query;
	}
	
	//	Movie
	
	public static String addMovie(String title, int year) {
		String query =    "insert into `Movie` (`title`, `year`) values ('" + title + "', " + year + ")"
						+ ";"
						;
		
		return query;
		
	}
	
	public static String removeMovie(String title, int year) throws SQLException{
		String query =    "delete from `Movie` "
						+ "where `title` = '"
						+ title
						+ "' AND `year` = "
						+ year
						+ " limit 1"
						+ ";"
						;
		
		return query;
	}
	
	//	Member
	
	public static String addMember(String username, int group_id){
		String query =    "insert into `Member` (`username`, `group_id`) values ('"
						+ username
						+ "', "
						+ group_id
						+ ")"
						+ ";"
						;
		
		return query;
	}
	
	public static String removeMember(String username, int group_id) {
		String query =    "delete from `Member` "
						+ "where `username` = '"
						+ username 
						+ "' AND `group_id` = "
						+ group_id
						+ ";"
						;
		
		return query;
		
	}
	
	
}
