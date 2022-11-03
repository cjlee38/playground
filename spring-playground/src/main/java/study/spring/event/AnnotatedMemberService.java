package study.spring.event;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import study.spring.setups.Member;

@Service
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

    @EventListener
    public void sendMail(AnnotatedMemberEnrollmentEvent event) {
        System.out.println("event = " + event.getContent());
    }
}
