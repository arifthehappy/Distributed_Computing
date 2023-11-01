import java.util.Map;
import java.util.Random;
import java.net.InetAddress;

public class MyUDPSender implements Runnable {
    private MyUDPSocket udpSocket;
    private int senderId;
    private Map<Integer, Integer> senderMessageCount;

    public MyUDPSender(MyUDPSocket udpSocket, int senderId, Map<Integer, Integer> senderMessageCount) {
        this.udpSocket = udpSocket;
        this.senderId = senderId;
        this.senderMessageCount = senderMessageCount;
    }

    public void receiveResponse() {
        try {
            String response = udpSocket.receiveMessage();
            System.out.println(response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
        	System.out.println("Sender " + senderId + " thread started.");
            Random random = new Random();
            InetAddress receiverAddress = InetAddress.getByName("localhost");
            int receiverPort = 9876;

            int numPackets = random.nextInt(11) + 5; // Random number of packets between 5 and 15

            for (int i = 1; i <= numPackets; i++) {
                String message = "Sender " + senderId + ", Packet " + i;
                udpSocket.sendMessage(message, receiverAddress, receiverPort);
                senderMessageCount.put(senderId, senderMessageCount.get(senderId) + 1); // Increment message count
                Thread.sleep(100); // Add a small delay between packets
            }

            // Send the last message
            udpSocket.sendMessage("The End!", receiverAddress, receiverPort);
            System.out.println("Sender " + senderId + " thread finished.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
