package dev.akozel.cleaningtime.core.validation;

import dev.akozel.cleaningtime.core.validation.exception.ApplicationValidationException;
import lombok.Builder;
import lombok.Data;
import org.junit.Test;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Negative;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import static com.googlecode.catchexception.apis.BDDCatchException.caughtException;
import static com.googlecode.catchexception.apis.BDDCatchException.when;
import static dev.akozel.cleaningtime.core.validation.helper.ApplicationValidationExceptionAssert.assertThat;

public class RulesValidatorImplTest {

    private static final String TOO_SHORT_STRING_VALUE = "1";

    private RulesValidatorImpl sut = new RulesValidatorImpl();


    @Test
    public void should_fill_path_to_incorrect_field_when_object_is_invalid() {
        //given
        OneField tooShortString = new OneField();
        tooShortString.setSomeString(TOO_SHORT_STRING_VALUE);

        //when
        when(sut).validate(tooShortString);

        //then
        assertThat(caughtException())
                .isInstanceOf(ApplicationValidationException.class)
                .hasErrorsSize(1)
                .errorAtIndexHasFieldName(0, "someString");
    }

    @Test
    public void should_fill_full_path_to_incorrect_inner_object_when_object_is_invalid() {
        //given
        InnersObjectValidation nullObjectAtTheLowLevel = InnersObjectValidation.builder()
                .innersObject(InnersObjectValidation.builder()
                        .innersObject(InnersObjectValidation.builder()
                                .build())
                        .build())
                .build();

        //when
        when(sut).validate(nullObjectAtTheLowLevel);

        //then
        assertThat(caughtException())
                .isInstanceOf(ApplicationValidationException.class)
                .hasErrorsSize(1)
                .errorAtIndexHasFieldName(0, "innersObject.innersObject.innersObject");
    }

    @Test
    public void should_fill_incorrect_value_to_the_value_field_of_validation_error() {
        //given
        OneField tooShortString = new OneField();
        tooShortString.setSomeString(TOO_SHORT_STRING_VALUE);

        //when
        when(sut).validate(tooShortString);

        //then
        assertThat(caughtException())
                .isInstanceOf(ApplicationValidationException.class)
                .hasErrorsSize(1)
                .errorAtIndexHasFieldValue(0, TOO_SHORT_STRING_VALUE);
    }

    @Test
    public void should_fill_appropriate_message_when_object_is_invalid() {
        //given
        OneField tooShortString = new OneField();
        tooShortString.setSomeString(TOO_SHORT_STRING_VALUE);

        //when
        when(sut).validate(tooShortString);

        //then
        assertThat(caughtException())
                .isInstanceOf(ApplicationValidationException.class)
                .hasErrorsSize(1)
                .errorAtIndexHasMessage(0, "size must be between 10 and 20");
    }

    @Test
    public void should_not_throw_exception_when_object_is_correct() {
        //given
        OneFieldWithManyValidation outOfRange = new OneFieldWithManyValidation();
        outOfRange.setSomeLong(120L);

        //when
        when(sut).validate(outOfRange);

        //then
        assertThat(caughtException())
                .isInstanceOf(ApplicationValidationException.class)
                .hasErrorsSize(2)
                .errorAtIndexHasFieldValue(0, 120L)
                .errorAtIndexHasFieldValue(1, 120L);
    }

    @Test
    public void should_raise_exception_with_given_parameters() {
        //given
        String message = "any message";
        String fieldName = "any_field_name";
        String value = "any_value";

        //when
        when(sut).raiseError(message, fieldName, value);

        //then
        assertThat(caughtException())
                .isInstanceOf(ApplicationValidationException.class)
                .hasErrorsSize(1)
                .errorAtIndexHasMessage(0, message)
                .errorAtIndexHasFieldName(0, fieldName)
                .errorAtIndexHasFieldValue(0, value);
    }


    @Data
    private static class OneField {

        @Size(min = 10, max = 20)
        private String someString;

    }

    @Data
    private static class OneFieldWithManyValidation {

        @Max(100)
        @Negative
        @NotNull
        private Long someLong;

    }

    @Builder
    @Data
    private static class InnersObjectValidation {

        @NotNull
        @Valid
        private InnersObjectValidation innersObject;

    }


}