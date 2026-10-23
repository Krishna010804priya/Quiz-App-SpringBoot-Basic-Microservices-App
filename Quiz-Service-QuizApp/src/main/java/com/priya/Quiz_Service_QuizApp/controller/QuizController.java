package com.priya.Quiz_Service_QuizApp.controller;

import com.priya.Quiz_Service_QuizApp.model.QuestionWrapper;
import com.priya.Quiz_Service_QuizApp.model.QuizDto;
import com.priya.Quiz_Service_QuizApp.model.Response;
import com.priya.Quiz_Service_QuizApp.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("quiz")
public class QuizController {

    @Autowired
    QuizService quizService;

//    /quiz/create?category=Java&numQ=5&title=JQuiz
    @PostMapping("create")
    public ResponseEntity<String> createQuiz(@RequestBody QuizDto quizDto){
        return quizService.createQuiz(quizDto.getCategoryName(), quizDto.getNumQuestions(), quizDto.getTitle());
    }


    //here we created the quiz and now need to fetch the quiz's questions with "id" to play quiz and
    //at the same time we need only questions not right_answer variable...in Question model we have that variable too...
    //so create a QuestionWrapper class which doesn't have right_answer variable...
    @GetMapping("/get/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(@PathVariable Integer id){
        return quizService.getQuizQuestions(id);
    }

    //from the response of user checking the results...
//    @GetMapping("/submit/{id}")
    @PostMapping("/submit/{id}")
    public ResponseEntity<Integer> submitQuiz(@PathVariable Integer id, @RequestBody List<Response> responses){
        return quizService.calculateResult(id, responses);
    }


}
