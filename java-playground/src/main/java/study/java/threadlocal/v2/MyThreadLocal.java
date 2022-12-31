package study.java.threadlocal.v2;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MyThreadLocal {
    private static Map<Thread, Object> threadLocalMap = new ConcurrentHashMap<>();

    public void set(Object object) {
        threadLocalMap.put(Thread.currentThread(), object);
    }

    public void get(Object object) {
        threadLocalMap.get(Thread.currentThread());
    }
}
