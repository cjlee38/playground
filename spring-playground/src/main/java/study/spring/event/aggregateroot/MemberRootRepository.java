package study.spring.event.aggregateroot;

import org.springframework.data.jpa.repository.JpaRepository;

interface MemberRootRepository extends JpaRepository<MemberRoot, Long> {

}
