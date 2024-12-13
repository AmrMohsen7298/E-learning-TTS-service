package com.example.demo.Tutorial;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.data.domain.Page;

import org.springframework.data.domain.PageRequest;

import org.springframework.data.domain.Pageable;
import java.io.IOException;
import java.util.List;


@Service
public class TutorialService {
    @Autowired
    private TutorialRepository tutorialRepository;

//    public List<Tutorial> getAllTutorials() {
//        try {
//            return tutorialRepository.findAll();
//        } catch (Exception e) {
//            System.err.println("Exception in getAllTutorials: " + e.getMessage());
//            e.printStackTrace();
//            return List.of();
//        }
//    }
public List<Tutorial> getFreeTutorials(int page,int size) {

    try {
        Pageable pageable = PageRequest.of(page, size);
        return tutorialRepository.findByIsPaid(false,pageable); // Fetch tutorials where isPaid is true

    } catch (Exception e) {

        System.err.println("Exception in getAllPaidTutorials: " + e.getMessage());

        e.printStackTrace();

        return List.of(); // Return an empty list in case of an error

    }

}
public List<Tutorial> getAllTutorials(int page,int size) {
    try {

        Pageable pageable = PageRequest.of(page, size);

        return tutorialRepository.findAll(pageable).getContent();

    } catch (Exception e) {

        System.err.println("Exception in getAllTutorials: " + e.getMessage());

        e.printStackTrace();

        return List.of(); // Return an empty list in case of an error



    }
}
    public Tutorial getTutorialById(int id) {
        try {
            return tutorialRepository.findById(id).orElse(null);
        } catch (Exception e) {
            System.err.println("Exception in getTutorialById: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    public Tutorial saveTutorial(String title, String description, String level,
                                 boolean isPaid, MultipartFile file) {
        try {
            Tutorial tutorial = new Tutorial();
            tutorial.setTitle(title);
            tutorial.setDescription(description);
            tutorial.setLevel(level);
            tutorial.setPaid(isPaid);
            tutorial.setImage(file.getBytes());
            return tutorialRepository.save(tutorial);
        } catch (IOException e) {
            System.err.println("IOException in saveTutorial: " + e.getMessage());
            e.printStackTrace();
            return null;
        } catch (Exception e) {
            System.err.println("Exception in saveTutorial: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    public void deleteTutorial(int id) {
        try {
            tutorialRepository.deleteById(id);
        } catch (Exception e) {
            System.err.println("Exception in deleteTutorial: " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }

    @Transactional
    public Tutorial updateTutorial(int tutorialId, String title, String description, String level,
                               boolean isPaid, MultipartFile file) {
        try {
            Tutorial tutorial = tutorialRepository.findById(tutorialId)
                    .orElseThrow(() -> new IllegalStateException("Tutorial with id " + tutorialId + " does not exist."));
                    tutorial.setTitle(title);
                    tutorial.setDescription(description);
                    tutorial.setLevel(level);
                    tutorial.setPaid(isPaid);
                    tutorial.setImage(file.getBytes());
                    return tutorialRepository.save(tutorial);
                } catch (IOException e) {
                    System.err.println("IOException in saveTutorial: " + e.getMessage());
                    e.printStackTrace();
                }
        return  null;
    }

    public List<Tutorial> getTutorialsByLevel(String level,int page , int size) {
        try {
            Pageable pageable = PageRequest.of(page, size);

            return tutorialRepository.findByLevel(level,pageable);
        }
        catch (Exception e) {
            System.err.println("Exception in getting Keywords: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    public List<Tutorial> getTutorialsByIsLearned(boolean isLearned) {
        return tutorialRepository.findByIsLearned(isLearned);
    }
}
