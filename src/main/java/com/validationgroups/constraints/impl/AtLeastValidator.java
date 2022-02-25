package com.validationgroups.constraints.impl;

import com.validationgroups.annotations.ValidationGroup;
import com.validationgroups.constraints.AtLeastConstraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Stream;

public class AtLeastValidator implements ConstraintValidator<AtLeastConstraint, Object> {

    private AtLeastConstraint atLeastConstraint;

    @Override
    public void initialize(AtLeastConstraint atLeastConstraint) {
        this.atLeastConstraint = atLeastConstraint;
    }

    @Override
    public boolean isValid(Object object, ConstraintValidatorContext context) {
        Class<?> clazz = object.getClass();
        Field[] fields = clazz.getDeclaredFields();

        Stream<Field> fieldsWithValidationGroup =
                Arrays.stream(fields)
                        .filter(field -> field.getDeclaredAnnotation(ValidationGroup.class) != null)
                        .filter(field -> atLeastConstraint.group()
                                .equals(field.getDeclaredAnnotation(ValidationGroup.class).group()));

        long nonEmptyFields =
                fieldsWithValidationGroup.map(field -> getFieldValue(object, field))
                        .filter(Objects::nonNull)
                        .count();

        return nonEmptyFields >= 1;
    }

    private Object getFieldValue(Object object, Field field) {
        field.setAccessible(true);
        try {
            return field.get(object);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
