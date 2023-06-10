package com.example.javacorecoursework2.Service;

import com.example.javacorecoursework2.Model.Question;
import com.example.javacorecoursework2.Repository.MathQuestionRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;
import java.util.Set;

@Service
public class MathQuestionService implements QuestionService {
    private final MathQuestionRepository questionRepository;

    private final Random random;

    public MathQuestionService(MathQuestionRepository mathQuestionRepository) {
        this.questionRepository = mathQuestionRepository;
        this.random = new Random();
    }

    @Override
    public Question add(String question, String answer) {

        return add(new Question(question, answer));
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
    public Collection<Question> getAll() {
        return questionRepository.getAll();
    }

    @Override
    public Question getRandomQuestion() {
        Collection<Question> questions = getAll();
        int num = random.nextInt(questions.size());
        return new ArrayList<>(questions).get(num);
    }
}
