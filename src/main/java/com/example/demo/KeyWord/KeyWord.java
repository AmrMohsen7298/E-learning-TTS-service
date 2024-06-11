package com.example.demo.KeyWord;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Entity
@Table
public class KeyWord {
    @Id
    @SequenceGenerator(
            name = "KeyWord_sequence",
            sequenceName = "KeyWord_sequence",
            allocationSize = 1
    )

    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "KeyWord_sequence"
    )
    private int keyword_id;
    private int tutorialId;
    private String type;
    private String description;
    private String translation;
    private boolean keyFlag = false;
    private byte[] audio;
    private String text;
    private String level;
}
