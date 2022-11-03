package study.spring.event.annotation;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import study.spring.setups.Member;

@Service
public class AnnotatedMemberService {

    private final AnnotatedMemberRepository annotatedMemberRepository;
    private ApplicationEventPublisher applicationEventPublisher;

    public AnnotatedMemberService(
            AnnotatedMemberRepository annotatedMemberRepository,
            ApplicationEventPublisher applicationEventPublisher
    ) {
        this.annotatedMemberRepository = annotatedMemberRepository;
        this.applicationEventPublisher = applicationEventPublisher;
    }

    public void enroll(Member member) {
        annotatedMemberRepository.save(member);

        AnnotatedMailSendService.AnnotatedMemberEnrollmentEvent event = new AnnotatedMailSendService.AnnotatedMemberEnrollmentEvent(member.getName());
        applicationEventPublisher.publishEvent(event);
    }
}

@Repository
class AnnotatedMemberRepository {

    public void save(Member member) {

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
