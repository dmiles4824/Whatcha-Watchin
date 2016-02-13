
/* This class handles queries to our SQL/iMDB database. It then returns these queries to Java-friendly language. Fun for kids! */
import java.sql.*;


public class DatabaseQuerier {
	
	/* Method for adding users to database.
	 * @param username New user's username.
	 * @param email The new user's email address.
	 * @param password The new user's password. Whoa. 
	 * @returns The number of elements added
	 */
	public static int addUser(String userName, String password, String email) {
		int countInserted = 0;

		try {
			Statement stmt = DatabaseQuerier.connector();
			
			String sqlInsert = "insert into user values ( '" + userName + "','" + password + "','" + email + "' )";
			System.out.println("The SQL query is: " + sqlInsert); //for debugging
			countInserted = stmt.executeUpdate(sqlInsert);
			System.out.println("User inserted.");
		}
		
		catch (SQLException e) {
			e.printStackTrace();
		}
		return countInserted;
	}
	/* Adds a group to the database.
	 * @param gid The group ID, used to identify the group in the database.
	 * @param gname The name of the group
	 * @returns The number of elements successfully added.
	 */
	public static int addGroup(String gid, String gname) {
		int countInserted = 0;
		
		countInserted = DatabaseQuerier.addTwoVarRow(gid, gname);

		return countInserted;
	}
	/* Adds the input movie and its details to the database. Will probably utilize OMDB API later on.
	 * @param mid The movie's numeric ID.
	 * @param title The title of the movie. :O
	 * @param year The year of the movie's first official release.
	 * @param genre The movie's genre. :O
	 * @param rating The movie's average critical rating.
	 * @returns Number of vars successfully added to database.
	 */
	public static int addMovie (String mid, String title, String year, String genre, String rating) {
		int countInserted = 0;
		try {
			Statement stmt = DatabaseQuerier.connector();
			
			String sqlInsert = "insert into movie values ( '" + mid + "','" + title + "','" + year + "','" + genre + "','" + rating + "' )";
			System.out.println("The SQL query is: " + sqlInsert);
			countInserted = stmt.executeUpdate(sqlInsert);
			System.out.println("Movie added.");
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return countInserted;
	}
	/* Adds rating to the input movie from the input user.
	 * @param username The user giving the rating.
	 * @param mid The ID of the movie being rated.
	 * @param yn Whether the user likes or does not like the movie.
	 */
	public static int addRates(String username, String mid, String yn) {
		int countInserted = 0;
		try {
			Statement stmt = DatabaseQuerier.connector();
			
			String sqlInsert = "insert into rates values ( '" + username + "','" + mid + "','" + yn + "' )";
			countInserted = stmt.executeUpdate(sqlInsert);
			System.out.println("Rating added.");
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return countInserted;
	}
	
	/* Helper method that deals with all database entries that consist of two columns.
	 * @param v1 The first column; could be gid, username, etc.
	 * @param v2 The second column; could be gname, mid, etc.
	 * @returns Number of elements added
	 */
	private static int addTwoVarRow(String v1, String v2) {
		int countInserted = 0;
		
		try {
			Statement stmt = DatabaseQuerier.connector();
			
			String sqlInsert = "insert into group values ( '" + v1 + "','" + v2 + "' )";
			System.out.println("The SQL query is: " + sqlInsert);
			countInserted = stmt.executeUpdate(sqlInsert);
			System.out.println("Row inserted.");
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return countInserted;
	}
	
	/* Helper method that connects the program to the SQL database.
	 * @throws SQLException */
	private static Statement connector() throws SQLException {
		Connection conn;
		try {
			 conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/wwdb", "root", "pokemon25"); 
		}
		catch (SQLException e) {
			throw e;
		}
		return conn.createStatement();
	}
		
	public static void main(String[] args) {
		;
	}
}



