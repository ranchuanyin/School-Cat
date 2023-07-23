package com.schoolcat.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.schoolcat.entity.Animal;
import com.schoolcat.mapper.AnimalMapper;
import com.schoolcat.service.AnimalService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class AnimalServiceImpl extends ServiceImpl<AnimalMapper, Animal> implements AnimalService {
    @Resource
    AnimalMapper animalMapper;

    @Override
    public List<Animal> findAll() {
        return animalMapper.findAll();
    }

    //MybatisPlus方法
    @Override
    public int addAnimals(Animal animal) {
        return animalMapper.insert(animal);
    }

    //MybatisPlus方法
    @Override
    public int updateAnimalInformation(Animal animal) {
        return animalMapper.updateById(animal);
    }
}
