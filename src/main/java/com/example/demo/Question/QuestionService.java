package com.example.demo.Question;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class QuestionService {

    private final QuestionRepository questionRepository;

    @Autowired
    public QuestionService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    public List<Question> getQuestions(){
        return questionRepository.findAll();
    }


    public void addNewQuestion(Question question) {
        Optional<Question> questionOptional = questionRepository.findQuestionByCode(question.getCode());
        if (questionOptional.isPresent()){
            throw new IllegalStateException("This code is taken.");
        }
        questionRepository.save(question);
    }

    public void deleteQuestion(int questionId) {
        boolean exists = questionRepository.existsById(questionId);
        if (!exists){
            throw new IllegalStateException("Question with id " + questionId + " does not exist");
        }
        questionRepository.deleteById(questionId);

    }
    public Question updateQuestion(int id,int quiz_id, String text, String choices, String answer) {
        Optional<Question> optionalQuestion = questionRepository.findById(id);
        if (optionalQuestion.isPresent()) {
            Question question = optionalQuestion.get();
            question.setQuiz_id(quiz_id);
            question.setText(text);
            question.setChoices(choices);
            question.setAnswer(answer);
            return questionRepository.save(question);
        } else {
            return null;
        }
    }
    public Optional<Question> getQuestionById(int id) {
        return questionRepository.findById(id);
    }
    
}
