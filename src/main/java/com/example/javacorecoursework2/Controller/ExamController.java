package com.example.javacorecoursework2.Controller;

import com.example.javacorecoursework2.Model.Question;
import com.example.javacorecoursework2.Service.ExaminerService;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
public class ExamController {
    private final ExaminerService examinerService;

    public ExamController(ExaminerService examinerService) {
        this.examinerService = examinerService;
    }

    @GetMapping("/get/{amount}")
    public Collection<Question> questionList(@PathVariable int amount) {
        return examinerService.getQuestions(amount);
    }
}
