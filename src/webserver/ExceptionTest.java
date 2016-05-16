package webserver;

import java.io.IOException;
import java.net.*;
import webserver.webexception.*;

public class ExceptionTest {

	public static void main(String[] args){
		
		try {
			
			System.out.println("In try block");
			
			//Socket s = new Socket(InetAddress.getByName("192.168.1.21"), 80, InetAddress.getByName("192.168.1.23"), 80);
			Socket s = new Socket("Brian-LT", 80);
			
			System.out.println("Socket created");
			
			String httpMessage = "GET whatcha-watchin.com/index.html HTTP/1.1\r\n" + "\r\n";
			
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
		
	}
	
}
