package com.schoolcat.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.schoolcat.entity.Adoptions;
import com.schoolcat.resp.CommonResp;
import jakarta.servlet.http.HttpSession;


public interface AdoptionService extends IService<Adoptions> {
    /**
     * 领养人取消领养
     * @param adoId
     * @param session
     * @return
     */
    CommonResp<String> cancelAdoption(Integer adoId, HttpSession session);


    /**
     * 修改领养状态：领养成功、失败
     * @param adoId
     * @param flag
     * @param session
     * @return
     */
    CommonResp<String> updateAdoptionStatus(Integer adoId, Integer flag, HttpSession session);

    /**
     * 获取领养记录列表，普通用户只能获取到自己的
     * @param page
     * @param pageSize
     * @param animalName
     * @param userRealname
     * @param adoptionStatus
     * @param session
     * @return
     */
    CommonResp<Page> getList(Integer page, Integer pageSize, String animalName, String userRealname, Integer adoptionStatus, HttpSession session);
}
