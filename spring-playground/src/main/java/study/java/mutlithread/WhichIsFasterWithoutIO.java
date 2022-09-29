package study.java.mutlithread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * IO 작업이 존재하지 않을 때 멀티스레드 환경이 더 빠른지 확인한다.
 */
public class WhichIsFasterWithoutIO {

    private static final Logger logger = LoggerFactory.getLogger(WhichIsFasterWithoutIO.class);

    public static void main(String[] args) throws InterruptedException {

        WhichIsFasterWithoutIO object = new WhichIsFasterWithoutIO();

        int size = 10_000_000;
        int iterateCount = 10;
        object.runSingle(size, iterateCount);
        object.runMultiple(size, iterateCount);
    }

    private void runSingle(int size, int iterateCount) throws InterruptedException {
        int threadCount = 1;
        start(threadCount, size, iterateCount);
    }

    private void runMultiple(int size, int iterateCount) throws InterruptedException {
        int threadCount = 4;
        start(threadCount, size, iterateCount);
    }

    public void start(int threadCount, int size, int iterateCount) throws InterruptedException {
        long sum = 0;
        for (int iter = 0; iter < iterateCount; iter++) {
            Thread[] threads = createThreads(threadCount, size);
            long start = System.nanoTime();

            for (Thread thread : threads) {
                thread.start();
            }
            for (Thread thread : threads) {
                thread.join();
            }
            long elapsed = System.nanoTime() - start;
            sum += elapsed;
        }
        long average = sum / iterateCount;
        logger.info("threadCount = " + threadCount +
                ", size = " + size +
                ", iterateCount = " + iterateCount +
                ", average time = " + average);
    }

    private Thread[] createThreads(int threadCount, int size) {
        Thread[] threads = new Thread[threadCount];
        int from = 0;
        int unit = size / threadCount;
        for (int i = 0; i < threadCount; i++) {
            int to = from + unit;
            int finalFrom = from;
            threads[i] = new Thread(() -> calculate(finalFrom, to));
            from += unit;
        }
        return threads;
    }

    private int calculate(int from, int to) {
        int count = 0;
        for (int i = from; i < to; i += (System.currentTimeMillis() % 10 == 1) ? 1 : 0) {
            count++;
        }
        return count;
    }
}
