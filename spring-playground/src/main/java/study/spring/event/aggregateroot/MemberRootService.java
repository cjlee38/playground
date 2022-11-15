package study.spring.event.aggregateroot;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionalEventListener;

@Service
@RequiredArgsConstructor
public class MemberRootService {

    private final MemberRootRepository memberRootRepository;

    public void loginNormalMember(String name) {
        MemberRoot member = new MemberRoot(1L);
        member.login(name);
        memberRootRepository.save(member);
    }

    @Transactional
    public void loginTransactionalMember(String name) {
        MemberRoot member = new MemberRoot(1L);
        member.login(name);
        memberRootRepository.save(member);
    }
}

@Service
class MemberLoginListener {

    private static final Logger log = LoggerFactory.getLogger(MemberLoginListener.class);

    @EventListener
    public void listenNormal(MemberLoginEvent event) {
        log.info("normal listen ... " + event.getName());
    }

    @TransactionalEventListener
    public void listenTransactional(MemberLoginEvent event) {
        log.info("transactional listen ... " + event.getName());
    }
}
