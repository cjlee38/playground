package study.java.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(10);
        service.execute(() -> System.out.println("hello world"));

        Main main = new Main();
    }
}
