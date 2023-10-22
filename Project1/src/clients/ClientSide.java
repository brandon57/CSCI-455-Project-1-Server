package clients;
import java.io.*;
import java.net.*;
import java.util.Scanner;

public class ClientSide {

	public static void main(String[] args) throws Exception {
		String result = "";
		Socket client ;
		String response = "";
		
		Scanner input = new Scanner(System.in);
		
		System.out.println("What do you want to do?\n1. Connect to website\n2. Exit");
		
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
				BufferedReader fromServer = new BufferedReader(new InputStreamReader(client.getInputStream()));
				
				for(int i = 0; i < 2; i++)
				{
					result = fromServer.readLine();
					System.out.println(result);
				}
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
		//DataInputStream fromServer = new DataInputStream(client.getInputStream());
		client.close();
		
		//The part where the User actually interacts with the Website
		
	}
	
	
	
	//Stops the program
	private static void close()
	{
		System.out.println("Closing...");
		System.exit(0);
	}

}
