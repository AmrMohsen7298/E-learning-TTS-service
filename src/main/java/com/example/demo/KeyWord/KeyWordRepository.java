package com.example.demo.KeyWord;

import com.example.demo.Grammar.Grammar;
import com.example.demo.Question.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface KeyWordRepository extends JpaRepository<KeyWord, Integer> {
    @Query("SELECT k from KeyWord k where k.text = ?1")
    Optional<KeyWord> findKeyWordByText(String text);
}
