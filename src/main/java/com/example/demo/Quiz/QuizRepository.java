package com.example.demo.Quiz;

import com.example.demo.Question.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface QuizRepository extends JpaRepository<Quiz, Long> {
    @Query("SELECT q from Quiz q where q.code = ?1")
    Optional<Quiz> findQuestionByCode(String code);
    /*return storytRepository.findAll();*/
}
