import java.util.Hashtable;
import java.util.ArrayList<E>

public class Group{
  private String groupName;
  private User[] members;
  private User admin;
  private ArrayList<Movie> groupList;
  
  public Group(User groupLeader){
    this.addMember(groupLeader);
    //make groupLeader the admin
  }
  
  public void addMember(User newMember){
	  //Danny function for adding a member to a group
  }
  
  //returns the list of members in the group
  public User[] getMembers(){
	return //Danny code  
  }
  
  public ArrayList<Movie> getGroupList(){ //kauhrgiaer=
	members = getMembers();
	groupTable = new Hashtable<Movie,Integer>();
	for(i=0;i<members.length;i++){
		ArrayList<Movie> memberList = members[i].getList();
		for(j=0;j<memberList.length;j++){
			if(groupTable.containsKey(memberList[j])){
				groupTable.put(memberList[j],groupList.get(memberList[j])+1);
			}
			else{
				groupTable.put(memberList[j],1);
			}	
		}	
	}
	groupList = new ArrayList<Movie>();
	set<Movie> movieList = groupTable.keySet();
	Iterator<Movie> movieListIterator = movieList.iterator();
	while(movieListIterator.hasNext()){
		Movie currentMovie = movieListIterator.next();
		i=0;
		while(groupList.get(i)!=null || groupTable.get(groupList.get(i))<groupTable.get(currentMovie)){
			i++;
		}
		groupList.insert(i,currentMovie);
	}
	return groupList;
  }
  
  public removeUser(User Noah){
	  //
  }
  
  public matchedMovie(Movie movie){
	members = getMembers()  
	for(i=0;i<members.length;i++){
		members[i].watchedMovie(movie);
	}
  }
  
  
}