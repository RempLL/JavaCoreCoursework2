package com.example.javacorecoursework2.Service;

import com.example.javacorecoursework2.Model.Question;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public interface ExaminerService {
    Collection<Question> getQuestions(int amount);
}
