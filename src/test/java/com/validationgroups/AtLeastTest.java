package com.validationgroups;

import com.validationgroups.beans.AtLeastAnnotatedBean;
import com.validationgroups.beans.AtLeastAnnotatedBeanWithMin2;
import com.validationgroups.beans.AtLeastAnnotatedBeanWithStringEmptyChecker;
import com.validationgroups.beans.AtLeastAnnotatedTwiceBean;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class AtLeastTest {

    private static Validator validator;

    @BeforeAll
    static void setupValidator() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void shouldSuccessfullyValidateAtLeastOne() {
        AtLeastAnnotatedBean atLeastAnnotatedBean = new AtLeastAnnotatedBean();
        atLeastAnnotatedBean.setFieldOne("value");

        Set<ConstraintViolation<AtLeastAnnotatedBean>> constraintViolations =
                validator.validate(atLeastAnnotatedBean);

        assertThat(constraintViolations).isEmpty();
    }

    @Test
    void shouldViolateValidationAtLeastOne() {
        AtLeastAnnotatedBean atLeastAnnotatedBean = new AtLeastAnnotatedBean();

        Set<ConstraintViolation<AtLeastAnnotatedBean>> constraintViolations =
                validator.validate(atLeastAnnotatedBean);

        assertThat(constraintViolations).hasSize(1);
        assertThat(constraintViolations.iterator().next().getMessage())
                .isEqualTo("Less than 1 field/s had a value in group GroupOne");
    }

    @Test
    void shouldSuccessfullyValidateAtLeastTwo() {
        AtLeastAnnotatedBeanWithMin2 atLeastAnnotatedBean = new AtLeastAnnotatedBeanWithMin2();
        atLeastAnnotatedBean.setFieldOne("value");
        atLeastAnnotatedBean.setFieldTwo("value");

        Set<ConstraintViolation<AtLeastAnnotatedBeanWithMin2>> constraintViolations =
                validator.validate(atLeastAnnotatedBean);

        assertThat(constraintViolations).isEmpty();
    }

    @Test
    void shouldViolateValidationAtLeastTwo() {
        AtLeastAnnotatedBeanWithMin2 atLeastAnnotatedBean = new AtLeastAnnotatedBeanWithMin2();
        atLeastAnnotatedBean.setFieldOne("value");

        Set<ConstraintViolation<AtLeastAnnotatedBeanWithMin2>> constraintViolations =
                validator.validate(atLeastAnnotatedBean);

        assertThat(constraintViolations).hasSize(1);
        assertThat(constraintViolations.iterator().next().getMessage())
                .isEqualTo("Less than 2 field/s had a value in group GroupOne");
    }

    @Test
    void shouldSuccessfullyValidateAtLeastOneWithStringEmptyChecker() {
        AtLeastAnnotatedBeanWithStringEmptyChecker atLeastAnnotatedBean =
                new AtLeastAnnotatedBeanWithStringEmptyChecker();
        atLeastAnnotatedBean.setFieldOne("value");

        Set<ConstraintViolation<AtLeastAnnotatedBeanWithStringEmptyChecker>> constraintViolations =
                validator.validate(atLeastAnnotatedBean);

        assertThat(constraintViolations).isEmpty();
    }

    @Test
    void shouldSuccessfullyValidateAtLeastOneWithDefaultEmptyChecker() {
        AtLeastAnnotatedBean atLeastAnnotatedBean = new AtLeastAnnotatedBean();
        atLeastAnnotatedBean.setFieldOne("");

        Set<ConstraintViolation<AtLeastAnnotatedBean>> constraintViolations =
                validator.validate(atLeastAnnotatedBean);

        assertThat(constraintViolations).isEmpty();
    }

    @Test
    void shouldViolateValidationAtLeastOneWithStringEmptyChecker() {
        AtLeastAnnotatedBeanWithStringEmptyChecker atLeastAnnotatedBean =
                new AtLeastAnnotatedBeanWithStringEmptyChecker();
        atLeastAnnotatedBean.setFieldOne("");

        Set<ConstraintViolation<AtLeastAnnotatedBeanWithStringEmptyChecker>> constraintViolations =
                validator.validate(atLeastAnnotatedBean);

        assertThat(constraintViolations).hasSize(1);
        assertThat(constraintViolations.iterator().next().getMessage())
                .isEqualTo("Less than 1 field/s had a value in group GroupOne");
    }

    @Test
    void shouldSuccessfullyValidateAtLeastOneTwice() {
        AtLeastAnnotatedTwiceBean atLeastAnnotatedBean = new AtLeastAnnotatedTwiceBean();
        atLeastAnnotatedBean.setFieldTwo("value");

        Set<ConstraintViolation<AtLeastAnnotatedTwiceBean>> constraintViolations =
                validator.validate(atLeastAnnotatedBean);

        assertThat(constraintViolations).isEmpty();
    }

    @Test
    void shouldViolateValidationAtLeastOneTwiceWithBothFailing() {
        AtLeastAnnotatedTwiceBean atLeastAnnotatedBean = new AtLeastAnnotatedTwiceBean();

        Set<ConstraintViolation<AtLeastAnnotatedTwiceBean>> constraintViolations =
                validator.validate(atLeastAnnotatedBean);

        assertThat(constraintViolations).hasSize(2);
        assertThat(constraintViolations).map(ConstraintViolation::getMessage).containsExactlyInAnyOrder(
                "Less than 1 field/s had a value in group GroupOne",
                "Less than 1 field/s had a value in group GroupTwo");
    }

    @Test
    void shouldViolateValidationAtLeastOneTwiceWithOnlyOneFailing() {
        AtLeastAnnotatedTwiceBean atLeastAnnotatedBean = new AtLeastAnnotatedTwiceBean();
        atLeastAnnotatedBean.setFieldOne("value");

        Set<ConstraintViolation<AtLeastAnnotatedTwiceBean>> constraintViolations =
                validator.validate(atLeastAnnotatedBean);

        assertThat(constraintViolations).hasSize(1);
        assertThat(constraintViolations.iterator().next().getMessage())
                .isEqualTo("Less than 1 field/s had a value in group GroupTwo");
    }
}
