package com.example.demo.Story;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.Period;

@Entity
@Table
public class Story {
    @Id
    @SequenceGenerator(
            name = "story_sequence",
            sequenceName = "story_sequence",
            allocationSize = 1
    )

    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "story_sequence"
    )
    private Long id;
    private String name;
    private String paragraph;
    private String level;

    public Story() {
    }

    public Story(Long id, String name, String paragraph, String level) {
        this.id = id;
        this.name = name;
        this.paragraph = paragraph;
        this.level = level;
    }

    public Story(String name, String paragraph, String level) {
        this.name = name;
        this.paragraph = paragraph;
        this.level = level;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParagraph() {
        return paragraph;
    }

    public void setParagraph(String paragraph) {
        this.paragraph = paragraph;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    @Override
    public String toString() {
        return "Story{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", paragraph='" + paragraph + '\'' +
                '}';
    }
}
