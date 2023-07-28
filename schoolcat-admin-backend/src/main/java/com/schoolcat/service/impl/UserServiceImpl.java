package com.schoolcat.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.schoolcat.entity.Adoptions;
import com.schoolcat.entity.Animal;
import com.schoolcat.entity.User;
import com.schoolcat.mapper.UserMapper;
import com.schoolcat.resp.CommonResp;
import com.schoolcat.service.AdoptionService;
import com.schoolcat.service.AnimalService;
import com.schoolcat.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    @Lazy
    private AdoptionService adoptionService;

    @Autowired
    @Lazy
    private AnimalService animalService;


    @Override
    public CommonResp<String> updateUserField(Long id, Integer code) {
        //1 获取要修改的用户信息 从数据库中
        User users = this.getById(id);
        //2 判断  用户存在
        if (users == null) {
            return CommonResp.error("操作错误，请重试！");
        }

        //3 判断 修改操作类型
        if (code == 1) {
            //用户类型
            users = updateUserType(users);
        } else if (code == 2) {
            //用户状态
            users = updateUserStatus(users);
        }

        return CommonResp.success("修改成功");
    }

    private User updateUserType(User users) {
        //1 获取账户类型
        Integer userType = users.getUserType();

        //2 判断账户类型
        switch (userType) {
            //是管理员用户，则取消管理员，置为普通用户或领养人
            case 1:
                //获取 此账户领养成功的记录 从数据库中
                LambdaQueryWrapper<Adoptions> queryWrapper = new LambdaQueryWrapper<>();
                queryWrapper.eq(Adoptions::getUserId, users.getUserId());
                queryWrapper.eq(Adoptions::getAdoStatus, 1);
                List<Adoptions> list = adoptionService.list(queryWrapper);

                //判断 有领养成功记录
                if (list.size() > 0) {
                    //置为领养人
                    users.setUserType(2);
                } else {
                    //置为普通用户
                    users.setUserType(0);
                }
                break;
            //不是管理员，设置为管理员
            default:
                users.setUserType(1);
        }
        //3 更新用户信息 到数据库
        this.updateById(users);
        return users;
    }

    /**
     * 修改账户状态：暂停使用、恢复使用
     * <p>
     * 0正常，1申请领养中，2暂停使用
     *
     * @param users
     * @return
     */
    @CacheEvict(value = "adoption", key = "'error_adopter_name'")
    public User updateUserStatus(User users) {
        //获取 账户类型
        Integer userStatus = users.getUserStatus();

        switch (userStatus) {
            //申请领养中用户
            case 1:
                //获取 申领中的记录
                LambdaQueryWrapper<Adoptions> queryWrapper = new LambdaQueryWrapper<>();
                queryWrapper.eq(Adoptions::getUserId, users.getUserId());
                queryWrapper.eq(Adoptions::getAdoStatus, 2);
                Adoptions adoptions = adoptionService.getOne(queryWrapper);

                //设置 领养状态为失败
                adoptions.setAdoStatus(0);
                //更新领养状态 到数据库中
                adoptionService.updateById(adoptions);

                //设置 宠物状态为待领养
                Animal animal = animalService.getById(adoptions.getAnimalId());
                animal.setAnimalStatus(0);
                //更新宠物状态 到数据库中
                animalService.updateById(animal);
                //正常用户，直接设置为冻结
            case 0:
                users.setUserStatus(2);
                break;
            //暂停使用的账户，恢复使用
            case 2:
                users.setUserStatus(0);
                break;
            default:
        }
        //更新用户信息 到数据库
        this.updateById(users);
        return users;
    }
}
