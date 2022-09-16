package study.java.mutlithread;

public class WhichisFaster {


    public static void main(String[] args) throws InterruptedException {
        WhichisFaster object = new WhichisFaster();

        Thread thread = new Thread(() -> object.single());
//        Thread thread1 = new Thread(() -> object.multi(0, 250000));
//        Thread thread2 = new Thread(() -> object.multi(250001, 500000));
//        Thread thread3 = new Thread(() -> object.multi(500001, 750000));
//        Thread thread4 = new Thread(() -> object.multi(750001, 1000000));

        int count = 10;
        long total = 0;
        for (int i = 0; i < count; i++) {
            long start = System.nanoTime();
            thread.run();
            thread.join();

//            thread1.run();
//            thread2.run();
//            thread3.run();
//            thread4.run();
//
//            thread1.join();
//            thread2.join();
//            thread3.join();
//            thread4.join();

            long elapsed = System.nanoTime() - start;
            total += elapsed;
        }
        long result = total / count;
        System.out.println("result = " + result);
    }


    public int single() {
        int count = 0;
        for (int i = 0; i < 1000000; i++) {
            count++;
        }
        return count;
    }

    public int multi(int start, int end) {
        int count = 0;
        for (int i = start; i < end; i++) {
            count++;
        }
        return count;
    }
}
