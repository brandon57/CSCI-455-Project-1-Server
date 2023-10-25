package clients;
import java.io.*;
import java.net.*;
import java.util.Scanner;

public class ClientSide {

	private static Socket client = null;
	
	public static void main(String[] args) throws Exception {
		String textFromServer = "";
		//Socket client = null;
		String response = "";
		DataOutputStream toServer = null;
		BufferedReader fromServer = null;
		
		Scanner input = new Scanner(System.in);
		
		System.out.println("What do you want to do?\n1. Connect to GoFundMe\n2. Exit");
		
		//Asks the user if they want to connect or not
		while(true)
		{
			response = input.nextLine();
			if(response.equals("1") || response.equalsIgnoreCase("connect"))
			{
				break;
			}
			else if(response.equals("2") || response.equalsIgnoreCase("Exit"))
			{
				input.close();
				close();
			}
			else
			{
				System.out.println("Your input is not valid\nWant to try again?\n1. Yes\n2. No");
				response = input.nextLine();
				while(true)
				{
					if(response.equals("1") || response.equalsIgnoreCase("yes"))
					{
						System.out.println("What do you want to do?\n1. Connect to website\n2. Exit");
						break;
					}
					else if(response.equals("2") || response.equalsIgnoreCase("no"))
					{
						input.close();
						close();
					}
					else
					{
						System.out.println("Not a valid option\nYou want to try again?\n1. Yes\n2. No");
						response = input.nextLine();
					}
				}
			}
		}
		
		//Opens socket
		while(true)
		{
			try
			{
				client = new Socket("localhost", 6789);
				fromServer = new BufferedReader(new InputStreamReader(client.getInputStream()));
				break;
				//client.close();
			}
			catch(Exception e)
			{
				System.out.println("Couldn't connect\nWant to try again?\n1. Yes\n2. No");
				while(true)
				{
					response = input.nextLine();
					if(response.equals("1") || response.equalsIgnoreCase("yes"))
					{
						break;
					}
					else if(response.equals("2") || response.equalsIgnoreCase("no"))
					{
						input.close();
						close();
					}
					else
					{
						System.out.println("Your input is not valid\nWant to try again?\n1. Yes\n2. No");
						response = input.nextLine();
						while(true)
						{
							if(response.equals("1") || response.equalsIgnoreCase("yes"))
							{
								System.out.println("Want to try and connect again?\n1. Yes\n2. No");
								break;
							}
							else if(response.equals("2") || response.equalsIgnoreCase("no"))
							{
								input.close();
								close();
							}
							else
							{
								System.out.println("Not a valid option\nYou want to try again?\n1. Yes\n2. No");
								response = input.nextLine();
							}
						}
					}
				}
			}
		}
		toServer = new DataOutputStream(client.getOutputStream());
		
		//The part where the User actually interacts with the Website
		System.out.println("");
		while(true)
		{		
			//This is how the User gets info from the Server
			try
			{
				while((textFromServer = fromServer.readLine()) != null)
				{
					if(textFromServer.equals("^^&^&^&"))
					{
						close();
					}
					else if(textFromServer.equalsIgnoreCase(" "))
					{
						break;
					}
					System.out.println(textFromServer);
				}
				toServer.writeBytes(input.nextLine() + "\n");
			}
			catch(Exception e)
			{
				System.out.println("You and the server have disconnected");
				close();
			}
		}
	}
	
	
	
	//Stops the program
	private static void close() throws Exception
	{
		if(client != null)
		{
			client.close();
		}
		System.out.println("Closing...");
		System.exit(0);
	}

}
