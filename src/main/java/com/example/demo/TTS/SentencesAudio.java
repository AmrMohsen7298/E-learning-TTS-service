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
    public SentencesAudio(Long storyId, ByteString audio){
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
    private Long id;
    private Long storyId;
    private ByteString audio;
}
