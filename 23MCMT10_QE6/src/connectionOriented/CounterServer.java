package connectionOriented;

import java.io.*;
import java.net.*;

import connectionless.DatagramMessage;


public class CounterServer {
	static int counter = 0;
	static String requestMessageList = "";
	
	public static void main(String[] args) {
		int serverPort = 12345; // default port
		String message;	
		if (args.length == 1)
			serverPort = Integer.parseInt(args[0]);
		try {
			// instantiates a stream socket for accepting
			// connections
			ServerSocket myConnectionSocket = new ServerSocket(serverPort);
			/**/ System.out.println("server ready at port" + serverPort);
			while (true) { // forever loop
				// wait to accept a connection
				/**/ System.out.println("Waiting for a connection.");
				MyStreamSocket myDataSocket = new MyStreamSocket(myConnectionSocket.accept());
				/**/ System.out.println("new connection accepted");
				// Start a thread to handle this client's sesson
				Thread theThread = new Thread(new ServerThread(myDataSocket));
				theThread.start();
				// and go on to the next client
			} // end while forever
		} // end try
		catch (Exception ex) {
			ex.printStackTrace();
		} // end catch
	} // end main
	
	static public int getCounter() {
		
		return counter;
	}
	
	static public String getMessages() {
		return requestMessageList;
	}
	
	static public synchronized void incrementAndAddMessage(String requestMessage) {
		counter++;	
		if(requestMessageList == "")
			requestMessageList = requestMessage;
		else
			requestMessageList = requestMessageList + ", " + requestMessage;
	}
	
	
}
