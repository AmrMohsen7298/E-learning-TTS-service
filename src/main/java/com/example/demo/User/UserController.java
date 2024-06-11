package com.example.demo.User;

import com.example.demo.Tutorial.Tutorial;
import com.example.demo.KeyWord.KeyWord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable int id) {
        User user = userService.getUserById(id);
        return user != null ?
                new ResponseEntity<>(user, HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestParam("username") String username,
                                           @RequestParam("password") String password,
                                           @RequestParam("name") String name) {
        User savedUser = userService.saveUser(username, password, name);
        return savedUser != null ?
                new ResponseEntity<>(savedUser, HttpStatus.CREATED) :
                new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable int id, 
                                           @RequestParam("username") String username,
                                           @RequestParam("password") String password,
                                           @RequestParam("name") String name) {
        User updatedUser = userService.updateUser(id, username, password, name);
        return updatedUser != null ?
                new ResponseEntity<>(updatedUser, HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable int id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{userId}/tutorials/learned")
    public ResponseEntity<List<Tutorial>> getLearnedTutorials(@PathVariable int userId) {
        List<Tutorial> tutorials = userService.getLearnedTutorials(userId);
        return new ResponseEntity<>(tutorials, HttpStatus.OK);
    }

    @GetMapping("/{userId}/tutorials/favorites")
    public ResponseEntity<List<Tutorial>> getFavTutorials(@PathVariable int userId) {
        List<Tutorial> tutorials = userService.getFavTutorials(userId);
        return new ResponseEntity<>(tutorials, HttpStatus.OK);
    }

    @GetMapping("/{userId}/keywords/learned")
    public ResponseEntity<List<KeyWord>> getLearnedKeywords(@PathVariable int userId) {
        List<KeyWord> keywords = userService.getLearnedKeywords(userId);
        return new ResponseEntity<>(keywords, HttpStatus.OK);
    }

    @PostMapping("/{userId}/keywords/learned/{keywordId}")
    public ResponseEntity<Void> addLearnedKeyword(@PathVariable int userId, @PathVariable int keywordId) {
        userService.addLearnedKeyword(userId, keywordId);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @DeleteMapping("/{userId}/keywords/learned/remove/{keywordId}")
    public ResponseEntity<Void> removeLearnedKeyword(@PathVariable int userId, @PathVariable int keywordId) {
        userService.removeLearnedKeyword(userId, keywordId); 
        return new ResponseEntity<>(HttpStatus.NO_CONTENT); 
    }

    @PostMapping("/{userId}/tutorials/learned/{tutorialId}")
    public ResponseEntity<Void> addLearnedTutorial(@PathVariable int userId, @PathVariable int tutorialId) {
        userService.addLearnedTutorial(userId, tutorialId);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/{userId}/tutorials/learned/remove/{tutorialId}")
    public ResponseEntity<Void> removeLearnedTutorial(@PathVariable int userId, @PathVariable int tutorialId) {
        userService.removeLearnedTutorial(userId, tutorialId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/{userId}/tutorials/favorites/{tutorialId}")
    public ResponseEntity<Void> addFavTutorial(@PathVariable int userId, @PathVariable int tutorialId) {
        userService.addFavTutorial(userId, tutorialId);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/{userId}/tutorials/favorites/remove/{tutorialId}")
    public ResponseEntity<Void> removeFavTutorial(@PathVariable int userId, @PathVariable int tutorialId) {
        userService.removeFavTutorial(userId, tutorialId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
