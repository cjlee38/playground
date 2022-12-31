package study.java.concurrency;

import java.lang.Thread.State;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Foo foo = new Foo();

        Thread threadA = new Thread(() -> foo.add());
        Thread threadB = new Thread(() -> foo.add());

        threadA.start();
        threadB.start();

        while (true) {
            State stateA = threadA.getState();
            State stateB = threadB.getState();
            System.out.println("stateA = " + stateA);
            System.out.println("stateB = " + stateB);
            System.out.println("======================");
            if (stateA == State.TERMINATED && stateB == State.TERMINATED) break;
        }

        System.out.println(foo.bar);
    }

    private static class Foo {
        private int bar = 0;

        public void add() {
            synchronized (this) {
                System.out.println("increaseed by " + Thread.currentThread().getName());
                bar++;
                notifyAll();
            }
        }
    }
}
