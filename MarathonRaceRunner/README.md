Project: Marathon race runners

Summary:
 The intent of this application is to simulate a marathon race for a sports company.

Input:  
runner's name, speed, rest time.

Data sources: 
input data comes from data sources like derby database, xml file, text file, user input(stdio) and default.

Implementation:

- Application is implemented using multi threaded concepts in java.
- A ThreadRunner class implements Runnable Interface and it run() method implements the runner's behaviour.
- Abstract class MarathonEventType has abstract method schduleMarathon().

  Different classes according to datasources extend the MarathonEventType class to implement the   scheduleMarathon().

 This method starts the runner thread and calls waitMarathonFinish(). This method checks if any of the runners have completed the race using isAlive() of thread.
 
 Once the first runner thread completes the race, a synchronized method (synchronized finish()) is called which will declare the completed runner thread as winner.
