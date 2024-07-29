package com.example.demo;

import com.example.demo.Story.Story;
import com.example.demo.config.FileStorageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(
		origins = {"https://tts.eliteacademyeg.com", "https://ttsdash.eliteacademyeg.com"}

)

@SpringBootApplication
@EnableConfigurationProperties({
		FileStorageProperties.class
})

public class DemoApplication {

	public static void main(String[] args) {

		SpringApplication.run(DemoApplication.class, args);
	}



}
