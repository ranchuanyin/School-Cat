package com.schoolcat.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.schoolcat.entity.Animal;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface AnimalMapper extends BaseMapper<Animal> {

    @Select("SELECT * from school_animal")
    List<Animal> findAll();

    @Override
    @Insert("INSERT INTO school_animal(animalName, `animalCharacter`,place,relationship) " +
            "VALUES(#{animalName},#{animalCharacter},#{place},#{relationship});")
    int insert(Animal animal);


    int update(Animal animal);
}
