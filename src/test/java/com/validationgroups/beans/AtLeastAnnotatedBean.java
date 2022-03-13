package com.validationgroups.beans;

import com.validationgroups.annotations.ValidationGroup;
import com.validationgroups.constraints.AtLeastConstraint;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AtLeastConstraint(group = "GroupOne")
public class AtLeastAnnotatedBean {

    @ValidationGroup(group = "GroupOne")
    private String fieldOne;

    @ValidationGroup(group = "GroupOne")
    private String fieldTwo;

}
