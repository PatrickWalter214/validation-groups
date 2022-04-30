package com.validationgroups.annotations;

import java.lang.annotation.*;

/**
 * Used to mark a field as part of a specific validation group.
 */
@Target( { ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(ValidationGroup.List.class)
public @interface ValidationGroup {
    String group();

    @Target( { ElementType.FIELD })
    @Retention(RetentionPolicy.RUNTIME)
    @interface List {
        ValidationGroup[] value();
    }
}
