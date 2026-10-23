package com.priya.Quiz_Service_QuizApp.service;

import com.priya.Quiz_Service_QuizApp.feign.QuizInterface;
import com.priya.Quiz_Service_QuizApp.model.QuestionWrapper;
import com.priya.Quiz_Service_QuizApp.model.Quiz;
import com.priya.Quiz_Service_QuizApp.model.Response;
import com.priya.Quiz_Service_QuizApp.repository.QuizRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuizService {

    @Autowired
    QuizRepo quizRepo;

    @Autowired
    QuizInterface quizInterface; //connection to question-service...

    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {
        //need to call the "generate" url of "question-service"...so we are communicate between two services running in different ports...
        //we can use RestTemplate ==>> http://localhost:8080/question/generate... but the problem is
        //we need to hardcode the port number or IP adderess...
        //but the core idea of MICROSERVICES ==> multiple instances of a service can be run there....so don't know which url it going to hit to get service...
        //that's why "Feign client" concept comes in... it is same as http web req but the difference is...Feign client provides declarative way of requesting the other service...no need of hard coding things to hit service url...
        //"Service Discovery" that is if quiz search for question service, qn service needed to be discoverable...
        //there comes in concept called "Eureka Server"==> so all the services need to be register itself in the eureka registry and the services can be discover other services using "Eureka Client"...
        //so using "Feign client" and "Eureka Registry" we can achieve communication between two services without hard coding ports or other things...
        //using service name we can easily achieve it...**********

        List<Integer> questions = quizInterface.getQuestionsForQuiz(category, numQ).getBody(); //the return type in question-service is ResposeEntity...so getting body only as result...
        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestionIds(questions);
        quizRepo.save(quiz);

        return new ResponseEntity<>("Successfully Quiz created...", HttpStatus.OK);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id) {
        Quiz quiz = quizRepo.findById(id).get();
        List<Integer> questionsIds = quiz.getQuestionIds();
        ResponseEntity<List<QuestionWrapper>> questions = quizInterface.getQuestionsFromId(questionsIds);
        return questions;
    }

    public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses) {
        ResponseEntity<Integer> score = quizInterface.getScore(responses);
        return score;
    }
}
