package syntax.wrappertype;

interface JavaGenerator<T> {
    T generate();
}

class IntJavaGenerator implements JavaGenerator<Integer> {

    @Override
    public Integer generate() {
        return 42;
    }
}

class VoidJavaGenerator implements JavaGenerator<Void> {

    @Override
    public Void generate() {
        System.out.println("void generator called and i wanna return null");
        return null; // 코틀린과 다르게 null을 반드시 명시해야 한다.
    }
}
public class UnitWithGeneric {
    public static void main(String[] args) {
        final VoidJavaGenerator generator = new VoidJavaGenerator();
        final Void generate = generator.generate();
        System.out.println(generate);
    }
}
