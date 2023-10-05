package syntax.receiver;

import java.util.function.IntSupplier;
import java.util.function.Supplier;

public class Receiver {

    public void invokeThis(Supplier<Integer> supplier) {
        System.out.println("wrapper style" + supplier.get());
    }

    public void invokeThis(IntSupplier supplier) {
        System.out.println("primitive style" + supplier.getAsInt());
    }
}
