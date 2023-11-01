import java.io.*;
import java.net.*;

public class Sender {
    public static void main(String[] args) {
        try {
            InetAddress receiverAddress = InetAddress.getLocalHost();
            int receiverPort = 9999;

            // Create a socket
            DatagramSocket socket = new DatagramSocket();

            // Prepare data for serialization
            int num1 = 10;
            int num2 = 20;

            // Serialize data
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            DataOutputStream dos = new DataOutputStream(baos);
            dos.writeInt(num1);
            dos.writeInt(num2);

            // Create a DatagramPacket with serialized data
            byte[] serializedData = baos.toByteArray();
            DatagramPacket requestPacket = new DatagramPacket(serializedData, serializedData.length,
                    receiverAddress, receiverPort);

            // Send the request DatagramPacket
            socket.send(requestPacket);

            // Receive the result DatagramPacket
            byte[] buffer = new byte[1024];
            DatagramPacket resultPacket = new DatagramPacket(buffer, buffer.length);
            socket.receive(resultPacket);

            // Deserialize the received data
            ByteArrayInputStream bais = new ByteArrayInputStream(resultPacket.getData());
            DataInputStream dis = new DataInputStream(bais);
            int result = dis.readInt();

            // Display the result
            System.out.println("Result from Receiver: " + result);

            // Close the socket
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
