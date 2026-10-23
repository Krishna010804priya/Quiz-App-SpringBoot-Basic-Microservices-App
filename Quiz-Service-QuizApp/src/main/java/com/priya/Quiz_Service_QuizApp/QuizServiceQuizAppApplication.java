package com.priya.Quiz_Service_QuizApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class QuizServiceQuizAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(QuizServiceQuizAppApplication.class, args);
	}

}
