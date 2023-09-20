package com.ranchuanyin.schoolcat.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ranchuanyin.schoolcat.domain.CatAccount;
import com.ranchuanyin.schoolcat.domain.ScComment;
import com.ranchuanyin.schoolcat.dto.CommentDTO;
import com.ranchuanyin.schoolcat.service.CatAccountService;
import com.ranchuanyin.schoolcat.service.ScCommentService;
import com.ranchuanyin.schoolcat.mapper.ScCommentMapper;
import com.ranchuanyin.schoolcat.units.RestBean;
import com.ranchuanyin.schoolcat.vo.CommentVO;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
* @author 33716
* @description 针对表【sc_comment】的数据库操作Service实现
* @createDate 2023-09-20 16:45:01
*/
@Service
public class ScCommentServiceImpl extends ServiceImpl<ScCommentMapper, ScComment>
    implements ScCommentService{

    @Resource
    CatAccountService catAccountService;

    @Override
    public RestBean<List<CommentVO>> commentList(CommentDTO commentDTO) {
        LambdaQueryWrapper<ScComment> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(ScComment::getArticleId,commentDTO.getArticleId())
                .eq(ScComment::getRootId,-1);
        Page<ScComment> page = new Page<>(commentDTO.getPageNum(),commentDTO.getPageSize());
        page(page,wrapper);
        List<CommentVO> commentVOList = this.toCommentVOList(page.getRecords());
        return RestBean.success(commentVOList);
    }

    private  List<CommentVO> toCommentVOList(List<ScComment> list){
        List<CommentVO> commentVOList = BeanUtil.copyToList(list, CommentVO.class);
        return commentVOList.parallelStream().map(one -> {
            CatAccount account = catAccountService.getById(one.getCreateBy());
            one.setUserName(account.getUsername());
            if (one.getToCommentUserId() != -1) {
                CatAccount account1 = catAccountService.getById(one.getToCommentUserId());
                one.setToCommentUserName(account1.getUsername());
            }
            return one;
        }).toList();
    }
}




