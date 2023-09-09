package com.ranchuanyin.schoolcat.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ranchuanyin.schoolcat.domain.SchoolAnimal;

import java.util.List;

/**
 * @author 冉船银
 * @description 针对表【school_animal】的数据库操作Service
 * @createDate 2023-08-24 23:03:27
 */
public interface SchoolAnimalService extends IService<SchoolAnimal> {
    public List<SchoolAnimal> paginationQuery(int page);

    public Long getNums();
}
