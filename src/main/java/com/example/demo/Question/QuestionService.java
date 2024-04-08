package com.example.demo.Question;

import com.example.demo.Story.Story;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

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

    public void deleteQuestion(Long questionId) {
        boolean exists = questionRepository.existsById(questionId);
        if (!exists){
            throw new IllegalStateException("Question with id " + questionId + " does not exist");
        }
        questionRepository.deleteById(questionId);

    }
    public Question updateQuestion(Long id, Question questionDetails) {
        Optional<Question> optionalQuestion = questionRepository.findById(id);
        if (optionalQuestion.isPresent()) {
            Question question = optionalQuestion.get();
            question.setText(questionDetails.getText());
            question.setChoices(questionDetails.getChoices());
            question.setAnswer(questionDetails.getAnswer());
            return questionRepository.save(question);
        } else {
            return null;
        }
    }

//    @Transactional
//    public void updateQuestion(Long questionId, String text, String code) {
//        Question question = questionRepository.findById(questionId).orElseThrow(()-> new IllegalStateException("Question with id" + questionId + " does not exist. "));
//
//        if (text != null && text.length() > 0 && !Objects.equals(question.getText(), text)){
//            question.setText(text);
//        }
//        if (code !=null && code.length() > 0){
//            Optional<Question> QuestionOptional  = questionRepository.findQuestionByCode(code);
//            if (QuestionOptional.isPresent()){
//                throw new IllegalStateException("code Taken.");
//            }
//            question.setCode(code);
//        }
//    }
}
