package com.example.demo.KeyWord;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface KeyWordRepository extends JpaRepository<KeyWord, Integer> {
//    @Query("SELECT k from KeyWord k where k.text = ?1 AND k.tutorial_Id = ?2")
    Optional<KeyWord> getByTextAndTutorialId(String text, int tutorialId);
    Optional<KeyWord> findByLevel(String level);
    Optional<KeyWord> findByTutorialId(int tutorialId);
}
