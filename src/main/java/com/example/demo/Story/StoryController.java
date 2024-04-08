package com.example.demo.Story;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/story")
public class StoryController {
    private final StoryService storyService;

    @Autowired
    public StoryController (StoryService storyService){
        this.storyService = storyService;
    }

    @GetMapping
    public List<Story> getStory(){
        return storyService.getStory();
    }

    @PostMapping
    public void addNewStory(@RequestBody Story story){
        storyService.addNewStory(story);
    }

    @DeleteMapping(path = "{storyId}")
    public void deleteStory(@PathVariable("storyId") Long storyId){
        storyService.deleteStory(storyId);
    }

    @PutMapping(path = "{storyId}")
    public void updateStudent(@PathVariable("storyId") Long storyId,
                              @RequestParam(required = false) String name,
                              @RequestParam(required = false) String paragraph){
        storyService.updateStudent(storyId, name, paragraph);
    }
}

