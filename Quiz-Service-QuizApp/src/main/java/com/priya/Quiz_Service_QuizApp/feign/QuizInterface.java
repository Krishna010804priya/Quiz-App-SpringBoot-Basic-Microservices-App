package com.priya.Quiz_Service_QuizApp.feign;

import com.priya.Quiz_Service_QuizApp.model.QuestionWrapper;
import com.priya.Quiz_Service_QuizApp.model.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("QUESTION-SERVICE-QUIZAPP")
public interface QuizInterface {
    //connecting to question-service with the help of eureka server as a feign client...

    //need to mention what the url needed to connect with question-service...
    //so copy all methods from quesiton-service and use here but as a abstract methods...because this place is Interface...

    //1
    @GetMapping("question/generate")
    public ResponseEntity<List<Integer>> getQuestionsForQuiz
    (@RequestParam String categoryName, @RequestParam Integer numQuestions);

    //2
    @PostMapping("question/getQuestions")
    public ResponseEntity<List<QuestionWrapper>> getQuestionsFromId(@RequestBody List<Integer> questionIds);

    //3
    @PostMapping("question/getScore")
    public ResponseEntity<Integer> getScore(@RequestBody List<Response> responses);

}
