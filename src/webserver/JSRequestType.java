package webserver;

public enum JSRequestType {
	
	/*******JS Request Types*******/
	
	//Done
	
	//Non-JS
	CAPITALIZE_JSREQ 				(1, 	new String[]{"String:any"}, 																"capitalize"),
	ECHO_JSREQ						(1, 	new String[]{"String:any"}, 																"echo"),
	HELP_JSREQ						(0, 	new String[]{""}, 																			"help"),
	HELLO_JSREQ						(0, 	new String[]{""}, 																			"hello"),
	
	//User
	ADDUSER_JSREQ					(2, 	new String[]{"String:username", "String:password"},											"addUser"),
	REMOVEUSER_JSREQ				(1, 	new String[]{"String:username"},															"removeUser"),
	
	//Group
	ADDGROUP_JSREQ					(1,		new String[]{"String:group_name"},															"addGroup"),
	REMOVEGROUP_JSREQ				(1,		new String[]{"int:group_id"},																"removeGroup"),
	
	//Movie
	ADDMOVIE_JSREQ					(2,		new String[]{"String:title", "int:year"},													"addMovie"),
	REMOVEMOVIE_JSREQ				(2,		new String[]{"String:title", "int:year"},													"removeMovie"),
	
	//Usergroup
	GETUSERSGROUPS_JSREQ			(1, 	new String[]{"String:username"}, 															"getUsersGroups"),
	ADDUSERTOGROUP_JSREQ			(2,		new String[]{"String:username", "int:group_id"},											"addUserToGroup"),
	REMOVEUSERFROMGROUP_JSREQ		(2,		new String[]{"String:username", "int:group_id"},											"removeUserFromGroup"),
	GETUSERSINGROUP_JSREQ			(1,		new String[]{"int:group_id"},																"getUsersInGroup"),
	
	//LikeInGroup
	LIKEINGROUP_JSREQ				(4,		new String[]{"String:username", "int:group_id", "String:title", "int:year"},				"likeInGroup"),
	UNLIKEINGROUP_JSREQ				(4,		new String[]{"String:username", "int:group_id", "String:title", "int:year"},				"unlikeInGroup"),
	GETUSERSLIKESINGROUP_JSREQ		(2,		new String[]{"String:username", "int:group_id"},											"getUsersLikesInGroup"),
	GETALLLIKESINGROUP_JSREQ		(1,		new String[]{"int:group_id"},																"getAllLikesInGroup"),
	
	//Watched
	WATCHINGROUP_JSREQ				(3,		new String[]{"int:group_id", "String:title", "int:year"},									"watchInGroup"),
	UNWATCHINGROUP_JSREQ			(3,		new String[]{"int:group_id", "String:title", "int:year"},									"unwatchInGroup"),
	GETMOVIESWATCHEDINGROUP_JSREQ	(1,		new String[]{"int:group_id"},																"getMoviesWatchedInGroup"),
	
	
	//To add
	
	//Interested
	
	
	UNKNOWN_JSREQ				(0, 	new String[]{""}, 								"unrecognized command");
	
	
	/*******Member fields*******/
	
	private final int numParam;
	private final String[] paramTypes;
	private final String commandString;
	
	
	/*******Constructors*******/
	
	JSRequestType(int numParam, String[] paramTypes, String commandString) {
		this.numParam = numParam;
		this.paramTypes = paramTypes;
		this.commandString = commandString;
	}
	
	/*******Get/set*******/
	
	//s
	public int getNumParam() {
		return numParam;
	}

	public String[] getParamTypes() {
		return paramTypes;
	}

	public String getCommandString() {
		return commandString;
	}
	//e
	
	
	
}
