package study.java.threadpool;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

public class ExceptionOnSubmission {
    /*
    exception 의 sout이 출력되고, 이후 get 하려는 순간 예외가 발생한다.
    즉, try-catch 로 감싸놓았다가, future에서 get 하려는 순간 잡아놓았던 예외를 터트린다.
     */
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ThreadPoolExecutor executorService = (ThreadPoolExecutor) Executors.newFixedThreadPool(2);
        Future<String> sleep = executorService.submit(() -> sleep(1000));
        Future<String> exception = executorService.submit(() -> exception());
        sleep(1000);
        System.out.println("get");
        exception.get();
    }

    private static String exception() {
        System.out.println("exception : Thread.currentThread().getName() = " + Thread.currentThread().getName());
        throw new IllegalArgumentException("error");
    }

    private static String sleep(long millis) {
        try {
            System.out.println("sleep : Thread.currentThread().getName() = " + Thread.currentThread().getName());
            Thread.sleep(millis);
            return "sleep";
        } catch (InterruptedException e) {
            System.out.println("what?");
            throw new RuntimeException(e);
        }
    }
}
