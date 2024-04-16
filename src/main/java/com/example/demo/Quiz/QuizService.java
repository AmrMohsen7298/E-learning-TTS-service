package com.example.demo.Quiz;

import com.example.demo.Question.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class QuizService {
    private final QuizRepository quizRepository;

    @Autowired
    public QuizService(QuizRepository quizRepository) {
        this.quizRepository = quizRepository;
    }

    public List<Quiz> getQuiz(){
        return quizRepository.findAll();
    }


    public void addNewQuiz(Quiz quiz) {
        Optional<Quiz> quizOptional = quizRepository.findQuestionByCode(quiz.getCode());
        if (quizOptional.isPresent()){
            throw new IllegalStateException("This code is taken.");
        }
        quizRepository.save(quiz);
    }

    public void deleteQuiz(Long quizId) {
        boolean exists = quizRepository.existsById(quizId);
        if (!exists){
            throw new IllegalStateException("quiz with id " + quizId + " does not exist");
        }
        quizRepository.deleteById(quizId);

    }

    @Transactional
    public void updateQuiz(Long quizId, String code, int tutorial_Id) {
        Quiz quiz = quizRepository.findById(quizId).orElseThrow(()-> new IllegalStateException("quiz with id" + quizId + " does not exist. "));
        if (code != null && code.length() > 0 && !Objects.equals(quiz.getCode(), code))
            quiz.setCode(code);
        quiz.setTutorial_Id(tutorial_Id);
        }
    }

