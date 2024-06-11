package com.example.demo.Question;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {

    private final QuestionRepository questionRepository;

    @Autowired
    public QuestionService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    public List<Question> getQuestions() {
        try {
            return questionRepository.findAll();
        } catch (Exception e) {
            System.err.println("Exception in getQuestions: " + e.getMessage());
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    public void addNewQuestion(int quizId, String code, String text, List<String> choices, String answer) {
        try {
            Optional<Question> questionOptional = questionRepository.findQuestionByCode(code);
            if (questionOptional.isPresent()) {
                throw new IllegalStateException("This code is taken.");
            }
            Question question = new Question();
            question.setCode(code);
            question.setText(text);
            question.setChoices(choices);
            question.setAnswer(answer);
            questionRepository.save(question);
        } catch (Exception e) {
            System.err.println("Exception in addNewQuestion: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void deleteQuestion(int questionId) {
        try {
            boolean exists = questionRepository.existsById(questionId);
            if (!exists) {
                throw new IllegalStateException("Question with id " + questionId + " does not exist");
            }
            questionRepository.deleteById(questionId);
        } catch (Exception e) {
            System.err.println("Exception in deleteQuestion: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public Question updateQuestion(int id, int quizId, String code, String text, List<String> choices, String answer) {
        try {
            Optional<Question> optionalQuestion = questionRepository.findById(id);
            if (optionalQuestion.isPresent()) {
                Question question = optionalQuestion.get();
                question.setCode(code);
                question.setText(text);
                question.setChoices(choices);
                question.setAnswer(answer);
                return questionRepository.save(question);
            } else {
                throw new IllegalStateException("Question with id " + id + " does not exist");
            }
        } catch (Exception e) {
            System.err.println("Exception in updateQuestion: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    public Optional<Question> getQuestionById(int id) {
        try {
            return questionRepository.findById(id);
        } catch (Exception e) {
            System.err.println("Exception in getQuestionById: " + e.getMessage());
            e.printStackTrace();
            return Optional.empty();
        }
    }

    public List<Question> getByQuizId(int quizId) {
        try {
            Optional<List<Question>> optionalQuestions = questionRepository.findByQuizId(quizId);
            return optionalQuestions.orElse(Collections.emptyList());
        } catch (Exception e) {
            System.err.println("Exception in getByQuizId: " + e.getMessage());
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
}
