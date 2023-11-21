import java.io.*; 
import java.net.*;
import java.util.Scanner;


public class ElectionManager {

   static MulticastSocket s;
   static InetAddress group;
   static int numberOfVoters = 3;
   static int votesCasted = 0;
   public static void main(String[] args) {

      try {      
 
    	 Scanner scan = new Scanner(System.in);
         group = InetAddress.getByName("239.1.2.3");
         s = new MulticastSocket(3456);
         System.out.println("Joined group at 239.1.2.3 port 3456");
         s.joinGroup(group);
         System.out.println(s.getLocalPort() + " is connected");
         System.out.println("\n-------Welcome to Election Booth--------");
         System.out.println("Candidates List\n1. Smith\n2. Jones");
         System.out.println("Total Number of voters = " + numberOfVoters + "\n"
        		 			+ "----------------------------------------");
         System.out.println("Enter voter name");
         String voter = scan.nextLine();
         // start receiving
         Thread theThread = new Thread(new ReceiverThread(s, numberOfVoters, voter,  group));
         theThread.start();
         // take vote cast
         System.out.println("Enter your vote choice Name \n 1. Smith\n 2. Jones");
         String myvote = scan.nextLine();
         scan.close();
         
         //send voter and vote multicast
         String vote = myvote;
         String msg = voter+":"+vote;
         DatagramPacket packet = 
            new DatagramPacket(msg.getBytes(), msg.length(),
                group, 3456);
         s.send(packet);
       
       }
       catch (Exception ex) { // here if an error has occurred
          ex.printStackTrace( ); 
       }
    }// end main
   
   static public int getVotesCasted() {
	   return votesCasted;
   }
   
   static public synchronized void castVote() {
	   votesCasted++;
   }
}// end class
 

