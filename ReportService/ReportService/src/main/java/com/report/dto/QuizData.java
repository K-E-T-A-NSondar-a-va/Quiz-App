package com.report.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuizData {
    private Long quizId;
    private String quizName;
    private List<QuestionWithAnswer> listOfQnA;
}
