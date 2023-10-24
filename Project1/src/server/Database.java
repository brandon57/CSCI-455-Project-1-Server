package server;

import java.util.HashMap;
import java.util.concurrent.locks.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
//import java.time.

//This class is used to store data
//Might get rid of this is I find out reading from a file is easier
public class Database <T> {	
	//private Date date = new Date();
	private LocalDate currentDate = LocalDate.now();
	private DateTimeFormatter date_format;
	private String format = "MM/dd/yyyy";
	//Where the Fundraisers are stored
	private ArrayList<ArrayList<T>> Fundraisers = new ArrayList<ArrayList<T>>();
	
	public <T> Database() throws ParseException
	{
		//Puts in a fundraiser to start with
		set("John's college fund", 1000, "10/30/2023");
		set("wedding", 10040, "10/20/2023");
	}
	
	synchronized public ArrayList<T> get(int choice)
	{
		
		ArrayList<T> Fundraiser = Fundraisers.get(choice);
		if(Fundraiser == null)
		{
			return null;		
		}
		
		return Fundraiser;
	}
	
	//Creates a new Fundraiser and puts it in the Fundraisers ArrayList
	synchronized public void set(String name, Integer target, String deadline)
	{
		LocalDate temp = LocalDate.parse(deadline, date_format.ofPattern(format));
		
		ArrayList<T> Fundraiser = new ArrayList<>()
		{
			{
				add((T) name);
				add((T) (Integer) 0);
				add((T) target);
				add((T) deadline);
			}
		};
		
		//Checks if the Fundraiser is currently going on or is done
		if(currentDate.isAfter(temp))
		{
			Fundraiser.add((T) (Integer) 1);
		}
		else
		{
			Fundraiser.add((T) (Integer) 0);
		}
		
		Fundraisers.add(Fundraiser);
		
		return;
	}
	
	synchronized public String donate(Integer choice, Integer amount)
	{
		
		try
		{
			ArrayList<T> Fundraiser = Fundraisers.get(choice);
			if(Fundraiser == null)
			{
				return null;
			}
			Fundraiser.set(1, (T) amount);
			return "Donation Completed!";
		}
		catch(Exception e)
		{
			return null;
		}
	}
	
	synchronized public boolean valid_Date(String date)
	{
		try
		{
			LocalDate temp = LocalDate.parse(date, date_format.ofPattern(format));
			return true;
		}
		catch(Exception e)
		{
			return false;
		}
	}
	
	synchronized public int size()
	{
		int result = Fundraisers.size();
		return result;
	}

}
