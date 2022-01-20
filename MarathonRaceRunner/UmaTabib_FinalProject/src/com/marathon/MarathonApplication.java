package com.marathon;
import java.util.*;

/**
 * Main class to accept user choice and inputs to run marathon event.
 * @author Uma T
 *
 */

public class MarathonApplication {

	/**
	 * Function that provide user with options and accepts their inputs.
	 */
	public void runRace()
	{
		List<String> continueOpt = Arrays.asList("y","n"); 		//List of valid options to continue or exit transaction.
		// perform 1 or more selections
		Scanner sc = new Scanner(System.in);

		// perform 1 or more actions
		String action = "1";
		while ((!action.equals("5")) || (!action.equalsIgnoreCase("Exit")))
		{
			// display the menu
			displayMenu();

			// get the input from the user
			action = Validator.getLine(sc,"Enter your choice : ");
			System.out.println();

			if (action.equalsIgnoreCase("1") || action.equalsIgnoreCase("Derby Database"))
			{
				DBRun dbr = new DBRun();
				dbr.scheduleMarathon();
			}
			else if (action.equals("2") || action.equalsIgnoreCase("XML file"))
			{
				String xmlFile = Validator.getLine(sc,"Enter XML file name.. (e.g. FinalXMLData.xml) :");
				XMLRun xr = new XMLRun(xmlFile);
				xr.scheduleMarathon();
			}
			else if (action.equals("3") || action.equalsIgnoreCase("Text file"))
			{
				String txtFile = Validator.getLine(sc,"Enter text file name.. (e.g. FinalTextData.txt) :");
				FileRun fr = new FileRun(txtFile);
				fr.scheduleMarathon();
			}
			else if (action.equals("4") || action.equalsIgnoreCase("Default two runners"))
			{
				DefaultRun def = new DefaultRun();
				def.scheduleMarathon();
			}
			else if (action.equals("5") || action.equalsIgnoreCase("Exit"))
			{
				break;
			}
			else
			{
				System.out.println("Error! Not a valid command.\n");
			}
			
			try{
				// main thread sleeps to make sure, that marathon event is finished before continuing with next choices.
				Thread.sleep(100);
			} catch (InterruptedException e){}

			action = Validator.getString(sc, "Continue?  (y/n) : ", continueOpt);
			if(action.equalsIgnoreCase("n")){
				break;
			}
			System.out.println();

		}
		System.out.println("Bye.\n");
	}

	/**
	 * Display menu to users.
	 */
	public static void displayMenu()
	{
		System.out.println("Welcome to the Marathon Race Runner Program");
		System.out.println();
		System.out.println("Select a number for your choice");

		System.out.println("1 -- Derby Database" );
		System.out.println("2 -- XML file" );
		System.out.println("3 -- Text file" );
		System.out.println("4 -- Default two runners" );
		System.out.println("5 -- Exit" );
	}

	
	/**
	 * Main method acts as entry point for Marathon Application.
	 * @param args
	 */
	public static void main(String[] args) 
	{
		MarathonApplication marathon = new MarathonApplication();
		marathon.runRace();

	}

}
