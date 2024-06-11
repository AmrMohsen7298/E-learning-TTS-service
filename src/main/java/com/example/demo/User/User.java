package com.example.demo.User;

import com.example.demo.Tutorial.Tutorial;
import com.example.demo.KeyWord.KeyWord;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Entity
@Table(name = "users")  // Use "users" to avoid conflicts with the SQL "user" keyword
public class User {
    @Id
    @SequenceGenerator(
            name = "user_sequence",
            sequenceName = "user_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_sequence"
    )
    private int id;
    private String username;
    private String password;
    private String name;

    @OneToMany
    @JoinColumn(name = "user_id")  // Foreign key column in Tutorial table
    private List<Tutorial> tutorials;

    @OneToMany
    @JoinColumn(name = "learned_tutorials_user_id")  // Foreign key column in Tutorial table
    private List<Tutorial> learnedTutorials;

    @OneToMany
    @JoinColumn(name = "fav_tutorials_user_id")  // Foreign key column in Tutorial table
    private List<Tutorial> favTutorials;

    @OneToMany
    @JoinColumn(name = "learned_keywords_user_id")  // Foreign key column in KeyWord table
    private List<KeyWord> learnedKeywords;
}
