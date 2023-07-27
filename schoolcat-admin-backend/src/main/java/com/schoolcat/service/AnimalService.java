package com.schoolcat.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.schoolcat.entity.Adoptions;
import com.schoolcat.entity.Animal;
import com.schoolcat.req.AnimalReq;
import com.schoolcat.resp.CommonResp;
import com.schoolcat.resp.PageResp;
import jakarta.servlet.http.HttpSession;

import java.util.List;

public interface AnimalService extends IService<Animal> {

    //查找所有的动物信息
    List<Animal> findAll();

    //没有id则增加动物,有id则更新信息
    int saveAnimals(Animal animal);


    PageResp<Animal> getList(AnimalReq animalReq);

    CommonResp<String> adoptionUp(Adoptions adoptions, HttpSession session);
}
