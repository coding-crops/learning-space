package coder.learning.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
// @EnableScheduling
public class CoderLearningApplication {

	public static void main(String[] args) {
		SpringApplication.run(CoderLearningApplication.class, args);
	}
	
}
