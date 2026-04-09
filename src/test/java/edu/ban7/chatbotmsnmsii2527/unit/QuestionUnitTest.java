package edu.ban7.chatbotmsnmsii2527.unit;

import edu.ban7.chatbotmsnmsii2527.model.Question;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Set;

public class QuestionUnitTest {

    static Validator validator;

    @BeforeAll
    public static void init()
    {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Test
    public  void questionWithBlankContent_shouldNotBeValid()
    {
        Question question = new Question();
        question.setContent("");

        Set<ConstraintViolation<Question>> violations = validator.validate(question);

        boolean violationExist = violations.stream()
                .anyMatch(c -> c.getPropertyPath().toString().equals("content")
                        && c.getConstraintDescriptor().getAnnotation().annotationType().getSimpleName().equals("NotBlank"));

        Assertions.assertTrue(violationExist);
    }

    @Test
    public void newQuestion_createShouldBeSet() {
        Question question = new Question();
        Assertions.assertNotNull(question.getCreate());
    }

}
