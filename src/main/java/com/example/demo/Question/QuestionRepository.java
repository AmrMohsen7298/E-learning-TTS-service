package com.example.demo.Question;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Integer> {
    Optional<Question> findQuestionByCode(String code);
    Optional<List<Question>> findByQuizId(int quizId);
}

