package com.schoolcat.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.schoolcat.entity.Adoptions;
import com.schoolcat.entity.Animal;
import com.schoolcat.entity.User;
import com.schoolcat.mapper.AnimalMapper;
import com.schoolcat.req.AnimalReq;
import com.schoolcat.resp.CommonResp;
import com.schoolcat.resp.PageResp;
import com.schoolcat.service.AdoptionService;
import com.schoolcat.service.AnimalService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class AnimalServiceImpl extends ServiceImpl<AnimalMapper, Animal> implements AnimalService {
    @Resource
    AnimalMapper animalMapper;

    @Resource
    AnimalService animalService;

    @Resource
    AdoptionService adoptionService;

    @Override
    public List<Animal> findAll() {
        return animalMapper.findAll();
    }

    //MybatisPlus方法
    @Override
    public int saveAnimals(Animal animal) {
        if (animal.getAnimalId() == null) {
            return animalMapper.insert(animal);
        } else {
            return animalMapper.updateById(animal);
        }
    }

    //查询分页
    @Override
    public PageResp<Animal> getList(AnimalReq animalReq) {
        QueryWrapper<Animal> queryWrapper = new QueryWrapper<>();
        if(!ObjectUtils.isEmpty(animalReq.getAnimalName())){
            queryWrapper.lambda().eq(Animal::getAnimalName,animalReq.getAnimalName());
        }
        if(!ObjectUtils.isEmpty(animalReq.getPlace())){
            queryWrapper.lambda().eq(Animal::getPlace,animalReq.getPlace());
        }

        Page<Animal> page = new Page<>(animalReq.getPage(), animalReq.getSize());
        IPage<Animal> animalIPage = animalMapper.selectPage(page,queryWrapper);
        PageResp<Animal> pageResp = new PageResp<>();
        pageResp.setTotal(animalIPage.getTotal());
        pageResp.setList(animalIPage.getRecords());

        return pageResp;
    }

    @Override
    public CommonResp<String> adoptionUp(Adoptions adoptions, HttpSession session) {
        //获取宠物id
        Integer petId = adoptions.getAnimalId();

        //获取宠物信息
        Animal pet = this.getById(petId);

        //获取操作者信息 从session中
        User users = (User) session.getAttribute("user");

        //设置宠物类型为被申请领养
        pet.setAnimalStatus(2);
        this.updateById(pet);

        //领养记录表新增一条记录
        Adoptions adoption = new Adoptions();
        adoption.setUserId(users.getUserId());
        adoption.setAnimalId(petId);
        adoptionService.save(adoption);

        return CommonResp.success("申请成功！");



    }


}






