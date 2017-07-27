package com.marathon;
import java.util.List;
import java.util.Scanner;

/**
 * Validator Class performs validations to check given inputs by application user.
 * @author Uma
 *
 */

public class Validator
{
    /**
     * Read entire line of user input.
     * @param sc - scanner object.
     * @param prompt - prompt options for user.
     * @return user input string.
     */
    public static String getLine(Scanner sc, String prompt)
    {
        System.out.print(prompt);
        String s = sc.nextLine();        // read the whole line
        return s;
    }

    /**
     * Read first string of user input and discard the rest of the line.
     * @param sc - scanner object.
     * @param prompt - prompt options for user.
     * @return user input string.
     */
    public static String getString(Scanner sc, String prompt)
    {
        System.out.print(prompt);
        String s = sc.next();        // read the first string on the line
        sc.nextLine();               // discard the rest of the line
        return s;
    }
    
    /**
     * Method to display prompt message to user and accept valid inputs only.
     * @param sc - scanner object
     * @param prompt - prompt options for user
     * @param expectedAns - expected valid input(s) from user
     * @return valid input entered by user
     */
    public static String getString(Scanner sc, String prompt, List<String> expectedAns)
    {
        
        String answer = "";
        while(true)
        {
        	System.out.print(prompt);
        	answer = sc.next();          // read the first string on the line
        	sc.nextLine();               // discard any other data entered on the line
        	
        	if(!expectedAns.contains(answer.toLowerCase()))		// Check answer entered by user is valid answer or not.
        	{
        		System.out.println("Error! Invalid entry. Try again.");
        		System.out.println();
        		continue;
        	}
        	else
        	{
        		break;
        	}	
        }
        return answer;
    }

}