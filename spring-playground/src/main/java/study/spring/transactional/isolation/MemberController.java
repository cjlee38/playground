package study.spring.transactional.isolation;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemberController {

    private MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/uncommitted")
    public void uncommitted() {
        memberService.uncommiited();
    }

    @GetMapping("/no-transaction")
    public void noTransaction() {
        memberService.noTransaction();
    }

    @GetMapping("/repeatable-read")
    public void repeatableRead() {
        memberService.repeatableRead();
    }
}
