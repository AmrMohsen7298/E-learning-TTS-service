package com.example.demo.Quiz;

import com.example.demo.Question.Question;
import com.example.demo.Question.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class QuizService {
    private final QuizRepository quizRepository;
    private final QuestionRepository questionRepository;

    @Autowired
    public QuizService(QuizRepository quizRepository, QuestionRepository questionRepository) {
        this.quizRepository = quizRepository;
        this.questionRepository = questionRepository;
    }

    public List<Quiz> getQuiz() {
        try {
            return quizRepository.findAll();
        } catch (Exception e) {
            System.err.println("Exception in getQuiz: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    public void addNewQuiz(Quiz quiz) {
        try {
            Optional<Quiz> quizOptional = quizRepository.findQuestionByCode(quiz.getCode());
            if (quizOptional.isPresent()) {
                throw new IllegalStateException("This code is taken.");
            }
            questionRepository.saveAll(quiz.getQuestions());
            quizRepository.save(quiz);
        } catch (Exception e) {
            System.err.println("Exception in addNewQuiz: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void deleteQuiz(int quizId) {
        try {
            Optional<Quiz> quiz = quizRepository.findById(quizId);
            if(quiz.isPresent()) {
//                    .orElseThrow(() ->
//                            new IllegalStateException("quiz with id " + quizId + " does not exist. "));
                quizRepository.deleteById(quizId);
                // Delete all questions associated with this quiz
                questionRepository.deleteAll(quiz.get().getQuestions());
            }
            // Delete the quiz itself

        } catch (Exception e) {
            System.err.println("Exception in deleteQuiz: " + e.getMessage());
            e.printStackTrace();
        }
    }


    @Transactional
    public Quiz updateQuiz(int id, String code, int tutorialId, List<Question> questions) {
        try {
            Quiz updatedQuiz = quizRepository.findById(id)
                    .orElseThrow(() -> new IllegalStateException("quiz with id " + id + " does not exist. "));

            if (code != null && code.length() > 0 && !Objects.equals(code, updatedQuiz.getCode())) {
                updatedQuiz.setCode(code);
            }
            updatedQuiz.setTutorialId(tutorialId);
            updatedQuiz.setQuestions(questions);
            return quizRepository.save(updatedQuiz);
        } catch (Exception e) {
            System.err.println("Exception in updateQuiz: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    public Quiz getByTutorialId(int tutorialId) {
        try {
            Optional<Quiz> quiz = quizRepository.findByTutorialId(tutorialId);
            return quiz.orElse(null);
        } catch (Exception e) {
            System.err.println("Exception in getByTutorialId: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
}
