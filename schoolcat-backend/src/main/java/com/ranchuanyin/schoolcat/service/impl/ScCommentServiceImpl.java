package com.ranchuanyin.schoolcat.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ranchuanyin.schoolcat.domain.CatAccount;
import com.ranchuanyin.schoolcat.domain.ScComment;
import com.ranchuanyin.schoolcat.dto.CommentDTO;
import com.ranchuanyin.schoolcat.dto.WriteACommentDTO;
import com.ranchuanyin.schoolcat.mapper.ScCommentMapper;
import com.ranchuanyin.schoolcat.service.CatAccountService;
import com.ranchuanyin.schoolcat.service.ScCommentService;
import com.ranchuanyin.schoolcat.units.RestBean;
import com.ranchuanyin.schoolcat.vo.CommentVO;
import com.ranchuanyin.schoolcat.vo.PageVO;
import jakarta.annotation.Resource;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Resource
    ScCommentMapper scCommentMapper;


    @Override
    @Cacheable(value = {"commentList"}, key = "#commentDTO.articleId")
    public RestBean<List<CommentVO>> commentList(CommentDTO commentDTO) {
        LambdaQueryWrapper<ScComment> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(ScComment::getArticleId, commentDTO.getArticleId())
                .eq(ScComment::getRootId, -1).orderByAsc(ScComment::getCreateTime);
        Page<ScComment> page = new Page<>(commentDTO.getPageNum(), commentDTO.getPageSize());
        page(page, wrapper);
        List<CommentVO> commentVOList = this.toCommentVOList(page.getRecords());
        PageVO pageVO = PageVO.builder().total(page.getTotal()).size(page.getSize()).build();
        //查询所有根评论对于的子评论的集合
        List<CommentVO> list = commentVOList.stream().peek(one -> {
            List<CommentVO> children = getChildren(one.getId());
            one.setChildren(children);
        }).toList();
        return RestBean.success(list, pageVO);
    }

    @Override
    public RestBean<String> sendComment(WriteACommentDTO writeACommentDTO) {
        ScComment bean = BeanUtil.toBean(writeACommentDTO, ScComment.class);
        int save = scCommentMapper.insert(bean);
        if (save != 0){
            return RestBean.success("评论成功");
        }else return RestBean.failure(500,"评论失败");

    }

    private List<CommentVO> getChildren(Long id) {
        LambdaQueryWrapper<ScComment> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(ScComment::getRootId, id).orderByAsc(ScComment::getCreateTime);
        return toCommentVOList(list(wrapper));
    }

    private List<CommentVO> toCommentVOList(List<ScComment> list) {
        List<CommentVO> commentVOList = BeanUtil.copyToList(list, CommentVO.class);
        return commentVOList.parallelStream().peek(one -> {
            CatAccount account = catAccountService.getById(one.getCreateBy());
            one.setUserName(account.getUsername());
            one.setAvatar(account.getAvatar());
            if (one.getToCommentUserId() != -1) {
                CatAccount account1 = catAccountService.getById(one.getToCommentUserId());
                one.setToCommentUserName(account1.getUsername());
                one.setAvatar(account1.getAvatar());
            }
        }).toList();
    }
}




