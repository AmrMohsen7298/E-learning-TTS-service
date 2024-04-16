package com.example.demo.Grammar;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Entity
@Table
public class Grammar {
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
    private int grammar_id;
    private int tutorial_id;
    private String topic;
    private String header;
    private String example;
    private String description;
    
}
