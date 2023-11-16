package connectionless;

import java.io.*;
import java.util.ArrayList;

@SuppressWarnings("unused")
public class CounterServer {
	
	static int counter = 0;
	static String requestMessageList = "";
	
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		int serverPort = 12345; // default port
		if(args.length == 1)
			serverPort = Integer.parseInt(args[0]);
		try {
			// instantiate a datagram socket for both sending
			// and receiving data
			MyServerDatagramSocket mySocket = new MyServerDatagramSocket(serverPort);
			System.out.println("Counter server ready at port " + serverPort);
			
			while(true) {
				DatagramMessage request = mySocket.receiveMessageAndSender();
				System.out.println("Request received: " + request.getMessage());
				
				incrementAndAddSender(request);
				
				// Use String.join to concatenate strings with commas
//		        String requesterList = String.join(", ", requestMessageList);
//		        System.out.println("requesterList: " + requesterList);
		        
		        // Create a Response object
		       // String response = new ResponseMessage(counter, requestMessageList);
		        String response = String.valueOf(counter) + " " + requestMessageList;
				System.out.println("counter sent: " + counter);
				//String message = String.valueOf(counter) + requesterList;
				// send the reply to requester
				mySocket.sendMessage(request.getAddress(),
									request.getPort(),
									response);
			}//end while
		} // end try
		catch(Exception ex){
			ex.printStackTrace();
		}//end catch
	} //end main
	
	static private synchronized void incrementAndAddSender(DatagramMessage request) {
		counter++;	
		if(requestMessageList == "")
			requestMessageList = request.getMessage();
		else
			requestMessageList = requestMessageList + ", " + request.getMessage();
	}
	
	
}// end class
