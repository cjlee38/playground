package study.spring.event.annotation;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import study.spring.event.AnnotatedMemberService;
import study.spring.setups.Member;

@SpringBootTest
class AnnotatedMemberServiceTest {

    @Autowired
    private AnnotatedMemberService service;

    @Test
    @DisplayName("어노테이션 기반 이벤트를 발행한다.")
    void asdxx() {
        // given
        Member member = new Member();
        member.setName("어노테이션 이벤트");
        service.enroll(member);

        // when

        // then
    }
}
