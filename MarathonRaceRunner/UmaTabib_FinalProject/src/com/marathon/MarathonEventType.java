package com.marathon;

import java.util.ArrayList;

/**
 * Abstract class defining methods for running marathon event.
 * @author Uma
 *
 */
public abstract class MarathonEventType {

	/**
	 * Abstract method that should implement marathon event type based on specific data source for
	 * runners information.
	 * 
	 */
	public abstract void scheduleMarathon();


	/**
	 * Method accepts thread objects simulating marathon runners.
	 * Calls finish method when first thread completes the race.
	 * 
	 * @param runnerList - List of thread objects simulating marathon runners 
	 */
	protected void waitMarathonFinish(ArrayList<Thread>runnerList)
	{
		boolean marathonFinished = false;
		while(true)
		{
			for(Thread i : runnerList){
				if(!i.isAlive())
				{
					finish(i,runnerList);
					marathonFinished = true;
					break;
				}
			}
			if(marathonFinished)
				break;
		}
	}

	/**
	 * Method declares winner thread as marathon winner and calls interrupt() method on any other threads still alive.
	 * Any other thread which is not alive also concedes to avoid a tie result.
	 *   
	 * @param winner - First thread completing marathon
	 * @param runnerList - List of thread objects simulating runners in marathon.
	 */
	protected synchronized void finish(Thread winner, ArrayList<Thread>runnerList)
	{
		System.out.println("The race is over! The " + winner.getName() + " is the winner.");
		for (Thread thread: runnerList){
			if(!thread.getName().equals(winner.getName())){
				if(thread.isAlive())
				{
					thread.interrupt();
				}
				else
				{
					thread.interrupt();
					System.out.println(thread.getName() + ": You beat me fair and square.");
				}

			}
		}
	}

}
