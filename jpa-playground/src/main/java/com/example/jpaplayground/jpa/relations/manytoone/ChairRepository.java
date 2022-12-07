package com.example.jpaplayground.jpa.relations.manytoone;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ChairRepository extends JpaRepository<Chair, Long> {

    @Query("SELECT c FROM Chair c JOIN FETCH c.desk")
    List<Chair> findAllWithDesks();
}
