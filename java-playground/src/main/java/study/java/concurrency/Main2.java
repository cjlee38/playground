package study.java.concurrency;

public class Main2 {

    public static void main(String[] args) throws InterruptedException {
        /*
        synchronized는 code가 아닌 객체에 대해 걸리기 때문에, 서로다른 메소드를 호출해도 동기화가 이루어진다.
         */
        Foo foo = new Foo(0);
        Thread threadA = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                foo.increase();
            }
        });

        Thread threadB = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                foo.decrease();
            }
        });

        threadA.start();
        threadB.start();
        threadA.join();
        threadB.join();
        System.out.println(foo.bar);
    }

    private static class Foo {
        private int bar;

        public Foo(int bar) {
            this.bar = bar;
        }

        public synchronized void increase() {
            bar++;
        }

        public synchronized void decrease() {
            bar--;
        }
    }
}
