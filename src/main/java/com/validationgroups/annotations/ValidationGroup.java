package com.validationgroups.annotations;

import java.lang.annotation.*;

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
