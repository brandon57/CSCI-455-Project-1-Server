package server;
import java.io.*;
import java.net.*;

//This class handles all incoming connections
public class Connections implements Runnable {

	public Connections()
	{
		try
		{
			ServerSocket User_Socket = new ServerSocket();
		}
		catch(Exception e)
		{
			System.out.println("Couldn't open socket");
			System.exit(0);
		}
	}
	
	@Override
	public void run() {
		
	}

}
