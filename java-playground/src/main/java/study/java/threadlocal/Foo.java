package study.java.threadlocal;

public class Foo {
    private int v;

    public Foo(int v) {
        this.v = v;
    }

    @Override
    public String toString() {
        return "Foo{" +
                "v=" + v +
                '}';
    }
}
