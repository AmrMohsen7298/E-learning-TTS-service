package com.example.demo.Story;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StoryService {


    private final StoryRepository storyRepository;

    @Autowired
    public StoryService(StoryRepository storyRepository) {
        this.storyRepository = storyRepository;
    }

    public List<Story> getStory(){
        return storyRepository.findAll();
    }


    public Story addNewStory(Story story) {
        Optional<Story> storyOptional = storyRepository.findByTutorialId(story.getTutorialId());
        if (storyOptional.isPresent()){
            throw new IllegalStateException("There is a story for this lesson.");
        }
        return storyRepository.save(story);
    }

    public void deleteStory(int storyId) {
        boolean exists = storyRepository.existsById(storyId);
        if (!exists){
            throw new IllegalStateException("Story with id " + storyId + " does not exist");
        }
        storyRepository.deleteById(storyId);

    }

    @Transactional
    public void updateStudent(int storyId, String name, String paragraph) {
        Story story = storyRepository.findById(storyId).orElseThrow(()-> new IllegalStateException("Story with id" + storyId + " does not exist. "));

        if (name != null && name.length() > 0 && !Objects.equals(story.getName(), name)){
            Optional<Story> storyOptional  = storyRepository.findStoryByName(name);
            if (storyOptional.isPresent()){
                throw new IllegalStateException("name Taken.");
            }
            story.setName(name);
            story.setParagraph(paragraph);
        }
    }

    public Story getByTutorialId(int tutorialId) {
        Optional<Story> story = storyRepository.findByTutorialId(tutorialId);
        return story.orElse(null);

    }
}
