package com.example.javacorecoursework2.Repository;

import com.example.javacorecoursework2.Exception.BadRequestException;
import com.example.javacorecoursework2.Exception.RemoveException;
import com.example.javacorecoursework2.Model.Question;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.Set;

@Repository
public class JavaQuestionRepository implements QuestionRepository {

    private final Set<Question> questionsJava = new HashSet<>();

    @Override
    public Question add(Question question) {
        if (question.getQuestion().equals(question.getAnswer())) {
            throw new BadRequestException();
        }
        questionsJava.add(question);
        return question;
    }

    @Override
    public Question remove(Question question) {
        if (questionsJava.contains(question)) {
            questionsJava.remove(question);
            return question;
        }
        throw new RemoveException();
    }

    @Override
    public Set<Question> getAll() {
        return new HashSet<>(questionsJava);
    }
}
