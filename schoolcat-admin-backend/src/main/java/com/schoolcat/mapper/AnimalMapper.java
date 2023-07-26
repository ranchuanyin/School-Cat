package com.schoolcat.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.schoolcat.entity.Animal;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface AnimalMapper extends BaseMapper<Animal> {

    @Select("SELECT * from school_animal")
    List<Animal> findAll();
}
