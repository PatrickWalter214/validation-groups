package com.validationgroups.constraints;

import com.validationgroups.checkers.DefaultEmptyChecker;
import com.validationgroups.constraints.impl.AtLeastValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;
import java.util.function.Predicate;

@Constraint(validatedBy = AtLeastValidator.class)
@Target( { ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(AtLeastConstraint.List.class)
public @interface AtLeastConstraint {
    String group();
    int min() default 1;
    Class<? extends Predicate<Object>> emptyChecker() default DefaultEmptyChecker.class;
    String message() default "The number of fields that must at least be set was not reached";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    @Target( { ElementType.TYPE })
    @Retention(RetentionPolicy.RUNTIME)
    @interface List {
        AtLeastConstraint[] value();
    }
}
