package com.example.demo.Tutorial;

import com.google.api.gax.paging.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import org.springframework.data.domain.PageRequest;

import org.springframework.data.domain.Pageable;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/tutorials")
public class TutorialController {
    @Autowired
    private TutorialService tutorialService;

    @GetMapping
    public List<Tutorial> getAllTutorials(@RequestParam(defaultValue = "0") int page,

                                          @RequestParam(defaultValue = "10") int size) {
        return tutorialService.getAllTutorials(page,size);
    }

    @GetMapping("/{id}")
    public Tutorial getTutorialById(@PathVariable int id) {
        return tutorialService.getTutorialById(id);

    }

    @PostMapping
    public ResponseEntity<Tutorial> createTutorial(@RequestParam("title") String title,
                                                   @RequestParam("description") String description,
                                                   @RequestParam("level") String level,
                                                   @RequestParam("isPaid") boolean isPaid,
                                                   @RequestParam("file") MultipartFile file) throws IOException {
        Tutorial savedTutorial = tutorialService.saveTutorial(title,description,level,isPaid,file);
        return new ResponseEntity<>(savedTutorial, HttpStatus.CREATED);
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<Void> deleteTutorial(@PathVariable int id) {
        tutorialService.deleteTutorial(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @PostMapping("/update/{id}")
    public ResponseEntity<Tutorial> updateTutorial(@PathVariable("id") int tutorialId,
                                                   @RequestParam("title") String title,
                                                   @RequestParam("description") String description,
                                                   @RequestParam("level") String level,
                                                   @RequestParam("isPaid") boolean isPaid,
                                                   @RequestParam("file") MultipartFile file) {
        Tutorial updatedtutorial = tutorialService.updateTutorial(tutorialId, title, description, level, isPaid, file);
        return new ResponseEntity<>(updatedtutorial, HttpStatus.OK);
    }
    @GetMapping("/level/{level}")
    public ResponseEntity<List<Tutorial>> getTutorialsByLevel(@PathVariable String level) {
        List<Tutorial> tutorials = tutorialService.getTutorialsByLevel(level);
        return ResponseEntity.ok(tutorials);
    }
    @GetMapping("/Learned/")
    public List<Tutorial> getTutorialsByIsLearned(@RequestParam("isLearned") boolean isLearned) {
        return tutorialService.getTutorialsByIsLearned(isLearned);
    }

}

