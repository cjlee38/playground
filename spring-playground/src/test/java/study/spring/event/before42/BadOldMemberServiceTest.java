package study.spring.event.before42;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BadMemberServiceTest {

    @Autowired
    private MemberService memberService;

    @Test
    @DisplayName("구버전 이벤트를 발행한다")
    void asdxx() {
        // given
        memberService.enroll();
        // when

        // then
    }
}
