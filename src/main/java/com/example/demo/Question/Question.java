package com.example.demo.Question;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
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
    private Long Question_ID;
    private String code;
    private String text;

    @ElementCollection
    @CollectionTable(name = "Question_questions", joinColumns = @JoinColumn(name = "Question_id"))
    
    @Column(name = "Choice")
    private List<String> choices;
    private String answer;

    public Question() {
    }
    public Question(Long id, String text, String code, List<String> choices, String answer) {
        this.Question_ID = id;
        this.text = text;
        this.choices = choices;
        this.code = code;
        this.answer = answer;
    }

    public Question(String text, String code, List<String> choices, String answer) {
        this.text = text;
        this.choices = choices;
        this.code = code;
        this.answer = answer;
    }
    
    public Long getId() {
        return Question_ID;
    }

    public void setId(Long id) {
        this.Question_ID = id;
    }
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<String> getChoices() {
        return choices;
    }

    public void setChoices(List<String> choices) {
        this.choices = choices;
    }
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
