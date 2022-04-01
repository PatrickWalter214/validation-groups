package com.validationgroups.constraints;

import com.validationgroups.checkers.DefaultEmptyChecker;
import com.validationgroups.constraints.impl.AtLeastValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;
import java.util.function.Predicate;

/**
 * Checks that at least a minimum of fields in a specific validation group have a value.
 */
@Constraint(validatedBy = AtLeastValidator.class)
@Target( { ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(AtLeastConstraint.List.class)
public @interface AtLeastConstraint {

    /**
     * Validation group to be checked.
     */
    String group();

    /**
     * Minimum number of fields that must have a value. Default is 1.
     */
    int min() default 1;

    /**
     * The class used to check if the field is empty. Default is {@link DefaultEmptyChecker}.
     */
    Class<? extends Predicate<Object>> emptyChecker() default DefaultEmptyChecker.class;

    String message() default "Less than {min} field/s had a value in group {group}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    @Target( { ElementType.TYPE })
    @Retention(RetentionPolicy.RUNTIME)
    @interface List {
        AtLeastConstraint[] value();
    }
}
