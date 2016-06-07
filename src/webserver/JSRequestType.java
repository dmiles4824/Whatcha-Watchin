package webserver;

public enum JSRequestType {
	
	/*******JS Request Types*******/
	
	CAPITALIZE_JSREQ 		(1, 	new String[]{"String"}, 						"capitalize"),
	ECHO_JSREQ				(1, 	new String[]{"String"}, 						"echo"),
	HELP_JSREQ				(0, 	new String[]{""}, 								"help"),
	HELLO_JSREQ				(0, 	new String[]{""}, 								"hello"),
	ADDUSER_JSREQ			(2, 	new String[]{"String", "String"},				"addUser"),
	REMOVEUSER_JSREQ		(1, 	new String[]{"String"},							"removeUser"),
	ADDGROUP_JSREQ			(1,		new String[]{"String"},							"addGroup"),
	REMOVEGROUP_JSREQ		(1,		new String[]{"int"},							"removeGroup"),
	ADDMOVIE_JSREQ			(2,		new String[]{"String", "int"},					"addMovie"),
	REMOVEMOVIE_JSREQ		(2,		new String[]{"String", "int"},					"removeMovie"),
	GETUSERSGROUPS_JSREQ	(1, 	new String[]{"String"}, 						"getUsersGroups"),
	ADDUSERTOGROUP_JSREQ	(2,		new String[]{"String", "int"},					"addUserToGroup"),
	GETUSERSINGROUP_JSREQ	(1,		new String[]{"int"},							"getUsersInGroup"),
	UNKNOWN_JSREQ			(0, 	new String[]{""}, 								"unrecognized command");
	
	
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
