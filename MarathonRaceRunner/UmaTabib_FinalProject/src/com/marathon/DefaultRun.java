package com.marathon;

import java.util.*;

/**
 * Class simulating marathon race between two default runners, hare and tortoise. 
 * @author Uma
 *
 */
public class DefaultRun extends MarathonEventType {


	private static final String TORTOISE = "Tortoise";
	private static final String  HARE = "Hare";
	
	private static final int T_SPEED = 10;
	private static final int H_SPEED = 100;
	
	private static final int T_REST = 0;
	private static final int H_REST = 90;

	/**
	 * Schedule marathon run between hare and tortoise.
	 * Runner information is specified in class as instance variables.
	 * Function creates and starts thread for each runner and waits for the marathon to finish.
	 */
	public void scheduleMarathon()
	{

		//Create new threads
		Thread h = new Thread(new ThreadRunner(HARE,H_REST,H_SPEED)); 
		Thread t = new Thread(new ThreadRunner(TORTOISE,T_REST,T_SPEED));  

		ArrayList<Thread> runnerList = new ArrayList<Thread>();
		runnerList.add(h);
		runnerList.add(t);

		System.out.println("Get set...Go! ");
		//Start threads
		for (Thread i: runnerList){
			i.start();
		}

		waitMarathonFinish(runnerList);
	}


}
