package connectionless;

import java.io.*;

public class CounterClient {
	public static void main(String[] args) {
		InputStreamReader is =new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(is);
		try {
			System.out.println("Welcome to the counter client. \n"
							+ "What is the name of the server host");
			String hostName = br.readLine();
			if(hostName.length() == 0) {
				hostName = "localhost"; // default host name
			}
			System.out.println("Enter the port # of the server host");
			String portNum = br.readLine();
			if(portNum.length() == 0) {
				portNum = "12345"; // default port number
			}
			
			System.out.println("Enter your message: ");
			String message = br.readLine();
			String response = CounterClientHelper1.getCounter(hostName, portNum, message);
			System.out.println("Here is the counter received from the server: "
					+ response );
			
		}//end try
		catch(Exception ex) {
			ex.printStackTrace();
		}// end catch
	}//end main

} // end class
