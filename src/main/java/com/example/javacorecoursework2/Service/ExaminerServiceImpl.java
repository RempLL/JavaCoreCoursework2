package com.example.javacorecoursework2.Service;

import com.example.javacorecoursework2.Exception.BadRequestException;
import com.example.javacorecoursework2.Model.Question;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ExaminerServiceImpl implements ExaminerService {

    private final QuestionService javaQuestionService;
    private final QuestionService mathQuestionService;

    public ExaminerServiceImpl(@Qualifier("javaQuestionService") QuestionService javaQuestionService,
                               @Qualifier("mathQuestionService") QuestionService mathQuestionService) {
        this.javaQuestionService = javaQuestionService;
        this.mathQuestionService = mathQuestionService;
    }

    @Override
    public List<Question> getQuestions(int amount) {
        if (amount > (javaQuestionService.getAll().size() + mathQuestionService.getAll().size()) || amount < 1) {
            throw new BadRequestException();
        } else {
            List<Question> questionList = new ArrayList<>();
            for (int i = 0; i < amount/2; i++) {
                Question javaQuestion = javaQuestionService.getRandomQuestion();
                Question mathQuestion = mathQuestionService.getRandomQuestion();
                if (questionList.contains(javaQuestion) || questionList.contains(mathQuestion)) {
                    i--;
                    continue;
                }
                questionList.add(javaQuestion);
                questionList.add(mathQuestion);
            }
            return questionList;
        }
    }
}
