package study.java.synchronization;

import java.util.Random;

public class Synchronization {
    /*
    싱글스레드일때 synchronized를 걸면 바이트코드는 ?
    속도에서는 별 차이가 없는 것 같다.
    synchronized 키워드가 byte code에 박혀버린다. 이거는 native level에서 뭔가 다른게 있을 듯
    */

    public static void main(String[] args) {
        Synchronization synchronization = new Synchronization();
        long sum = 0;
        for (int i = 0; i < 10; i++) {
            long start = System.nanoTime();
            synchronization.hello();
            long elapsed = System.nanoTime() - start;
            sum += elapsed;
        }
        System.out.println("elapsed = " + (sum / 10));
    }

    public void hello() {
        int a = 1;
        a += 1;
        Random random = new Random();
        for (int i = 0; i < 10000; i++) {
            a += random.nextInt();
        }
    }
}
