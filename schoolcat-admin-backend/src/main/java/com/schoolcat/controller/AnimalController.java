package com.schoolcat.controller;

import com.schoolcat.entity.Animal;
import com.schoolcat.mapper.AnimalMapper;
import com.schoolcat.req.AnimalReq;
import com.schoolcat.resp.CommonResp;
import com.schoolcat.resp.PageResp;
import com.schoolcat.service.AnimalService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@RestController
@RequestMapping("/animal")
public class AnimalController {

    @Resource
    private AnimalMapper animalMapper;

    @Resource
    private AnimalService animalService;

    //查询所有数据
    @GetMapping
    public List<Animal> findAll() {
        return animalService.findAll();
    }


    //新增和修改
    @PostMapping("save")
    public int save(@RequestBody Animal animal) {
        return animalService.saveAnimals(animal);
    }

    //根据id删除数据
    @DeleteMapping("/{id}")
    public Integer delete(@PathVariable Integer id) {
        return animalMapper.deleteById(id);
    }

    @GetMapping("/getList")
    public CommonResp getList(AnimalReq animalReq){
        CommonResp<PageResp<Animal>> resp = new CommonResp<>();
        PageResp<Animal> list = animalService.getList(animalReq);
        resp.setContent(list);

        return resp;
    }

}