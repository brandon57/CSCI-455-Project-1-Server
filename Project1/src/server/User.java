package server;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.net.*;

public class User implements Runnable {
	
	private Socket Connection = null;
	
	public User(Socket new_Connection)
	{
		Connection = new_Connection;
	}
	
	@Override
	public void run() {
		DataOutputStream toClient = null;
		BufferedReader fromClient = null;
		
		try
		{
			toClient = new DataOutputStream(Connection.getOutputStream());
			toClient.writeBytes("You are connected!\n");
			toClient.writeBytes(display());
		}
		catch(Exception e)
		{
			
		}
		
	}
	
	//List of Fund
	private String display()
	{
		String sentence = "";
		for(int i = 0; i < 20; i++)
		{
			sentence = sentence + "-";
		}
		String result = sentence + "\n";
		return result;
	}
	
}
