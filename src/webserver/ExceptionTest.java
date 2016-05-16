package webserver;

import java.io.IOException;
import java.net.*;
import webserver.webexception.*;

public class ExceptionTest {

	public static void main(String[] args){
		
		try {
			
			System.out.println("In try block");
			
			//Socket s = new Socket(InetAddress.getByName("192.168.1.21"), 80, InetAddress.getByName("192.168.1.23"), 80);
			Socket s = new Socket(InetAddress.getByName("Brian-LT"), 80, InetAddress.getByName("Brian-RP"), 80);
			
			System.out.println("Socket created");
			
			String httpMessage = "";
			
			s.getOutputStream().write(httpMessage.getBytes());
			
			System.out.println("Message written");
			
			s.close();
			
			System.out.print("Socket closed");
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
	}
	
}
