package com.marathon;

import java.util.*;
import java.io.*;
import java.nio.file.*;

import javax.xml.stream.*;  // StAX API

/**
 * Class Simulating marathon race between multiple participants.
 * All runner information is fetched from xml file placed under 'Resources' folder of eclipse project.
 * @author Uma
 *
 */
public class XMLRun extends MarathonEventType {

	private Path runnersPath = null;
	private File runnerFile = null;
	private ArrayList<Runner> runners = null;

	/**
	 * Default constructor that considers FinalXMLData.xml for getting runners information.
	 */
	public XMLRun()
	{
		runnersPath = Paths.get("Resources/FinalXMLData.xml");
		runnerFile = runnersPath.toFile();
	}

	/**
	 * constructor that considers user input xml file for getting runners information.
	 * @param fileName - file placed under 'Resources' folder of eclipse project
	 */
	public XMLRun(String fileName)
	{
		runnersPath = Paths.get("Resources/" + fileName);
		runnerFile = runnersPath.toFile();
	}

	/**
	 * Function that parses xml file to get runners information.
	 * @return ArrayList consisting of Runner DAOs'
	 */
	private ArrayList<Runner> fetchRunnerData()
	{
		// if the XML file has already been read, don't read it again
		if (runners != null)
			return runners;        

		runners = new ArrayList<>();        
		Runner r = null;        
		
		// prevent the FileNotFoundException
		if (Files.exists(runnersPath))  
		{
			// create the XMLInputFactory object
			XMLInputFactory inputFactory = XMLInputFactory.newFactory();
			try
			{
				// create a XMLStreamReader object
				FileReader fileReader = new FileReader(runnerFile);
				XMLStreamReader reader = inputFactory.createXMLStreamReader(fileReader);

				// read the products from the file
				while (reader.hasNext())
				{
					int eventType = reader.getEventType();

					switch (eventType)
					{
					case XMLStreamConstants.START_ELEMENT:

						String elementName = reader.getLocalName();

						if (elementName.equals("Runner"))
						{
							r = new Runner();
							String name = reader.getAttributeValue(0);
							r.setRunnerName(name);
						}
						if (elementName.equals("RunnersMoveIncrement"))
						{
							String speed = reader.getElementText();
							double dspeed = Double.parseDouble(speed);
							r.setRunnersSpeed(dspeed);
						}
						if (elementName.equals("RestPercentage"))
						{
							String restPercent = reader.getElementText();
							double dRest = Double.parseDouble(restPercent);
							r.setRestPercentage(dRest);
						}
						break;
					case XMLStreamConstants.END_ELEMENT:
						elementName = reader.getLocalName();
						if (elementName.equals("Runner"))
						{
							runners.add(r);
						}
						break;
					default:
						break;
					}
					reader.next();
				}
			}catch (IOException | XMLStreamException e){
				System.out.println(e.toString());
			}catch(Exception ex){
				System.out.println("Error:" + ex.toString());
			}
		}
		return runners;
	}

	/**
	 * Schedule marathon for runners whose data is fetched from xml file. 
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
