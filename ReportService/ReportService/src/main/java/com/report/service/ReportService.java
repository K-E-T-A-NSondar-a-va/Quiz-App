package com.report.service;

import com.report.dto.Answer;
import com.report.dto.QuestionWithAnswer;
import com.report.dto.Quiz;
import com.report.dto.QuizData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ReportService {

    @Autowired
    private RestTemplate template;

    public List<QuizData> formatAndForwardQuiz() {
        Quiz[] quizzes = template.getForEntity("http://localhost:8080/quiz/", Quiz[].class).getBody();

        List<QuizData> quizDataList = new ArrayList<>();

        Arrays.stream(quizzes).forEach(quiz -> {
            QuizData quizData = new QuizData();
            List<QuestionWithAnswer> qnaList = new ArrayList<>();

            quizData.setQuizId(quiz.getId());
            quizData.setQuizName(quiz.getTitle());

            quiz.getQuestions().forEach(question -> {
                QuestionWithAnswer qna = new QuestionWithAnswer();
                qna.setQuestionId(question.getQuestionId());
                qna.setQuestion(question.getQuestion());

                Answer answer = template.getForObject("http://localhost:8083/answer/question/"+question.getQuestionId(), Answer.class);
                qna.setAnswerId(answer.getAnswerId());
                qna.setAnswer(answer.getAnswer());

                qnaList.add(qna);
            });
            quizData.setListOfQnA(qnaList);
            quizDataList.add(quizData);
        });

        return quizDataList;
    }

}
