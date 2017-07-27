package com.marathon;

/**
 * Marathon runner data access object giving access to runner information.
 * @author Uma
 *
 */
public class Runner {

	private String runnerName;
	private double runnersSpeed;
	private double restPercentage;

	
	/**
	 * Default constructor.
	 */
	public Runner()
	{
		this("", 0, 0);
	}

	
	/**
	 * Create runner DAO with name string, runnerSpeed double and restPercentage double
	 * @param runnerName - runner name
	 * @param restPercentage - runner rest percentage
	 * @param runnersSpeed - runner speed
	 */
	public Runner(String runnerName, double restPercentage, double runnersSpeed)
	{
		this.runnerName = runnerName;
		this.runnersSpeed = runnersSpeed;
		this.restPercentage = restPercentage;
	}

	/**
	 * Setter method for runner name
	 * @param runnerName
	 */
	public void setRunnerName(String runnerName)
	{
		this.runnerName = runnerName;
	}

	/**
	 * Getter method for runner name
	 * @return runnerName
	 */
	public String getRunnerName(){
		return runnerName;
	}

	/**
	 * Setter method for runner speed
	 * @param runnersSpeed
	 */
	public void setRunnersSpeed(double runnersSpeed)
	{
		this.runnersSpeed = runnersSpeed;
	}

	/**
	 * Getter method for runner speed
	 * @return runnerSpeed
	 */
	public double getRunnersSpeed()
	{
		return runnersSpeed;
	}

	
	/**
	 * Setter method for runner rest percentage
	 * @param restPercentage
	 */
	public void setRestPercentage(double restPercentage)
	{
		this.restPercentage = restPercentage;
	}

	
	/**
	 * Getter method for runner rest percentage
	 * @return restPercentage
	 */
	public double getRestPercentage()
	{
		return restPercentage;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString()
	{
		return "runnerName:        " + runnerName + "\n" +
				"runnersSpeed: " + runnersSpeed + "\n" +
				"restPercentage:       " + restPercentage + "\n";
	}
}


