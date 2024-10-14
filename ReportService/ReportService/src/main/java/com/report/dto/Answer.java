package com.report.dto;

import lombok.Data;

@Data
public class Answer {
    private Long answerId;
    private Long questionId;
    private Long quizId;
    private String answer;
}
