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
        private Long tutorial_Id;

        public Quiz(String code, Question question,Long tutorial_Id) {
            this.code = code;
            this.question = question;
            this.tutorial_Id = tutorial_Id;
        }
}
