package com.example.javacorecoursework2.Service;

import com.example.javacorecoursework2.Exception.BadRequestException;
import com.example.javacorecoursework2.Exception.RemoveException;
import com.example.javacorecoursework2.Model.Question;
import com.example.javacorecoursework2.Repository.MathQuestionRepository;
import com.example.javacorecoursework2.Repository.QuestionRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

@Service
public class MathQuestionService implements QuestionService {
    private QuestionRepository questionRepository = new MathQuestionRepository();

    @Override
    public Question add(String question, String answer) {

        return questionRepository.add(new Question(question, answer));
    }
    @Override
    public Question add(Question question) {
        return questionRepository.add(question);
    }

    @Override
    public Question remove(Question question) {
        return questionRepository.remove(question);
    }

    @Override
    public Set<Question> getAll() {
        return questionRepository.getAll();
    }

    @Override
    public Question getRandomQuestion() {
        Random random = new Random();
        int num = random.nextInt(getAll().size());
        return questionRepository.getAll().stream().toList().get(num);
    }
}
