/**
 * Web Server. 
 * Portal from which to download the java applet.
 */

/*******Package*******/

package webserver;


/*******Imports*******/

import java.net.*;
import java.nio.file.Path;
import java.nio.file.Paths;


/**
 * WebServer is the Java server that will run on the web host and
 * accept connection requests. If properly authenticated, the 
 * WebServer will allow the user to download the Java applet which
 * the user will continue to use to interact with the database.
 * @author Brian
 */
public class WebServer {
	
	/*******Constants*******/
	
	public final static int webPort = 80;
	
	
	/*******Main*******/
	
	public static void main(String[] args) throws Exception{
		
		ServerSocket welcomeSocket = new ServerSocket(webPort);
		
		Path currentRelativePath = Paths.get("");
		String s = currentRelativePath.toAbsolutePath().toString();
		System.out.println("Current relative path is: " + s);
		
		while (true) {
			
			System.out.println("Ready for connections!!!!");
			
			//block while waiting for next connection attempt
			Socket connectionSocket = welcomeSocket.accept();

			//Start thread to handle new connection
			try {
				
				Thread t = new Thread(new ConnectionManager(connectionSocket));
				System.out.println("Connection request received");
				t.start();
				
				

			}
			
			//Could not extract inputstream
			catch(Exception e){
				e.printStackTrace();
			}
			
		}
		
	}
	
}