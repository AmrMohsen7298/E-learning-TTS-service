package com.example.demo.TTS;

import com.google.protobuf.ByteString;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ParagraphAudioRepository extends JpaRepository<ParagraphAudio, ByteString> {
Optional<ParagraphAudio> getByStoryId(Long id);
}
