package server;

import java.net.*;
import java.util.concurrent.*;

public class ServerSide <T> {
	
	public static <T> void main(String[] args) throws Exception {
		
		//Manages the threads
		ExecutorService Thread_Manager = Executors.newCachedThreadPool();
		
		ServerSocket User_Socket = null;
		
		//Creates a socket
		try
		{
			System.out.println("Opening Socket...");
			User_Socket = new ServerSocket(6789);
			System.out.println("Socket is open");
		}
		catch(Exception e)
		{
			System.out.println("Couldn't open socket");
			e.printStackTrace();
			System.exit(0);
		}
		
		Database Fundraisers = new Database();
		
		//Waits for a User to connect then
		while(true)
		{
			Socket New_Connection = User_Socket.accept();
			Thread_Manager.execute(new User(New_Connection, Fundraisers));
		}
	}
}
