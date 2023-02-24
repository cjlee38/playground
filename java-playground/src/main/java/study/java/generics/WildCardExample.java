package study.java.generics;

import java.util.ArrayList;
import java.util.List;

public class WildCardExample {

    public static void main(String[] args) {
        List<String> stringList = new ArrayList<>();

        wildCardMethod(stringList);
//        objectMethod(stringList); // compile error
    }

    public static void wildCardMethod(List<?> list) {
        for (Object obj : list) {
            System.out.println("obj = " + obj);
        }
    }

    public static void objectMethod(List<Object> list) {
        for (Object obj : list) {
            System.out.println("obj = " + obj);
        }
    }
}
