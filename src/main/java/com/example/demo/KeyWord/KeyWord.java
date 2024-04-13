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
    private Long id;
    @Column(nullable = false)
    private Long tutorial_Id;

    @Column(nullable = false)
    private String text;

    @Column(nullable = false)
    private int level;
}
