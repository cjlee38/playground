package study.java.threadlocal.v0;

public class MyData {
    private int value = 0;

    public MyData(int value) {
        this.value = value;
    }

    public void increase() {
        this.value++;
    }
}
