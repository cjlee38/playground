package study.spring.defaultset;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class DefaultControllerAdvice {

    @ExceptionHandler(RuntimeException.class)
    public String catchRuntime(RuntimeException e) {
        return e.getMessage();
    }
}
