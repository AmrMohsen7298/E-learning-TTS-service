package com.example.demo.TTS;

import com.google.protobuf.ByteString;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

public class WordAudio {
    public WordAudio(int storyId, ByteString audio){
        this.storyId = storyId;
        this.audio = audio;
    }
    @Id
    @SequenceGenerator(
            name = "word_sequence",
            sequenceName = "word_sequence",
            allocationSize = 1
    )

    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "word_sequence"
    )
    private int id;
    private int storyId;
    private ByteString audio;
    private String text;
}
