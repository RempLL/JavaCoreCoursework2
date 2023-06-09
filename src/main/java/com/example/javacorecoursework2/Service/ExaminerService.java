package com.example.javacorecoursework2.Service;

import com.example.javacorecoursework2.Model.Question;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface ExaminerService {
    List<Question> getQuestions(int amount);
}
