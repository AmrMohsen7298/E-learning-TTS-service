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
    private int question_ID;
    private int quiz_id;
    private String code;
    private String text;
    @Column(columnDefinition = "TEXT")
    private String choices; // Store as JSON String
    private String answer;
    
}
