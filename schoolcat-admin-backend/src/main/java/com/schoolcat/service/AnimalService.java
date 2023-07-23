package com.schoolcat.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.schoolcat.entity.Animal;

import java.util.List;

public interface AnimalService extends IService<Animal> {
    List<Animal> findAll();


    int addAnimals(Animal animal);


    int updateAnimalInformation(Animal animal);
}
