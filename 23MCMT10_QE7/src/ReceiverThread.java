

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class ReceiverThread implements Runnable {
	 MulticastSocket s;
	 int Jones = 0;
	 int Smith = 0;
	 int numberOfVoters;
	 String voter = "";
	 String myName ;
	 String candidate = "";
	 static InetAddress group;
	 
	 ReceiverThread(MulticastSocket s, int num, String myName, InetAddress group){
		 this.s = s;
		 this.myName = myName;
		 this.numberOfVoters = num;
		 ReceiverThread.group = group;
	 }
	 
	 public void run() {
		 
		 int votesCasted;
		 try {
			 votesCasted = ElectionManager.getVotesCasted();
			 
			while(votesCasted < numberOfVoters-1) {
				votesCasted = ElectionManager.getVotesCasted();	
	             byte[] buf = new byte[100];
	             DatagramPacket recv = new DatagramPacket(buf, buf.length);
	        	    s.receive(recv);
	        	   // System.out.println(new String(buf));
	        	    String message = new String(buf);	   
	        	    if(message.equals("end")) {
	        	    	s.close();
	        	    	return;
	        	    }
	                // Split the string based on the colon delimiter
	                String[] parts = message.split(":");
	                // Check if there are two parts
	                if (parts.length == 2) {
	                    voter = parts[0]; // "voter"
	                    candidate = parts[1]; // "voter"                 
	                    if(candidate.trim().equals("Smith")) {
	                    	Smith++;
	                    	ElectionManager.castVote();
	                    	System.out.println(voter + " voted for " + candidate);
	                    }
	                    else if(candidate.trim().equals("Jones")) {
	                    	Jones++;
	                    	ElectionManager.castVote();
	                    	System.out.println(voter + " voted for " + candidate);
	                    }
	                    else {
	                    	System.out.println("Invalid Candidate Choice");
	                    	s.close();
	                    	return;
	                    }
	 
	                } else {
	                    System.out.println("Invalid input");
	                }
	         }// end receive
			
			String msg = "end";
	         DatagramPacket packet = 
	                 new DatagramPacket(msg.getBytes(), msg.length(),
	                     group, 3456);
	              s.send(packet);
			
			// output local result
	        System.out.println("\n"+ myName + "'s Vote Tally:");
	        System.out.println("Smith: " + Smith);
	        System.out.println("Jones: " + Jones);
	        System.out.println("------------------------");
			s.close();

		 }
		 catch(Exception ex) {
			 System.out.println("Exception caught in thread: " + ex);
		 }
	 }
}

