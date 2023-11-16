package connectionless;

import java.io.*;
import java.net.*;

public class MyServerDatagramSocket extends DatagramSocket {
	static final int MAX_LEN = 100;
	MyServerDatagramSocket(int portNo) throws SocketException{
		super(portNo);
	}
	
	public void sendMessage(InetAddress receiverHost,
						int receiverPort,
						String message) 
		throws IOException {
//
		System.out.println("message send " + message);
		byte[] sendBuffer = message.getBytes();
		DatagramPacket datagram = 
				new DatagramPacket(sendBuffer, sendBuffer.length,
						receiverHost, receiverPort);
		this.send(datagram);
		
	}// end sendMessage
	

	public DatagramMessage receiveMessageAndSender() throws IOException{
		byte[ ] receiveBuffer = new byte[MAX_LEN];
		DatagramPacket datagram = 
				new DatagramPacket(receiveBuffer, MAX_LEN);
		this.receive(datagram);
		// create a datagramMessage object to contain
		// message received and senders address
		DatagramMessage returnVal = new DatagramMessage();
		returnVal.putVal(new String(receiveBuffer),
						datagram.getAddress(),
						datagram.getPort());
		return returnVal;
	}// end receiveMessage
}// end class

