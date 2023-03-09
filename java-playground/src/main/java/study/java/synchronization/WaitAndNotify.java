package study.java.synchronization;

import lombok.SneakyThrows;

public class WaitAndNotify {

    static class Example {
        int value = 0;

        @SneakyThrows
        public synchronized void increment() {
            String name = Thread.currentThread().getName();
            int threadNumber = name.charAt(name.length() - 1) - 48;
            while (threadNumber != value) {
                System.out.println(threadNumber + " : release");
                wait();
            }
            value++;
            System.out.println(threadNumber + " notify");
            notifyAll();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Example example = new Example();
        Thread[] threads = new Thread[10];
        for (int i = 0; i < 10; i++) {
            threads[i] = new Thread(() -> example.increment());
            threads[i].start();
        }
        for (Thread thread : threads) {
            thread.join();
        }
        System.out.println(example.value);
    }
}

