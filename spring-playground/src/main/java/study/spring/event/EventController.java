package study.spring.event;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import study.spring.setups.Member;

@RestController
public class EventController {

    private final AnnotatedMemberService annotatedMemberService;

    public EventController(AnnotatedMemberService annotatedMemberService) {
        this.annotatedMemberService = annotatedMemberService;
    }

    @GetMapping("/spring/event")
    public void request() {
        annotatedMemberService.enroll(new Member("hello!"));
        System.out.println("request done after enrollment");
    }
}
