package sqlserver;

import java.sql.SQLException;
import java.util.ArrayList;

public class SQLTest {

	
	
	public static void main(String[] args){
		
		try{
			ArrayList<String> brianGroups = SQLQueries.getUsersGroups("brian");
			
			System.out.println("Results:");
			for(String s : brianGroups){
				System.out.println(s);
			}
			
		}
		
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		
		try {
			String brianString = SQLQueryWrapper.getUsersGroups("brian");
			
			System.out.println("Resutls:");
			System.out.println(brianString);
		}
		
		catch(SQLException e){
			e.printStackTrace();
		}
		
		
	}
	
}
