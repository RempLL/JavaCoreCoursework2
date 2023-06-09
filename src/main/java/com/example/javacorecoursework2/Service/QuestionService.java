package com.example.javacorecoursework2.Service;

import com.example.javacorecoursework2.Model.Question;
import org.springframework.stereotype.Service;

import java.util.Set;
@Service
public interface QuestionService {

    Question add(String question, String answer);

    Question add(Question question);

    Question remove(Question question);

    Set<Question> getAll();

    Question getRandomQuestion();
}
