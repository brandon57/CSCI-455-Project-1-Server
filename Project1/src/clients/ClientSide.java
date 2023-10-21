package clients;
import java.io.*;
import java.net.*;

public class ClientSide {

	public static void main(String[] args) throws Exception {
		String test = "";
		Socket client ;
		
		//Opens socket
		try
		{
			client = new Socket("localhost", 6789);
			BufferedReader fromServer = new BufferedReader(new InputStreamReader(client.getInputStream()));
			
			test = fromServer.readLine();
			
			System.out.println("This is from Server: " + test);
			
			client.close();
		}
		catch(Exception e)
		{
			System.out.println("Didn't work");
		}
		//DataInputStream fromServer = new DataInputStream(client.getInputStream());
		
		
	}

}
