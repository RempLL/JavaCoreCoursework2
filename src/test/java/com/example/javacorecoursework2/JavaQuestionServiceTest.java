package com.example.javacorecoursework2;

import com.example.javacorecoursework2.Exception.BadRequestException;
import com.example.javacorecoursework2.Exception.RemoveException;
import com.example.javacorecoursework2.Model.Question;
import com.example.javacorecoursework2.Service.JavaQuestionService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class JavaQuestionServiceTest {
    private final JavaQuestionService javaQuestionService = new JavaQuestionService();

    @BeforeEach
    public void beforeEach() {
        javaQuestionService.add("Кто делает му", "Корова");
        javaQuestionService.add("Кто делает гав", "Собака");
        javaQuestionService.add("Кто делает Мяу", "Кот");
    }

    @AfterEach
    public void afterEach() {
        javaQuestionService.getAll().forEach(javaQuestionService::remove);
    }

    @Test
    public void addTest() {
        Question expected = new Question("Иван", "Иванов");
        Assertions.assertThat(javaQuestionService.add("Иван", "Иванов")).isEqualTo(expected);
    }

    @Test
    public void addNegativeTest() {
        Assertions.assertThatExceptionOfType(BadRequestException.class)
                .isThrownBy(() -> javaQuestionService.add("Иван", "Иван"));
    }

    @Test
    public void removeTest() {
        Question expected = new Question("Кто делает му", "Корова");
        Assertions.assertThat(javaQuestionService.remove(new Question("Кто делает му", "Корова")))
                .isEqualTo(expected);
    }

    @Test
    public void removeNegativeTest() {
        Assertions.assertThatExceptionOfType(RemoveException.class)
                .isThrownBy(() -> javaQuestionService.remove(new Question("Иван", "Иванов")));
    }


    @Test
    public void addsTest() {
        Question expected = new Question("Кто делает му", "Корова");
        Assertions.assertThat(javaQuestionService.add(new Question("Кто делает му", "Корова")))
                .isEqualTo(expected);
    }
}
