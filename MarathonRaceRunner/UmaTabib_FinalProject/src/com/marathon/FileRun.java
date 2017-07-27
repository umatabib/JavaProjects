package com.marathon;
import java.util.*;
import java.io.*;
import java.nio.file.*;

/**
 * Class Simulating marathon race between multiple participants.
 * All runner information is fetched from text file placed under 'Resources' folder of eclipse project.
 * @author Uma
 *
 */
public class FileRun extends MarathonEventType {

	private ArrayList<Runner> runners = null;
	private Path runnerPath = null;
	private File runnerFile = null;

	private final String FIELD_SEP = "\t";

	/**
	 * Default constructor that considers FinalTextData.txt for getting runners information.
	 */
	public FileRun()
	{
		runnerPath = Paths.get("Resources/FinalTextData.txt");
		runnerFile = runnerPath.toFile();
	}

	/**
	 * constructor that considers user input text file for getting runners information.
	 * @param fileName - File placed under 'Resources' folder of eclipse project
	 */
	public FileRun(String fileName)
	{
		runnerPath = Paths.get("Resources/" + fileName);
		runnerFile = runnerPath.toFile();
	}


	/**
	 * Function that parses text file to get runners information.
	 * @return ArrayList consisting of Runner DAOs'
	 */
	private ArrayList<Runner> fetchRunnerData()
	{
		// if the runner file has already been read, don't read it again
		if (runners != null)
			return runners;        

		runners = new ArrayList<>();        

		if (Files.exists(runnerPath))  // prevent the FileNotFoundException
		{

			try (BufferedReader in = new BufferedReader(new FileReader(runnerFile))){
				// read all runner info stored in the file into the array list
				String line = in.readLine();
				while(line != null)
				{
					String[] columns = line.split(FIELD_SEP);
					String name = columns[0];
					String speed = columns[1];
					String restPercent = columns[2];

					Runner r = new Runner(name, Double.parseDouble(restPercent), Double.parseDouble(speed));

					runners.add(r);

					line = in.readLine();                    
				}
			}
			catch(IOException ie){
				System.out.println("Error:" + ie.toString());
			}catch(Exception e){
				System.out.println("Error:" + e.toString());
			}
		}
		return runners;            
	}

	/**
	 * Schedule marathon for runners whose data is fetched from text file. 
	 * Function creates and starts thread for each runner and waits for the marathon to finish.
	 */
	public void scheduleMarathon()
	{
		ArrayList<Runner> runnerInfo = fetchRunnerData();

		if(runnerInfo == null || runnerInfo.isEmpty())
		{
			System.out.println("Error: Problem getting runner information from input file. Please check file name and content for validity !!");
			return;
		}

		ArrayList<Thread> runnerList = new ArrayList<Thread>();
		int arrSize = runnerInfo.size();
		for(int i=0;i<arrSize;i++)
		{
			Runner runner = runnerInfo.get(i);

			String name= runner.getRunnerName();
			int rest = (int)runner.getRestPercentage();
			int speed = (int)runner.getRunnersSpeed();

			//create new thread
			Thread t = new Thread(new ThreadRunner(name,rest,speed));

			runnerList.add(t);
		}
		
		System.out.println("Get set...Go! ");
		// start threads
		for (Thread i: runnerList){
			i.start();
		}

		waitMarathonFinish(runnerList);
	}

}
