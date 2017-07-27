package com.marathon;

import java.util.*;

/**
 * Class Simulating marathon race between multiple participants.
 * All runner information is fetched from derby database - FinalDB placed under 'Resources' folder of eclipse project.
 * @author Uma
 *
 */

public class DBRun extends MarathonEventType{


	/**
	 * Function that uses function of DBHelper class to connect to FinalDB (database) and get runners information.
	 * @return ArrayList consisting of Runner DAOs'
	 */
	private ArrayList<Runner> fetchRunnerData()
	{
		DBHelper dbHelper = new DBHelper();
		ArrayList<Runner> r = dbHelper.fetchDBRunners();
		return r;
	}

	/**
	 * Schedule marathon for runners whose data is fetched from database. 
	 * Function creates and starts thread for each runner and waits for the marathon to finish.
	 */
	public void scheduleMarathon()
	{
		ArrayList<Runner> runnerInfo = fetchRunnerData();

		if(runnerInfo == null || runnerInfo.isEmpty())
		{
			System.out.println("Error: Problem getting runner information from FinalDB-Database. Please check database and content for validity !!");
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

			//create new thread.
			Thread t = new Thread(new ThreadRunner(name,rest,speed));

			runnerList.add(t); 
		}
		
		System.out.println("Get set...Go! ");
		// start threads.
		for (Thread i: runnerList){
			i.start();
		}

		waitMarathonFinish(runnerList);

	}

}
