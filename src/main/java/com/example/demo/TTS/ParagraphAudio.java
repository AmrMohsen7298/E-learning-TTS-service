package com.example.demo.TTS;

import com.google.protobuf.ByteString;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Entity
@Table
public class ParagraphAudio {
    @Id
    private Long storyId;
    private ByteString audioContent;
}
