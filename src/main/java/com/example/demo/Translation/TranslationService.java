package com.example.demo.Translation;

import org.springframework.stereotype.Service;
 import com.google.cloud.translate.*;


@Service
public class TranslationService {
    public String translateText(String word){
        Translate translate = TranslateOptions.getDefaultInstance().getService();
        return translate.translate(word).getTranslatedText();

    }


}
