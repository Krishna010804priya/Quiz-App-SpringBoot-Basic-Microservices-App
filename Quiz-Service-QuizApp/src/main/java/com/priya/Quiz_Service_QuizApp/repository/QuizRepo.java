package com.priya.Quiz_Service_QuizApp.repository;

import com.priya.Quiz_Service_QuizApp.model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizRepo extends JpaRepository<Quiz, Integer> {
}
