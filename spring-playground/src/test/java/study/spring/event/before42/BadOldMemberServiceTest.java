package study.spring.event.before42;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import study.spring.event.OldMemberService;
import study.spring.setups.Member;

@SpringBootTest
class OldMemberServiceTest {

    @Autowired
    private OldMemberService oldMemberService;

    @Test
    @DisplayName("구버전 이벤트를 발행한다")
    void asdxx() {
        // given
        Member member = new Member();
        member.setName("이벤트 ?");
        oldMemberService.enroll(member);
        // when

        // then
    }
}
