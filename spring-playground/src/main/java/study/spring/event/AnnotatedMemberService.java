package study.spring.event;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionalEventListener;
import study.spring.setups.Member;

/**
 * 일반적인 이벤트 리스너를 사용하면, 이벤트를 발행하는 시점에 바로 동작한다. TransactionalEventListener를 사용하면, 나중에 commit phase 에서 commit 이후
 * `afterCompletion` 단계에서 동작한다.
 */
@Service
@Transactional
public class AnnotatedMemberService {

    private ApplicationEventPublisher applicationEventPublisher;

    public AnnotatedMemberService(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    public void enroll(Member member) {
        AnnotatedMemberEnrollmentEvent event = new AnnotatedMemberEnrollmentEvent(member.getName());
        applicationEventPublisher.publishEvent(event);
    }
}

class AnnotatedMemberEnrollmentEvent {
    private String content;

    public AnnotatedMemberEnrollmentEvent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }
}

@Component
class AnnotatedMailSendService {

    @TransactionalEventListener
    public void sendMail(AnnotatedMemberEnrollmentEvent event) {
        System.out.println("event = " + event.getContent());
    }
}
