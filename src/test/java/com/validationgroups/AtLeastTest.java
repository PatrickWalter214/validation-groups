package com.validationgroups;

import com.validationgroups.beans.AtLeastAnnotatedBeanWithStringEmptyChecker;
import com.validationgroups.beans.AtLeastFieldAnnotatedBean;
import com.validationgroups.beans.AtLeastFieldAnnotatedBeanWithMin2;
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
        AtLeastFieldAnnotatedBean atLeastFieldAnnotatedBean = new AtLeastFieldAnnotatedBean();
        atLeastFieldAnnotatedBean.setFieldOne("value");

        Set<ConstraintViolation<AtLeastFieldAnnotatedBean>> constraintViolations =
                validator.validate(atLeastFieldAnnotatedBean);

        assertThat(constraintViolations).isEmpty();
    }

    @Test
    void shouldViolateValidationAtLeastOne() {
        AtLeastFieldAnnotatedBean atLeastFieldAnnotatedBean = new AtLeastFieldAnnotatedBean();

        Set<ConstraintViolation<AtLeastFieldAnnotatedBean>> constraintViolations =
                validator.validate(atLeastFieldAnnotatedBean);

        assertThat(constraintViolations).hasSize(1);
        assertThat(constraintViolations.iterator().next().getMessage())
                .isEqualTo("The number of fields that must at least be set was not reached");
    }

    @Test
    void shouldSuccessfullyValidateAtLeastTwo() {
        AtLeastFieldAnnotatedBeanWithMin2 atLeastFieldAnnotatedBean = new AtLeastFieldAnnotatedBeanWithMin2();
        atLeastFieldAnnotatedBean.setFieldOne("value");
        atLeastFieldAnnotatedBean.setFieldTwo("value");

        Set<ConstraintViolation<AtLeastFieldAnnotatedBeanWithMin2>> constraintViolations =
                validator.validate(atLeastFieldAnnotatedBean);

        assertThat(constraintViolations).isEmpty();
    }

    @Test
    void shouldViolateValidationAtLeastTwo() {
        AtLeastFieldAnnotatedBeanWithMin2 atLeastFieldAnnotatedBean = new AtLeastFieldAnnotatedBeanWithMin2();
        atLeastFieldAnnotatedBean.setFieldOne("value");

        Set<ConstraintViolation<AtLeastFieldAnnotatedBeanWithMin2>> constraintViolations =
                validator.validate(atLeastFieldAnnotatedBean);

        assertThat(constraintViolations).hasSize(1);
        assertThat(constraintViolations.iterator().next().getMessage())
                .isEqualTo("The number of fields that must at least be set was not reached");
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
        AtLeastFieldAnnotatedBean atLeastAnnotatedBean = new AtLeastFieldAnnotatedBean();
        atLeastAnnotatedBean.setFieldOne("");

        Set<ConstraintViolation<AtLeastFieldAnnotatedBean>> constraintViolations =
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
                .isEqualTo("The number of fields that must at least be set was not reached");
    }
}
