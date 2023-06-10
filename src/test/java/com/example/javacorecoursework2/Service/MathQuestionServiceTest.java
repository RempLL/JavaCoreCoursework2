package com.example.javacorecoursework2.Service;

import com.example.javacorecoursework2.Exception.BadRequestException;
import com.example.javacorecoursework2.Exception.RemoveException;
import com.example.javacorecoursework2.Model.Question;
import com.example.javacorecoursework2.Repository.MathQuestionRepository;
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
public class MathQuestionServiceTest {
    @Mock
    private MathQuestionRepository mathQuestionRepository;
    @InjectMocks
    private MathQuestionService questionService;

    @Test
    public void addTest() {
        Question questionNegative = new Question("7", "8");
        Question expected = new Question("7", "8");

        Mockito.when(mathQuestionRepository.add(new Question("7", "8"))).thenReturn(expected);
        Assertions.assertThat(questionService.add("7", "8")).isEqualTo(expected);

        Mockito.when(mathQuestionRepository.add(questionNegative)).thenThrow(new BadRequestException());
        Assertions.assertThatExceptionOfType(BadRequestException.class).isThrownBy(() -> questionService.add(questionNegative));
    }

    @Test
    public void add2Test() {
        Question questionNegative = new Question("9", "10");
        Question expected = new Question("9", "10");

        Mockito.when(mathQuestionRepository.add(new Question("7", "10"))).thenReturn(expected);
        Assertions.assertThat(questionService.add(new Question("7", "10"))).isEqualTo(expected);

        Mockito.when(mathQuestionRepository.add(questionNegative)).thenThrow(new BadRequestException());
        Assertions.assertThatExceptionOfType(BadRequestException.class).isThrownBy(() -> questionService.add(questionNegative));
    }

    @Test
    public void removeTest() {
        Question expected = new Question("2", "3");
        questionService.add("2", "3");
        Mockito.when(mathQuestionRepository.remove(expected)).thenReturn(expected);
        Assertions.assertThat(questionService.remove(new Question("2", "3")))
                .isEqualTo(expected);
    }

    @Test
    public void removeNegativeTest() {
        Mockito.when(mathQuestionRepository.remove(new Question("1", "7"))).thenThrow(RemoveException.class);
        Assertions.assertThatExceptionOfType(RemoveException.class)
                .isThrownBy(() -> questionService.remove(new Question("1", "7")));
    }

    @Test
    public void getRandom() {
        Set<Question> expected = new HashSet<>(Set.of(
                new Question("7", "8"),
                new Question("9", "10")
        ));

        Mockito.when(mathQuestionRepository.getAll()).thenReturn(expected);
        Assertions.assertThat(questionService.getRandomQuestion())
                .isIn(questionService.getAll());
    }
}