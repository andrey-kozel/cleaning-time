package dev.akozel.cleaningtime.core.community.domain;

import com.googlecode.catchexception.apis.BDDCatchException;
import dev.akozel.cleaningtime.core.validation.RulesValidator;
import dev.akozel.cleaningtime.core.validation.RulesValidatorImpl;
import dev.akozel.cleaningtime.core.validation.exception.ApplicationValidationException;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(JUnitParamsRunner.class)
public class CommunityTest {

    RulesValidator rulesValidator = new RulesValidatorImpl();

    public Object[] invalidCommunityNames() {
        return new Object[]{null, "", "1", "22"};
    }

    @Test
    @Parameters(method = "invalidCommunityNames")
    public void should_throw_an_exception_when_parameter_invalid(String invalidName) {
        Community invalidCommunity = Community.builder()
                .name(invalidName)
                .build();
        //when
        BDDCatchException.when(rulesValidator)
                .validate(invalidCommunity);

        //then
        assertThat(BDDCatchException.caughtException())
                .isInstanceOf(ApplicationValidationException.class)
                .hasNoCause();
    }

    public Object[] validCommunityNames() {
        return new Object[]{"short", "longlonglonglonglonglonglonglonglonglonglong name", "1!@#$&^%$&*%$^%@"};
    }

    @Test
    @Parameters(method = "validCommunityNames")
    public void should_not_throw_an_exception_for_valid_values(String validName) {
        Community invalidCommunity = Community.builder()
                .name(validName)
                .build();

        //when
        rulesValidator.validate(invalidCommunity);

        //then no errors
    }

}