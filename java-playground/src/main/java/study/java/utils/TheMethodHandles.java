package study.java.utils;

import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodHandles.Lookup;

/**
 * @see java.util.concurrent.FutureTask
 */
public class TheMethodHandles {
    public static void main(String[] args) {
        Lookup lookup = MethodHandles.lookup();
    }
}
