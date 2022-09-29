package study.spring.defaultset;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class DefaultController {

    private static final Logger logger = LoggerFactory.getLogger(DefaultController.class);

    @GetMapping("/")
    @ResponseBody
    public String doNothing() {
        logger.info("DefaultController.doNothing");
        return "hello-world";
    }

    @GetMapping("/error")
    public void doError() {
        logger.info("DefaultController.doError");
        throw new RuntimeException("default-error");
    }

    @GetMapping("/jasper")
    public String doJasper() {
        logger.info("DefaultController.doJasper");
        return "jasper";
    }
}
