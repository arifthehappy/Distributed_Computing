package connectionOriented;

import java.io.*;


public class ServerThread implements Runnable {
	MyStreamSocket myDataSocket;

	ServerThread(MyStreamSocket myDataSocket) {
		this.myDataSocket = myDataSocket;
	}

	public void run() {
//		boolean done = false;
		String response, message;
		String messageList = "";
		int count;
		try {
			message = myDataSocket.receiveMessage();
			/**/ System.out.println("message received: " + message);
			// Now send the echo to the requester
			
			CounterServer.incrementAndAddMessage(message);
			
			messageList = CounterServer.getMessages();
			count = CounterServer.getCounter();
			
			response = count + " " + messageList;
			System.out.println(response);
			myDataSocket.sendMessage(response);
			// Session over; close the data socket.
			System.out.println("Session over for " + message);
			myDataSocket.close();
		} // end try
		catch (Exception ex) {
			System.out.println("Exception caught in thread: " + ex);
		} // end catch
	} // end run
} // end class