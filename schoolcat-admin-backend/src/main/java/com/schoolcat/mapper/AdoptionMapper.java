package com.schoolcat.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.schoolcat.entity.Adoptions;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AdoptionMapper extends BaseMapper<Adoptions> {
}
