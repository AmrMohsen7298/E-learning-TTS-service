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

    public List<Story> getStory() {
        try {
            return storyRepository.findAll();
        } catch (Exception e) {
            System.err.println("Exception in getStory: " + e.getMessage());
            e.printStackTrace();
            return List.of();
        }
    }

    public Story addNewStory(Story story) {
        try {
            Optional<Story> storyOptional = storyRepository.findByTutorialId(story.getTutorialId());
            if (storyOptional.isPresent()) {
                throw new IllegalStateException("There is a story for this lesson.");
            }
            return storyRepository.save(story);
        } catch (Exception e) {
            System.err.println("Exception in addNewStory: " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }

    public void deleteStory(int storyId) {
        try {
            boolean exists = storyRepository.existsById(storyId);
            if (!exists) {
                throw new IllegalStateException("Story with id " + storyId + " does not exist");
            }
            storyRepository.deleteById(storyId);
        } catch (Exception e) {
            System.err.println("Exception in deleteStory: " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }

    @Transactional
    public void updateStory(int storyId, String name, String paragraph) {
        try {
            Story story = storyRepository.findById(storyId)
                    .orElseThrow(() -> new IllegalStateException("Story with id " + storyId + " does not exist."));

            if (name != null && name.length() > 0 && !Objects.equals(story.getName(), name)) {
                Optional<Story> storyOptional = storyRepository.findStoryByName(name);
                if (storyOptional.isPresent()) {
                    throw new IllegalStateException("name Taken.");
                }
                story.setName(name);
                story.setParagraph(paragraph);
            }
        } catch (Exception e) {
            System.err.println("Exception in update Story: " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }

    public Story getByTutorialId(int tutorialId) {
        try {
            Optional<Story> story = storyRepository.findByTutorialId(tutorialId);
            return story.orElse(null);
        } catch (Exception e) {
            System.err.println("Exception in getByTutorialId: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
}
