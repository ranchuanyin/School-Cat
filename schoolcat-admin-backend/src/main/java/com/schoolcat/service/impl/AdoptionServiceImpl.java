package com.schoolcat.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.schoolcat.entity.AdoptionLogs;
import com.schoolcat.entity.Adoptions;
import com.schoolcat.entity.Animal;
import com.schoolcat.entity.User;
import com.schoolcat.mapper.AdoptionMapper;
import com.schoolcat.resp.CommonResp;
import com.schoolcat.service.AdoptionService;
import com.schoolcat.service.AnimalService;
import com.schoolcat.service.UserService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdoptionServiceImpl extends ServiceImpl<AdoptionMapper, Adoptions> implements AdoptionService {


    @Autowired
    @Lazy
    private UserService userService;

    @Autowired
    @Lazy
    private AnimalService animalService;



    /**
     * 领养人取消领养
     * @param adoId
     * @param session
     * @return
     */
    @Override
    public CommonResp<String> cancelAdoption(Integer adoId, HttpSession session) {
        //领养记录
        Adoptions adoption = this.getById(adoId);
        //领养人
        User user = userService.getById(adoption.getUserId());
        //被领宠物
        Animal animal = animalService.getById(adoption.getAnimalId());

        //修改操作, 取消领养
        adoption.setAdoStatus(0);

        user.setUserStatus(0);

        animal.setAnimalStatus(0);

        //持久化
        this.updateById(adoption);
        animalService.updateById(animal);
        userService.updateById(user);

        return CommonResp.success("取消成功");
    }


    /**
     * 修改领养状态：领养成功、失败
     * @param adoId
     * @param flag
     * @param session
     * @return
     */
    @Override
    public CommonResp<String> updateAdoptionStatus(Integer adoId, Integer flag, HttpSession session) {
        //获取领养记录
        Adoptions adoptions = this.getById(adoId);

        //领养人
        User user = userService.getById(adoptions.getUserId());
        //宠物
        Animal pet = animalService.getById(adoptions.getAnimalId());

        if (flag == 1) {
            //领养成功
            pet.setAnimalStatus(1);
            if (user.getUserType() != 1) {
                //不是管理员，则设置为领养人
                user.setUserType(2);
            }
        } else {
            //领养失败
            pet.setAnimalStatus(0);
        }

        //领养人状态恢复正常
        user.setUserStatus(0);
        //更新数据
        userService.updateById(user);
        animalService.updateById(pet);

        return CommonResp.success("操作成功");
    }



    /**
     * 获取领养记录列表，普通用户只能获取到自己的
     *
     * @param page
     * @param pageSize
     * @param animalName
     * @param userRealname
     * @param adoptionStatus
     * @param session
     * @return
     */
    @Override
    public CommonResp<Page> getList(Integer page, Integer pageSize, String animalName, String userRealname, Integer adoptionStatus, HttpSession session) {
        //通过宠物昵称，拿到符合条件的宠物id
        Long[] petIds = getAnimalIds(animalName);
        //通过领养人姓名，拿到符合条件的领养人id
        Long[] userIds = getUserIds(userRealname);

        //查询条件对象
        LambdaQueryWrapper<Adoptions> queryWrapper = getQueryWrapper(petIds, userIds,adoptionStatus, (User) session.getAttribute("user"));

        //进行查询数据
        Page<Adoptions> adoptionsPage = new Page<>(page,pageSize);
        this.page(adoptionsPage,queryWrapper);
        //分页数据对象
        Page<AdoptionLogs> pageInfo = new Page<>(page, pageSize);

        //不拷贝数据对象
        BeanUtils.copyProperties(adoptionsPage,pageInfo,"records");

        //获取到record数据
        List<Adoptions> adoptions = adoptionsPage.getRecords();

        //数据装配
        List<AdoptionLogs> adoptionLogsDTOS = new ArrayList<>();

        //设置私有属性：具体的宠物信息，领养人信息
        for (int i = 0; i < adoptions.size(); i++) {
            AdoptionLogs adoptionLogs = new AdoptionLogs();
            //复制信息
            BeanUtils.copyProperties(adoptions.get(i), adoptionLogs);

            //获取宠物数据
            Animal pets = animalService.getById(adoptions.get(i).getAnimalId());
            //获取领养人数据
            User users = userService.getById(adoptions.get(i).getUserId());

            //装入adoptionLogs中
            adoptionLogs.setAnimal(pets);
            adoptionLogs.setUser(users);

            adoptionLogsDTOS.add(adoptionLogs);
        }

        pageInfo.setRecords(adoptionLogsDTOS);

        //进行响应数据
        return CommonResp.success(pageInfo);
    }

    private LambdaQueryWrapper<Adoptions> getQueryWrapper(Long[] animalIds, Long[] userIds, Integer adoptionStatus, User user) {
        LambdaQueryWrapper<Adoptions> queryWrapper = new LambdaQueryWrapper<>();
        //查询条件
        queryWrapper.in(animalIds != null && animalIds.length > 0, Adoptions::getAnimalId, animalIds);
        queryWrapper.eq(adoptionStatus!=null,Adoptions::getAdoStatus,adoptionStatus);
        //排序条件
        queryWrapper.orderByDesc(Adoptions::getAdoId);

        //操作者是普通用户，只能查看自己的领养记录
        if (user.getUserType() == 0 || user.getUserType()==2) {
            queryWrapper.eq(Adoptions::getUserId, user.getUserId());
        }else {
            //是管理员
            queryWrapper.in(userIds != null && userIds.length > 0, Adoptions::getUserId, userIds);
        }

        return queryWrapper;
    }

    private Long[] getUserIds(String userRealname) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like((userRealname != null) && !(userRealname.isEmpty()), User::getUserRealname, userRealname);


        List<User> users = userService.list(queryWrapper);

        Long[] ids = new Long[users.size()];

        return ids;
    }

    private Long[] getAnimalIds(String animalName) {
        LambdaQueryWrapper<Animal> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(animalName != null && !(animalName.isEmpty()), Animal::getAnimalName, animalName);
        List<Animal> pets = animalService.list(queryWrapper);

        Long[] ids = new Long[pets.size()];

        return ids;
    }
}
