package com.example.demo.Question;

import com.example.demo.Story.Story;
import com.example.demo.Story.StoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public void createNewQuestion(@RequestBody Question question){
        questionService.addNewQuestion(question);
    }

    @DeleteMapping(path = "{questionId}")
    public void deleteStudent(@PathVariable("questionId") Long questionId){
        questionService.deleteQuestion(questionId);
    }

//    @PutMapping(path = "{questionId}")
//    public void updateStudent(@PathVariable("questionId") Long questionId,
//                              @RequestParam(required = false) String text,
//                              @RequestParam(required = false) String code){
//        questionService.updateQuestion(questionId, text, code);
//    }

    @PutMapping("/{id}")
    public ResponseEntity<Question> updateQuestion(@PathVariable Long id, @RequestBody Question question) {
        Question updatedQuestion = questionService.updateQuestion(id, question);
        if (updatedQuestion != null) {
            return new ResponseEntity<>(updatedQuestion, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
