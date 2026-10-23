package com.priya.Question_Service_QuizApp.controller;

import com.priya.Question_Service_QuizApp.model.Question;
import com.priya.Question_Service_QuizApp.model.QuestionWrapper;
import com.priya.Question_Service_QuizApp.model.Response;
import com.priya.Question_Service_QuizApp.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/question")
public class QuestionController {

    @Autowired
    QuestionService questionService;

    @GetMapping("/allQuestions")
    public ResponseEntity<List<Question>> getAllQuestions(){
//        return new ResponseEntity<>(questionService.getAllQuestions(), HttpStatus.OK);
        //instead of doing here do in Service layer...
        return questionService.getAllQuestions();
    }

    @GetMapping("category/{cat}") //if the name is not same here and below passing variable...
    public ResponseEntity<List<Question>> getQuestionsByCategory(@PathVariable("cat") String category){
        return questionService.getQuestionsByCategory(category);
    }

    @PostMapping("/add")
    public ResponseEntity<String> addQuestion(@RequestBody Question question){
        return questionService.addQuestion(question);
    }

    @PutMapping("/updateQuestion")
    public ResponseEntity<String> updateQuestion(@RequestBody Question question){
        return questionService.updateQuestion(question);
    }

    @DeleteMapping("/deleteQuestionById/{id}")
    public ResponseEntity<String> deleteQuestionById(@PathVariable Integer id){
        return questionService.deleteQuestionById(id);
    }

    //--- new editing for question-service microservices.... ---

    //LOAD BALANCER CONCEPT...
    //here in this question-service we have two instances one is running in 8080 and other is in 8081...
    //when req comes from quiz-service, on which instance the req hits?
    //which instance is free on the req hits, that instance will give response...this is called LOAD BALANCER CONCEPT...
    //in starting we do many configurations...but now no need...
    //Load balancing is achieved with the help of "eureka server" and "open feign"...
    @Autowired
    Environment environment; //to know which INSTANCE  is calling when req comes from quiz service...
    //1
    @GetMapping("generate")
    public ResponseEntity<List<Integer>> getQuestionsForQuiz
            (@RequestParam String categoryName, @RequestParam Integer numQuestions){
        return  questionService.getQuestionsForQuiz(categoryName, numQuestions);
    }

    //2
    @PostMapping("getQuestions")
    public ResponseEntity<List<QuestionWrapper>> getQuestionsFromId(@RequestBody List<Integer> questionIds){
        System.out.println(environment.getProperty("local.server.port"));
        //to know which INSTANCE  is calling when req comes from quiz service... tested with "getQuestions" url....
        return questionService.getQuestionsFromId(questionIds);
    }

    //3
    @PostMapping("getScore")
    public ResponseEntity<Integer> getScore(@RequestBody List<Response> responses){
        return questionService.getScore(responses);
    }

    //need to work on the below urls to make this question-service...as a independent microservice....
        //1 //generate ==> quiz service will req for generating quiz to question service, and quiz have access to question's
//                     db...in the mono app previously...but now we make question and quiz to run separately and both have
//                     separate db...so quiz will req to question to give/generate qns for quiz through url...that's why
//                     "generate" url...
        //2 //getQuestions (questionid) ==> so the quiz need qns for a quiz id...so this url used...
        //3 //getScore ==> this url to calculate score of a quiz...

}



