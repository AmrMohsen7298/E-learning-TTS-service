package com.example.demo.TTS;

import com.google.protobuf.ByteString;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SentenceAudioRepository extends JpaRepository<SentencesAudio, ByteString> {

}
