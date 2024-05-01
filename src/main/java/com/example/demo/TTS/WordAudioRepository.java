package com.example.demo.TTS;

import com.google.protobuf.ByteString;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WordAudioRepository extends JpaRepository<WordAudio, ByteString>  {
    Optional<WordAudio[]> getByStoryId(int storyId);
}
