package com.ranchuanyin.schoolcat.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ranchuanyin.schoolcat.domain.SchoolAnimal;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author 冉船银
 * @description 针对表【school_animal】的数据库操作Mapper
 * @createDate 2023-08-24 23:03:27
 * @Entity com.ranchuanyin.schoolcat.domain.SchoolAnimal
 */
@Mapper
public interface SchoolAnimalMapper extends BaseMapper<SchoolAnimal> {

}




