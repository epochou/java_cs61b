/* OpenCommercial.java */

import java.net.*;
import java.io.*;

/**  A class that provides a main function to read five lines of a commercial
 *   Web page and print them in reverse order, given the name of a company.
 */
 
public class OpenCommercial {
	/** Prompts the user for the name X of a company (a single string), opens
	 *  the Web site corresponding to www.X.com, and prints the first five lines
	 *  of the Web page in reverse order.
	 *  @param arg is not used.
	 *  @exception Exception thrown if there are any problems parsing the 
	 *             user's input or opening the connection.
	 */
    public static void main(String[] args) throws Exception {
	
        BufferedReader keyboard;
        String x; 
		
        System.out.print("Please input x:");
        keyboard = new BufferedReader(new InputStreamReader(System.in));
        x = keyboard.readLine();
        URL url = new URL("http://www." + x +".com");
        keyboard = new BufferedReader(new InputStreamReader(url.openStream()));
		
		// use a String array to print it reversely.
        String str[] = new String[5];
        for(int i = 0; i < 5; i++) 
            str[i] = keyboard.readLine();
        for(int i = 4; i >= 0; i--)
            System.out.println(str[i]);
    }
}
		