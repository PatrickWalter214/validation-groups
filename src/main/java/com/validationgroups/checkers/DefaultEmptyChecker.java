package com.validationgroups.checkers;

import java.util.Objects;
import java.util.function.Predicate;

/**
 * General purpose implementation of an empty checker.
 */
public class DefaultEmptyChecker implements Predicate<Object> {

    /**
     * @param o - object to be checked.
     * @return true if the object is null.
     */
    @Override
    public boolean test(Object o) {
        return Objects.isNull(o);
    }
}
