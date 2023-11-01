import java.util.concurrent.*;
import java.util.HashMap;
import java.util.Map;

public class MyUDPReceiver implements Runnable {
    private MyUDPSocket udpSocket;
    private ExecutorService executorService;
    private LinkedBlockingQueue<String> packetQueue;
    private int totalMessagesReceived;
    private boolean shouldRun;

    public MyUDPReceiver(MyUDPSocket udpSocket) {
        this.udpSocket = udpSocket;
        this.executorService = Executors.newCachedThreadPool();
        this.packetQueue = new LinkedBlockingQueue<>();
        this.totalMessagesReceived = 0;
        this.shouldRun = true;
    }

    private static final int TIMEOUT_MILLIS = 5000;

    @Override
    public void run() {
        try {
        	System.out.println("Receiver thread started.");
            long startTime = System.currentTimeMillis();

            while (shouldRun || !executorService.isTerminated()) {
                String receivedMessage = udpSocket.receiveMessage();

                if (receivedMessage != null) {
                    int senderId = Integer.parseInt(receivedMessage.split(",")[0].replaceAll("[^0-9]", ""));

                    // Process each packet in a separate thread
                    executorService.submit(() -> {
                        new MyProcessPacketTask(senderId, receivedMessage).run();
                    });

                    // Push the packet to the queue along with the sender number
                    packetQueue.put(senderId + ": " + receivedMessage);

                    // Increment total messages received
                    totalMessagesReceived++;
                }

                // Check if the timeout has occurred
                if (System.currentTimeMillis() - startTime > TIMEOUT_MILLIS) {
                    shouldRun = false;
                }
            }

            // Signal the executor service to stop accepting new tasks
            executorService.shutdown();

            // Wait for the executor service to finish
            try {
                if (!executorService.awaitTermination(5000, TimeUnit.MILLISECONDS)) {
                    // Forcefully shut down the executor service if it doesn't terminate in 500 milliseconds
                    executorService.shutdownNow();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Receiver thread finished.");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Shutdown executor service when the loop exits
            executorService.shutdown();
        }
    }

    public void shutdown() {
        // Shut down the executor service
        executorService.shutdown();
    }

    public LinkedBlockingQueue<String> getPacketQueue() {
        return packetQueue;
    }

    public int getTotalMessagesReceived() {
        return totalMessagesReceived;
    }
}
