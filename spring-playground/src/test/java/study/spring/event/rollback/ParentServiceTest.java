package study.spring.event.rollback;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootTest
@EnableJpaRepositories
class ParentTest {

    @Autowired
    private Parent parent;

    @Autowired
    private RollbackMemberRepository rollbackMemberRepository;

    @Test
    @DisplayName("이벤트 수신 서비스에서 에서 REQUIRES_NEW 설정시 예외가 전파되는지 확인한다.")
    void name() {
        parent.publish();
        List<RollbackMember> list = rollbackMemberRepository.findAll();
        for (RollbackMember member : list) {
            System.out.println("member = " + member);
        }
    }
}
