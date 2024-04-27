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
    public void addNewStory(@RequestBody Quiz quiz){
        quizService.addNewQuiz(quiz);
    }

    @DeleteMapping(path = "{quizId}")
    public void deleteStory(@PathVariable("quizId") Long quizId){
        quizService.deleteQuiz(quizId);
    }

    @PutMapping(path = "{quizId}")
    public void updateStudent(@PathVariable("quizId") Long quizId,
                              @RequestParam(required = false) String code,
                              @RequestParam(required = false) int questionId){
        quizService.updateQuiz(quizId, code, questionId);
    }
    @GetMapping("/tutorial/{tutorialId}")
    public Quiz getByTutorialId(@PathVariable int tutorialId){
        return quizService.getByTutorialId(tutorialId);
    }
}
