package com.priya.Question_Service_QuizApp.service;

import com.priya.Question_Service_QuizApp.model.Question;
import com.priya.Question_Service_QuizApp.model.QuestionWrapper;
import com.priya.Question_Service_QuizApp.model.Response;
import com.priya.Question_Service_QuizApp.repository.QuestionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {

    @Autowired
    QuestionRepo questionRepo;

    public ResponseEntity<List<Question>> getAllQuestions() {
        //exception handling request...
        try{
            return new ResponseEntity<>(questionRepo.findAll(), HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<List<Question>> getQuestionsByCategory(String category) {
        try{
            return new ResponseEntity<>(questionRepo.findByCategory(category), HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<String> addQuestion(Question question) {
//         try{
//             questionRepo.save(question);
//             return new ResponseEntity<>("Added...", HttpStatus.CREATED);
//         }catch (Exception e){
//             e.printStackTrace();
//         }
//         return new ResponseEntity<>("Not Added!!!", HttpStatus.BAD_REQUEST);
        questionRepo.save(question);
        return new ResponseEntity<>("Added...", HttpStatus.CREATED);
    }

    public ResponseEntity<String> updateQuestion(Question question){
//        try{
//            questionRepo.save(question);
//            return new ResponseEntity<>("Updated...", HttpStatus.CREATED);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//        return new ResponseEntity<>("Not Updated!!!", HttpStatus.BAD_REQUEST);
        questionRepo.save(question);
        return new ResponseEntity<>("Updated...", HttpStatus.CREATED);
    }

    public ResponseEntity<String> deleteQuestionById(Integer id) {
//        try{
//            questionRepo.deleteById(id);
//            return new ResponseEntity<>("Deleted...", HttpStatus.OK);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//        return new ResponseEntity<>("Not Deleted!!!", HttpStatus.BAD_REQUEST);
         questionRepo.deleteById(id);
         return new ResponseEntity<>("Deleted...", HttpStatus.OK);
    }


    //-------------------------------------------new one--------------------------------------------
    //here we are just returning questions id...because all the caluculations are done here itself...
    // so quiz has no need to know about questions...
    public ResponseEntity<List<Integer>> getQuestionsForQuiz(String categoryName, Integer numQuestions) {
        List<Integer> questions = questionRepo.findRandomQuestionByCategory(categoryName, numQuestions);
        return new ResponseEntity<>(questions, HttpStatus.OK);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuestionsFromId(List<Integer> questionIds) {
        List<QuestionWrapper> wrappers = new ArrayList<>();
        List<Question> questions = new ArrayList<>();
        for(Integer id: questionIds){
            questions.add(questionRepo.findById(id).get()); //return type is optional so use get()...
        }
        for(Question question: questions){
            QuestionWrapper wrapper = new QuestionWrapper();
            wrapper.setId(question.getId());
            wrapper.setQuestionTitle(question.getQuestionTitle());
            wrapper.setOption1(question.getOption1());
            wrapper.setOption2(question.getOption2());
            wrapper.setOption3(question.getOption3());
            wrapper.setOption4(question.getOption4());
            wrappers.add(wrapper);
        }
        return new ResponseEntity<>(wrappers, HttpStatus.OK);
    }

    public ResponseEntity<Integer> getScore(List<Response> responses) {
        int right = 0;
        for(Response response : responses){
            Question question = questionRepo.findById(response.getId()).get();
            if(response.getResponse().equals(question.getRightAnswer())){
                right++;
            }
        }
        return new ResponseEntity<>(right, HttpStatus.OK);
    }
}
