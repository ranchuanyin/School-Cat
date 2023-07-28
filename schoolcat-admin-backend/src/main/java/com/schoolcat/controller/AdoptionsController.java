package com.schoolcat.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.schoolcat.entity.Adoptions;
import com.schoolcat.resp.CommonResp;
import com.schoolcat.service.AdoptionService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/adoptions")
public class AdoptionsController {

    @Autowired
    private AdoptionService adoptionService;


    @Cacheable(value = "adoption",key = "'adopter_count'",unless = "#result == null")
    @GetMapping
    public CommonResp<Long> getCount(){
        //获取 状态为处理中的 申领记录数量
        LambdaQueryWrapper<Adoptions> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Adoptions::getAdoStatus,2);
        long count = adoptionService.count(queryWrapper);

        return CommonResp.success(count);
    }

    /**
     * 取消领养宠物
     * @param adoId
     * @param session
     * @return
     */
    @CacheEvict(value = {"adoption","pet"},allEntries = true)
    @PutMapping("/{adoId}")
    public CommonResp<String> cancel(@PathVariable Integer adoId, HttpSession session){
        CommonResp<String> resp = adoptionService.cancelAdoption(adoId,session);
        return resp;
    }


    /**
     * 修改领养状态
     * @param adoId
     * @param flag 1，成功，0失败
     * @return
     */
    @CacheEvict(value = {"adoption","pet"},allEntries = true)
    @PutMapping("/{adoId}/{flag}")
    public CommonResp<String> update(@PathVariable Integer adoId,@PathVariable Integer flag,HttpSession session){
        CommonResp<String> resp = adoptionService.updateAdoptionStatus(adoId,flag,session);
        return resp;
    }


    /**
     * 获取领养记录列表
     * @param page
     * @param pageSize
     * @param petName
     * @param userRealname
     * @param adoptionStatus
     * @param session
     * @return
     */
    @GetMapping("/page")
    public CommonResp<Page> page(Integer page, Integer pageSize, String petName, String userRealname, Integer adoptionStatus, HttpSession session) {
        CommonResp<Page> resp = adoptionService.getList(page,pageSize,petName,userRealname,adoptionStatus,session);
        return resp;
    }
}
