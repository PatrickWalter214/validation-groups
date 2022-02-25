package com.validationgroups.annotations;

import java.lang.annotation.*;

@Target( { ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidationGroup {
    String group();
}
