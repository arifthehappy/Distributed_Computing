
public class DivisorFinder {

    private static final int START_RANGE = 1;
    private static final int END_RANGE = 100000;

    private static DivisorResult globalMax = new DivisorResult(0, 0);

    private static class DivisorTask implements Runnable {
        private final int start;
        private final int end;

        public DivisorTask(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public void run() {
            findMaxDivisorsInRange(start, end);
        }
    }

    public static void main(String[] args) {
        int numThreads = Runtime.getRuntime().availableProcessors();
        Thread[] threads = new Thread[numThreads];

        // Initialize globalMax with the values of the first number in the range
        globalMax = new DivisorResult(START_RANGE, countDivisors(START_RANGE));

        long startTime = System.currentTimeMillis();

        for (int i = 0; i < numThreads; i++) {
            int start = START_RANGE + i * (END_RANGE - START_RANGE) / numThreads;
            int end = i == numThreads - 1 ? END_RANGE : START_RANGE + (i + 1) * (END_RANGE - START_RANGE) / numThreads - 1;
            threads[i] = new Thread(new DivisorTask(start, end));
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

        System.out.println("Elapsed Time: " + elapsedTime + "ms");
        System.out.println("Integer with the most divisors: " + globalMax.number);
        System.out.println("Number of divisors: " + globalMax.divisorCount);
    }

    private static void findMaxDivisorsInRange(int start, int end) {
        DivisorResult localMax = new DivisorResult(0, 0);

        for (int i = start; i <= end; i++) {
            int divisors = countDivisors(i);
            if (divisors > localMax.divisorCount) {
                localMax.number = i;
                localMax.divisorCount = divisors;
            }
        }

        // Update globalMax using synchronized block
        synchronized (DivisorFinder.class) {
            if (localMax.divisorCount > globalMax.divisorCount) {
                globalMax.number = localMax.number;
                globalMax.divisorCount = localMax.divisorCount;
            }
        }
    }

    private static int countDivisors(int number) {
        if (number <= 1) {
            return 1;
        }

        int count = 2;  // 1 and the number itself

        for (int i = 2; i <= Math.sqrt(number); i++) {
            if (number % i == 0) {
                count += (i == number / i) ? 1 : 2;
            }
        }

        return count;
    }

    private static class DivisorResult {
        int number;
        int divisorCount;

        public DivisorResult(int number, int divisorCount) {
            this.number = number;
            this.divisorCount = divisorCount;
        }
    }
}
