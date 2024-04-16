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
    private int story_id;
    private int tutorial_Id;
    private String name;
    private String paragraph;

    @Override
    public String toString() {
        return "Story{" +
                "id=" + story_id +
                ", tutorial_id=" + tutorial_Id +
                ", name='" + name + '\'' +
                ", paragraph='" + paragraph + '\'' +
                '}';
    }
}
