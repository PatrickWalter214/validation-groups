package com.validationgroups.beans;

import com.validationgroups.annotations.ValidationGroup;
import com.validationgroups.constraints.AtLeastConstraint;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AtLeastConstraint(group = "GroupOne")
@AtLeastConstraint(group = "GroupTwo")
public class AtLeastAnnotatedTwiceBean {

    @ValidationGroup(group = "GroupOne")
    private String fieldOne;

    @ValidationGroup(group = "GroupOne")
    @ValidationGroup(group = "GroupTwo")
    private String fieldTwo;

    @ValidationGroup(group = "GroupTwo")
    private String fieldThree;

}
