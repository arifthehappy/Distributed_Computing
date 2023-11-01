// package Q4;
import java.util.concurrent.atomic.AtomicLong;

public class MonteCarloPi {

    private static final int TOTAL_POINTS = 1000000;

    public static void main(String[] args) {
        int numThreads = 4; // Default number of threads
        int[] pointsPerThread = {TOTAL_POINTS / numThreads}; // Points per thread

        if (args.length >= 1) {
            numThreads = Integer.parseInt(args[0]);
            pointsPerThread[0] = TOTAL_POINTS / numThreads;
        }

        AtomicLong[] insideCircleResults = new AtomicLong[numThreads];
        Thread[] threads = new Thread[numThreads];

        long startTime = System.currentTimeMillis();

        for (int i = 0; i < numThreads; i++) {
            insideCircleResults[i] = new AtomicLong(0);

            final int threadIndex = i;
            threads[i] = new Thread(() -> {
                long insideCircle = simulatePointsInsideCircle(pointsPerThread[0]);
                insideCircleResults[threadIndex].addAndGet(insideCircle);
            });
            threads[i].start();
        }

        // Wait for all threads to finish
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        long endTime = System.currentTimeMillis();
        long elapsedTime = endTime - startTime;

        long totalInsideCircle = 0;
        for (AtomicLong result : insideCircleResults) {
            totalInsideCircle += result.get();
        }

        double piEstimate = 4.0 * totalInsideCircle / TOTAL_POINTS;

        System.out.println("Elapsed Time: " + elapsedTime + "ms");
        System.out.println("Estimated value of π: " + piEstimate);
    }

    private static long simulatePointsInsideCircle(int numPoints) {
        long insideCircle = 0;

        for (int i = 0; i < numPoints; i++) {
            double x = Math.random();
            double y = Math.random();

            if (x * x + y * y < 1) {
                insideCircle++;
            }
        }

        return insideCircle;
    }
}
