package study.syntax.lambda;

import java.util.ArrayList;
import java.util.List;

import kotlin.Unit;
import kotlin.collections.CollectionsKt;

public class ReceiverLambdaOnJava {
    public static void main(String[] args) {
        List<String> strings = new ArrayList<>();

        CollectionsKt.forEach(strings, s -> {
                System.out.println(s);
                return Unit.INSTANCE;
            }
        );
    }
}
