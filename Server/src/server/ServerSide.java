package server;

import java.io.IOException;
import java.net.*;
import java.util.concurrent.*;
import java.util.Scanner;

public class ServerSide {
	
	private static ServerSocket User_Socket = null;
	
	public static void main(String[] args) throws Exception {
		
		Scanner input = new Scanner(System.in);
		String inputText = "";
		
		//Manages the threads
		ExecutorService Thread_Manager = Executors.newCachedThreadPool();
		
		URL server = new URI("http://test").toURL();
		//Creates a socket
		while(true)
		{
			try
			{
				//System.out.println("Set the ip");
				//inputText = input.
				System.out.println("Set the port number");
				inputText = input.nextLine();
				System.out.println("Opening Socket...");
				User_Socket = new ServerSocket(Integer.valueOf(inputText));
				System.out.println("Socket is open");
				break;
	
			}
			catch(NumberFormatException n)
			{
				System.out.println("Your input has to be a number\nTry again");
			}
			catch(IOException i)
			{
				System.out.println("Couldn't open socket\nTry again");
			}
		}
		
		Database Fundraisers = new Database();
		
		//Waits for a User to connect then
		while(true)
		{
			Socket New_Connection = User_Socket.accept();
			Thread_Manager.execute(new User(New_Connection, Fundraisers));
		}
	}
	
	private void socketSetup(String input)
	{

	}
}
