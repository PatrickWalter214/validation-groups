package com.validationgroups.checkers;

import java.util.function.Predicate;

/**
 * Implementation of an empty checker focused on String objects.
 */
public class StringEmptyChecker implements Predicate<Object> {

    /**
     * @param s - object to be checked.
     * @return true if the object is not of type String or if it is an empty String, meaning it's length is 0.
     */
    @Override
    public boolean test(Object s) {
        if(s instanceof String) {
            return ((String) s).isEmpty();
        }
        return true;
    }
}