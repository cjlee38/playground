package study.syntax.generics;

import java.util.List;

public class JavaVersionVariance {
    public static void main(String[] args) {
        List<ChildOne> children = List.of(new ChildOne(1), new ChildOne(2));
//        read(children); // compile error !
        read2(children);
    }

    public static void read(List<Parent> list) {
        list.forEach(System.out::println);
    }

    public static <T extends Parent> void read2(List<T> list) {
        list.forEach(System.out::println);
    }
}
