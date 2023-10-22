package server;
import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.*;
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;

public class ServerSide <T> {
	
	//The database for the server
	ArrayList<String> Fundraisers = new ArrayList<>(4);
	HashMap<Integer, ArrayList<String>> Current_Fundraisers = new HashMap<>();
	
	public static <T> void main(String[] args) throws Exception {

		
		//HashMap<String, ArrayList<String>> Past_Fundraisers = new HashMap<>();
		
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
		
		int num = 0;
		//Waits for a User to connect then
		while(true)
		{
			num++;
			Socket New_Connection = User_Socket.accept();
			Thread_Manager.execute(new User(New_Connection));
			System.out.println("New client has connected");
			System.out.println("User " + num + ": " + "IP address: " + New_Connection.getInetAddress() + " Port number: " + New_Connection.getPort());
			
		}
		
		
		
	}
	
	
	public void Setup_Database()
	{
		Fundraisers.add("John's college fund");
		Fundraisers.add("1000");
		Fundraisers.add("October, 20, 2023");
		Current_Fundraisers.put(1, Fundraisers);
	}

}
