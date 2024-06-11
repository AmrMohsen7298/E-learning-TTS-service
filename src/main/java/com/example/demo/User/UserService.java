package com.example.demo.User;

import com.example.demo.Tutorial.Tutorial;
import com.example.demo.Tutorial.TutorialRepository;
import com.example.demo.KeyWord.KeyWord;
import com.example.demo.KeyWord.KeyWordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TutorialRepository tutorialRepository;

    @Autowired
    private KeyWordRepository keyWordRepository;

    public List<User> getAllUsers() {
        try {
            return userRepository.findAll();
        } catch (Exception e) {
            System.err.println("Exception in getAllUsers: " + e.getMessage());
            e.printStackTrace();
            return List.of();
        }
    }

    public User getUserById(int id) {
        try {
            return userRepository.findById(id).orElse(null);
        } catch (Exception e) {
            System.err.println("Exception in getUserById: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    public User saveUser(String username,String password,String name) {
        try {
            User user = new User();
            user.setUsername(username);
            user.setPassword(password);
            user.setName(name);
            return userRepository.save(user);
        } catch (Exception e) {
            System.err.println("Exception in saveUser: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    public User updateUser(int id, String username,String password,String name) {
        try {
            User user = new User();
            user.setId(id);
            user.setUsername(username);
            user.setPassword(password);
            user.setName(name);
            return userRepository.save(user);
        } catch (Exception e) {
            System.err.println("Exception in UpdateUser: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    public void deleteUser(int id) {
        try {
            userRepository.deleteById(id);
        } catch (Exception e) {
            System.err.println("Exception in deleteUser: " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }

    public List<Tutorial> getLearnedTutorials(int userId) {
        try {
            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new IllegalStateException("User with id " + userId + " does not exist."));
            return user.getLearnedTutorials();
        } catch (Exception e) {
            System.err.println("Exception in getLearnedTutorials: " + e.getMessage());
            e.printStackTrace();
            return List.of();
        }
    }

    public List<Tutorial> getFavTutorials(int userId) {
        try {
            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new IllegalStateException("User with id " + userId + " does not exist."));
            return user.getFavTutorials();
        } catch (Exception e) {
            System.err.println("Exception in getFavTutorials: " + e.getMessage());
            e.printStackTrace();
            return List.of();
        }
    }

    public List<KeyWord> getLearnedKeywords(int userId) {
        try {
            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new IllegalStateException("User with id " + userId + " does not exist."));
            return user.getLearnedKeywords();
        } catch (Exception e) {
            System.err.println("Exception in getLearnedKeywords: " + e.getMessage());
            e.printStackTrace();
            return List.of();
        }
    }

    public void addLearnedKeyword(int userId, int keyWordId) {
        try {
            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new IllegalStateException("User with id " + userId + " does not exist."));
            KeyWord keyWord = keyWordRepository.findById(keyWordId)
                    .orElseThrow(() -> new IllegalStateException("KeyWord with id " + keyWordId + " does not exist."));

            List<KeyWord> learnedKeyWords = user.getLearnedKeywords();
            if (!learnedKeyWords.contains(keyWord)) {
                learnedKeyWords.add(keyWord);
                user.setLearnedKeywords(learnedKeyWords);
                userRepository.save(user);
            }
        } catch (Exception e) {
            System.err.println("Exception in addLearnedKeyWord: " + e.getMessage());
            e.printStackTrace();
        }
    }
    public void removeLearnedKeyword(int userId, int keyWordId) {
        try {
            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new IllegalStateException("User with id " + userId + " does not exist."));
            KeyWord keyWord = keyWordRepository.findById(keyWordId)
                    .orElseThrow(() -> new IllegalStateException("KeyWord with id " + keyWordId + " does not exist."));

            List<KeyWord> learnedKeyWords = user.getLearnedKeywords();
            if (learnedKeyWords.contains(keyWord)) {
                learnedKeyWords.remove(keyWord);
                user.setLearnedKeywords(learnedKeyWords);
                userRepository.save(user);
            }
        } catch (Exception e) {
            System.err.println("Exception in removeLearnedKeyWord: " + e.getMessage());
            e.printStackTrace();
        }
    }
    public void addLearnedTutorial(int userId, int tutorialId) {
        try {
            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new IllegalStateException("User with id " + userId + " does not exist."));
            Tutorial tutorial = tutorialRepository.findById(tutorialId)
                    .orElseThrow(() -> new IllegalStateException("Tutorial with id " + tutorialId + " does not exist."));

            List<Tutorial> learnedTutorials = user.getLearnedTutorials();
            if (!learnedTutorials.contains(tutorial)) {
                learnedTutorials.add(tutorial);
                user.setLearnedTutorials(learnedTutorials);
                userRepository.save(user);
            }
        } catch (Exception e) {
            System.err.println("Exception in addLearnedTutorial: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void removeLearnedTutorial(int userId, int tutorialId) {
        try {
            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new IllegalStateException("User with id " + userId + " does not exist."));
            Tutorial tutorial = tutorialRepository.findById(tutorialId)
                    .orElseThrow(() -> new IllegalStateException("Tutorial with id " + tutorialId + " does not exist."));

            List<Tutorial> learnedTutorials = user.getLearnedTutorials();
            if (learnedTutorials.contains(tutorial)) {
                learnedTutorials.remove(tutorial);
                user.setLearnedTutorials(learnedTutorials);
                userRepository.save(user);
            }
        } catch (Exception e) {
            System.err.println("Exception in removeLearnedTutorial: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void addFavTutorial(int userId, int tutorialId) {
        try {
            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new IllegalStateException("User with id " + userId + " does not exist."));
            Tutorial tutorial = tutorialRepository.findById(tutorialId)
                    .orElseThrow(() -> new IllegalStateException("Tutorial with id " + tutorialId + " does not exist."));

            List<Tutorial> learnedTutorials = user.getFavTutorials();
            if (!learnedTutorials.contains(tutorial)) {
                learnedTutorials.add(tutorial);
                user.setLearnedTutorials(learnedTutorials);
                userRepository.save(user);
            }
        } catch (Exception e) {
            System.err.println("Exception in addFavTutorial: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    public void removeFavTutorial(int userId, int tutorialId) {
        try {
            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new IllegalStateException("User with id " + userId + " does not exist."));
            Tutorial tutorial = tutorialRepository.findById(tutorialId)
                    .orElseThrow(() -> new IllegalStateException("Tutorial with id " + tutorialId + " does not exist."));

            List<Tutorial> learnedTutorials = user.getFavTutorials();
            if (learnedTutorials.contains(tutorial)) {
                learnedTutorials.remove(tutorial);
                user.setLearnedTutorials(learnedTutorials);
                userRepository.save(user);
            }
        } catch (Exception e) {
            System.err.println("Exception in removeFavTutorial: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
