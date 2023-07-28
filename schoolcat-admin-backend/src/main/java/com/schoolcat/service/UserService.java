package com.schoolcat.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.schoolcat.entity.User;
import com.schoolcat.resp.CommonResp;



public interface UserService extends IService<User> {

    /**
     * 修改用户类型
     * @param id
     * @param code
     * @return
     */
    CommonResp<String> updateUserField(Long id, Integer code);

}
