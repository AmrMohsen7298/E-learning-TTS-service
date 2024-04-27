package com.example.demo.Tutorial;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Table
@Entity
public class Tutorial {
    @Id
    @SequenceGenerator(
            name = "tutorial_sequence",
            sequenceName = "tutorial_sequence",
            allocationSize = 1
    )

    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "tutorial_sequence"
    )
    private int id;
    private String title;
    private String description;
    private String level;
    private boolean isPaid;
    
}

