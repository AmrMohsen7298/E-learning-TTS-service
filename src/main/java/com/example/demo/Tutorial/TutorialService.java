package com.example.demo.Tutorial;

import com.example.demo.Quiz.Quiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
public class TutorialService {
    @Autowired
    private TutorialRepository tutorialRepository;

    public List<Tutorial> getAllTutorials() {
        return tutorialRepository.findAll();
    }

    public Tutorial getTutorialById(int id) {
        return tutorialRepository.findById(id).orElse(null);
    }

    public Tutorial saveTutorial(Tutorial tutorial) {
        return tutorialRepository.save(tutorial);
    }

    public void deleteTutorial(int id) {
        tutorialRepository.deleteById(id);
    }

    @Transactional
    public void updateTutorial(int tutorialId, String title, String level) {
        Tutorial tutorial = tutorialRepository.findById(tutorialId).orElseThrow(()-> new IllegalStateException("tutorial with id" + tutorialId + " does not exist. "));
        if (title != null && title.length() > 0 && !Objects.equals(tutorial.getTitle(), title))
            tutorial.setTitle(title);
        tutorial.setLevel(level);
        
    }
}

