package com.quiz.implementation;

import com.quiz.dto.Question;
import com.quiz.entity.Quiz;
import com.quiz.repository.QuizRepository;
import com.quiz.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class QuizServiceImpl implements QuizService {

    @Autowired
    private QuizRepository quizRepository;

    @Autowired
    private RestTemplate template;

    @Override
    public void addQuiz(Quiz quiz) {
        quizRepository.save(quiz);
    }

    @Override
    public List<Quiz> getAllQuizes() {
        return quizRepository.findAll();
    }

    @Override
    public Quiz getQuizById(Long id) {
        return quizRepository.findById(id).get();
    }

    @Override
    public void deleteQuiz(Long id) {
        quizRepository.deleteById(id);
    }

    @Override
    public List<Question> getAllQuestionByQuizId(Long quizId) {
        Question[] questions = template.getForEntity("http://localhost:8082/question/quiz/"+quizId, Question[].class).getBody();
        return Arrays.stream(questions).toList();
    }
}
