package com.example.javacorecoursework2;

import com.example.javacorecoursework2.Exception.BadRequestException;
import com.example.javacorecoursework2.Model.Question;
import com.example.javacorecoursework2.Service.ExaminerServiceImpl;
import com.example.javacorecoursework2.Service.JavaQuestionService;
import com.example.javacorecoursework2.Service.QuestionService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ExaminerServiceImplTest {
    @Mock
    private QuestionService questionService;
    @InjectMocks
    private ExaminerServiceImpl examinerService;


    @Test
    public void getQuestionsTest() {
        when(questionService.getAll()).thenReturn(
                Set.of(
                        new Question("Кто делает Му", "Корова"),
                        new Question("Кто делает Мяу", "Кот"),
                        new Question("Кто делая гав", "Собака")
                )
        );

        when(questionService.getRandomQuestion()).thenReturn(
                new Question("Кто делает Мяу", "Кот"),
                new Question("Кто делает Гав", "Собака"));
        List<Question> expected = new ArrayList<>(List.of(
                new Question("Кто делает Мяу", "Кот"),
                new Question("Кто делает Гав", "Собака")
        ));
        Assertions.assertThat(examinerService.getQuestions(2)).isEqualTo(expected);
    }

    @Test
    public void getQuestionNegativeTest() {
        when(questionService.getAll()).thenReturn(Set.of(
                new Question("Кто делает Му", "Корова"),
                new Question("Кто делает Мяу", "Кот"),
                new Question("Кто делая гав", "Собака")
        ));

        //?????
        when(questionService.getAll().size()).thenReturn(4);
        Assertions.assertThatExceptionOfType(BadRequestException.class)
                .isThrownBy(() -> examinerService.getQuestions(4));
    }

}
