package com.project.hospitalManagement.dto;

import com.project.hospitalManagement.type.BloodGroupType;
import lombok.Data;

@Data
public class BloodGroupStats {

    private final BloodGroupType bloodGroup;
    private final Long count;

}
