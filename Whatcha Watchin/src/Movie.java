/**
 * Purpose: Represent a "Movie" object that a User/Group can add to a list
 */
public class Movie 
{
	//title of the movie
	private String title;
	
	//year that movie was released
	private String yearOfRelease;
	
	//genre of the movie
	private String genre;
	
	//Database ID for the movie
	private int mID; 
	
	//runtime of the movie
	private int runtime;
	
	public Movie(String title)
	{
		this.title = title;
	}
	
	public Movie(String title, String yearOfRelease)
	{
		this.title = title;
		this.yearOfRelease = yearOfRelease;
	}
	
	public Movie(String title, String yearOfRelease, String genre) 
	{
		this.title = title;
		this.yearOfRelease = yearOfRelease;
		this.genre = genre;
	}

	public Movie(String title, String yearOfRelease, String genre, int mID) 
	{
		this.title = title;
		this.yearOfRelease = yearOfRelease;
		this.genre = genre;
		this.mID = mID;
	}
	
	public Movie(String title, String yearOfRelease, String genre, int mID, int runtime) 
	{
		this.title = title;
		this.yearOfRelease = yearOfRelease;
		this.genre = genre;
		this.mID = mID;
		this.runtime = runtime;
	}

	//Get the title of this movie
	public String getTitle()
	{
		return title;
	}
	
	//Get the movie's release year
	public String getYearOfRelease()
	{
		return yearOfRelease;
	}
	
	//Get the genre of the movie
	public String genre()
	{
		return genre;
	}
	
	//Get the ID of the movie
	public int getID()
	{
		return mID;
	}
	
	//Get the runtime of the movie
	public int getRuntime()
	{
		return runtime;
	}
}
