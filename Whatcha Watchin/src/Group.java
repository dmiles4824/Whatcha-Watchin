import java.util.*;

public class Group{
  private String groupName;
  private User[] members;
  private User admin;
  private Hashtable<Movie,Boolean> groupList;
  private int groupID;
  
  //
  public Group(User groupLeader){
    this.addMember(groupLeader);
    //make groupLeader the admin
  }
  
  public void addMember(User newMember){
	  //Danny function for adding a member to a group
	  addMovies(newMember);
	  
	  
	  /*
    for(int i=0; i<length.members; i++){
      if(members[i] == null){
        members[i] = newMember;
        i=length.members+1;
      }
      if(i==length.members){        
        User[] newMemberList = new User[length.members*2];
        for(int num=0; num<length.members; num++){
          newMemberList[num] = members[num];
        newMemberList[length.members] = newMember;
        }
      }
    }*/
  }
  
  public void addMovies(User member){
    Set<Movie> userMovieList= member.getPersonalList().keyset();
    Iterator<Movie> userMovieListIterator = userMovieList.iterator();
    while(userMovieListIterator.hasNext()){
    	Movie aMovie = next();
    	Boolean liked = member.getList().get(aMovie);
    	//Insert whatever we are doing in SQL
    }	
  }
  
  //returns the list of members in the group
  public User[] getMembers(){
	//Danny code  
	  
  }
  
  //Get the group's database ID number
  public int getID()
  {
	  return groupID;
  }
  
  public Hashtable<Movie,Boolean> getGroupList(){
	members = getMembers();
	
  }
}