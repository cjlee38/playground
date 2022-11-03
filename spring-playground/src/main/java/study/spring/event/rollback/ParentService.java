package study.spring.event.rollback;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class Parent {

    private final ApplicationEventPublisher eventPublisher;
    private final RollbackMemberRepository rollbackMemberRepository;

    @Transactional
    public void publish() {
        RollbackMember member = new RollbackMember(null, "name");
        rollbackMemberRepository.save(member);
        eventPublisher.publishEvent(new MemberEnrollEvent(member.getId()));
    }
}




