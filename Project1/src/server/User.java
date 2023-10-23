package server;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.*;
import java.util.ArrayList;

public class User implements Runnable {
	
	private Socket Connection = null;
	Database Fundraisers;
	
	public User(Socket new_Connection, Database database)
	{
		Connection = new_Connection;
		Fundraisers = database;
	}
	
	@Override
	public void run() {
		DataOutputStream toClient = null;
		BufferedReader fromClient = null;
		 
		
		try
		{
			toClient = new DataOutputStream(Connection.getOutputStream());
			fromClient = new BufferedReader(new InputStreamReader(Connection.getInputStream()));
			
			toClient.writeBytes("You are connected!\n");
			toClient.writeBytes(display());
			
			
			
			System.out.println("This is from the client: " + fromClient.readLine());
		}
		catch(Exception e)
		{
			
		}
		
		ArrayList<String> temp;
		//Prints out the fundraisers
		for(int i = 1; i < Fundraisers.size(); i++)
		{
			temp = Fundraisers.get(i);
			for(int j = 0; j < temp.size(); i++)
			{
				System.out.println(temp.get(j));
			}
				
		}
		System.out.println(Fundraisers.get(1));
		
		
	}
	
	
	
	//List of Fund
	private String display()
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
		return result;
	}
	
}
