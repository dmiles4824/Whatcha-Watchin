package webserver;

import java.io.IOException;
import java.net.*;
import webserver.webexception.*;

public class ExceptionTest {

	public static void main(String[] args){
		
		try {
			Socket s = new Socket("192.168.1.21", 80);
			
			String httpMessage = "";
			
			s.getOutputStream().write(httpMessage.getBytes());
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
	}
	
}
