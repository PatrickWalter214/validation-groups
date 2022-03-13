package com.validationgroups.constraints;

import java.util.Objects;
import java.util.function.Predicate;

public class DefaultEmptyChecker implements Predicate<Object> {

    @Override
    public boolean test(Object o) {
        return Objects.isNull(o);
    }
}
