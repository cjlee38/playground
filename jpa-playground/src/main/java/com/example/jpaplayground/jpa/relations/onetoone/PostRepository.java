package com.example.jpaplayground.jpa.relations.onetoone;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
