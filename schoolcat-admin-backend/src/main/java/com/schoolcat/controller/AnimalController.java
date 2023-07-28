package com.schoolcat.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.schoolcat.entity.Animal;
import com.schoolcat.entity.AdoptionLogs;
import com.schoolcat.entity.User;
import com.schoolcat.mapper.AnimalMapper;
import com.schoolcat.resp.CommonResp;
import com.schoolcat.service.AnimalService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@RestController
@RequestMapping("/animal")
public class AnimalController {

    @Autowired
    private AnimalMapper animalMapper;

    @Autowired
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

    /**
     * 分页查询数据
     * @param page     查询页码
     * @param pageSize 页码大小
     * @param animal     数据，包含了查询条件，可能为空
     * @return
     */
    @GetMapping("/getList")
    public CommonResp<Page> page(Integer page, Integer pageSize, Animal animal, HttpSession session){
        //获取用户类型
        Integer userType = ((User) session.getAttribute("user")).getUserType()==1?1:0;
        //获取数据
        CommonResp<Page> resp = animalService.getList(page,pageSize,animal,userType);
        return resp;
    }


    /**
     * 申请领养
     * @param adoptionlogs
     * @param session
     * @return
     */
    @CacheEvict(value = {"adoption","pet"},allEntries = true)
    @PostMapping("/adoption")
    public CommonResp<String> adoption(@RequestBody AdoptionLogs adoptionlogs, HttpSession session) {
        CommonResp<String> resp = animalService.adoptionUp(adoptionlogs,session);
        return resp;
    }
}