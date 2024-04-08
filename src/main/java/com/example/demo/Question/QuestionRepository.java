package com.example.demo.Question;

import com.example.demo.Story.Story;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
    @Query("SELECT q from Question q where q.code = ?1")
    Optional<Question> findQuestionByCode(String code);
    /*return storytRepository.findAll();*/
}
