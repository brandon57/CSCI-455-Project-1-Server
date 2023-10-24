package server;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.util.ArrayList;

public class User<T> implements Runnable {
	
	private Socket Connection = null;
	Database<T> Fundraisers;
	private DataOutputStream toClient = null;
	private BufferedReader fromClient = null;
	private String textFromClient = "";
	
	public User(Socket new_Connection, Database<T> database)
	{
		Connection = new_Connection;
		Fundraisers = database;
	}
	
	@Override
	public void run() {
		try
		{
			toClient = new DataOutputStream(Connection.getOutputStream());
			fromClient = new BufferedReader(new InputStreamReader(Connection.getInputStream()));
			toClient.writeBytes("You are connected!\n");
			
			//The beginning of the UI
			//This is how the User interacts with the Server
			int current_past = 0;
			//display_Fundraisers(0);
			//display_options();
			while(true)
			{
				//Keeps the user at either current or past fundrasiers 
				display_Fundraisers(current_past);
				
				display_options();
				textFromClient = fromClient.readLine().toLowerCase();
				switch(textFromClient)
				{
				case "current":
				case "1":
					current_past = 0;
					break;
				case "past":
				case "2":
					current_past = 1;
					break;
				case "create":
				case "3":
					create();
					break;
				case "donate":
				case "4":
					donate();
					break;
				case "back":
				case "5":
					current_past = 0;
					break;
				case "exit":
				case "6":
					exit();
					break;
				default:
					
					break;
				}
				
				
			}
		}
		catch(Exception e)
		{
			//toClient.writeBytes("");
		}
		
	}
	
	private void exit()
	{
		
	}
	
	private void donate()
	{
		
	}
	
	
	private void display_options() throws Exception
	{
		toClient.writeBytes("These are the options you have to chose from\n"
		+"1. See current funderaisers\n"
		+"2. See past funderaisers\n"
		+"3. Create a funderaiser\n"
		+"4. donate to a funderaiser\n"
		+"5. Go back\n"
		+"6. Exit\n" + " \n");
	}
	
	
	//User Creating a new fundraiser
	private void create() throws Exception
	{
		String name;
		Integer target = 0;
		String deadline;
		
		toClient.writeBytes("What is the name of your fundraiser?\n" + " \n");
		name = fromClient.readLine();
		
		toClient.writeBytes("What is your target amount?\n" + " \n");
		while(true)
		{
			try
			{
				target = Integer.valueOf(fromClient.readLine());
				break;
			}
			catch(Exception e)
			{
				toClient.writeBytes("Your input is not valid\n");
				toClient.writeBytes("Try again\n" + " \n");
			}
		}
		
		toClient.writeBytes("What is the deadline? Your input should look like this \"MM/dd/yyyy\"\n" + " \n");
		while(true)
		{
			deadline = fromClient.readLine();
			if(Fundraisers.valid_Date(deadline) == true)
			{
				break;
			}
			else
			{
				toClient.writeBytes("Your input is not valid\n");
				toClient.writeBytes("Try again\n" + " \n");
			}
		}
		Fundraisers.set(name, target, deadline);
	}
	
	
	//Prints out the fundraisers
	//The input determines if either past or current fundraisers get displayed
	//0 for current and 1 for past
	private void display_Fundraisers(int display) throws Exception
	{
		ArrayList<T> temp;
		ArrayList<ArrayList<T>> Old_Funderaisers = new ArrayList<ArrayList<T>>();
		ArrayList<ArrayList<T>> Current_Funderaisers = new ArrayList<ArrayList<T>>();
		
		for(int i = 0; i < Fundraisers.size(); i++)
		{
			if(Fundraisers.get(i).get(4).equals(0))
			{
				Current_Funderaisers.add(Fundraisers.get(i));
			}
			else if(Fundraisers.get(i).get(4).equals(1))
			{
				Old_Funderaisers.add(Fundraisers.get(i));
			}
		}
		
		String temp1 = "";
		display();
		switch(display)
		{
			//Current Fundraisers
			case 0:
				toClient.writeBytes("Here are on going funderaisers:\n");
				display();
				for(int i = 0; i < Current_Funderaisers.size(); i++)
				{
					temp = Current_Funderaisers.get(i);
					//toClient.writeBytes((i+1) + ".\n");
					for(int j = 0; j < temp.size()-1; j++)
					{
						temp1 = temp1 + " " + temp.get(j).toString() + " |";
					}
					toClient.writeBytes((i+1) + "." + temp1 + "\n");
					toClient.flush();
				}
				break;
			//Old Fundraisers
			case 1:
				toClient.writeBytes("Here are past funderaisers:\n");
				for(int i = 0; i <	Old_Funderaisers.size(); i++)
				{
					temp = Old_Funderaisers.get(i);
					//toClient.writeBytes((i+1) + ".\n");
					for(int j = 0; j < temp.size()-1; j++)
					{
						temp1 = temp1 + " " + temp.get(j).toString() + " |";
					}
					toClient.writeBytes((i+1) + "." + temp1 + "\n");
					toClient.flush();
				}
				break;
		}
		display();
		//toClient.writeBytes(" \n");
		
	}
	//List of Fund
	private void display() throws Exception
	{
		
		String sentence = "";
		for(int i = 0; i < 20; i++)
		{
			if(i == 10)
			{
				sentence = sentence + "+";
			}
			else
			{
				sentence = sentence + "-";
			}
				
		}
		String result = sentence + "\n";
		toClient.writeBytes(result);
		toClient.flush();
	}
	
}
