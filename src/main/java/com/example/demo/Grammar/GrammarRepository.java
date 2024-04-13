package com.example.demo.Grammar;

import com.example.demo.Question.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface GrammarRepository extends JpaRepository<Grammar, Long> {
    @Query("SELECT g from Grammar g where g.header = ?1")
    Optional<Grammar> findGrammarByHeader(String header);
}

