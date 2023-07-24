package com.schoolcat.controller;

import com.schoolcat.entity.Animal;
import com.schoolcat.mapper.AnimalMapper;
import com.schoolcat.service.AnimalService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    //分页查询
    //接口路径：/animal/page
    //@RequestParam 接受 ?pageNum=1&pageSize=10
    //limit第一个参数 = (pageNum - 1) * pageSize

    @GetMapping("/page")
    public Map<String, Object> findPage(@RequestParam Integer pageNum, @RequestParam Integer pageSize) {
        pageNum = (pageNum - 1) * pageSize;
        List<Animal> data = animalService.selectPage(pageNum, pageSize);
        Integer count = animalService.selectCount();
        Map<String, Object> res = new HashMap<>();
        res.put("data", data);
        res.put("total", count);
        return res;
    }
}
