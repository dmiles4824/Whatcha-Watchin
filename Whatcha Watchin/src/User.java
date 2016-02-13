import java.util.Hashtable;

public class User 
{
	private String username;
	private Hashtable<Movie,Boolean> personalList;
	
	public User(String username)
	{
		this.username = username;
	}
}
