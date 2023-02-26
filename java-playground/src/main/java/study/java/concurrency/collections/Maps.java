package study.java.concurrency.collections;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Maps {

    public static void main(String[] args) {
        Map<String, String> concurrentHashMap = new ConcurrentHashMap<>();
        Map<String, String> objectObjectMap = Collections.synchronizedMap(new HashMap<>());
    }
}
