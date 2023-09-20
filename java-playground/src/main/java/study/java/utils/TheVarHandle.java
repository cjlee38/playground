package study.java.utils;

import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodHandles.Lookup;
import java.lang.invoke.VarHandle;

/**
 * @see java.util.concurrent.FutureTask
 */
public class TheVarHandle {
    private static final VarHandle varHandle;

    static {
        Lookup lookup = MethodHandles.lookup();
        try {
            varHandle = lookup.findVarHandle(TheVarHandle.class, "varHandle", int.class);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        TheVarHandle instance = new TheVarHandle();
        instance.run();
    }

    private void run() {
        varHandle.compareAndSet(this, 1, 2);
    }
}
