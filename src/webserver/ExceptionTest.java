package webserver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.net.*;

import webserver.webexception.*;

public class ExceptionTest {

	public static void main(String[] args){
		
		try {
			
			System.out.println("In try block");
			
			//Socket s = new Socket(InetAddress.getByName("192.168.1.21"), 80, InetAddress.getByName("192.168.1.23"), 80);
			Socket s = new Socket("Brian-LT", 80);
			
			System.out.println("Socket created");
			
			String httpMessage = "GET / HTTP/1.1\r\n" + "\r\n";
			
			s.getOutputStream().write(httpMessage.getBytes());
			
			System.out.println("Message written");
			
			s.close();
			
			System.out.print("Socket closed");
			System.exit(0);
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

//		try {
//			File f = new File("D:/Documents/Projects/Watcha-Watchin/Whatcha-Watchin/resources/webpages/index.html");
//			Path p = Paths.get(f.getAbsolutePath());
//			byte[] htmlBytes = ParseTools.readBytesFromFile(p);
//			HTTPResponse response = ServerTools.formHTMLResponse("D:/Documents/Projects/Watcha-Watchin/Whatcha-Watchin/resources/webpages/index.html");
//			System.out.println(response.toString());
//		}
//		catch(WebException e) {
//			e.printStackTrace();
//		}
	}
	
}
