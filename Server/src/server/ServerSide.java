package server;

import java.io.IOException;
import java.net.*;
import java.util.concurrent.*;
import java.util.Scanner;

public class ServerSide {
	
	private static ServerSocket User_Socket = null;
	private static Scanner input = new Scanner(System.in);
	private static ExecutorService Thread_Manager = Executors.newCachedThreadPool();
	
	
	public static void main(String[] args) throws Exception {
		
		
		String inputText = "";
		
		//Manages the threads
		
		
		//Creates a socket
		connect();
//		while(true)
//		{
//			try
//			{	
//				connect();
//				break;
//			}
//			catch(NumberFormatException n)
//			{
//				System.out.println("Your input has to be a number\nTry again");
//			}
//			catch(IOException i)
//			{
//				System.out.println("Couldn't open socket\nTry again");
//			}
//		}
		
		Database Fundraisers = new Database();
		
		//Waits for a User to connect then
		while(true)
		{
			Socket New_Connection = User_Socket.accept();
			Thread_Manager.execute(new User(New_Connection, Fundraisers));
		}
	}
	
	private static void connect() throws Exception
	{
		String port = null;
		while(true)
		{
			System.out.println("What is the port number of the server? Your input should be between 0-65535\nYou can exit if you want");
			port = input.nextLine();
			if(validPort(port) == true)
			{
				break;
			}
			else if(port.equalsIgnoreCase("exit"))
			{
				close();
			}
			else
			{
				System.out.println("That port number is not valid");
			}
		}
		System.out.println("Opening Socket...");
		User_Socket = new ServerSocket(Integer.valueOf(port));
		System.out.println("Socket is open");
	}
	
	private static boolean validPort(String port)
	{
		Integer temp = 0;
		try
		{
			temp = Integer.valueOf(port);
			if(temp >= 0 && temp <= 65535)
			{
				return true;
			}
		}
		catch(Exception e)
		{
			return false;
		}
		return false;
	}
	
	private static void close() throws Exception
	{
		if(User_Socket != null)
		{
			User_Socket.close();
		}
		if(input != null)
		{
			input.close();
		}
		Thread_Manager.isShutdown();
		System.out.println("Shuting down...");
		System.exit(0);
	}
}
