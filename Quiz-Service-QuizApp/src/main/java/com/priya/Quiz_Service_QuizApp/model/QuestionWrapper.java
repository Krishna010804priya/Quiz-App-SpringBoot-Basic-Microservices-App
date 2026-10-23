package com.priya.Quiz_Service_QuizApp.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class QuestionWrapper {

    private Integer id;
    private String questionTitle;
    private String option1;
    private String option2;
    private String option3;
    private String option4;
}

//Yes, QuestionWrapper is a DTO (Data Transfer Object).

//A DTO is a simple class used to carry data between layers, or from your API to the client,
// containing only the fields you want to expose.
