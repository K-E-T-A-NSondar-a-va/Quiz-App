package com.report.controller;

import com.report.dto.QuizData;
import com.report.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/report")
public class ReportController {

    @Autowired
    private ReportService reportService;

    @GetMapping("/")
    ResponseEntity<List<QuizData>> getAllQuizData() {
        return ResponseEntity.ok(reportService.formatAndForwardQuiz());
    }
}
