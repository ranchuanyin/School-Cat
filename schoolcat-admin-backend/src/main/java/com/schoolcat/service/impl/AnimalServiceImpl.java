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
    public int saveAnimals(Animal animal) {
        if (animal.getId() == null) {
            return animalMapper.insert(animal);
        } else {
            return animalMapper.update(animal);
        }
    }

    @Override
    public List<Animal> selectPage(Integer pageNum, Integer pageSize) {
        return animalMapper.selectPage(pageNum, pageSize);
    }

    @Override
    public Integer selectCount() {
        return animalMapper.selectCount();
    }

}
