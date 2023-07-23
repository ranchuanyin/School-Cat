package com.schoolcat.controller;

import com.schoolcat.entity.Animal;
import com.schoolcat.mapper.AnimalMapper;
import com.schoolcat.service.AnimalService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/animal")
public class AnimalController {

    @Resource
    private AnimalMapper animalMapper;

    @Resource
    private AnimalService animalService;

    //新增
    @GetMapping
    public List<Animal> index(){
        return animalMapper.findAll();
    }


}
