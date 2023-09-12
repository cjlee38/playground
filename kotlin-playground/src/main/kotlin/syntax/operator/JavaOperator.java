package study.syntax.operator;

public class JavaOperator {

    private int v;

    public JavaOperator(int v) {
        this.v = v;
    }

    // public JavaOperator plus(JavaOperator other) {
    //     return new JavaOperator(this.v + other.v);
    // }

    public JavaOperator otherNamePlus(JavaOperator other) {
        return new JavaOperator(this.v + other.v);
    }

    @Override
    public String toString() {
        return "JavaOperator{" +
            "v=" + v +
            '}';
    }
}
