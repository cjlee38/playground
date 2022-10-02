package study.spring.setups;


import org.springframework.data.jpa.repository.JpaRepository;
import study.spring.setups.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Member findByName(String name);
}
