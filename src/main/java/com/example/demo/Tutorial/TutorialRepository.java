package com.example.demo.Tutorial;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TutorialRepository extends JpaRepository<Tutorial, Integer>  {
    @Query("SELECT t from Tutorial t where t.title = ?1")
    Optional<Tutorial> findTutorialByTitle(String title);
    List<Tutorial> findByLevel(String level, Pageable pageable);
    List<Tutorial> findByIsLearned(boolean isLearned);

    List<Tutorial> findByIsPaid(boolean isPaid);
}
