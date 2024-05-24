package com.example.demo.TTS;

import com.google.cloud.texttospeech.v1beta1.Timepoint;
import com.google.protobuf.ByteString;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Entity
@Table
public class ParagraphAudio {
    @Id
    private int storyId;
    private ByteString audioContent;
    @Column(length=1024)
    private String timePoints;
}
