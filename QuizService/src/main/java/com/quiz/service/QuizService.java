package com.quiz.service;

import com.quiz.dto.Question;
import com.quiz.entity.Quiz;

import java.util.List;

public interface QuizService {
    void addQuiz(Quiz quiz);

    List<Quiz> getAllQuizes();

    Quiz getQuizById(Long id);

    void deleteQuiz(Long id);

    List<Question> getAllQuestionByQuizId(Long quizId);
}
