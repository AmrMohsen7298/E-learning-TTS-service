package com.example.demo.TTS;

import com.google.protobuf.ByteString;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SentenceAudioRepository extends JpaRepository<SentencesAudio, ByteString> {

  Optional<SentencesAudio[]> getByStoryId(int storyId);
}
