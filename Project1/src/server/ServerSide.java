package server;
import java.io.*;
import java.net.*;
import java.util.concurrent.*;
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;

public class ServerSide {

	public static <T> void main(String[] args) throws Exception {
		
		ExecutorService Thread_Manager = Executors.newCachedThreadPool();
		
		//Future<T> start = (Future<T>) Thread_Manager.submit(new Connections());
		String test = "Hello";
		
		
		//Creates a socket
		try
		{
			System.out.println("Opening Socket...");
			ServerSocket User_Socket = new ServerSocket(6789);
			System.out.println("Socket is open");
			while(true)
			{
				Socket New_Connection = User_Socket.accept();
				
				System.out.println("New client has connected");
				
				DataOutputStream toClient = new DataOutputStream(New_Connection.getOutputStream());
				toClient.writeBytes(test + " Test\n");
				
				if(New_Connection.isConnected())
				{
					System.out.println("This is the address of the new User: " + New_Connection.getInetAddress());
				}
			}
		}
		catch(Exception e)
		{
			System.out.println("Couldn't open socket");
			e.printStackTrace();
			System.exit(0);
		}
		
		
		int n = 1;
		
		//When a new User connects a new thread is created
		if(n == 1)
		{
			//Thread_Manager.sub
			Thread_Manager.execute(new User());
			
		}
	}

}
