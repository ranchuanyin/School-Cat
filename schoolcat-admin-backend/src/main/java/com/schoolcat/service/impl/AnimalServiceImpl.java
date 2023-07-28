package com.schoolcat.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.schoolcat.entity.Adoptions;
import com.schoolcat.entity.Animal;
import com.schoolcat.entity.User;
import com.schoolcat.mapper.AnimalMapper;
import com.schoolcat.req.AnimalQueryListReq;
import com.schoolcat.resp.CommonResp;
import com.schoolcat.service.AdoptionService;
import com.schoolcat.service.AnimalService;
import com.schoolcat.service.UserService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class AnimalServiceImpl extends ServiceImpl<AnimalMapper, Animal> implements AnimalService {
    @Autowired
    @Lazy
    AnimalMapper animalMapper;

    @Autowired
    @Lazy
    UserService userService;

    @Autowired
    @Lazy
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
    @Cacheable(value = "pet",key = "#page+'_'+#pageSize+'_'+#userType+'_'+#animal.animalName+'_'+#animal.animalStatus")
    @Override
    public CommonResp<Page> getList(Integer page, Integer pageSize, Animal animal, Integer userType) {
        //查询条件封装
        AnimalQueryListReq petsQueryList = new AnimalQueryListReq(page, pageSize, animal, userType);
        //从数据库中获取数据
        Page pageInfo = this.getPetsByDB(petsQueryList);
        return CommonResp.success(pageInfo);
    }

    private Page getPetsByDB(AnimalQueryListReq petsQueryList) {
        //取出查询条件
        Integer page = petsQueryList.getPage();
        Integer pageSize = petsQueryList.getPageSize();
        Animal animal = petsQueryList.getAnimal();

        //构建 查询条件对象
        LambdaQueryWrapper<Animal> queryWrapper = new LambdaQueryWrapper<>();
        //查询条件：宠物昵称、宠物状态，可能为空
        queryWrapper.like(StringUtils.isNotBlank(animal.getAnimalName()), Animal::getAnimalName, animal.getAnimalName());
        queryWrapper.eq(animal.getAnimalStatus() != null, Animal::getAnimalStatus, animal.getAnimalStatus());
        queryWrapper.orderByDesc(Animal::getAnimalId);

        //准备 页面数据对象
        Page<Animal> pageInfo = new Page<>(page, pageSize);
        //查询数据 从数据库中
        this.page(pageInfo, queryWrapper);

        //进行响应数据
        return pageInfo;
    }

    @Override
    public CommonResp<String> adoptionUp(Adoptions adoptions, HttpSession session) {
        //获取宠物id
        Integer petId = adoptions.getAnimalId();

        //获取操作者信息 从session中
        User users = (User) session.getAttribute("user");

        //修改用户类型为申领中
        users.setUserStatus(1);
        userService.updateById(users);

        //获取宠物信息
        Animal animal = this.getById(petId);
        //设置宠物类型为被申请领养中
        animal.setAnimalStatus(2);
        this.updateById(animal);

        //设置宠物类型为被申请领养
        animal.setAnimalStatus(2);
        this.updateById(animal);

        //领养记录表新增一条记录
        Adoptions adoption = new Adoptions();
        adoption.setUserId(users.getUserId());
        adoption.setAnimalId(petId);
        adoptionService.save(adoption);

        return CommonResp.success("申请成功！");



    }


}






