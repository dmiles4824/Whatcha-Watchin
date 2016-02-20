/**
 * Purpose: Represent a "User" object that contains various lists
 */
import java.util.*;

public class User 
{
	//user's name
	private String username;
	
	//user's password
	private String password;
	
	//user's email
	private String email;
	
	//list to contain user's personal list of movies
	private ArrayList<Integer> personalList;
	
	//list of the groups that the user is in
	private ArrayList<Integer> groupList;
	
	//list of watched movies
	private ArrayList<Integer> watchedList;
	
	public User(String username, String password, String email)
	{
		this.username = username;
		this.password = password;
		this.email = email;
		this.personalList = new ArrayList<Integer>();
		this.watchedList = new ArrayList<Integer>();
		this.groupList = new ArrayList<Integer>();
	}
	
	/**
	 * Get the username
	 * @return String containing username
	 */
	public String getUserName()
	{
		return username;
	}
	
	/**
	 * Get the user's password
	 * @return String containing the user's password
	 */
	public String getPassword()
	{
		return password;
	}
	
	/**
	 * Get the user's email
	 * @return String containing the user's email address
	 */
	public String getEmail()
	{
		return email;
	}
	
	/**
	 * Get the user's personal list of movies
	 * @return An ArrayList<Integer> of movie IDs that are in the user's personal list
	 */
	public ArrayList<Integer> getPersonalList()
	{
		return personalList;
	}
	
	/**
	 * Get the user's list of already watched movies
	 * @return An ArrayList<Integer> of movie IDs of movies 
	 * that the user has already watched
	 */
	public ArrayList<Integer> getWatchedList()
	{
		return watchedList;
	}
	
	/**
	 * Get the list of groups that the user belongs to
	 * @return An ArrayList<Integer> of group IDs of groups that the user belongs to
	 */
	public ArrayList<Integer> getGroups()
	{
		return groupList;
	}
	
	/**
	 * Adds a new movie to the user's list
	 * @param chosenList List to add the movie to (Personal or Watched)
	 * @param movieID ID of the movie that is being added
	 */
	public void addMovie(ArrayList<Integer> chosenList, int movieID)
	{
		//****Do something with database to update when adding a movie
		chosenList.add(movieID);
	}
	
	/**
	 * Remove a movie from the user's list
	 * @param chosenList List to remove a movie from (Personal or Watched)
	 * @param selectedMovieID ID of the movie to be removed
	 */
	public void removeMovie(ArrayList<Integer> chosenList, int selectedMovieID)
	{
		chosenList.remove(selectedMovieID);
	}
	
	/**
	 * Adds a movie to watched list and removes it from personal list
	 * @param selectedMovieID ID of the movie to be denoted as watched
	 */
	public void watchedMovie(int selectedMovieID)
	{
		int index = 0;
		
		//Check through movie list for the movie we want to put in the watch list
		while(getWatchedList().get(index)!= selectedMovieID || index < getWatchedList().size())
			index++;
		
		//Only perform operations if we managed to find our movie/didn't go out of array bound
		if(getPersonalList().get(index) != null)
		{
			//*****UPDATE DATABASE INFO
			addMovie(getWatchedList(), selectedMovieID);
			removeMovie(getPersonalList(), selectedMovieID);
		}
		else
			System.out.println("Movie is not in list");
	}
	
	/**
	 * Remove a user from a selected group (Only works if user is the admin of the group)
	 * @param selectedUsername Username of the user to be removed from the group
	 * @param selectedGroupID ID of the group to remove user from
	 */
	public void removeMember(String selectedUsername, int selectedGroupID)
	{
		//****UPDATE GROUP MEMBER LIST IN DATABASE
	}
	
	/**
	 * Remove this current user from one of the selected groups that they are in
	 * @param selectedGroup ID of the group to leave
	 */
	public void leaveGroup(int selectedGroupID)
	{
		//*****UPDATE GROUP MEMBER LIST IN DATABASE;
	}
	
	//Make a random movie suggestion for a group from its list of movies
public void suggest(int groupID)
	{
		int random;
		
	}
	
	//Make a random movie suggestion for the user
	public void suggestSelf()
	{
		int random = (int)(Math.random() * (this.getPersonalList().size()));
		System.out.println("Your suggested movie is.....\n" + getPersonalList().get(random));
	}
	
	public static void main(String[] args)
	{
		User kai = new User("Kaius", "Blah", "kek");
		int movie1 = 951291;
		int movie2 = 12839212;
		int movie3 = 5421029;
		
		kai.addMovie(kai.getPersonalList(), movie1);
		kai.addMovie(kai.getPersonalList(), movie2);
		kai.addMovie(kai.getPersonalList(), movie3);
		
		kai.suggestSelf();
			
	}
}
