package com.example.demo.Question;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/Question")
public class QuestionController {
    private final QuestionService questionService;

    @Autowired
    public QuestionController (QuestionService questionService){
        this.questionService = questionService;
    }

    @GetMapping
    public List<Question> getQuestions(){
        return questionService.getQuestions();
    }

    @PostMapping
    public void createNewQuestion(@RequestParam int quizId,
                                  @RequestParam String code,
                                  @RequestParam String text,
                                  @RequestParam List<String> choices,
                                  @RequestParam String answer){
        questionService.addNewQuestion(quizId, code, text, choices, answer);
    }

    @DeleteMapping(path = "{questionId}")
    public void deleteStudent(@PathVariable("questionId") int questionId){
        questionService.deleteQuestion(questionId);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Question> updateQuestion(@PathVariable int id,
                                                   @RequestParam int quizId,
                                                   @RequestParam String code,
                                                   @RequestParam String text,
                                                   @RequestParam List<String> choices,
                                                   @RequestParam String answer) {
        Question updatedQuestion = questionService.updateQuestion(id, quizId, code, text, choices, answer);
        if (updatedQuestion != null) {
            return ResponseEntity.ok(updatedQuestion);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<Question> getQuestionById(@PathVariable int id) {
        Optional<Question> questionOptional = questionService.getQuestionById(id);
        return questionOptional
                .map(question -> ResponseEntity.ok(question))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/getByQuiz/{quizId}")
    public ResponseEntity<List<Question>> getByQuizId(@PathVariable int quizId) {
        List<Question> questions = questionService.getByQuizId(quizId);
        if (questions.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(questions, HttpStatus.OK);
        }
    }
}
