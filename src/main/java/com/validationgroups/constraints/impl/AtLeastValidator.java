package com.validationgroups.constraints.impl;

import com.validationgroups.annotations.ValidationGroup;
import com.validationgroups.constraints.AtLeastConstraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.lang.reflect.Field;
import java.util.AbstractMap;
import java.util.Arrays;
import java.util.Map;
import java.util.function.Predicate;
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
                        .filter(field -> field.getDeclaredAnnotation(ValidationGroup.class) != null ||
                                field.getDeclaredAnnotation(ValidationGroup.List.class) != null)
                        .flatMap(this::mapFieldToFieldGroupPairs)
                        .filter(fieldEntry -> atLeastConstraint.group().equals(fieldEntry.getValue()))
                        .map(Map.Entry::getKey);

        long nonEmptyFields =
                fieldsWithValidationGroup.map(field -> getFieldValue(object, field))
                        .filter(instantiateEmptyChecker().negate())
                        .count();

        return nonEmptyFields >= atLeastConstraint.min();
    }

    private Stream<Map.Entry<Field, String>> mapFieldToFieldGroupPairs(Field field) {
        if(field.getDeclaredAnnotation(ValidationGroup.List.class) != null) {
            return Arrays.stream(field.getDeclaredAnnotation(ValidationGroup.List.class).value())
                    .map(validationGroup -> new AbstractMap.SimpleEntry<>(field,
                            validationGroup.group()));
        }
        else {
            return Stream.of(new AbstractMap.SimpleEntry<>(field,
                    field.getDeclaredAnnotation(ValidationGroup.class).group()));
        }
    }

    private Object getFieldValue(Object object, Field field) {
        field.setAccessible(true);
        try {
            return field.get(object);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    private Predicate<Object> instantiateEmptyChecker() {
        try {
            return atLeastConstraint.emptyChecker().newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
