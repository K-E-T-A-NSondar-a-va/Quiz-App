package com.answer.controller;

import com.answer.entity.Answer;
import com.answer.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/answer")
public class AnswerController {

    @Autowired
    private AnswerService answerService;

    @PostMapping("/")
    public ResponseEntity<String> createAnswer(@RequestBody Answer answer) {
        answerService.saveAnswer(answer);
        return new ResponseEntity<>("answer saved successfully !!", HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<List<Answer>> findAllAnswers() {
        return ResponseEntity.ok(answerService.getAllAnswers());
    }

    @GetMapping("/{answerId}")
    public ResponseEntity<Answer> findAnswerByItsId(@PathVariable Long answerId) {
        return ResponseEntity.ok(answerService.getAnswerById(answerId));
    }

    @PutMapping("/")
    public ResponseEntity<Answer> updateTheAnswer(@RequestBody Answer answer) {
        return ResponseEntity.ok(answerService.updateAnswer(answer));
    }

    @DeleteMapping("/{answerId}")
    public ResponseEntity<String> deleteTheAnswer(@PathVariable Long answerId) {
        answerService.deleteAnswerById(answerId);
        return new ResponseEntity<>("Answer with id "+answerId+"deleted", HttpStatus.ACCEPTED);
    }

    @GetMapping("/quiz/{quizId}")
    public ResponseEntity<List<Answer>> getAnswerListByQuiz(@PathVariable Long quizId) {
        return ResponseEntity.ok(answerService.getAllAnswerByQuiz(quizId));
    }

    @GetMapping("/question/{questionId}")
    public ResponseEntity<Answer> getAnsOfTheQuestion(@PathVariable Long questionId) {
        return ResponseEntity.ok(answerService.getAnswerOfQuestion(questionId));
    }

}
