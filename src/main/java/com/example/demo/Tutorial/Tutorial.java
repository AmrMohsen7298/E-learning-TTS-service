package com.example.demo.Tutorial;

import com.example.demo.User.User;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Entity
@Table(name = "tutorials")
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
    private byte[] image;
    private boolean isLearned;
    
}
