package connectionOriented;

import java.io.*;

public class Client {
	
	   public static void main(String[] args) {
	      InputStreamReader is = new InputStreamReader(System.in);
	      BufferedReader br = new BufferedReader(is);
	      try {
	         System.out.println("Welcome to the Counter client.\n" +
	            "What is the name of the server host?");
	         String hostName = br.readLine();
	         if (hostName.length() == 0) // if user did not enter a name
	            hostName = "localhost";  //   use the default host name
	         System.out.println("What is the port number of the server host?");
	         String portNum = br.readLine();
	         if (portNum.length() == 0)
	            portNum = "12345";          // default port number
	         ClientHelper helper = 
	            new ClientHelper(hostName, portNum);
	         
	         String message, response;

	         System.out.println("Enter your message: ");
	         message = br.readLine( );

	            
	         response = helper.getCount( message);
	               System.out.println("Here is the counter received from the server: "
	            		   + response );
	        

	      } // end try  
	      catch (Exception ex) {
	         ex.printStackTrace( );
	      } //end catch
	   } //end main
	} // end class