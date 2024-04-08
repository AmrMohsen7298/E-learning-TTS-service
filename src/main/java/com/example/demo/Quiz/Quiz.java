package com.example.demo.Quiz;

import com.example.demo.Question.Question;
import lombok.*;

import javax.persistence.*;

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
        private Long id;
        private String code;
        @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST,CascadeType.MERGE})
        @JoinColumn(name = "Question_ID")
        private Question question;

        public Quiz(String code, Question question) {
            this.code = code;
            this.question = question;
        }
}
