
import java.net.*;
import java.io.*;

public class OpenCommercial {
    public static void main(String[] args) throws Exception {
        BufferedReader keyboard;
        String x; 
        System.out.print("Please input x:");
        keyboard = new BufferedReader(new InputStreamReader(System.in));
        x = keyboard.readLine();
        URL url = new URL("http://www." + x +".com");
        keyboard = new BufferedReader(new InputStreamReader(url.openStream()));
        String str[] = new String[5];
        for(int i = 0; i < 5; i++) 
            str[i] = keyboard.readLine();
        for(int i = 4; i >= 0; i--)
            System.out.println(str[i]);
    }
}
		