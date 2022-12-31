package study.java.threadlocal;

public class Test2 {
    private static final ThreadLocal<Foo> foo = new ThreadLocal<>();

    public static void main(String[] args) {
        foo.set(new Foo(987));
        new Test2().run();
    }

    private void run() {
        Thread thread = new Thread(() -> {
            System.out.println("== other thread == Test2.foo.get() = " + Test2.foo.get());
        });
        thread.start();
        System.out.println("== my thread == Test2.foo.get() = " + Test2.foo.get());
    }
}
