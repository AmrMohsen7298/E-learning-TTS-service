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


    public void addNewStory(Story story) {
        Optional<Story> storyOptional = storyRepository.findStoryByName(story.getName());
        if (storyOptional.isPresent()){
            throw new IllegalStateException("This name is taken.");
        }
        storyRepository.save(story);
    }

    public void deleteStory(Long storyId) {
        boolean exists = storyRepository.existsById(storyId);
        if (!exists){
            throw new IllegalStateException("Story with id " + storyId + " does not exist");
        }
        storyRepository.deleteById(storyId);

    }

    @Transactional
    public void updateStudent(Long storytId, String name, String email) {
        Story story = storyRepository.findById(storytId).orElseThrow(()-> new IllegalStateException("Story with id" + storytId + " does not exist. "));

        if (name != null && name.length() > 0 && !Objects.equals(story.getName(), name)){
            story.setName(name);
            }
        if (email !=null && email.length() > 0){
            Optional<Story> storyOptional  = storyRepository.findStoryByName(email);
            if (storyOptional.isPresent()){
                throw new IllegalStateException("Email Taken.");
            }
            story.setName(name);
        }
    }
}
