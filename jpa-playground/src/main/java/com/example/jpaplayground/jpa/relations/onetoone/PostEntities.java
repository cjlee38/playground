package com.example.jpaplayground.jpa.relations.onetoone;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
class Post {

    @Id
    private Long id;

    private String title;
}

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
class PostDetails {

    @Id
    @GeneratedValue
    private Long id;

    private String createdBy;

    @OneToOne(fetch = FetchType.LAZY)
    @LazyToOne(LazyToOneOption.NO_PROXY)
    @MapsId
    @JoinColumn(name = "id")
    private Post post;
}
