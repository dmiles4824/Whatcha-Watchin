import java.util.Hashtable;
import java.util.ArrayList<E>

public class Group{
  private String groupName;
  private String[] members;//List of string names of members
  private String admin;//name of admin
  private ArrayList<Integer> groupList;//List of MovieIds
  
  public Group(String groupLeader){
    this.addMember(groupLeader);
    //make groupLeader the admin
  }
  
  public void addMember(String newMember){
	  //Danny function for adding a member to a group
  }
  
  //returns the list of members in the group
  public String[] getMembers(){
	return //Danny code  
  }
  
  //Returns list of MovieIds
  public ArrayList<Movie> getGroupList(){ 
	String[] members = getMembers();
	groupTable = new Hashtable<Integer,Integer>();//<MovieID,NumberOfWants>
	for(i=0;i<members.length;i++){
		ArrayList<Movie> memberList = members[i].getList();//personal list of a single member
		for(j=0;j<memberList.length;j++){
			if(groupTable.containsKey(memberList.get(j))){
				groupTable.put(memberList.get(j),groupList.get(memberList.get(j)+1));
			}
			else{
				groupTable.put(memberList.get(j),1);
			}	
		}	
	}
	groupList = new ArrayList<Integer>();//<MovieID>
	set<Integer> movieList = groupTable.keySet();
	Iterator<Integer> movieListIterator = movieList.iterator();//<MovieID>
	while(movieListIterator.hasNext()){ //Insertion Sort
		Movie currentMovie = movieListIterator.next();
		i=0;
		while(groupList.get(i)!=null || groupTable.get(groupList.get(i))<groupTable.get(currentMovie)){
			i++;
		}
		groupList.insert(i,currentMovie);
	}
	return groupList;
  }
  
  public removeUser(String Noah){
	  //
  }
  
  public watchedMovie(int movieID){
	String[] members = getMembers()  
	for(i=0;i<members.length;i++){
		members[i].watchedMovie(movieID);
	}
  }
  
  
}