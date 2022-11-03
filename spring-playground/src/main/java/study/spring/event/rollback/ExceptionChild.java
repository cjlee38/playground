package study.spring.event.rollback;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionalEventListener;

@Service
public class ExceptionChild {

    @TransactionalEventListener
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void listen(MemberEnrollEvent event) {
        throw new IllegalArgumentException("롤백?");
    }
}
