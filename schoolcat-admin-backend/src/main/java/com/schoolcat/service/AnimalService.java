package com.schoolcat.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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


    //获取宠物仓库列表信息
    CommonResp<Page> getList(Integer page, Integer pageSize, Animal animal, Integer userType);

    /**
     * 申领宠物操作
     * @param adoptions
     * @param session
     * @return
     */
    CommonResp<String> adoptionUp(Adoptions adoptions, HttpSession session);
}
