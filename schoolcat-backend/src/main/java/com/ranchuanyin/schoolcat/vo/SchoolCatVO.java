package com.ranchuanyin.schoolcat.vo;

import com.ranchuanyin.schoolcat.domain.SchoolAnimal;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class SchoolCatVO {
    private List<SchoolAnimal> schoolAnimals;
    private Long animalNumber;
}
