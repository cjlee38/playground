package study.java.threadpool;

import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;

public class ForkJoin {
    public static void main(String[] args) {
        ForkJoinPool these  = ForkJoinPool.commonPool();
        ForkJoinPool are    = (ForkJoinPool) Executors.newWorkStealingPool();
        ForkJoinPool same   = new ForkJoinPool();
    }
}
