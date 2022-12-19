package study.java.threadpool;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class ExceptionOnExecution {
    /**
     * 실행후 스레드 번호를 확인해보자.
     * 스레드풀 사이즈는 2임에도 불구하고 3번이 새로 생긴다.
     */
    public static void main(String[] args) {
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(2);
        executor.execute(() -> sleep(1000));
        executor.execute(() -> exception());
        executor.execute(() -> sleep(1000));
        executor.shutdown();
        System.out.println("executor.getPoolSize() = " + executor.getPoolSize());
    }

    private static void exception() {
        System.out.println("exception : Thread.currentThread().getName() = " + Thread.currentThread().getName());
        throw new IllegalArgumentException("error");
    }

    private static void sleep(long millis) {
        try {
            System.out.println("sleep : Thread.currentThread().getName() = " + Thread.currentThread().getName());
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            System.out.println("what?");
            throw new RuntimeException(e);
        }
    }
}
