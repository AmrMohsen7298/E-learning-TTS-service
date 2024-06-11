package com.example.demo.Question;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Table
@Entity
public class Question {
    @Id
    @SequenceGenerator(
            name = "Question_sequence",
            sequenceName = "Question_sequence",
            allocationSize = 1
    )

    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "Question_sequence"
    )
    private int id;
    private int quizId;
    private String code;
    private String text;
    @ElementCollection
    @CollectionTable(name = "question_choices", joinColumns = @JoinColumn(name = "question_id"))
    @Column(name = "choice", columnDefinition = "TEXT")
    private List<String> choices;
    private String answer;
    
}
