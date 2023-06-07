package com.example.javacorecoursework2.Controller;

import com.example.javacorecoursework2.Model.Question;
import com.example.javacorecoursework2.Service.ExaminerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/exam")
public class ExamController {
    private final ExaminerService examinerService;

    public ExamController(ExaminerService examinerService) {
        this.examinerService = examinerService;
    }

    @GetMapping
    public List<Question> questionList(@RequestParam("num") int num) {
        return examinerService.getQuestions(num);
    }
}
