package com.example.javacorecoursework2.Controller;

import com.example.javacorecoursework2.Model.Question;
import com.example.javacorecoursework2.Service.QuestionService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Set;


@RestController
@RequestMapping("/java")
public class JavaQuestionController {
    @Qualifier("javaQuestionService")
    private final QuestionService service;


    public JavaQuestionController(@Qualifier("javaQuestionService") QuestionService questionService) {
        this.service = questionService;
    }

    @GetMapping
    public Collection<Question> questionSet()   {
        return service.getAll();
    }

    @GetMapping("/add")
    public Question add(@RequestParam("question") String question, @RequestParam("answer") String answer) {
        return service.add(question, answer);
    }

    @GetMapping("/remove")
    public Question remove(@RequestParam("question") String question, @RequestParam("answer") String answer) {
        Question removeQuestion = new Question(question, answer);
        return service.remove(removeQuestion);
    }
}
