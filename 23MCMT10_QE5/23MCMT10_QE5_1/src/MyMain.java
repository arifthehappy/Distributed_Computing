import java.net.*;
import java.util.*;
import java.util.concurrent.*;

public class MyMain {
    public static void main(String[] args) {
        try {
            MyUDPSocket udpSocket = new MyUDPSocket();

            // Create receiver thread
            MyUDPReceiver udpReceiver = new MyUDPReceiver(udpSocket);
            Thread receiverThread = new Thread(udpReceiver);

            // Create and start multiple sender threads
            Map<Integer, Integer> senderMessageCount = new HashMap<>();
            List<Thread> senderThreads = new ArrayList<>();
            
            // Start the receiver thread
            receiverThread.start();
            
            for (int i = 1; i <= 3; i++) {
                senderMessageCount.put(i, 0); // Initialize message count for each sender
                Thread senderThread = new Thread(new MyUDPSender(udpSocket, i, senderMessageCount));
                senderThreads.add(senderThread);
                senderThread.start();
            }

            

            // Wait for sender and receiver threads to finish
            for (Thread senderThread : senderThreads) {
                senderThread.join();
            }

         

            // Send response to all senders with message count
            for (int senderId : senderMessageCount.keySet()) {
                int messagesSentBySender = senderMessageCount.get(senderId);
                String responseMessage = "Response to Sender " + senderId + ": Messages sent by you - " +
                        messagesSentBySender + ", Total messages - " + udpReceiver.getTotalMessagesReceived();
                udpSocket.sendMessage(responseMessage, InetAddress.getByName("localhost"), 9876);
                System.out.println(responseMessage);
            }
            
            // Wait for the receiver thread to finish
            receiverThread.join();
            
            

            // Print all messages received by the receiver
            System.out.println("All messages received by the receiver:");
            while (!udpReceiver.getPacketQueue().isEmpty()) {
                System.out.println(udpReceiver.getPacketQueue().poll());
            }
            
         

            // Close the socket
            udpSocket.close();
            
         

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
