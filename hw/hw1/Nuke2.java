import java.io.*;

public class Nuke2 {
    public static void main(String[] args) throws Exception{
        System.out.println("Please input a string:");
        BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
        String str = keyboard.readLine();
        System.out.println(str.substring(0,1) + str.substring(2));
    }
}
        