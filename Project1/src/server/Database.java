package server;

import java.util.HashMap;
import java.util.concurrent.locks.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

//This class is used to store data
//Might get rid of this is I find out reading from a file is easier
public class Database {
	//Lock for threads
	private static Lock lock = new ReentrantLock();
	
	
	private ArrayList<String> First_Fundraiser = new ArrayList<>()
	{
		{
			add("john college fund");
			add("1000");
			add("October 30 2023");
		}
	};
	private HashMap<String, ArrayList<String>> Current_Fundraisers = new HashMap<>();

	private HashMap<String, ArrayList<String>> Past_Fundraisers = new HashMap<>();
	
	public <T> Database()
	{
		Current_Fundraisers.put("1", First_Fundraiser);
	}
	
//	public ArrayList<String> get(int choice)
//	{
//		ArrayList<String> Fundraiser = Current_Fundraisers.get(String.valueOf(choice));
//		if(Fundraiser == null)
//		{
//			return null;
//		}
//		return Fundraiser;
//	}
//	
//	public void set(String name, int target, String deadline)
//	{
//		
//	}
//	
//	public void donate(String choice, String amount)
//	{
//		//Current_Fundraisers.
//	}
//	
//	public int size()
//	{
//		return Current_Fundraisers.size();
//	}
//	
	
	synchronized public ArrayList<String> get(int choice)
	{
		lock.lock();
		ArrayList<String> Fundraiser = Current_Fundraisers.get(String.valueOf(choice));
		if(Fundraiser == null)
		{
			return null;
		}
		lock.unlock();
		return Fundraiser;
	}
	
	synchronized public void set(String name, int target, String deadline)
	{
		lock.lock();
		ArrayList<String> Fundraiser = new ArrayList<>()
		{
			{
				add(name);
				add("0");
				add(String.valueOf(target));
				add(deadline);
			}
		};
		Current_Fundraisers.put();
		lock.unlock();
	}
	
	synchronized public void donate(String choice, String amount)
	{
		//Current_Fundraisers.
	}
	
	synchronized public int size()
	{
		return Current_Fundraisers.size();
	}
	
	
	
	
	
	
//	private <T> T[] generic_array(int size)
//	{
//		T[] Fundraiser = new T[size];
//		return Fundraiser;
//	}

}
