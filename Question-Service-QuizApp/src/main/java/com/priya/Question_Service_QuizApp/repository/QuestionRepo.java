package com.priya.Question_Service_QuizApp.repository;


import com.priya.Question_Service_QuizApp.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepo extends JpaRepository<Question, Integer> {

    //JPA is smart enough to understand that user needs list based on Category...because category is in Table...
    //but if we want to do customization on this...this won't work...need to go for HQL, JPQL...
    List<Question> findByCategory(String category);

    //here we are doing customization... with query annotation...
    @Query(value = "SELECT q.id FROM question q where q.category= :category ORDER BY RAND() LIMIT :numQ", nativeQuery = true)
    List<Integer> findRandomQuestionByCategory(String category, int numQ);
    //to mention the variable here in above command use ":", like :category and :numQ...
}
