package study.spring.transactional.isolation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MemberService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    public void uncommiited() {
        System.out.println("BEFORE memberRepository.findByName(\"hello\") = " + memberRepository.findByName("hello"));

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("AFTER memberRepository.findByName(\"hello\") = " + memberRepository.findByName("hello"));

    }

    public void noTransaction() {
        System.out.println("BEFORE memberRepository.findByName(\"hello\") = " + memberRepository.findByName("hello"));

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("AFTER memberRepository.findByName(\"hello\") = " + memberRepository.findByName("hello"));
    }

    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public void repeatableRead() {
        System.out.println("BEFORE memberRepository.findByName(\"hello\") = " + memberRepository.findByName("hello"));

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("AFTER memberRepository.findByName(\"hello\") = " + memberRepository.findByName("hello"));
    }
}
