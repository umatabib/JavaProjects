package com.marathon;

import java.util.Random;


/**
 * ThreadRunner class simulates run for marathon runners.
 * @author Uma
 *
 */

public class ThreadRunner implements Runnable {

	private String name;
	private int rest;
	private int speed;
	private int distanceCovered;

	/**
	 * Creates ThreadRunner instance from a string runner name , int runner's rest percentage and int runner's speed.
	 * @param rName - runner name
	 * @param rRest - runner rest percentage
	 * @param rSpeed - runner speed
	 */
	public ThreadRunner(String rName, int rRest, int rSpeed) 
	{
		this.name = rName;
		this.rest = rRest;
		this.speed = rSpeed;
		distanceCovered = 0;
	}


	/**
	 * ThreadRunner method that simulates runner's marathon run.
	 * Generates a random number and if generated random number is less than  equal to rest percent then runner will rest.
	 * Else will cover the distance equal to runner's speed.
	 * If interrupted, runner will concede the race.
	 */
	@Override
	public void run() {

		Thread.currentThread().setName(name); // set thread name as runner's name.

		Random r = new Random();

		int Low = 1;
		int High = 100;
		int randomNumber;

		try
		{		    
			while(distanceCovered<1000)
			{
				randomNumber = r.nextInt(High-Low) + Low; //generate random number

				if(randomNumber > rest)
				{
					distanceCovered += speed;
					System.out.println(name + "   :  " + distanceCovered);
				}
				Thread.sleep(100);          // delay 100 milliseconds    
			}
			
		}
		catch (InterruptedException e)
		{
			System.out.println(name + ": You beat me fair and square.");
		}

	}

}
