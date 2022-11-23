package study.spring.transactional.propagation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class OuterService {

    private final MouseRepository mouseRepository;
    private final InnerService innerService;

    public void hello() {
        /**
         * 트랜잭션을 언제 가져오는지 확인한다.
         *
         * 하이버네이트 설정을 하지 않는 경우를 기준으로, 메소드를 시작할때 트랜잭션을 가져온다.
         *
         * 1. requires_new 인 경우에는 connection과 physical trnasaction을 새로 가져온다.
         * 이벤트를 발행하는 경우도 마찬가지로 새로 가져온다.
         *
         * 2. requires_new 인 경우에는 예외가 전파되지 않는다.
         */
//        try {
            mouseRepository.save(new Mouse(null, "my-mouse"));
            innerService.hi();
//        } catch (RuntimeException e) {
//            System.out.println("zxczxc");
//        }
    }
}

@Component
class InnerService {

        @Transactional(propagation = Propagation.REQUIRES_NEW)
//    @Transactional
    public void hi() {
        throw new RuntimeException("error");
    }
}
