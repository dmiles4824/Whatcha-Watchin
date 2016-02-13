/**
 * Purpose: Represent a "User" object that contains various lists
 */
import java.util.*;

public class User 
{
	//user's name
	private String username;
	
	//list to contain user's personal list of movies
	private ArrayList<Movie> personalList;
	
	/*
	//list of the groups that the user is in
	private ArrayList<Groups> groupList;
	*/
	
	//list of watched movies
	private ArrayList<Movie> watchedList;
	
	public User(String username)
	{
		this.username = username;
	}
	
	//Get the username
	public String getUserName()
	{
		return username;
	}
	
	//Get the user's personal list of movies
	public ArrayList<Movie> getPersonalList()
	{
		return personalList;
	}
	
	//Get the user's list of already watched movies
	public ArrayList<Movie> getWatchedList()
	{
		return watchedList;
	}
	
	/*Get the list of groups that the user belongs to
	public ArrayList<Group> getGroups
	{
		return groupList;
	}
	*/
	
	//Adds a new movie to the user's list
	public void addMovie(ArrayList<Movie> chosenList, Movie chosenMovie)
	{
		chosenList.add(chosenMovie);
	}
	
	//Remove a movie from the user's list
	public void removeMovie(ArrayList<Movie> chosenList, Movie selectedMovie)
	{
		personalList.remove(selectedMovie);
	}
	
	//Adds a movie to watched list and removes it from personal list
	public void watchedMovie(Movie selectedMovie)
	{
		String name = selectedMovie.getTitle();
		int index = 0;
		
		//Check through movie list for the movie we want to put in the watch list
		while(getWatchedList().get(index).getTitle() != name || index < getWatchedList().size())
			index++;
		
		//Only perform operations if we managed to find our movie/didn't go out of array bound
		if(getPersonalList().get(index) != null)
		{
			addMovie(getWatchedList(), selectedMovie);
			removeMovie(getPersonalList(), selectedMovie);
		}
		else
			System.out.println("Movie is not in list");
	}
	
	/*
	//Remove a user from a selected group (Only works if user is the admin of the group)
	public void removeMember(User selectedUser, Group selectedGroup)
	{
		
	}*/
	
	/*
	//Remove this current user from one of the selected groups that they are in
	public void leaveGroup(Group selectedGroup)
	{
		
	}*/
	
	/*
	//Make a random movie suggestion for a group from its list of movies
	public void suggest(Group selectedGroup)
	{
		int random;
		
	}*/
}
