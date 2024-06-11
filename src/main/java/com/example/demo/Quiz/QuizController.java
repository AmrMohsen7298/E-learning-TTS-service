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
                              @RequestParam(required = false) String code,
                              @RequestParam(required = false) int tutorialId,
                              @RequestParam(required = false) List<Question> questions){
          return  quizService.updateQuiz( quizId,  code,  tutorialId, questions);
    }
    @GetMapping("/tutorial/{tutorialId}")
    public Quiz getByTutorialId(@PathVariable int tutorialId){
        return quizService.getByTutorialId(tutorialId);
    }
    
}
