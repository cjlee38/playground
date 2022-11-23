package study.spring.transactional.propagation;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MouseRepository extends JpaRepository<Mouse, Long> {
}
