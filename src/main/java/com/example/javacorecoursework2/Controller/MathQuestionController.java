package com.example.javacorecoursework2.Controller;

import com.example.javacorecoursework2.Model.Question;
import com.example.javacorecoursework2.Service.QuestionService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/exam/math")
public class MathQuestionController {
    @Qualifier("mathQuestionService")
    private final QuestionService service;
    public MathQuestionController(@Qualifier("mathQuestionService") QuestionService questionService) {
        this.service = questionService;
    }

    @GetMapping
    public Set<Question> questionSet() {
        return service.getAll();
    }

    @GetMapping("/add")
    public Question add(@RequestParam("question") String question, @RequestParam("answer") String answer) {
        return service.add(question, answer);
    }

    @GetMapping("/adds")
    public Question add(@RequestParam("question") Question question){
        return service.add(question);
    }

    @GetMapping("/remove")
    public Question remove(@RequestParam("question") String question, @RequestParam("answer") String answer) {
        Question removeQuestion = new Question(question, answer);
        return service.remove(removeQuestion);
    }

}
