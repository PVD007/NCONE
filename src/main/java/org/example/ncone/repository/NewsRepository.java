package org.example.ncone.repository;

import org.example.ncone.data.domain.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.Instant;
import java.util.List;

public interface NewsRepository extends JpaRepository<News, Long> {

    @Query("SELECT (COUNT(n) > 0) FROM News n WHERE n.headline = ?1")
    boolean existsByHeadline(String newsHeadline);

    @Query("SELECT n FROM News n WHERE n.publicationTime BETWEEN ?1 AND ?2")
    List<News> getAllByPeriod(Instant from, Instant to);

}
