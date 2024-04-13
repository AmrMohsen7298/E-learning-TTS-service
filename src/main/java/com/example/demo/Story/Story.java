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
    private Long id;
    private Long tutorial_Id;
    private String name;
    private String paragraph; 
    private String level;

    public Story(String name, String paragraph, String level) {
        this.name = name;
        this.paragraph = paragraph;
        this.level = level;
    }

    @Override
    public String toString() {
        return "Story{" +
                "id=" + id +
                ", tutorial_id=" + tutorial_Id +
                ", name='" + name + '\'' +
                ", paragraph='" + paragraph + '\'' +
                ", level='" + level + '\'' +
                '}';
    }
}
