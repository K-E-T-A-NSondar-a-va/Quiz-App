package com.quiz.controller;

import com.quiz.entity.Quiz;
import com.quiz.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quiz")
public class QuizController {

    @Autowired
    private QuizService quizService;

    @PostMapping("/add")
    public ResponseEntity<String> addNewQuiz(@RequestBody Quiz quiz) {
        quizService.addQuiz(quiz);
        return new ResponseEntity<>("quiz added successfully", HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<List<Quiz>> getListOfAllQuizes() {
        return ResponseEntity.ok(quizService.getAllQuizes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Quiz> getQuizById(@PathVariable Long id) {
        return ResponseEntity.ok(quizService.getQuizById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTheQuizById(@PathVariable Long id) {
        quizService.deleteQuiz(id);
        return new ResponseEntity<>("deleted the quiz with id: "+id, HttpStatus.OK);
    }

}
