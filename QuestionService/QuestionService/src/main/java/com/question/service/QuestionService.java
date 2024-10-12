package com.question.service;

import com.question.entity.Question;
import com.question.model.Quiz;

import java.util.List;

public interface QuestionService {
    void addQuestion(Question question);
    Question getQuestionById(Long id);
    List<Question> getAllQuestions();
    List<Quiz> getAllQuizes();
    void deleteQuestionByQueIdAndQuizId(Long questionId);
    List<Question> getQuestionOfQuiz(Long quizId);
    Question updateQuestion(Question question);
}
