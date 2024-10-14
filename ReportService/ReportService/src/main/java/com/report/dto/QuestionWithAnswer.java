package com.report.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestionWithAnswer {
    private Long questionId;
    private String question;
    private Long answerId;
    private String answer;
}
