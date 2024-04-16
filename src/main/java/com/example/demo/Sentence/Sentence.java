package com.example.demo.Sentence;

import lombok.*;
import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Entity
@Table
public class Sentence {
    @Id
    @SequenceGenerator(
            name = "sentence_sequence",
            sequenceName = "sentence_sequence",
            allocationSize = 1
    )

    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "sentence_sequence"
    )
    private int sentence_id;
    private int story_id;
    private String text;

    @Override
    public String toString() {
        return "Sentence{" +
                "id=" + sentence_id +
                ", story_id=" + story_id +
                ", text='" + text + '\'' +
                '}';
    }
}
