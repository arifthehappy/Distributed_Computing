public class MyProcessPacketTask implements Runnable {
    private int senderId;
    private String packet;

    public MyProcessPacketTask(int senderId, String packet) {
        this.senderId = senderId;
        this.packet = packet;
    }

    @Override
    public void run() {
        // Process the packet (you can add your processing logic here)
        System.out.println("Processing packet from Sender " + senderId + ": " + packet);
    }
}
