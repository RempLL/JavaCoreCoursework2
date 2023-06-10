package com.example.javacorecoursework2.Service;

import com.example.javacorecoursework2.Exception.BadRequestException;
import com.example.javacorecoursework2.Exception.RemoveException;
import com.example.javacorecoursework2.Model.Question;
import com.example.javacorecoursework2.Repository.JavaQuestionRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Set;
@ExtendWith(MockitoExtension.class)
public class JavaQuestionServiceTest {
    @Mock
    private JavaQuestionRepository javaQuestionRepository;
    @InjectMocks
    private JavaQuestionService questionService;

    @Test
    public void addTest() {
        Question questionNegative = new Question("Иван", "Иван");
        Question expected = new Question("Иван", "Иванов");

        Mockito.when(javaQuestionRepository.add(new Question("Иван", "Иванов"))).thenReturn(expected);
        Assertions.assertThat(questionService.add("Иван", "Иванов")).isEqualTo(expected);

        Mockito.when(javaQuestionRepository.add(questionNegative)).thenThrow(new BadRequestException());
        Assertions.assertThatExceptionOfType(BadRequestException.class).isThrownBy(() -> questionService.add(questionNegative));
    }
    @Test
    public void add2Test() {
        Question questionNegative = new Question("Иван", "Иван");
        Question expected = new Question("Иван", "Иванов");

        Mockito.when(javaQuestionRepository.add(new Question("Иван", "Иванов"))).thenReturn(expected);
        Assertions.assertThat(questionService.add(new Question("Иван", "Иванов"))).isEqualTo(expected);

        Mockito.when(javaQuestionRepository.add(questionNegative)).thenThrow(new BadRequestException());
        Assertions.assertThatExceptionOfType(BadRequestException.class).isThrownBy(() -> questionService.add(questionNegative));
    }
    @Test
    public void removeTest() {
        Question expected = new Question("Кто делает му", "Корова");
        questionService.add("Кто делает му", "Корова");
        Mockito.when(javaQuestionRepository.remove(expected)).thenReturn(expected);
        Assertions.assertThat(questionService.remove(new Question("Кто делает му", "Корова")))
                .isEqualTo(expected);
    }
    @Test
    public void removeNegativeTest() {
        Mockito.when(javaQuestionRepository.remove(new Question("Петя", "Петров"))).thenThrow(RemoveException.class);
        Assertions.assertThatExceptionOfType(RemoveException.class)
                .isThrownBy(() -> questionService.remove(new Question("Петя", "Петров")));
    }
    @Test
    public void getRandom() {
        Set<Question> expected = new HashSet<>(Set.of(
                new Question("Ваня","Темный"),
                new Question("Оля", "Мак")
        ));

        Mockito.when(javaQuestionRepository.getAll()).thenReturn(expected);
        Assertions.assertThat(questionService.getRandomQuestion())
                .isIn(questionService.getAll());
    }
}