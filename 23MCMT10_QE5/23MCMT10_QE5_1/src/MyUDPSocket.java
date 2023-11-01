import java.net.*;

public class MyUDPSocket {
    private static DatagramSocket socket;
    private boolean closed;

    public MyUDPSocket() throws Exception {
        socket = new DatagramSocket();
        closed = false;
    }

    public void sendMessage(String message, InetAddress receiverAddress, int receiverPort) throws Exception {
        if (!closed) {
            byte[] sendData = message.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, receiverAddress, receiverPort);
            socket.send(sendPacket);
            System.out.println("Message sent to receiver: " + message);
        }
    }

    public String receiveMessage() throws Exception {
        if (!closed) {
            byte[] receiveData = new byte[1024];
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            socket.receive(receivePacket);
            return new String(receivePacket.getData(), 0, receivePacket.getLength());
        }
        return null;
    }

    public void close() {
        closed = true;
        // Do not close the socket here
    }
}
