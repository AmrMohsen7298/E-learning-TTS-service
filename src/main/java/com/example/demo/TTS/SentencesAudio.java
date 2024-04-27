package com.example.demo.TTS;

import com.google.protobuf.ByteString;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Entity
@Table
public class SentencesAudio {
    public SentencesAudio(int storyId, ByteString audio){
        this.storyId = storyId;
        this.audio = audio;
    }
    @Id
    @SequenceGenerator(
            name = "sentence_sequence",
            sequenceName = "sentence_sequence",
            allocationSize = 1
    )

    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "sentence_sequence"
    )
    private int id;
    private int storyId;
    private ByteString audio;
    private String text;
}
