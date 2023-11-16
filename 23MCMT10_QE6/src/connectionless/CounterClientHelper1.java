package connectionless;

import java.net.InetAddress;

public class CounterClientHelper1 {
	public static String getCounter(String hostName, String portNum, String message) {
		String response = "";
		try {
			InetAddress serverHost = InetAddress.getByName(hostName);
			int serverPort = Integer.parseInt(portNum);
			// instantiates a datagram socket for both sending
			// and receiving data
			MyClientDatagramSocket mySocket = new MyClientDatagramSocket();
			mySocket.sendMessage(serverHost, serverPort, message);
			// now receive the response
	
			 response = mySocket.receiveMessage();
			/**/ System.out.println("Message received: " + response);

			mySocket.close();
		}// end try
		catch(Exception ex) {
			ex.printStackTrace();
		}//end catch
		return response;
	}//end main
}// end class
