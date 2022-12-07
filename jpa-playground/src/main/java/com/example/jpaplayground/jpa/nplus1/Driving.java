package com.example.jpaplayground.jpa.nplus1;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Driving {

    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /*
    batch fetch size 관련
    https://www.inflearn.com/questions/34469
    https://americanopeople.tistory.com/377

    관련 버그
    https://yjksw.github.io/jpa-default-batch-fetch-size-not-working/
     */
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "driving_id", nullable = false, updatable = false)
//    @Fetch(FetchMode.SUBSELECT)
//    @BatchSize()
    private List<BatchFetchMode> modes = new ArrayList<>();
}
