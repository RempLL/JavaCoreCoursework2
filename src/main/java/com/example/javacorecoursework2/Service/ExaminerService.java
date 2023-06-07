package com.example.javacorecoursework2.Service;

import com.example.javacorecoursework2.Model.Question;

import java.util.List;

public interface ExaminerService {
    List<Question> getQuestions(int amount);
}
