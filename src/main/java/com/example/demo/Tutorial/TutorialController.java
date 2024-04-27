package com.example.demo.Tutorial;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/tutorials")
public class TutorialController {
    @Autowired
    private TutorialService tutorialService;

    @GetMapping
    public List<Tutorial> getAllTutorials() {
        return tutorialService.getAllTutorials();
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

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTutorial(@PathVariable int id) {
        tutorialService.deleteTutorial(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Tutorial> updateTutorial(@PathVariable("id") int tutorialId,
                                                   @RequestParam(value = "title", required = false) String title,
                                                   @RequestParam(value = "level", required = false) String level) {
        tutorialService.updateTutorial(tutorialId, title, level);
        return ResponseEntity.ok().build();
    }
}

