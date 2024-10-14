package com.report.dto;

import lombok.Data;

import java.util.List;

@Data
public class Quiz {
    private Long id;
    private String title;
    transient private List<Question> questions;
}
