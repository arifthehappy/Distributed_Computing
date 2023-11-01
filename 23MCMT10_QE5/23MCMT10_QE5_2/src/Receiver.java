import java.io.*;
import java.net.*;

public class Receiver {
    public static void main(String[] args) {
        try {
            int receiverPort = 9999;

            // Create a socket
            DatagramSocket socket = new DatagramSocket(receiverPort);

            // Receive the request DatagramPacket
            byte[] buffer = new byte[1024];
            DatagramPacket requestPacket = new DatagramPacket(buffer, buffer.length);
            socket.receive(requestPacket);

            // Deserialize the received data
            ByteArrayInputStream bais = new ByteArrayInputStream(requestPacket.getData());
            DataInputStream dis = new DataInputStream(bais);
            int num1 = dis.readInt();
            int num2 = dis.readInt();

            // Perform addition
            int result = num1 + num2;

            // Prepare data for serialization
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            DataOutputStream dos = new DataOutputStream(baos);
            dos.writeInt(result);

            // Create a new DatagramPacket with serialized result
            byte[] serializedResult = baos.toByteArray();
            DatagramPacket resultPacket = new DatagramPacket(serializedResult, serializedResult.length,
                    requestPacket.getAddress(), requestPacket.getPort());

            // Send the result DatagramPacket back to the sender
            socket.send(resultPacket);

            // Close the socket
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
