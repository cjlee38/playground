package study.jpa.relations.onetoone;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PostDetailRepository extends JpaRepository<PostDetails, Long> {
}
