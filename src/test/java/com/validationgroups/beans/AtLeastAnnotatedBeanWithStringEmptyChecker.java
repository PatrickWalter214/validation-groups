package com.validationgroups.beans;

import com.validationgroups.annotations.ValidationGroup;
import com.validationgroups.constraints.AtLeastConstraint;
import com.validationgroups.checkers.StringEmptyChecker;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AtLeastConstraint(group = "GroupOne", emptyChecker = StringEmptyChecker.class)
public class AtLeastAnnotatedBeanWithStringEmptyChecker {

    @ValidationGroup(group = "GroupOne")
    private String fieldOne;

    @ValidationGroup(group = "GroupOne")
    private String fieldTwo;

}
