package com.example.javacorecoursework2.Repository;

import com.example.javacorecoursework2.Model.Question;
import org.springframework.stereotype.Repository;

import java.util.Set;
@Repository
public interface QuestionRepository {
    Question add(Question question);

    Question remove(Question question);

    Set<Question> getAll();


}
