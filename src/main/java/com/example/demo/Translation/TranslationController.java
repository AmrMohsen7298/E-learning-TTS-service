package com.example.demo.Translation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/translate")
public class TranslationController {
    @Autowired
    TranslationService translationService;
    @GetMapping
    public String translation(){
        return translationService.translateText("انا احمد");
    }
}
