package com.answer.service;

import com.answer.entity.Answer;

import java.util.List;

public interface AnswerService {
    void saveAnswer(Answer answer);
    Answer getAnswerById(Long answerId);
    void deleteAnswerById(Long answerId);
    Answer updateAnswer(Answer answer);
    List<Answer> getAllAnswers();
    List<Answer> getAllAnswerByQuiz(Long quizId);
}
