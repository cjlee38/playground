package com.example.jpaplayground.jpa.relations.onetoone;

import javax.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
public class LazyLoadingTest {

    @Autowired
    private PostRepository postRepository;
    @Autowired
    private PostDetailRepository postDetailRepository;
    @Autowired
    private EntityManager entityManager;

    @Test
    @Transactional
    @DisplayName("@LazyToOne(LazyToOneOption.FALSE) 인 경우 의도치않게 쿼리가 두번 나간다. postdetails -> post"
            + "@LazyToOne(LazyToOneOption.PROXY) 인 경우, postdetails에 대한 쿼리만 나간다. getPost를 땡기면 그때 가져온다"
            + "@LazyToOne(LazyToOneOption.NO_PROXY) 인 경우도 마찬가지로 getPost를 땡기면 그때 가져온다.")
        // 그렇다면, PROXY와 NO_PROXY의 차이는 무엇일까 ?
    void asdasdx() {
        // given
        Post post = new Post(10L, "the post");
        entityManager.persist(post);
        PostDetails postDetails = new PostDetails(null, "chalee!", post);
        entityManager.persist(postDetails);
        // when
        entityManager.flush();
        entityManager.clear();
        System.out.println("=================================");
        System.out.println("post.getId() = " + post.getId()); // post.getId() = 10
        System.out.println("postDetails.getId() = " + postDetails.getId()); // postDetails.getId() = 10
        PostDetails result = entityManager.find(PostDetails.class, post.getId());
        // then
        System.out.println("result = " + result);
        System.out.println("result.getPost() = " + result.getPost());
    }
}
