package com.example.demo.Quiz;

import com.example.demo.Question.Question;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Entity
@Table
public class Quiz {
    @Id
    @SequenceGenerator(
            name = "quiz_sequence",
            sequenceName = "quiz_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "quiz_sequence"
    )
    private int id;

    private String code;
    private int tutorialId;
    @OneToMany(fetch = FetchType.EAGER, targetEntity = Question.class)
    private List<Question> questions;
}
