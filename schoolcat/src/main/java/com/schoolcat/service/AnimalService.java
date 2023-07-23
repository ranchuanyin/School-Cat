package com.schoolcat.service;

import com.schoolcat.entity.Animal;
import com.schoolcat.mapper.AnimalMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnimalService {
    @Autowired
    private AnimalMapper animalMapper;

    public int save(Animal animal){
        if (animal.getId() == null){
            return animalMapper.insert(animal);
        }else {
            return animalMapper.update(animal);
        }
    }
}
