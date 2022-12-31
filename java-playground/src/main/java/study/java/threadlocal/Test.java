package study.java.threadlocal;

/*
서로 다른 Thread Local object에서 접근하면 get으로 가져올 수 있을까 ?
 */
public class Test {
    public static void main(String[] args) {
        ThreadLocal<Foo> tl1 = new ThreadLocal();
        tl1.set(new Foo(99));

        ThreadLocal<Foo> tl2 = new ThreadLocal<>();
        Foo result = tl2.get(); // null
        System.out.println("result = " + result);
    }
}
