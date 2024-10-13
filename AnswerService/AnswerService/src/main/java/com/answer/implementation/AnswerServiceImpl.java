package com.answer.implementation;

import com.answer.entity.Answer;
import com.answer.repository.AnswerRepository;
import com.answer.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnswerServiceImpl implements AnswerService {

    @Autowired
    private AnswerRepository answerRepository;

    @Override
    public void saveAnswer(Answer answer) {
        answerRepository.save(answer);
    }

    @Override
    public Answer getAnswerById(Long answerId) {
        return answerRepository.findById(answerId).get();
    }

    @Override
    public void deleteAnswerById(Long answerId) {
        answerRepository.deleteById(answerId);
    }

    @Override
    public Answer updateAnswer(Answer answer) {
        return answerRepository.save(answer);
    }

    @Override
    public List<Answer> getAllAnswers() {
        return answerRepository.findAll();
    }

    @Override
    public List<Answer> getAllAnswerByQuiz(Long quizId) {
        return answerRepository.findAll().stream().filter(answer -> answer.getQuizId().equals(quizId)).toList();
    }
}
