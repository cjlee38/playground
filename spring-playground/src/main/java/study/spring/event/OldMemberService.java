package study.spring.event;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Service;
import study.spring.setups.Member;

@Service
public class OldMemberService implements ApplicationEventPublisherAware {

    private ApplicationEventPublisher applicationEventPublisher;

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    public void enroll(Member member) {
        OldMemberEnrollmentEvent oldMemberEnrollmentEvent = new OldMemberEnrollmentEvent(this, member.getName());
        applicationEventPublisher.publishEvent(oldMemberEnrollmentEvent);
    }
}

class OldMemberEnrollmentEvent extends ApplicationEvent {

    private String content;

    public OldMemberEnrollmentEvent(Object source, String content) {
        super(source);
        this.content = content;
    }

    public String getContent() {
        return content;
    }
}

@Service
class OldMailSendService implements ApplicationListener<OldMemberEnrollmentEvent> {

    @Override
    public void onApplicationEvent(OldMemberEnrollmentEvent event) {
        sendMail(event.getContent());
    }

    private void sendMail(String content) {
        // send mail actually
        System.out.println("content = " + content);
    }
}
