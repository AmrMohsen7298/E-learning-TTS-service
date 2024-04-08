package com.example.demo.Tutorial;

import com.example.demo.Question.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface TutorialRepository extends JpaRepository<Tutorial, Long>  {
    @Query("SELECT t from Tutorial t where t.title = ?1")
    Optional<Question> findQuestionByTitle(String title);
}
