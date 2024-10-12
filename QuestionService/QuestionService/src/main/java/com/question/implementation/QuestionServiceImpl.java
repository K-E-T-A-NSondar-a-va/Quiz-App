package com.question.implementation;

import com.question.repository.QuestionRepository;
import com.question.entity.Question;
import com.question.model.Quiz;
import com.question.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private RestTemplate template;

    @Override
    public void addQuestion(Question question) {
        questionRepository.save(question);
    }

    @Override
    public Question getQuestionById(Long id) {
        return questionRepository.findById(id).get();
    }

    @Override
    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }

    @Override
    public List<Quiz> getAllQuizes() {
        ResponseEntity<Quiz[]> quizes = template.getForEntity("http://localhost:8081/quiz/", Quiz[].class);
        Quiz[] quizzes = quizes.getBody();
        return Arrays.stream(quizzes).toList();
    }

    @Override
    public void deleteQuestionByQueIdAndQuizId(Long questionId) {
        questionRepository.deleteById(questionId);
    }

    @Override
    public List<Question> getQuestionOfQuiz(Long quizId) {
        return questionRepository.findByQuizId(quizId);
    }

    @Override
    public Question updateQuestion(Question question) {
        return questionRepository.save(question);
    }
}
