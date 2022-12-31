package study.java.atomic;

import java.util.concurrent.CountDownLatch;
import java.util.stream.IntStream;

public class CustomAtomic {

    private volatile int v;

    public CustomAtomic(int v) {
        this.v = v;
    }

    public int increase(int v) {
        int current;
        int next;
        do {
            current = this.v;
            next = current + v;
        } while (!compareAndSet(current, next));
        return next;
    }

    private boolean compareAndSet(int current, int next) {
        if (this.v == current) {
            this.v = next;
            return true;
        }
        return false;
    }

    public static void main(String[] args) throws InterruptedException {
        CustomAtomic atomic = new CustomAtomic(0);
        CountDownLatch latch = new CountDownLatch(4);
        IntStream.range(0, 4)
                .mapToObj(it -> new Thread(() -> {
                    for (int i = 0; i < 1000; i++) {
                        atomic.increase(1);
                    }
                    latch.countDown();
                }))
                .forEach(Thread::start);

        latch.await();
        System.out.println("atomic.v = " + atomic.v);
    }
}
