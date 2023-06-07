package com.example.javacorecoursework2.Service;

import com.example.javacorecoursework2.Exception.BadRequestException;
import com.example.javacorecoursework2.Model.Question;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ExaminerServiceImpl implements ExaminerService {

    private final QuestionService questionService;

    public ExaminerServiceImpl(QuestionService questionService) {
        this.questionService = questionService;
    }

    @Override
    public List<Question> getQuestions(int amount) {
        if (amount > questionService.getAll().size()) {
            throw new BadRequestException();
        } else {
            List<Question> questionList = new ArrayList<>();
            for (int i = 0; i < amount; i++) {
                Question question = questionService.getRandomQuestion();
                if (questionList.contains(question)) {
                    i--;
                    continue;
                }
                questionList.add(question);
            }
            return questionList;
        }
    }
}
