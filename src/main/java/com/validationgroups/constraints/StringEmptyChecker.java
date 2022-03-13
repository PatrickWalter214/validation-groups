package com.validationgroups.constraints;

import java.util.function.Predicate;

public class StringEmptyChecker implements Predicate<Object> {

    @Override
    public boolean test(Object s) {
        if(s instanceof String) {
            return ((String) s).isEmpty();
        }
        return true;
    }
}