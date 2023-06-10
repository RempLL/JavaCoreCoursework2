package com.example.javacorecoursework2.Repository;

import com.example.javacorecoursework2.Exception.BadRequestException;
import com.example.javacorecoursework2.Exception.RemoveException;
import com.example.javacorecoursework2.Model.Question;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class JavaQuestionRepositoryTest {
    private final QuestionRepository questionRepository = new JavaQuestionRepository();

    @BeforeEach
    public void beforeEach(){
        questionRepository.add(new Question("Ягода","Земляника"));
        questionRepository.add(new Question("Гриб","Белый"));
    }

    @Test
    public void addTest(){
        Question expected = new Question("Вода","Огонь");
        Assertions.assertThat(questionRepository.add(new Question("Вода","Огонь")))
                .isEqualTo(expected);
    }
    @Test
    public void addTestNegative(){
        Assertions.assertThatExceptionOfType(BadRequestException.class)
                .isThrownBy(() ->questionRepository.add(new Question("Огонь","Огонь")));
    }

    @Test
    public void removeTest(){
        int size = questionRepository.getAll().size();
        Question question = new Question("Ягода","Земляника");
        Assertions.assertThat(questionRepository.remove(question))
                .isEqualTo(question)
                .isNotIn(questionRepository.getAll());
        Assertions.assertThat(questionRepository.getAll()).hasSize(size-1);
    }

    @Test
    public void removeTestNegative(){
        Assertions.assertThatExceptionOfType(RemoveException.class)
                .isThrownBy(() -> questionRepository.remove(new Question("Лось","Кот")));
    }
}
