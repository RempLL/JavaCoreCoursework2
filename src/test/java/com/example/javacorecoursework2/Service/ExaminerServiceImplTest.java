package com.example.javacorecoursework2.Service;

import com.example.javacorecoursework2.Exception.BadRequestException;
import com.example.javacorecoursework2.Model.Question;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.lenient;

@ExtendWith(MockitoExtension.class)
public class ExaminerServiceImplTest {
    @Mock
    private JavaQuestionService javaQuestionService;
    @Mock
    private MathQuestionService mathQuestionService;

    @InjectMocks
    private ExaminerServiceImpl examinerService;

    private final Set<Question> javaQuestions = new HashSet<>();
    private final Set<Question> mathQuestions = new HashSet<>();

    @BeforeEach
    public void beforeEach() {
        javaQuestions.clear();
        mathQuestions.clear();

        javaQuestions.addAll(
                Stream.of(
                        new Question("а", "б"),
                        new Question("в", "г"),
                        new Question("д", "е")
                ).collect(Collectors.toSet())
        );
        mathQuestions.addAll(
                Stream.of(
                        new Question("1", "2"),
                        new Question("3", "4")
                ).collect(Collectors.toSet())
        );
        when(javaQuestionService.getAll()).thenReturn(javaQuestions);
        when(mathQuestionService.getAll()).thenReturn(mathQuestions);
    }
    @Test
    public void getQuestionsNegativeTest() {
        assertThatExceptionOfType(BadRequestException.class)
                .isThrownBy(() -> examinerService.getQuestions(-1));

        assertThatExceptionOfType(BadRequestException.class)
                .isThrownBy(() -> examinerService.getQuestions(javaQuestions.size() + mathQuestions.size() + 1));
    }



    @ParameterizedTest
    @MethodSource("params")
    public void getQuestionsPositiveTest(int amount) {
        Random random = new Random();
        ReflectionTestUtils.setField(javaQuestionService, "random", random);
        ReflectionTestUtils.setField(mathQuestionService, "random", random);
        lenient().when(javaQuestionService.getRandomQuestion()).thenCallRealMethod();
        lenient().when(mathQuestionService.getRandomQuestion()).thenCallRealMethod();

        assertThat(examinerService.getQuestions(amount))
                .hasSize(amount)
                .containsAnyElementsOf(Stream.concat(javaQuestions.stream(), mathQuestions.stream()).collect(Collectors.toSet()));
    }

    public static Stream<Arguments> params() {
        return Stream.of(
                Arguments.of(1),
                Arguments.of(2),
                Arguments.of(3),
                Arguments.of(4),
                Arguments.of(5)
        );
    }
}
