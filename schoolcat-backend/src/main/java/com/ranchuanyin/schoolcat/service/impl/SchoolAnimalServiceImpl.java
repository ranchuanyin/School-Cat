package com.ranchuanyin.schoolcat.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ranchuanyin.schoolcat.domain.SchoolAnimal;
import com.ranchuanyin.schoolcat.mapper.SchoolAnimalMapper;
import com.ranchuanyin.schoolcat.service.SchoolAnimalService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 冉船银
 * @description 针对表【school_animal】的数据库操作Service实现
 * @createDate 2023-08-24 23:03:27
 */
@Service
public class SchoolAnimalServiceImpl extends ServiceImpl<SchoolAnimalMapper, SchoolAnimal>
        implements SchoolAnimalService {
    @Resource
    private SchoolAnimalMapper schoolAnimalMapper;

    public List<SchoolAnimal> paginationQuery(int page) {
        LambdaQueryWrapper<SchoolAnimal> wrapper = new LambdaQueryWrapper<>();
        Page<SchoolAnimal> schoolAnimalPage = new Page<>(page, 5);
        return schoolAnimalMapper.selectPage(schoolAnimalPage, wrapper).getRecords();
    }

    @Override
    public Long getNums() {
        return schoolAnimalMapper.selectCount(null);
    }
}




