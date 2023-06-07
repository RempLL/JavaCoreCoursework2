package com.example.javacorecoursework2.Service;

import com.example.javacorecoursework2.Exception.BadRequestException;
import com.example.javacorecoursework2.Exception.RemoveException;
import com.example.javacorecoursework2.Model.Question;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class JavaQuestionService implements QuestionService {

    public Set<Question> questions = new HashSet<>();

    @Override
    public Question add(String question, String answer) {
        if(question.equals(answer)){
            throw new BadRequestException();
        }
        Question addQuestion = new Question(question, answer);
        questions.add(addQuestion);
        return addQuestion;
    }

    @Override
    public Question add(Question question) {
        questions.add(question);
        return question;
    }

    @Override
    public Question remove(Question question) {
        if(questions.contains(question)){
            questions.remove(question);
            return question;
        }
        throw new RemoveException();
    }

    @Override
    public Set<Question> getAll() {
        return new HashSet<>(questions);
    }

    @Override
    public Question getRandomQuestion() {
        Random random = new Random();
        int num = random.nextInt(getAll().size());
        return questions.stream().toList().get(num);
    }
}
