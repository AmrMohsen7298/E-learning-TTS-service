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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int tutorial_id;
    private String title;
    private String level;
    
}

