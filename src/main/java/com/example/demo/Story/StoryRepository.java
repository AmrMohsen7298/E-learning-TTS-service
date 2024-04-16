package com.example.demo.Story;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface StoryRepository extends JpaRepository<Story, Integer> {

    @Query("SELECT s from Story s where s.name = ?1")
    Optional<Story> findStoryByName(String name);
    /*return studentRepository.findAll();*/

}
