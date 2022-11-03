package study.spring.event.rollback;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RollbackMemberRepository extends JpaRepository<RollbackMember, Long> {
}
