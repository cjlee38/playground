//package study.spring.event.rollback;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//import java.util.List;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//
//@SpringBootTest
//@EnableJpaRepositories
//class ParentServiceTest {
//
//    @Autowired
//    private ParentService parentService;
//
//    @Autowired
//    private RollbackMemberRepository rollbackMemberRepository;
//
//    @Test
//    @DisplayName("이벤트 수신 서비스에서 에서 REQUIRES_NEW 설정시 예외가 전파되는지 확인한다.")
//    void name() {
//        String name = "name in parent";
//        parentService.doService(name);
//        List<RollbackMember> list = rollbackMemberRepository.findAll();
//        assertThat(list).hasSize(1);
//        // 예외가 전파되지 않는다.
//    }
//}
