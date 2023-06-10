package com.example.javacorecoursework2.Service;

import com.example.javacorecoursework2.Exception.BadRequestException;
import com.example.javacorecoursework2.Model.Question;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ExaminerServiceImpl implements ExaminerService {

    private final QuestionService javaQuestionService;
    private final QuestionService mathQuestionService;

    private final Random random;

    public ExaminerServiceImpl(JavaQuestionService javaQuestionService,
                               MathQuestionService mathQuestionService) {
        this.javaQuestionService = javaQuestionService;
        this.mathQuestionService = mathQuestionService;
        this.random = new Random();
    }

    @Override
    public Collection<Question> getQuestions(int amount) {
        List<QuestionService> questionList = List.of(javaQuestionService, mathQuestionService);
        if (amount > (javaQuestionService.getAll().size() + mathQuestionService.getAll().size()) || amount < 1) {
            throw new BadRequestException();
        } else {

            Set<Question> setRandom = new HashSet<>(amount);
            while (setRandom.size() < amount) {
                int index = random.nextInt(questionList.size());
                setRandom.add(questionList.get(index).getRandomQuestion());
            }
            return setRandom;
        }
    }
}

