package server;
import java.io.*;
import java.net.*;
import java.util.concurrent.*;
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;

public class ServerSide {

	public static <T> void main(String[] args) {
		
		ExecutorService Thread_Manager = Executors.newCachedThreadPool();
		Future<T> start = (Future<T>) Thread_Manager.submit(new Connections());
		
		//Creates a socket
//		try
//		{
//			ServerSocket User_Socket = new ServerSocket(5999);
//			User_Socket.accept();
//		}
//		catch(Exception e)
//		{
//			System.out.println("Didn't work");
//			System.exit(0);
//		}
		
		//
		
		
		int n = 1;
		
		//When a new User connects a new thread is created
		if(n == 1)
		{
			//Thread_Manager.sub
			Thread_Manager.execute(new User());
		}
	}

}
