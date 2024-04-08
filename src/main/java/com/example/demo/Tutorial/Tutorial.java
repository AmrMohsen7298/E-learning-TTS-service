package com.example.demo.Tutorial;
import antlr.Grammar;
import com.example.demo.Quiz.Quiz;
import com.example.demo.Story.Story;
import javassist.compiler.ast.Keyword;
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
public class Tutorial {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "story_id")
    private Story story;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "quiz_id")
    private Quiz quiz;

    @OneToMany(mappedBy = "tutorial", cascade = CascadeType.ALL)
    private List<Keyword> keywords;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "grammar_id")
    private Grammar grammar;
    
}

