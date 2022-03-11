package com.validationgroups.beans;

import com.validationgroups.annotations.ValidationGroup;
import com.validationgroups.constraints.AtLeastConstraint;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AtLeastConstraint(group = "GroupOne", min = 2)
public class AtLeastFieldAnnotatedBeanWithMin2 {

    @ValidationGroup(group = "GroupOne")
    private String fieldOne;

    @ValidationGroup(group = "GroupOne")
    private String fieldTwo;

}
