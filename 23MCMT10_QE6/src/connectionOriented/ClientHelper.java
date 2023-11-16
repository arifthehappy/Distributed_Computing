package connectionOriented;

import java.io.IOException;
import java.net.*;

public class ClientHelper {
	
	   static final String endMessage = ".";
	   private MyStreamSocket mySocket;
	   private InetAddress serverHost;
	   private int serverPort;
	   
	   ClientHelper(String hostName,
               String portNum) throws SocketException,
               UnknownHostException, IOException {
                               
	   this.serverHost = InetAddress.getByName(hostName);
		this.serverPort = Integer.parseInt(portNum);
	//Instantiates a stream-mode socket and wait for a connection.
		this.mySocket = new MyStreamSocket(this.serverHost,
	   this.serverPort); 
	/**/  System.out.println("Connection request made");
	} // end constructor
	   
	   public String getCount( String message) throws SocketException,
	      IOException{     
	      String recievedMessage = "";    
	      mySocket.sendMessage( message);
		   // now receive 
	      recievedMessage = mySocket.receiveMessage();
	      return recievedMessage;
	   } // end getEcho
	   
	   public void done( ) throws SocketException,
       IOException{
		mySocket.sendMessage(endMessage);
		mySocket.close( );
	   } // end done 
} //end class
