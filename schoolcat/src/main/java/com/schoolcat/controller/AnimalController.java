package com.schoolcat.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.schoolcat.entity.Animal;
import com.schoolcat.mapper.AnimalMapper;
import com.schoolcat.service.AnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/animal")
public class AnimalController {

    @Autowired
    private AnimalMapper animalMapper;

    @Autowired
    private AnimalService animalService;

    //新增
    @PostMapping
    public int save(@RequestBody Animal animal){
        //新增或者更新
        return animalService.save(animal);
    }

    @GetMapping
    public List<Animal> index(){
        return animalMapper.findAll();
    }


}
