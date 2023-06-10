package com.example.javacorecoursework2.Repository;

import com.example.javacorecoursework2.Exception.BadRequestException;
import com.example.javacorecoursework2.Exception.RemoveException;
import com.example.javacorecoursework2.Model.Question;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MathQuestionRepositoryTest {

    private final QuestionRepository questionRepository = new MathQuestionRepository();

    @BeforeEach
    public void beforeEach(){
        questionRepository.add(new Question("2","3"));
        questionRepository.add(new Question("1","2"));
    }

    @Test
    public void addTest(){
        Question expected = new Question("5","4");
        Assertions.assertThat(questionRepository.add(new Question("5","4")))
                .isEqualTo(expected);
    }
    @Test
    public void addTestNegative(){
        Assertions.assertThatExceptionOfType(BadRequestException.class)
                .isThrownBy(() ->questionRepository.add(new Question("5","5")));
    }

    @Test
    public void removeTest(){
        int size = questionRepository.getAll().size();
        Question question = new Question("2","3");
        Assertions.assertThat(questionRepository.remove(question))
                .isEqualTo(question)
                .isNotIn(questionRepository.getAll());
        Assertions.assertThat(questionRepository.getAll()).hasSize(size-1);
    }

    @Test
    public void removeTestNegative(){
        Assertions.assertThatExceptionOfType(RemoveException.class)
                .isThrownBy(() -> questionRepository.remove(new Question("8","9")));
    }

}
