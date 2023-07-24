package com.schoolcat.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.schoolcat.entity.Animal;

import java.util.List;

public interface AnimalService extends IService<Animal> {

    //查找所有的动物信息
    List<Animal> findAll();

    //没有id则增加动物,有id则更新信息
    int saveAnimals(Animal animal);

    List<Animal> selectPage(Integer pageNum, Integer pageSize);

    Integer selectCount();
}
