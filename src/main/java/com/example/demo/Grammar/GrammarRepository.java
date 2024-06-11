package com.example.demo.Grammar;

import com.example.demo.Question.Question;
import com.example.demo.Story.Story;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface GrammarRepository extends JpaRepository<Grammar, Integer> {
    @Query("SELECT g from Grammar g where g.header = ?1")
    Optional<Grammar> findGrammarByHeader(String header);
    List<Grammar> findByTutorialId(int tutorialId);}

