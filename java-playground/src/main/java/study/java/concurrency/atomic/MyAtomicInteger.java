package study.java.concurrency.atomic;

public class MyAtomicInteger {

    public static void main(String[] args) throws InterruptedException {

        MyThreadSafeInteger myThreadSafeInteger = new MyThreadSafeInteger(0);

        Thread[] threads = new Thread[10];
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(() -> {
                for (int j = 0; j < 100; j++) {
                    myThreadSafeInteger.increment();
                }
            });
            threads[i] = thread;
            thread.start();
        }
        for (Thread thread : threads) {
            thread.join();
        }
        System.out.println(myThreadSafeInteger.getValue());
    }

    static class MyThreadSafeInteger {
        private volatile int value;

        public MyThreadSafeInteger(int value) {
            this.value = value;
        }

        public void increment() {
            setValue(getValue() + 1);
        }

        public int getValue() {
            return value;
        }

        public void setValue(int newValue) {
            while (true) {
                int currentValue = value;
                if (compareAndSet(currentValue, newValue)) {
                    return;
                }
            }
        }

        private synchronized boolean compareAndSet(int expectedValue, int newValue) {
            if (value == expectedValue) {
                value = newValue;
                return true;
            } else {
                return false;
            }
        }
    }

}

