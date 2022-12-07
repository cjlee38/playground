package study.java.concurrency;

public class Main {
//    public static void main(String[] args) throws InterruptedException {
//        Foo foo = new Foo();
//        int threadCount = 10;
//        Runnable runnable = () -> {
//            for (int i = 0; i < 100; i++) {
//                foo.add();
//            }
//        };
//
//        Thread[] threads = createThreads(threadCount, runnable);
//        for (Thread thread : threads) {
//            thread.start();
//        }
//        for (Thread thread : threads) {
//            thread.join();
//        }
//        System.out.println("foo.bar = " + foo.bar);
//    }
//
//    private static Thread[] createThreads(int threadCount, Runnable runnable) {
//        Thread[] threads = new Thread[10];
//        for (int i = 0; i < 10; i++) {
//            threads[i] = new Thread(runnable);
//        }
//        return threads;
//    }

    private static class Foo {
        private int bar = 0;

        public void add() {
            bar++;
        }
    }
}
