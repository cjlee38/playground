package study.spring.dispatcherservlet;

import org.junit.jupiter.api.Test;
import study.SimpleRestAssured;
import study.spring.AcceptanceTest;

public class DispatcherServletDebugger extends AcceptanceTest {

    @Test
    void debugDefault() {
        SimpleRestAssured.get("/");
    }
}
