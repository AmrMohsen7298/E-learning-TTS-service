package com.example.demo.Story;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
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
    private int id;
    private int tutorialId;
    private String name;
    private String paragraph;
    private String translation;

    @Override
    public String toString() {
        return "Story{" +
                "id=" + id +
                ", tutorial_id=" + tutorialId +
                ", name='" + name + '\'' +
                ", paragraph='" + paragraph + '\'' +
                '}';
    }
}
