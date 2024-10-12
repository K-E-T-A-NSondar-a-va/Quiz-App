package com.question.controller;

import com.question.entity.Question;
import com.question.model.Quiz;
import com.question.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/question")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @PostMapping("/add")
    public ResponseEntity<String> saveQuestion(@RequestBody Question question) {
        questionService.addQuestion(question);
        return new ResponseEntity<>("question saved successfully !!", HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Question> findQuestionById(@PathVariable Long id) {
        return ResponseEntity.ok(questionService.getQuestionById(id));
    }

    @GetMapping("/")
    public ResponseEntity<List<Question>> findAllQuestion() {
        return ResponseEntity.ok(questionService.getAllQuestions());
    }

    @GetMapping("/quizes")
    public ResponseEntity<List<Quiz>> findAllQuizes() {
        return ResponseEntity.ok(questionService.getAllQuizes());
    }

    @GetMapping("/quiz/{quizId}")
    public ResponseEntity<List<Question>> getAllQuestionByQuiz(@PathVariable Long quizId) {
        return ResponseEntity.ok(questionService.getQuestionOfQuiz(quizId));
    }

    @PutMapping("/")
    public ResponseEntity<Question> updateTheQuestion(@RequestBody Question question) {
        return ResponseEntity.ok(questionService.updateQuestion(question));
    }
}
