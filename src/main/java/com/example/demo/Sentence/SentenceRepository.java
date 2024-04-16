package com.example.demo.Sentence;

import com.example.demo.Story.Story;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SentenceRepository extends JpaRepository<Sentence, Integer> {

    @Query("SELECT s from Sentence s where s.text = ?1")
    Optional<Sentence> findSentenceByText(String text);
    /*return studentRepository.findAll();*/

}
