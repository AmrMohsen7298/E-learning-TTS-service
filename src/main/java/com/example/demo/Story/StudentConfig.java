package com.example.demo.Story;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class StudentConfig {
    private List<Story> storyList;

    @Bean
    CommandLineRunner commandLineRunner(StoryRepository repository){
        return args -> {
            Story mohammady = new Story(
                    "mohammady",
                    "Mohammady is a coffee shop for youth to smoke argila",
                    "A1"
            );
            Story talia = new Story(
                    "Talia",
                    "Talia is a young girl that sells cigarettes",
                    "A2"
            );
            storyList = new ArrayList<>();
            storyList.add(mohammady);
            storyList.add(talia);
            repository.saveAll(storyList);
        };
    }
}
