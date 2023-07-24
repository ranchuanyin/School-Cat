package com.schoolcat.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.schoolcat.entity.Animal;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AnimalMapper extends BaseMapper<Animal> {

    List<Animal> findAll();

    @Override
    int insert(Animal animal);

    int update(Animal animal);

    Integer deleteById(@Param("id") Integer id);

    List<Animal> selectPage(Integer pageNum, Integer pageSize);

    Integer selectCount();
}
