import java.util.Hashtable;

public class User 
{
	//user's name
	private String username;
	
	//list to contain user's personal list of movies
	private Hashtable<Movie,Boolean> personalList;
	
	public User(String username)
	{
		this.username = username;
	}
	
	//Get the username
	public String getUserName()
	{
		return username;
	}
	
	//Get the user's list of movies
	public Hashtable<Movie,Boolean> getList()
	{
		return personalList;
	}
	
	//Adds a new movie to the user's list
	public void addMovie(Movie newMovie, Boolean liked)
	{
		personalList.put(newMovie,liked);
	}
	
	//Remove a movie from the user's list
	public void removeMovie(Movie selectedMovie)
	{
		personalList.remove(selectedMovie);
	}
	
	
}
