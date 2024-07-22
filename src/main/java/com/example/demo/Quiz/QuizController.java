package com.example.demo.Quiz;

import com.example.demo.Question.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/quiz")
public class QuizController {
    private final QuizService quizService;

    @Autowired
    public QuizController (QuizService quizService){
        this.quizService = quizService;
    }

    @GetMapping
    public List<Quiz> getQuiz(){
        return quizService.getQuiz();
    }

    @PostMapping
    public void addNewQuiz(@RequestBody Quiz quiz){
        quizService.addNewQuiz(quiz);
    }

    @DeleteMapping(path = "{quizId}")
    public void deleteQuiz(@PathVariable("quizId") int quizId){
        quizService.deleteQuiz(quizId);
    }

    @PutMapping(path = "{quizId}")

    public Quiz updateQuiz(@PathVariable("quizId") int quizId,

                           @RequestBody QuizUpdateRequest request) {

        return quizService.updateQuiz(quizId, request.getCode(), request.getTutorialId(), request.getQuestions());

    }


    public static class QuizUpdateRequest {

        private String code;

        private int tutorialId;

        private List<Question> questions;
        public QuizUpdateRequest() {} // no-arg constructor


        public String getCode() {

            return code;

        }


        public void setCode(String code) {

            this.code = code;

        }


        public int getTutorialId() {

            return tutorialId;

        }


        public void setTutorialId(int tutorialId) {

            this.tutorialId = tutorialId;

        }


        public List<Question> getQuestions() {

            return questions;

        }


        public void setQuestions(List<Question> questions) {

            this.questions = questions;

        }

        // getters and setters

    }
    @GetMapping("/tutorial/{tutorialId}")
    public Quiz getByTutorialId(@PathVariable int tutorialId){
        return quizService.getByTutorialId(tutorialId);
    }
    
}
