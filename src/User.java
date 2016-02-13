/**
 * Purpose: Represent a "User" object that can perform operations relating to adding Movies 
 * to their own personal list
 */
import java.util.Hashtable;

public class User 
{
	//user's name
	private String username;
	
	//**For the time being** Represent a list that stores the user's selected movies
	private Hashtable<Movie,Boolean> personalList;
	
	public User(String username)
	{
		this.username = username;
		personalList = new Hashtable<Movie,Boolean>();
	}
	
	//Get and return the user's name
	public String getName()
	{
		return username;
	}
	
	//Get and return the user's personal movie list
	public Hashtable<Movie,Boolean> getList()
	{
		return personalList;
	}
	
	//Add a new movie to the user's list
	public void addMovie(Movie newMovie, Boolean liked)
	{
		personalList.put(newMovie, liked);
	}
	
	//Remove a movie from the user's list
	public void removeMovie(Movie chosenMovie)
	{
		personalList.remove(chosenMovie);
	}
}
